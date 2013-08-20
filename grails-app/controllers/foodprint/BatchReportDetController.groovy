package foodprint

import org.springframework.dao.DataIntegrityViolationException
import grails.converters.JSON

class BatchReportDetController {

    static allowedMethods = [create: "POST",update: "PUT",  delete: "DELETE"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        def batch_id=params.batchid
        //print report_id
        def batchob=Batch.findById(batch_id,params);
        //print reportob
        def resultList= BatchReportDet.findAllByBatch(batchob)
        [batchReportDetInstanceList:resultList, batchReportDetInstanceTotal: BatchReportDet.count()]
    }

    def listJson(Integer max) {
        JSON.use('deep')
        def converter=list()as JSON
        converter.render(response)
        //render (contentType: 'text/json') {
        //    print list(max)
         //   list(max)        
        //}
    }

    def create() {
        [batchReportDetInstance: new BatchReportDet(params)]
    }

    def show(Long id) {
        def batchReportDetInstance = BatchReportDet.get(id)
        if (!batchReportDetInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'batchReportDet.label', default: 'BatchReportDet'), id])
            redirect(action: "list")
            return
        }

        [batchReportDetInstance: batchReportDetInstance]
    }

    def edit(Long id) {
        def batchReportDetInstance = BatchReportDet.get(id)
        if (!batchReportDetInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'batchReportDet.label', default: 'BatchReportDet'), id])
            redirect(action: "list")
            return
        }

        [batchReportDetInstance: batchReportDetInstance]
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

}
