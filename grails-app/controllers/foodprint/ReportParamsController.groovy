package foodprint

import org.springframework.dao.DataIntegrityViolationException

class ReportParamsController {

    def domainService

    def index = {
        log.debug "${controllerName}-${actionName}"
        log.debug params

        def report=Report.findById(params.report.id);
        def reportParams = ReportParams.findAllByReport(report)

        if(reportParams){   
        
            render (contentType: 'application/json') {
                [success: true,reportParamsInstanceList: reportParams, reportParamsInstanceTotal: reportParams.size()]
            }
        }
        else{
            render (contentType: 'application/json') {
                [success: false, message:message(code: 'default.message.show.failed')]
            }          
        }
        
    }

    def show = {

        log.debug "${controllerName}-${actionName}"

        def reportParams=ReportParams.findById(params.id);

        if(reportParams){   
            render (contentType: 'application/json') {
                [success: true, data:reportParams]
            }
        }else {
            render (contentType: 'application/json') {
                [success: false, message:message(code: 'default.message.show.failed')]
            }          
        }
    }

    def create = {
        if(params.report.id){

            def reportParams=new ReportParams(params)

            render (contentType: 'application/json') {
                [success: true,data:reportParams]
            }
        }else {
            render (contentType: 'application/json') {
                [success: false,message:message(code: 'batchRoute.message.create.failed')]
            }            
        }   
    }

    def save = {
        log.debug "${controllerName}-${actionName}"
        def reportParams=new ReportParams(params)
        
        render (contentType: 'application/json') {
            domainService.save(reportParams)
        }
    }

    def update = {

        log.debug "${controllerName}-${actionName}"

        def reportParams = ReportParams.findById(params.id)
        if(params.workstation.id==null || !params.workstation.id.trim()){
            params.remove("workstation.id")
            params.remove("workstation.title")
            params.put("workstation",null) 
        }
        if(params.supplier.id==null || !params.supplier.id.trim()){
            params.remove("supplier.id")
            params.remove("supplier.title")
            params.put("supplier",null)
        }

        reportParams.properties=params
        render (contentType: 'application/json') {
            domainService.save(reportParams)
        }
    }

    def delete = {
        log.debug "${controllerName}-${actionName}"
        def reportParams = ReportParams.findById(params.id)
        def result
        try {
            result = domainService.delete(reportParams)
        }catch(DataIntegrityViolationException e){
            log.error e
            def msg = message(code: 'default.message.delete.failed', args: [reportParams, e.getMessage()])
            result = [success:false, message: msg] 
        }
        
        render (contentType: 'application/json') {
            result
        }
    }
}
