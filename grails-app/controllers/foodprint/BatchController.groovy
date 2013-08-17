package foodprint

import org.springframework.dao.DataIntegrityViolationException
import grails.converters.JSON

class BatchController {

    static allowedMethods = [create: "POST",update: "PUT",  delete: "DELETE"]

    def index() {
        redirect(action: "list", params: params)
    }

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
        if (!batchInstance.validate()) {
            batchInstance.errors.each {
                log.debug  it
            }
            return [success:false]
        }
        if (!batchInstance.save(failOnError: true)) {//flush:true?
                return [success:false]
        }
        else{
                return [success:true]
        }
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

        def batchInstance=Batch.get(params.id)
        
        if (!batchInstance) {

            log.warning "${controllerName}--${actionName}--batchInstance not found"
            render (contentType: 'text/json') {
                [success:false]
            }
            return null
        }
        batchInstance.properties = params

        log.debug "dueDate = ${batchInstance.dueDate}"

        render (contentType: 'text/json') {
            save(batchInstance)
        }
    }

    def show(Long id) {
        def batchInstance = Batch.get(id)
        if (!batchInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'batch.label', default: 'Batch'), id])
            redirect(action: "list")
            return
        }

        [batchInstance: batchInstance]
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

    def delete(){
        log.debug "BatchController--delete"
        def batchInstance=Batch.get(params.id)
        
        if (!batchInstance) {
            log.debug "BatchController--delete--Cant find BatchInstance"
            render (contentType: 'text/json') {
                return [success:false]
            }
        }
        //else
        //    log.debug "BatchController--updateBatch--has find BatchInstance"

        try {
            batchInstance.delete(failOnError: true)
            render (contentType: 'text/json') {
                return [success:true]
            }
        }
        catch (e) {
            render (contentType: 'text/json') {
                return [success:false]
            }
        }

    }
    /**
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
}
