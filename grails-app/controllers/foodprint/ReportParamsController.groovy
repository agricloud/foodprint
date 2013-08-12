package foodprint

import org.springframework.dao.DataIntegrityViolationException

class ReportParamsController {

    static allowedMethods = [create: "POST",update: "PUT",  delete: "DELETE"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        def report_id=params.reportid
        //print report_id
        def reportob=Report.findById(report_id,params);
        //print reportob
        def resultList= ReportParams.findAllByReport(reportob)

        [reportParamsInstanceList: resultList, reportParamsInstanceTotal: ReportParams.count()]
    }
    def listJson(Integer max) {
        render (contentType: 'text/json') {
            list(max)        
        }
        
    }

    def create(){
        println"reportParamsController--create"

        def reportParamsInstance=new ReportParams(params)
        reportParamsInstance.report=Report.findById(params.report_id)
        reportParamsInstance.param=Param.findById(params.param_id)
        reportParamsInstance.workstation=Workstation.findById(params.workstation_id)
        reportParamsInstance.item=Item.findById(params.item_id)

        render (contentType: 'text/json') {
            save(reportParamsInstance);
        }
    }

    def save(ReportParams reportParamsInstance){
        if (!reportParamsInstance.validate()) {
            reportParamsInstance.errors.each {
                println it
            }
            return [success:false]
        }
        if (!reportParamsInstance.save(failOnError: true)) {//flush:true?
               return [success:false]
        }
        else{
               return [success:true]
        }
    }

    def show(Long id) {
        def reportParamsInstance = ReportParams.get(id)
        if (!reportParamsInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'reportParams.label', default: 'ReportParams'), id])
            redirect(action: "list")
            return
        }

        [reportParamsInstance: reportParamsInstance]
    }

    def edit(Long id) {
        def reportParamsInstance = ReportParams.get(id)
        if (!reportParamsInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'reportParams.label', default: 'ReportParams'), id])
            redirect(action: "list")
            return
        }

        [reportParamsInstance: reportParamsInstance]
    }

    def update() {
        println"reportParamsController--update"
         def reportParamsInstance=ReportParams.get(params.id)

        if(!reportParamsInstance){
            println"reportParamsController--update--cant find reportParamsInstance"
            return render (contentType: 'text/json') {[success:false]}
        }
        if(!params.report_id.equals(null)){
            reportParamsInstance.report=Report.findById(params.report_id)
        }
        if(!params.param_id.equals(null)){
            reportParamsInstance.param=Param.findById(params.param_id)
        }
        if(params.workstation_id.equals(null)){
            reportParamsInstance.workstation=null
        }else{
            reportParamsInstance.workstation=Workstation.findById(params.workstation_id)
        }
        if(params.item_id.equals(null)){
            reportParamsInstance.item=null
        }else{
            reportParamsInstance.item=Item.findById(params.item_id)
        }
        render (contentType: 'text/json') {
            save(reportParamsInstance);
        }   
    }

    def delete(){
        println"reportParamsController--delete"

        def reportParamsInstance = ReportParams.get(params.id)
        if (!reportParamsInstance) {
            println"reportParamsController--delete--Cant find reportParamsInstance"
            render (contentType: 'text/json') {
               return [success:false]
            }
        }

        try {
            reportParamsInstance.delete()
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
