package foodprint

import org.springframework.dao.DataIntegrityViolationException

class ParamController {

    static allowedMethods = [create:"POST",update: "PUT",  delete: "DELETE"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [paramInstanceList: Param.list(params), paramInstanceTotal: Param.count()]
    }

    def listJson(Integer max) {
       // println"paramController--listJson"
        render (contentType: 'text/json') {
            list(max)        
        }
        
    }

    def create(){
        println"ParamController--create"

        def paramInstance=new Param(params)
        render (contentType: 'text/json') {
            save(paramInstance);
        }
    }

    def save(Param paramInstance){
        if (!paramInstance.validate()) {
                paramInstance.errors.each {
                println it
            }
            return [success:false]
        }
        if (!paramInstance.save(failOnError: true)) {//flush:true?
                return [success:false]
        }
        else{
                return [success:true]
        }
    }

    def update(){
        println"ParamController--update"
        def paramInstance=Param.get(params.id)

        if(!paramInstance){
            println"ParamController--update--cant find ParamInstance"
            return render (contentType: 'text/json') {[success:false]}
        }

       paramInstance.properties = params
        render (contentType: 'text/json') {
            save(paramInstance);
        }         
    }

    def show(Long id) {
        def paramInstance = Param.get(id)
        if (!paramInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'param.label', default: 'Param'), id])
            redirect(action: "list")
            return
        }

        [paramInstance: paramInstance]
    }

    def edit(Long id) {
        def paramInstance = Param.get(id)
        if (!paramInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'param.label', default: 'Param'), id])
            redirect(action: "list")
            return
        }

        [paramInstance: paramInstance]
    }


    def delete(){
        println"ParamController--delete"
        def paramInstance=Param.get(params.id)
        if (!paramInstance) {
            println"ParamController--delete--Cant find paramInstance"
            render (contentType: 'text/json') {
                return [success:false]
            }
        }
        //else
        //    println"BatchController--updateBatch--has find BatchInstance"

        try {
            paramInstance.delete()
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
