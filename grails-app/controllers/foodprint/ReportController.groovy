package foodprint

import org.springframework.dao.DataIntegrityViolationException

class ReportController {

    static allowedMethods = [create:"POST",update: "POST",  delete: "POST"]
    def domainService

    def index(params) {

        def list = Report.createCriteria().list(params,params.criteria)


        render (contentType: 'application/json') {
            [reportInstanceList: list, reportInstanceTotal: list.totalCount]
        }
        
    }

 
    def create(){

        def reportInstance=new Report(params)
        
        render (contentType: 'application/json') {
            domainService.save(reportInstance)
        }
    }

    def update(){
        def reportInstance = Report.findById(params.id)
        reportInstance.properties=params
        render (contentType: 'application/json') {
            domainService.save(reportInstance)
        }         
    }


    def delete(){
        def  reportInstance = Report.findById(params.id)
        render (contentType: 'application/json') {
            domainService.delete(reportInstance)
        }
    }
    
}
