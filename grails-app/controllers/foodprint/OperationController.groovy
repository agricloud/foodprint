package foodprint

import org.springframework.dao.DataIntegrityViolationException

class OperationController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [operationInstanceList: Operation.list(params), operationInstanceTotal: Operation.count()]
    }
    
     def listJson(Integer max) {
        println"OperationController--listJson"
        render (contentType: 'text/json') {
            list(max)        
        }
    }

    def create() {
        println"OperationController--create"

        def operationInstance=new Operation(params)
        render (contentType: 'text/json') {
            save(operationInstance);
        }
    }

    def save(Operation operationInstance){
        if (!operationInstance.validate()) {
                operationInstance.errors.each {
                println it
            }
            return [success:false]
        }
        if (!operationInstance.save(failOnError: true)) {//flush:true?
                return [success:false]
        }
        else{
                return [success:true]
        }
    }

    def show(Long id) {
        def operationInstance = Operation.get(id)
        if (!operationInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'operation.label', default: 'Operation'), id])
            redirect(action: "list")
            return
        }

        [operationInstance: operationInstance]
    }

    def edit(Long id) {
        def operationInstance = Operation.get(id)
        if (!operationInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'operation.label', default: 'Operation'), id])
            redirect(action: "list")
            return
        }
        [operationInstance: operationInstance]
    }

    def update(){
        println"OperationController--update"
        def operationInstance=Operation.get(params.id)

        if(!operationInstance){
            println"OperationController--update--cant find operationInstance"
            return render (contentType: 'text/json') {[success:false]}
        }

       operationInstance.properties = params
        render (contentType: 'text/json') {
            save(operationInstance);
        }         
    }

    def delete(){
        println"OperationController--delete"
        def operationInstance=Operation.get(params.id)
        if (!operationInstance) {
            println"OperationController--delete--Cant find operationInstance"
            render (contentType: 'text/json') {
                return [success:false]
            }
        }
        try {
            operationInstance.delete()
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
