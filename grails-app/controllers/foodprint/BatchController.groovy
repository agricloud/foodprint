package foodprint

import org.springframework.dao.DataIntegrityViolationException

class BatchController {

    static allowedMethods = [save: "POST",  delete: "DELETE"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [batchInstanceList: Batch.list(params), batchInstanceTotal: Batch.count()]
    }

    def listJson(Integer max) {
        render (contentType: 'text/json') {
            list(max)        
        }
    }

    def save(){

        def batchInstance=Batch.get(params.id)
        if(!batchInstance){
            println"BatchController--create"
            batchInstance=new Batch(params)
            batchInstance.item=Item.findById(params.item_id)
        }
        else{
            println"BatchController--update"
            batchInstance.properties = params
        }
       
        if (!batchInstance.save(failOnError: true)) {//flush:true?
            println batchInstance
            render (contentType: 'text/json') {
                return [success:false]
            }
        }
        else{
            render (contentType: 'text/json') {
                return [success:true]
            }
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

    def delete2(Long id) {
        def batchInstance = Batch.get(id)
        if (!batchInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'batch.label', default: 'Batch'), id])
            redirect(action: "list")
            return
        }

        try {
            batchInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'batch.label', default: 'Batch'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'batch.label', default: 'Batch'), id])
            redirect(action: "show", id: id)
        }
    }

    def delete(){
        def batchInstance=Batch.get(params.id)
        
        if (!batchInstance) {
            println"BatchController--delete--Cant find BatchInstance"
            render (contentType: 'text/json') {
                return [success:false]
            }
        }
        //else
        //    println"BatchController--updateBatch--has find BatchInstance"

        batchInstance.properties = params

        if (!batchInstance.save()) {//flush:true?
            render (contentType: 'text/json') {
                return [success:false]
            }
        }
        else{
            render (contentType: 'text/json') {
                return [success:true]
            }
        }
    }
}
