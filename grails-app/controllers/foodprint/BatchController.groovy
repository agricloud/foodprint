package foodprint

import org.springframework.dao.DataIntegrityViolationException
import grails.converters.JSON

class BatchController {

    static allowedMethods = [create: "POST",update: "PUT",  delete: "DELETE"]

    def messageSource

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [batchInstanceList: Batch.list(params), batchInstanceTotal: Batch.count()]
    }

    def listJson(Integer max) {
        log.debug "BatchController--listJson"
        JSON.use('deep')
        def converter=list() as JSON
        converter.render(response)

        // render (contentType: 'text/json') {
        //     list(max)        
        // }
    }

    def save(Batch batchInstance){
        def msg=[]
        def isSuccess;
        if (!batchInstance.validate()) {
            batchInstance.errors.allErrors.each{ 
                msg << messageSource.getMessage(it, Locale.getDefault())
            }
            isSuccess=false;
        }
        else{
            if (!batchInstance.save()) {//flush:true?  
                batchInstance.errors.allErrors.each{ 
                    msg << messageSource.getMessage(it, Locale.getDefault())
                }
                isSuccess=false;
            }
            else{
                msg<< message(code: "default.message.save.success", args: [batchInstance.name])
                isSuccess=true;
            }
        }

        return [success: isSuccess, message: msg.join('<br>')]

    }
    
    def create(){
        log.debug"BatchController--create"

        def batchInstance=new Batch(params)

        render (contentType: 'text/json') {
            save(batchInstance);
        }
    }


    def update(){
        log.debug "BatchController--update"

        def msg=[]
        def batchInstance=Batch.get(params.id)
        
        if (!batchInstance) {

            //使用log.debug 會跳錯誤訊息！
            println "${controllerName}--${actionName}--batchInstance not found"
            msg<< message(code: "default.message.update.notfound", args: [params.id])
            render (contentType: 'text/json') {
                [success:false, message: msg.join('<br>')]
            }
        }

        batchInstance.properties = params

        render (contentType: 'text/json') {
            def result=save(batchInstance)
            if(result.success)
                result.message = message(code: "default.message.update.success", args: [batchInstance.name])

            result
        }
    }


    def delete(){
        log.debug "BatchController--delete"
        def batchInstance=Batch.get(params.id)
        
        if (!batchInstance) {
            println "${controllerName}--${actionName}--batchInstance not found"
            msg<< message(code: "default.message.update.notfound", args: [params.id])
            render (contentType: 'text/json') {
                [success:false, message: msg.join('<br>')]
            }
        }

        try {
            batchInstance.delete(failOnError: true)
            msg<< message(code: "default.message.delete.success", args: [batchInstance.name])

            render (contentType: 'text/json') {
                return [success:true, message: msg.join('<br>')]
            }
        }
        catch (e) {
            render (contentType: 'text/json') {
                return [success:false]
            }
        }

    }
    /**
    * 此方法已不使用 僅供參考寫法
    * @param batch.id
    * 取得Batch對應之Item的ItemRoute
    * 可直接呼叫ItemRoute中已有的listJson方法
    * 由於ItemRoute篩選的params為item.id
    * 而Batch接收的params為batch.id
    * 因此需method中找出item.id才可call ItemRoute.listJson
    * 使用redirect重新導向ItemRoute較為精簡
    * 也可避免params中屬性命名相同等衝突
    */
    def itemRouteList(){
        log.debug "BatchController--itemRouteList"
        redirect(controller: "ItemRoute",action: "listJson" ,params:["item.id":Batch.get(params.id).item.id])
        //old version
        // JSON.use('deep')
        // def converter= [itemRouteList:Batch.get(params.id).item.itemRoutes.collect()] as JSON
        // converter.render(response)
    }

    def index() {
        redirect(action: "list", params: params)
    }

    def show(Long id) {
        def batchInstance = Batch.get(id)
        if (!batchInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'batch.label', default: 'Batch'), id])
            redirect(action: "list")
        }

        render (contentType: 'text/json') {
            [batchInstanceList:batchInstance]
        }
    }

    def edit(Long id) {
        def batchInstance = Batch.get(id)
        if (!batchInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'batch.label', default: 'Batch'), id])
            redirect(action: "list")
            return
        }

        [batchInstance: batchInstance]
    }
}
