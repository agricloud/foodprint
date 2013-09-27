package foodprint

import org.springframework.dao.DataIntegrityViolationException
import grails.converters.JSON
import org.apache.commons.lang.exception.ExceptionUtils

class BatchRouteController {

    static allowedMethods = [create: "POST", update: "PUT", delete: "DELETE"]

    def messageSource
    def batchRouteService

    def index() {
        redirect(action: "list", params: params)
    }

    def show() {
        log.debug "${controllerName}-${actionName}"
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        def batchRoute=Batch.findById(params.batch.id).batchRoutes
        /** 
	*[batchRouteInstanceList:batchRoute.collect(), batchRouteInstanceTotal: batchRoute.size()]
	*or use
	*[batchRouteInstanceList: BatchRoute.list(params), batchRouteInstanceTotal: BatchRoute.count()]
        **/
        [batchRouteInstanceList:batchRoute.collect(), batchRouteInstanceTotal: batchRoute.size()]
    }

    def listJson(Integer max) {
        log.debug "${controllerName}-${actionName}"
        
        JSON.use('deep')
        def converter=list(max) as JSON
        converter.render(response)
    }


    def create() {
        log.debug "${controllerName}-${actionName}"
        def batchRouteInstance = new BatchRoute(params)
        print params
        render (contentType: 'text/json') {
            save(batchRouteInstance)
        }
    }

    def save(BatchRoute batchRouteInstance) {
    	log.debug "${controllerName}-${actionName}"

        def msg=[]
        def isSuccess;
        if (!batchRouteInstance.validate()) {
            batchRouteInstance.errors.allErrors.each{ 
                msg << messageSource.getMessage(it, Locale.getDefault())
            }
            isSuccess=false;
        }
        else{
            if (!batchRouteInstance.save()) {//flush:true?  
                batchRouteInstance.errors.allErrors.each{ 
                    msg << messageSource.getMessage(it, Locale.getDefault())
                }
                isSuccess=false;
            }
            else{
                msg<< message(code: "default.message.save.success", args: [batchRouteInstance.sequence])
                isSuccess=true;
            }
        }

        return [success: isSuccess, message: msg.join('<br>')]

	}



/*
    def edit(Long id) {
        def batchRouteInstance = BatchRoute.get(id)
        if (!batchRouteInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'batchRoute.label', default: 'BatchRoute'), id])
            redirect(action: "list")
            return
        }

        [batchRouteInstance: batchRouteInstance]
    }
*/


    def update() {
        log.debug "${controllerName}-${actionName}"

        def msg=[]
        def batchRouteInstance=BatchRoute.get(params.id)
        
        if (!batchRouteInstance) {

            //使用log.debug 會跳錯誤訊息！
            log.debug "${controllerName}--${actionName}--batchRouteInstance not found"
            msg<< message(code: "default.message.update.notfound", args: [params.sequence])
            render (contentType: 'text/json') {
                [success:false, message: msg.join('<br>')]
            }
        }

        batchRouteInstance.properties = params

        render (contentType: 'text/json') {
            def result=save(batchRouteInstance)
            if(result.success)
                result.message = message(code: "default.message.update.success", args: [batchRouteInstance.sequence])

            result
        }

    }

    def delete() {
        log.debug "${controllerName}-${actionName}"

        def msg=[]
        def batchRouteInstance=BatchRoute.get(params.id)
        
        if (!batchRouteInstance) {
            log.debug "${controllerName}--${actionName}--batchRouteInstance not found"
            msg<< message(code: "default.message.delete.notfound", args: [params.sequence])
            render (contentType: 'text/json') {
                [success:false, message: msg.join('<br>')]
            }
        }

        try {
            
            batchRouteService.deleteBatchRoute(batchRouteInstance)

            msg<< message(code: "default.message.delete.success", args: [batchRouteInstance.sequence])
            render (contentType: 'text/json') {
                return [success:true, message: msg.join('<br>')]
            }
        }
        catch (e) {
            def eMessage = ExceptionUtils.getRootCauseMessage(e)

            msg<< message(code: "default.message.delete.failed", args: [batchRouteInstance.sequence, eMessage])
            render (contentType: 'text/json') {
                return [success:false, message: msg.join('<br>')]
            }
        }
    }
}
