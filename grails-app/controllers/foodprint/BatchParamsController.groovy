package foodprint

import org.springframework.dao.DataIntegrityViolationException
import grails.converters.JSON

class BatchParamsController {

    static allowedMethods = [create: "POST",update: "PUT",  delete: "DELETE"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        def item=Batch.get(params.id).item
        def workstations=item.itemRoutes*.workstation.unique()

        def batchParams=ReportParams.findAll(){
            workstation in workstations || item == item
        }
        log.info "batchParams.param.id= ${batchParams.param.id}"

        [batchParamsList:batchParams, batchParamsTotal: batchParams.size()]
    }

    def show() {
        log.info "BatchParamsController--listJson"
        JSON.use('deep')
        def converter=list() as JSON
        converter.render(response)
    }

    def save(Batch batchInstance){
        if (!batchInstance.validate()) {
            batchInstance.errors.each {
                println it
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
        println"BatchController--create"

        def batchInstance=new Batch(params)
        batchInstance.item=Item.findById(params.item_id)

        render (contentType: 'text/json') {
            save(batchInstance);
        }
    }


    def update(){
        println"BatchController--update"

        def batchInstance=Batch.get(params.id)
        
        if (!batchInstance) {

            log.warning "${controllerName}--${actionName}--batchInstance not found"
            render (contentType: 'text/json') {
                [success:false]
            }
            return null
        }

        batchInstance.properties = params

        log.info "dueDate = ${batchInstance.dueDate}"

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
        println"BatchController--delete"
        def batchInstance=Batch.get(params.id)
        
        if (!batchInstance) {
            println"BatchController--delete--Cant find BatchInstance"
            render (contentType: 'text/json') {
                return [success:false]
            }
        }
        //else
        //    println"BatchController--updateBatch--has find BatchInstance"

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
}
