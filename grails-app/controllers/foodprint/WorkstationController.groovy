package foodprint

class WorkstationController {

    static allowedMethods = [create:"POST",update: "POST",  delete: "POST"]
    def domainService

    def index() {

        render (contentType: 'application/json') {
            [workstationInstanceList: Workstation.list(params), workstationInstanceTotal: Workstation.count()]
    
        }
        
    }

 
    def create(){

        def workstationInstance=new Workstation(params)
        
        render (contentType: 'application/json') {
            domainService.save(workstationInstance)
        }
    }

    def update(){
        def workstationInstance = Workstation.findById(params.id)
        workstationInstance.properties=params
        render (contentType: 'application/json') {
            domainService.save(workstationInstance)
        }         
    }


    def delete(){
        def workstationInstance = Workstation.findById(params.id)
        render (contentType: 'application/json') {
            domainService.delete(workstationInstance)
        }
    }
    
}
