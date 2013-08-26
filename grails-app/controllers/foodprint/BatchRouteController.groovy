package foodprint

import org.springframework.dao.DataIntegrityViolationException
import grails.converters.JSON

class BatchRouteController {

    static allowedMethods = [create: "POST", update: "PUT", delete: "DELETE"]

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

	if(!batchRouteInstance.validate()) { // validate id
	            batchRouteInstance.errors.each {
	               println it
	            }
	            return [success: false]
	}

	if (!batchRouteInstance.save(failOnError: true)) {
	            return [success: false]
	        }
	        else{
	            return [success:true]
	        }
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
        def batchRouteInstance=BatchRoute.findById(params.id)
        if(!batchRouteInstance) {
            log.warning "${controllerName}-${actionName}-batchRouteInstance not found"
            return render (contentType: 'text/json') {
                [success:false]
            }
        }
        batchRouteInstance.properties = params
        render (contentType: 'text/json') {
            save(batchRouteInstance)
        }
    }

    def delete() {
        log.debug "${controllerName}-${actionName}"
        def batchRouteInstance = BatchRoute.get(params.id)
        if (!batchRouteInstance) {
            log.warning "${controllerName}-${actionName}-Cant find itemRouteInstance"
            render(contentType: 'text/json') {
                return [success: false]
            }
        }
        batchRouteInstance.delete(failOnError: true)
            render(contentType: 'text/json') {
                return [success: true]
            }
        try {
            batchRouteInstance.delete(failOnError: true)
            render(contentType: 'text/json') {
                return [success: true]
            }
        }
        catch (e) {
            render (contentType: 'text/json') {
                return [success: false]
            }
        }
    }
}
