package foodprint

import org.springframework.dao.DataIntegrityViolationException

class OperationController {

    static allowedMethods = [create:"POST",update: "POST",  delete: "POST",  index: "GET"]
    def domainService

    def index() {
        
        def list = Operation.createCriteria().list(params,params.criteria)


        render (contentType: 'application/json') {
            [operationInstanceList: list, operationInstanceTotal: list.totalCount]
        }
        
    }
    def show(Long id){

        def operation=Operation.findById(id);  
        if(operation){   
            render (contentType: 'application/json') {
                [success: true,data:operation]
            }
        }else {
            render (contentType: 'application/json') {
                [success: false,message:message(code: 'default.message.show.failed')]
            }          
        }
    }
    def create(){

        def operation=new Operation()        
        render (contentType: 'application/json') {
            [success: true,data:operation]
        }
    }

    def save(){

        def operationInstance=new Operation(params)
        
        render (contentType: 'application/json') {
            domainService.save(operationInstance)
        }
    }

    def update(){
        def  operationInstance = Operation.findById(params.id)
        operationInstance.properties=params
        render (contentType: 'application/json') {
            domainService.save(operationInstance)
        }         
    }


    def delete(){
        def operationInstance = Operation.findById(params.id)
        def result
        try {
            
            result = domainService.delete(operationInstance)
        
        }catch(e){
            log.error e
            def msg = message(code: 'default.message.delete.failed', args: [operationInstance, e.getMessage()])
            result = [success:false, message: msg] 
        }
        
        render (contentType: 'application/json') {
            result
        }
    }
}
