package foodprint

import org.springframework.dao.DataIntegrityViolationException

class CustomerController {

    def domainService

    def index(params) {

        render (contentType: 'application/json') {
            [customerInstanceList: Customer.list(params), customerInstanceTotal: Customer.count()]
    
        }
        
    }

     def show(Long id){

        def customer=Customer.findById(id);  
        if(customer){   
            render (contentType: 'application/json') {
                [success: true,data:customer]
            }
        }else {
            render (contentType: 'application/json') {
                [success: false,message:message(code: 'default.message.show.failed')]
            }          
        }
    }
    def create(){

        def customer=new Customer()        
        render (contentType: 'application/json') {
            [success: true,data:customer]
        }
    }
    def save(){

        def customerInstance=new Customer(params)
        
        render (contentType: 'application/json') {
            domainService.save(customerInstance)
        }
    }

    def update(){
        def  customerInstance = Customer.findById(params.id)
        customerInstance.properties=params
        render (contentType: 'application/json') {
            domainService.save(customerInstance)
        }         
    }


    def delete(){
        def customerInstance = Customer.findById(params.id)
        def result
        try {
            
            result = domainService.delete(customerInstance)
        
        }catch(e){
            log.error e
            def msg = message(code: 'default.message.delete.failed', args: [customerInstance, e.getMessage()])
            result = [success:false, message: msg] 
        }
        
        render (contentType: 'application/json') {
            result
        }
    }
    
}
