package foodprint

import org.springframework.dao.DataIntegrityViolationException
import grails.transaction.Transactional

@Transactional(readOnly = true)
class OperationController {

    static allowedMethods = [create:"POST",update: "POST",  delete: "POST",  index: "GET"]
    def domainService

    def index() {
        
        render (contentType: 'text/json') {
            [operationInstanceList: Operation.list(params), operationInstanceTotal: Operation.count()]
    
        }
        
    }


    @Transactional
    def create(){

        def operationInstance=new Operation(params)
        
        render (contentType: 'text/json') {
            domainService.save(operationInstance)
        }
    }

    @Transactional
    def update(Operation operationInstance){

        render (contentType: 'text/json') {
            domainService.save(operationInstance)
        }         
    }


    @Transactional
    def delete(Operation operationInstance){
        
        render (contentType: 'text/json') {
            domainService.delete(operationInstance)
        }
    }
}
