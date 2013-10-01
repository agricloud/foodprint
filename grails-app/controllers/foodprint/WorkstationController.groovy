package foodprint

import org.springframework.dao.DataIntegrityViolationException
import grails.transaction.Transactional

@Transactional(readOnly = true)
class WorkstationController {

    static allowedMethods = [create:"POST",update: "POST",  delete: "POST"]
    def domainService

   def index(params) {

        render (contentType: 'text/json') {
            list(params)        
        }
        
    }

    def list(params) {
        //params.max = Math.min(max ?: 10, 100)
        [workstationInstanceList: Workstation.list(params), workstationInstanceTotal: Workstation.count()]
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


    def show(Long id) {
        def workstationInstance = Workstation.get(id)
        if (!workstationInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'workstation.label', default: 'Workstation'), id])
            redirect(action: "list")
            return
        }

        [workstationInstance: workstationInstance]
    }

    @Transactional
    def delete(Workstation workstationInstance){
        
        render (contentType: 'text/json') {
            domainService.delete(workstationInstance)
        }
    }
    
}
