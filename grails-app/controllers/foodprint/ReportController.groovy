package foodprint

import org.springframework.dao.DataIntegrityViolationException
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ReportController {

    static allowedMethods = [create:"POST",update: "POST",  delete: "POST"]
    def domainService

    def index(params) {

        render (contentType: 'text/json') {
            [reportInstanceList: Report.list(params), reportInstanceTotal: Report.count()]
    
        }
        
    }

 
    @Transactional
    def create(){

        def reportInstance=new Report(params)
        
        render (contentType: 'text/json') {
            domainService.save(reportInstance)
        }
    }

    @Transactional
    def update(Report reportInstance){

        render (contentType: 'text/json') {
            domainService.save(reportInstance)
        }         
    }


    @Transactional
    def delete(Report reportInstance){
        
        render (contentType: 'text/json') {
            domainService.delete(reportInstance)
        }
    }
    
}
