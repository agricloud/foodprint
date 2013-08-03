package foodprint

import org.springframework.dao.DataIntegrityViolationException

class ParamController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

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

    def create() {
        [paramInstance: new Param(params)]
    }

    def save() {
        def paramInstance = new Param(params)
        if (!paramInstance.save(flush: true)) {
            render(view: "create", model: [paramInstance: paramInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'param.label', default: 'Param'), paramInstance.id])
        redirect(action: "show", id: paramInstance.id)
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

    def update(Long id, Long version) {
        def paramInstance = Param.get(id)
        if (!paramInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'param.label', default: 'Param'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (paramInstance.version > version) {
                paramInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'param.label', default: 'Param')] as Object[],
                          "Another user has updated this Param while you were editing")
                render(view: "edit", model: [paramInstance: paramInstance])
                return
            }
        }

        paramInstance.properties = params

        if (!paramInstance.save(flush: true)) {
            render(view: "edit", model: [paramInstance: paramInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'param.label', default: 'Param'), paramInstance.id])
        redirect(action: "show", id: paramInstance.id)
    }

    def delete(Long id) {
        def paramInstance = Param.get(id)
        if (!paramInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'param.label', default: 'Param'), id])
            redirect(action: "list")
            return
        }

        try {
            paramInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'param.label', default: 'Param'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'param.label', default: 'Param'), id])
            redirect(action: "show", id: id)
        }
    }
}
