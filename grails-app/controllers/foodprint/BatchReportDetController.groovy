package foodprint

import org.springframework.dao.DataIntegrityViolationException
import grails.converters.JSON

class BatchReportDetController {

    static allowedMethods = [create:"POST",update: "POST",  delete: "POST"]

    def messageSource
    def domainService

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
        //找出指定批號、指定途程所需收集的履歷參數。
        /*
        * [Deep properties]
        *
        * batchRouteParamsInstanceList::
        *   -reportParams
        *     -param
        */

        JSON.use('deep')
        def converter=batchRouteParamsList() as JSON
        JSON.use('default')
        converter.render(response)
    }

    /**
     * @param batch.id
     * 找出指定批號所有的履歷及對應的履歷參數
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
        //找出指定批號所有的履歷及對應的履歷參數。
        /*
        * [Deep properties]
        *
        * batchReportInstanceList::
        *   -batchReport
        *   -batchReportDets
        *     -reportParams
        *     -param  
        */
        JSON.use('deep')
        def converter=batchReportList() as JSON
        JSON.use('default')
        converter.render(response)
    }


    def doSaveOrUpdate(){
        log.debug "BatchReportDetController--doSaveOrUpdate"
        log.debug params

        def failure=[]
        def success=[]
        def msg=[]

        //更新履歷參數值
        params.each{

            if(it.key!="file" && it.key!="_dc" && it.key!="format" && it.key!="action" 
                && it.key!="controller" && it.key!="criteria" 
                && it.key!="id" && it.key!="startDate" && it.key!="endDate"){

                def batchReportDetInstance=BatchReportDet.findById(it.key)
                if (!batchReportDetInstance) {
                    log.warning "${controllerName}--${actionName}--batchReportDetInstance ${it.key} not found"

                    msg<< message(code: "default.message.notfound", args: [message(code: "batchReportDet.label"),it.key])
                    render (contentType: 'application/json') {
                        [success:false, message: msg.join('<br>')]
                    }
                }

                batchReportDetInstance.value = it.value

                if(!domainService.save(batchReportDetInstance).success)
                    failure<< batchReportDetInstance.reportParams.param.title
                else
                    success<< batchReportDetInstance.reportParams.param.title
            }//end if

        }//end each

        //更新製程開始結束日期
        def batchRouteInstance = BatchRoute.findById(params.id)
        if(!batchRouteInstance){
            msg<< message(code: "default.message.notfound", args: [message(code: "batchRoute.label"),params.id])
            render (contentType: 'application/json') {
                [success:false, message: msg.join('<br>')]
            }
        }
        if(params.startDate!="" && params.startDate!=null)
            batchRouteInstance.startDate = params.startDate
        if(params.endDate!="" && params.endDate!=null)
            batchRouteInstance.endDate = params.endDate

        if(!domainService.save(batchRouteInstance).success)
            failure<< "製程日期"
        else
            success<< "製程日期"


        if(success.size()>0){
            msg<< message(code: "default.message.update.success",args: [success.join(' , ')])
        }
        if(failure.size()>0){
            msg<< message(code: "default.message.update.failed",args: [failure.join(' , ')])
            render (contentType: 'application/json') {
                [success:false, message: msg.join('<br>')]
            }
        }
        else{
            render (contentType: 'application/json') {
                [success:true, message: msg.join('<br>')]
            }
        }
    }

    /* 確認以下功能未被使用後可刪除
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
        //找出指定批號所需收集的履歷參數。
        //目前似乎尚未使用此功能
        JSON.use('deep')
        def converter=list()as JSON
        JSON.use('default')
        converter.render(response)
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
    */
}
