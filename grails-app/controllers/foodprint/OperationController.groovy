package foodprint

import org.springframework.dao.DataIntegrityViolationException

class OperationController {

    static allowedMethods = [create:"POST",update: "POST",  delete: "POST",  index: "GET"]
    def domainService

    def index() {
        
        render (contentType: 'text/json') {
            [operationInstanceList: Operation.list(params), operationInstanceTotal: Operation.count()]
    
        }
        
    }


    def create(){

        def operationInstance=new Operation(params)
        
        render (contentType: 'text/json') {
            domainService.save(operationInstance)
        }
    }

    def update(){
        def  operationInstance = Operation.findById(params.id)
        operationInstance.properties=params
        render (contentType: 'text/json') {
            domainService.save(operationInstance)
        }         
    }


    def delete(){
        def  operationInstance = Operation.findById(params.id)
        render (contentType: 'text/json') {
            domainService.delete(operationInstance)
        }
    }
}
