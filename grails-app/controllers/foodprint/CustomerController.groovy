package foodprint

import org.springframework.dao.DataIntegrityViolationException

class CustomerController {

    static allowedMethods = [create:"POST",update: "PUT",  delete: "DELETE"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        log.debug "${controllerName}-${actionName}"
        params.max = Math.min(max ?: 10, 100)
        [customerInstanceList: Customer.list(params), customerInstanceTotal: Customer.count()]
    }
    
    def listJson(Integer max) {
        log.debug "${controllerName}-${actionName}"
        render (contentType: 'text/json') {
            list(max)        
        }
    }

    def create(){
        log.debug "${controllerName}-${actionName}"

        def customerInstance=new Customer(params)
        render (contentType: 'text/json') {
            save(customerInstance);
        }
    }

    def save(Customer customerInstance){
        log.debug "${controllerName}-${actionName}"
        if (!customerInstance.validate()) {
                customerInstance.errors.each {
                println it
            }
            return [success:false]
        }
        if (!customerInstance.save(failOnError: true)) {//flush:true?
                return [success:false]
        }
        else{
                return [success:true]
        }
    }

    def show(Long id) {
        def customerInstance = Customer.get(id)
        if (!customerInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'customer.label', default: 'Customer'), id])
            redirect(action: "list")
            return
        }

        [customerInstance: customerInstance]
    }

    def edit(Long id) {
        def customerInstance = Customer.get(id)
        if (!customerInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'customer.label', default: 'Customer'), id])
            redirect(action: "list")
            return
        }

        [customerInstance: customerInstance]
    }

    def update(){
        log.debug "${controllerName}-${actionName}"
        def customerInstance=Customer.get(params.id)

        if(!customerInstance){
            log.debug "${controllerName}-${actionName}-cant find customerInstance"
            return render (contentType: 'text/json') {[success:false]}
        }

       customerInstance.properties = params
        render (contentType: 'text/json') {
            save(customerInstance);
        }         
    }

    def delete(){
        log.debug "${controllerName}-${actionName}"
        def customerInstance=Customer.get(params.id)
        if (!customerInstance) {
            log.debug "${controllerName}-${actionName}"
            render (contentType: 'text/json') {
                return [success:false]
            }
        }
        //else
        //    println"BatchController--updateBatch--has find BatchInstance"
        try {
            customerInstance.delete()
            render (contentType: 'text/json') {
                return [success:true]
            }
        }
        catch (e) {
            render (contentType: 'text/json') {
                return [success:false]
            }
        }
    }

}
