package foodprint

import org.springframework.dao.DataIntegrityViolationException
import grails.converters.JSON

class BatchReportDetController {

    static allowedMethods = [create: "POST",update: "PUT",  delete: "DELETE"]

    def index() {
        redirect(action: "list", params: params)
    }

    /**
     * @param batch.id
     * 找出指定批號中所有相關參數
    **/
    def list() {
        def batch=Batch.get(params.batch.id)
        def operations=batch.batchRoutes*.operation.unique()
        def batchReportDet=BatchReportDet.findAll(){
            batch==batch
        }
        [batchReportDetInstanceList:batchReportDet, batchReportDetInstanceTotal: batchReportDet.size()]
    }

    def listJson() {
        JSON.use('deep')
        def converter=list() as JSON
        converter.render(response)
    }

    /**
     * @param batch.id
     * @param operation.id
     * 找出指定批號及途程中所有相關參數
    **/
    def batchRouteReportDetlist(){
        log.debug "BatchRouteReportDetController--batchRouteReportDetList"
        def batchInstance=Batch.get(params.batch.id)
        def operationInstance=Operation.get(params.operation.id)
        def batchRouteReportDetInstance=BatchReportDet.findAll(){
            batch==batchInstance && reportParams.operation==operationInstance
        }
        [batchRouteReportDetInstanceList:batchRouteReportDetInstance, batchRouteReportDetInstanceTotal: batchRouteReportDetInstance.size()]
    }

    def batchRouteReportDetListJson(){
        JSON.use('deep')
        def converter=batchRouteReportDetlist() as JSON
        converter.render(response)
    }

    def save(BatchReportDet batchReportDetInstance){
        
        if (!batchReportDetInstance.validate()) {
            batchReportDetInstance.errors.each {
                log.debug it
            }
            return [success:false]
        }
        if (!batchReportDetInstance.save(failOnError: true)) {//flush:true?
                return [success:false]
        }
        else{
                return [success:true]
        }
    }
    
    def create(){
        log.debug "BatchController--create"

        def batchInstance=new Batch(params)
        batchInstance.item=Item.findById(params.item_id)

        render (contentType: 'text/json') {
            save(batchInstance);
        }
    }


    def update(){
        log.debug "BatchReportDetController--update"

        def failure=[]
        params.each{

            if(it.key!="action" && it.key!="controller"){
                def batchReportDetInstance=BatchReportDet.get(it.key)
                if (!batchReportDetInstance) {
                    log.warning "${controllerName}--${actionName}--batchReportDetInstance ${it.key} not found"
                    render (contentType: 'text/json') {
                        [success:false]
                    }
                }

                batchReportDetInstance.value = it.value

                if(!save(batchReportDetInstance))
                    failure.add(it.key)
            }

        }//end each

        if(failure.size()>0){
            render (contentType: 'text/json') {
                [success:false,failure:failue]
            }
        }
        else{
            render (contentType: 'text/json') {
                [success:true]
            }
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
}
