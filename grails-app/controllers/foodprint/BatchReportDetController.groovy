package foodprint

import org.springframework.dao.DataIntegrityViolationException
import grails.converters.JSON

class BatchReportDetController {

    def messageSource
    def domainService

    /**
     * @param batch.id
     * @param operation.id
     * 找出指定批號及製程中所有相關參數
    **/
    def showBatchRouteParams(){
        //找出指定批號、指定製程所需收集的履歷參數。
        /*
        * [Deep properties]
        *
        * batchRouteParamsInstanceList::
        *   -reportParams
        *     -param
        */
        log.debug "BatchReportDetController--batchRouteParamsDetList"

        def batchInstance=Batch.get(params.batch.id)
        def operationInstance=Operation.get(params.operation.id)
        def workstationInstance=Workstation.get(params.workstation.id)
        def supplierInstance=Supplier.get(params.supplier.id)

        if(batchInstance && operationInstance && (workstationInstance||supplierInstance)){
            def reportParamsInstance=ReportParams.findAll(){
                item==batchInstance.item && operation==operationInstance && (workstation==workstationInstance || supplier==supplierInstance)
            }

            reportParamsInstance.each{
                if(!BatchReportDet.findByBatchAndReportParams(batchInstance,it)){
                    println "新增批號履歷參數.."+params.batch.id+"/"+it.param.id+"/"+it.param.title
                    def newBRD=new  BatchReportDet (batch:batchInstance,reportParams:it,value:null)
                    domainService.save(newBRD)
                }
            }

            def batchRouteParamsInstance=[]
            if(reportParamsInstance.size>0){
                batchRouteParamsInstance=BatchReportDet.createCriteria().list {
                    eq('batch', batchInstance)
                    'in'("reportParams",reportParamsInstance)
                }
            }

            //log.debug batchRouteParamsInstance as JSON

            def batchRouteParamsInstanceJson =  JSON.parse((batchRouteParamsInstance as JSON).toString()) 
            batchRouteParamsInstanceJson.eachWithIndex{ brp, i ->
                 brp.reportParams.param=JSON.parse((batchRouteParamsInstance[i].reportParams.param as JSON).toString()) 
            }

            render (contentType: 'application/json') {
                [sucess:true, data:batchRouteParamsInstanceJson, total: batchRouteParamsInstance.size()]
            }

        }
        else{
            render (contentType: 'application/json') {
                //錯誤訊息待調整 目前沒有資料時顯示請選擇檢視資料
                [success: false, message:message(code: 'default.message.show.failed')]
            }
        }
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
