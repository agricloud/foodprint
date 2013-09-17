package foodprint

import org.springframework.dao.DataIntegrityViolationException
import grails.converters.JSON

class BatchReportDetController {

    static allowedMethods = [create: "POST",update: "PUT",  delete: "DELETE"]

    def messageSource

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        def batch_id=params.batch.id
        //print report_id
        def batchob=Batch.findById(batch_id,params);
        //print reportob
        def batchReportDetInstance= BatchReportDet.findAllByBatch(batchob)
        [batchReportDetInstanceList:batchReportDetInstance, batchReportDetInstanceTotal: batchReportDetInstance.size()]
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
    def batchRouteParamsList(){
        log.debug "BatchReportDetController--batchRouteParamsDetList"
        def batchInstance=Batch.get(params.batch.id)
        def operationInstance=Operation.get(params.operation.id)
        def batchRouteParamsInstance=BatchReportDet.findAll(){
            batch==batchInstance && reportParams.operation==operationInstance
        }
        [batchRouteParamsInstanceList:batchRouteParamsInstance, batchRouteParamsInstanceTotal: batchRouteParamsInstance.size()]
    }

    def batchRouteParamsListJson(){
        JSON.use('deep')
        def converter=batchRouteParamsList() as JSON
        converter.render(response)
    }

    /**
     * @param batch.id
     * 找出指定批號所有的報表及對應參數
    **/
    def batchReportList(){
        log.debug "BatchReportDetController--batchReportList"
        def batchInstance=Batch.get(params.batch.id)
        def batchReportDetInstance=BatchReportDet.findAll(){
           batch==batchInstance 
        }

        def reportInstance=batchReportDetInstance.reportParams*.report.unique()
        def batchReportInstance=[]

        reportInstance.each{ rept ->
            
            batchReportInstance.add([batchReport:rept, batchReportDets:BatchReportDet.findAll(){
                batch==batchInstance && reportParams.report==rept
            }])
        }

        [batchReportInstanceList:batchReportInstance, batchReportInstanceTotal: batchReportInstance.size()]
    }

    def batchReportListJson(){
        JSON.use('deep')
        def converter=batchReportList() as JSON
        converter.render(response)
    }


    def save(BatchReportDet batchReportDetInstance){
        def msg=[]
        def isSuccess;

        if (!batchReportDetInstance.validate()) {
            batchReportDetInstance.errors.each {
                errorsMsg << messageSource.getMessage(it, Locale.getDefault())
            }
            isSuccess=false;
        }
        else{
            if (!batchReportDetInstance.save(failOnError: true)) {//flush:true?
                batchInstance.errors.allErrors.each{ 
                    msg << messageSource.getMessage(it, Locale.getDefault())
                }
                isSuccess=false;
            }
            else{
                msg<< message(code: "default.message.save.success", args: [batchReportDetInstance.reportParams.param.title])
                isSuccess=true;
            }
        }
        return [success: isSuccess, message: msg.join('<br>')]

    }


    def update(){
        log.debug "BatchReportDetController--update"

        def failure=[]
        def success=[]
        def msg=[]

        params.each{

            if(it.key!="_dc" && it.key!="action" && it.key!="controller"){
                def batchReportDetInstance=BatchReportDet.get(it.key)
                if (!batchReportDetInstance) {
                    log.warning "${controllerName}--${actionName}--batchReportDetInstance ${it.key} not found"

                    msg<< message(code: "default.message.update.notfound", args: [params.id])
                    render (contentType: 'text/json') {
                        [success:false, message: msg.join('<br>')]
                    }
                }

                batchReportDetInstance.value = it.value

                if(!save(batchReportDetInstance).success)
                    failure<< batchReportDetInstance.reportParams.param.title
                else
                    success<< batchReportDetInstance.reportParams.param.title
            }

        }//end each

        if(success.size()>0){
            msg<< message(code: "default.message.update.success",args: [success.join(' , ')])
        }
        if(failure.size()>0){
            msg<< message(code: "default.message.update.failed",args: [failure.join(' , ')])
            render (contentType: 'text/json') {
                [success:false, message: msg.join('<br>')]
            }
        }
        else{
            render (contentType: 'text/json') {
                [success:true, message: msg.join('<br>')]
            }
        }
    }

}
