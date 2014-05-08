package foodprint

import org.springframework.dao.DataIntegrityViolationException

class ReportController {

    static allowedMethods = [create:"POST",update: "POST",  delete: "POST"]
    def domainService

    def enumService

    def index = {

        def list = Report.createCriteria().list(params,params.criteria)


        render (contentType: 'application/json') {
            [data: list, total: list.totalCount]
        }
        
    }

    def show = {

        log.debug "${controllerName}-${actionName}"

        def report=Report.get(params.id);

        if(report){   

            render (contentType: 'application/json') {
                [success: true, data:report]
            }
        }else {
            render (contentType: 'application/json') {
                [success: false, message:message(code: 'default.message.show.failed')]
            }          
        }
    }
 
    def create = {

        def report=new Report()
        render (contentType: 'application/json') {
            [success: true,data:report]
        }
    }

    def save = {
        log.debug "${controllerName}-${actionName}"
        def report=new Report(params)
        
        render (contentType: 'application/json') {
            domainService.save(report)
        }
    }

    def update = {

        def report = Report.get(params.id)
        report.properties=params
        render (contentType: 'application/json') {
            domainService.save(report)
        }
    }

    def delete = {
        def report = Report.get(params.id)
        def result
        try {
            
            result = domainService.delete(report)
        
        }catch(DataIntegrityViolationException e){
            log.error e
            def msg = message(code: 'default.message.delete.failed', args: [report, e.getMessage()])
            result = [success:false, message: msg] 
        }
        
        render (contentType: 'application/json') {
            result
        }
    }
    
}
