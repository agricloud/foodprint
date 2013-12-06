package foodprint

class WorkstationController {

    static allowedMethods = [create:"POST",update: "POST",  delete: "POST"]
    def domainService

    def index() {

        def list = Workstation.createCriteria().list(params,params.criteria)


        render (contentType: 'application/json') {
            [workstationInstanceList: list, workstationInstanceTotal: list.totalCount]
        }
        
    }

     def show(Long id){

        def workstation=Workstation.get(id);  
        if(workstation){   
            render (contentType: 'application/json') {
                [success: true,data:workstation]
            }
        }else {
            render (contentType: 'application/json') {
                [success: false,message:message(code: 'default.message.show.failed')]
            }          
        }
    }
    def create(){

        def workstation=new Workstation()        
        render (contentType: 'application/json') {
            [success: true,data:workstation]
        }
    }
    def save(){

        def workstationInstance=new Workstation(params)
        
        render (contentType: 'application/json') {
            domainService.save(workstationInstance)
        }
    }

    def update(){
        def workstationInstance = Workstation.get(params.id)
        workstationInstance.properties=params
        render (contentType: 'application/json') {
            domainService.save(workstationInstance)
        }         
    }


    def delete(){
        def workstationInstance = Workstation.get(params.id)
        def result
        try {
            
            result = domainService.delete(workstationInstance)
        
        }catch(e){
            log.error e
            def msg = message(code: 'default.message.delete.failed', args: [workstationInstance, e.getMessage()])
            result = [success:false, message: msg] 
        }
        
        render (contentType: 'application/json') {
            result
        }
    }
    
}
