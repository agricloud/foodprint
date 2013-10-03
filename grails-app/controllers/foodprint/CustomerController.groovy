package foodprint

import org.springframework.dao.DataIntegrityViolationException

class CustomerController {

    static allowedMethods = [create:"POST",update: "POST",  delete: "POST"]
    def domainService

    def index(params) {

        render (contentType: 'text/json') {
            [customerInstanceList: Customer.list(params), customerInstanceTotal: Customer.count()]
    
        }
        
    }

 
    def create(){

        def customerInstance=new Customer(params)
        
        render (contentType: 'text/json') {
            domainService.save(customerInstance)
        }
    }

    def update(){
        def  customerInstance = Customer.findById(params.id)
        customerInstance.properties=params
        render (contentType: 'text/json') {
            domainService.save(customerInstance)
        }         
    }


    def delete(){
        def  customerInstance = Customer.findById(params.id)
        render (contentType: 'text/json') {
            domainService.delete(customerInstance)
        }
    }
    
}
