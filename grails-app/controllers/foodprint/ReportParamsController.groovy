package foodprint

import org.springframework.dao.DataIntegrityViolationException

class ReportParamsController {

    static allowedMethods = [create: "POST",update: "PUT",  delete: "DELETE"]



    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        def report_id=params.reportid
        //print report_id
        def reportob=Report.findById(report_id,params);
        //print reportob
        def resultList= ReportParams.findAllByReport(reportob)

        [reportParamsInstanceList: resultList, reportParamsInstanceTotal: ReportParams.count()]
    }
    def index(Integer max) {
        render (contentType: 'application/json') {
            list(max)        
        }
        
    }


    def create(){

        def reportParamsInstance=new ReportParams(params)
        
        render (contentType: 'application/json') {
            domainService.save(reportParamsInstance)
        }
    }

    def update(){
        def reportParamsInstance = ReportParams.findById(params.id)
        reportParamsInstance.properties=params
        render (contentType: 'application/json') {
            domainService.save(reportParamsInstance)
        }         
    }


    def delete(){
        def reportParamsInstance = ReportParams.findById(params.id)
        render (contentType: 'application/json') {
            domainService.delete(reportParamsInstance)
        }
    }
}
