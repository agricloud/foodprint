package foodprint

import org.springframework.dao.DataIntegrityViolationException

class ReportParamsController {

    static allowedMethods = [create: "POST",update: "PUT",  delete: "DELETE"]




    def index(Integer max) {
        log.debug "${controllerName}-${actionName}"
        def report=Report.findById(params.report.id);
        def reportParams = ReportParams.findAllByReport(report)
        println report
        reportParams.each{
            println it.id+"/"+it.report.id+"/"+it.param.title
        }

        if(reportParams){   
            render (contentType: 'application/json') {
                [success: true,reportParamsInstanceList: ReportParams.findById(39)]//reprotParams, reportParamsInstanceTotal: reportParams.size()]
            }
        }
        else{
            render (contentType: 'application/json') {
                [success: false, message:message(code: 'default.message.show.failed')]
            }          
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
