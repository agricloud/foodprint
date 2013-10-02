package foodprint

import org.springframework.dao.DataIntegrityViolationException
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
class CustomerController {

    static allowedMethods = [create:"POST",update: "POST",  delete: "POST"]
    def domainService

    def index(params) {

        render (contentType: 'text/json') {
            [customerInstanceList: Customer.list(params), customerInstanceTotal: Customer.count()]
    
        }
        
    }

 
    @Transactional
    def create(){

        def customerInstance=new Customer(params)
        
        render (contentType: 'text/json') {
            domainService.save(customerInstance)
        }
    }

    @Transactional
    def update(Customer customerInstance){

        render (contentType: 'text/json') {
            domainService.save(customerInstance)
        }         
    }


    @Transactional
    def delete(Customer customerInstance){
        
        render (contentType: 'text/json') {
            domainService.delete(customerInstance)
        }
    }
    
}
