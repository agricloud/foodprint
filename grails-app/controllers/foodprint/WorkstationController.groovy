package foodprint

import org.springframework.dao.DataIntegrityViolationException
import grails.transaction.Transactional

@Transactional(readOnly = true)
class WorkstationController {

    static allowedMethods = [create:"POST",update: "POST",  delete: "POST"]
    def domainService

    def index() {

        render (contentType: 'text/json') {
            [workstationInstanceList: Workstation.list(params), workstationInstanceTotal: Workstation.count()]
    
        }
        
    }

 
    @Transactional
    def create(){

        def workstationInstance=new Workstation(params)
        
        render (contentType: 'text/json') {
            domainService.save(workstationInstance)
        }
    }

    @Transactional
    def update(Workstation workstationInstance){

        render (contentType: 'text/json') {
            domainService.save(workstationInstance)
        }         
    }


    @Transactional
    def delete(Workstation workstationInstance){
        
        render (contentType: 'text/json') {
            domainService.delete(workstationInstance)
        }
    }
    
}
