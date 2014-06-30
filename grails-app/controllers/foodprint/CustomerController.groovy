package foodprint

import org.springframework.dao.DataIntegrityViolationException

class CustomerController {

    def domainService

    def index = {

        def list = Customer.createCriteria().list(params,params.criteria)


        render (contentType: 'application/json') {
            [data: list, total: list.totalCount]
        }
        
    }

     def show = {

        def customer=Customer.get(params.id);  
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
    def create = {

        def customer=new Customer()        
        render (contentType: 'application/json') {
            [success: true,data:customer]
        }
    }
    def save = {
        //\d表示需輸入的內容需為數字0-9,-或者輸入的內容為符號"-",^表示開頭$表示結尾
        if(params.tel && !(params.tel ==~ /^[\d-]*$/)){
            render (contentType: 'application/json') {
                [success: false,message:message(code: 'customer.tel.not.valid')]
            }
            return
        }

        if(params.fax && !(params.fax ==~ /^[\d-]*$/)){
            render (contentType: 'application/json') {
                [success: false,message:message(code: 'customer.tel.not.valid')]
            }
            return
        }

        if(params.email && !(params.email ==~ /^[a-zA-Z0-9]*[@]+$/)){
            render (contentType: 'application/json') {
                [success: false,message:message(code: 'customer.email.not.valid')]
            }
            return
        }

        def customerInstance=new Customer(params)
        
        render (contentType: 'application/json') {
            domainService.save(customerInstance)
        }
    }

    def update = {
        if(params.tel && !(params.tel ==~ /^[\d-]*$/)){
            render (contentType: 'application/json') {
                [success: false,message:message(code: 'customer.tel.not.valid')]
            }
            return
        }

        if(params.fax && !(params.fax ==~ /^[\d-]*$/)){
            render (contentType: 'application/json') {
                [success: false,message:message(code: 'customer.tel.not.valid')]
            }
            return
        }

        if(params.email && !(params.email ==~ /^[a-zA-Z0-9]*[@]+$/)){
            render (contentType: 'application/json') {
                [success: false,message:message(code: 'customer.email.not.valid')]
            }
            return
        }

        def  customerInstance = Customer.get(params.id)
        customerInstance.properties=params
        render (contentType: 'application/json') {
            domainService.save(customerInstance)
        }  
    }


    def delete = {
        def customerInstance = Customer.get(params.id)
        def result
        try {
            
            result = domainService.delete(customerInstance)
        
        }catch(DataIntegrityViolationException e){
            log.error e
            def msg = message(code: 'default.message.delete.failed', args: [customerInstance, e.getMessage()])
            result = [success:false, message: msg] 
        }
        
        render (contentType: 'application/json') {
            result
        }
    }
    
}
