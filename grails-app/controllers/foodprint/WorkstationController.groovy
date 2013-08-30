package foodprint

import org.springframework.dao.DataIntegrityViolationException

class WorkstationController {

    static allowedMethods = [create:"POST",update: "PUT",  delete: "DELETE"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [workstationInstanceList: Workstation.list(params), workstationInstanceTotal: Workstation.count()]
    }

    def listJson(Integer max) {

        render (contentType: 'text/json') {
            list(max)        
        }
        
    }

    def create(){
        log.debug "${controllerName}-${actionName}"

        def workstationInstance=new Workstation(params)
        render (contentType: 'text/json') {
            save(workstationInstance);
        }
    }

    def update(){
        println"WorkstationController--update"
        def workstationInstance=Workstation.get(params.id)

        if(!workstationInstance){
            println"WorkstationController--update--cant find workstationInstance"
            return render (contentType: 'text/json') {[success:false]}
        }

       workstationInstance.properties = params
        render (contentType: 'text/json') {
            save(workstationInstance);
        }         
    }

    def save(Workstation workstationInstance){
        if (!workstationInstance.validate()) {
                workstationInstance.errors.each {
                println it
            }
            return [success:false]
        }
        if (!workstationInstance.save(failOnError: true)) {//flush:true?
                return [success:false]
        }
        else{
                return [success:true]
        }
    }

    def show(Long id) {
        def workstationInstance = Workstation.get(id)
        if (!workstationInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'workstation.label', default: 'Workstation'), id])
            redirect(action: "list")
            return
        }

        [workstationInstance: workstationInstance]
    }

    def edit(Long id) {
        def workstationInstance = Workstation.get(id)
        if (!workstationInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'workstation.label', default: 'Workstation'), id])
            redirect(action: "list")
            return
        }

        [workstationInstance: workstationInstance]
    }

    def delete(){
        println"WorkstationController--delete"
        def workstationInstance=Workstation.get(params.id)
        if (!workstationInstance) {
            println"WorkstationController--delete--Cant find workstationInstance"
            render (contentType: 'text/json') {
                return [success:false]
            }
        }
        //else
        //    println"BatchController--updateBatch--has find BatchInstance"

        try {
            workstationInstance.delete()
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
