package foodprint

import org.springframework.dao.DataIntegrityViolationException

class ReportController {

    static allowedMethods = [create:"POST",update: "POST",  delete: "POST"]
    def domainService

    def index(params) {

        render (contentType: 'text/json') {
            [reportInstanceList: Report.list(params), reportInstanceTotal: Report.count()]
    
        }
        
    }

 
    def create(){

        def reportInstance=new Report(params)
        
        render (contentType: 'text/json') {
            domainService.save(reportInstance)
        }
    }

    def update(){
        def reportInstance = Report.findById(params.id)
        reportInstance.properties=params
        render (contentType: 'text/json') {
            domainService.save(reportInstance)
        }         
    }


    def delete(){
        def  reportInstance = Report.findById(params.id)
        render (contentType: 'text/json') {
            domainService.delete(reportInstance)
        }
    }
    
}
