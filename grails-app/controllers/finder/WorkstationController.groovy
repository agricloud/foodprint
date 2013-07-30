package finder

import org.springframework.dao.DataIntegrityViolationException

class WorkstationController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [workstationInstanceList: Workstation.list(params), workstationInstanceTotal: Workstation.count()]
    }

    def create() {
        [workstationInstance: new Workstation(params)]
    }

    def save() {
        def workstationInstance = new Workstation(params)
        if (!workstationInstance.save(flush: true)) {
            render(view: "create", model: [workstationInstance: workstationInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'workstation.label', default: 'Workstation'), workstationInstance.id])
        redirect(action: "show", id: workstationInstance.id)
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

    def update(Long id, Long version) {
        def workstationInstance = Workstation.get(id)
        if (!workstationInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'workstation.label', default: 'Workstation'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (workstationInstance.version > version) {
                workstationInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'workstation.label', default: 'Workstation')] as Object[],
                          "Another user has updated this Workstation while you were editing")
                render(view: "edit", model: [workstationInstance: workstationInstance])
                return
            }
        }

        workstationInstance.properties = params

        if (!workstationInstance.save(flush: true)) {
            render(view: "edit", model: [workstationInstance: workstationInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'workstation.label', default: 'Workstation'), workstationInstance.id])
        redirect(action: "show", id: workstationInstance.id)
    }

    def delete(Long id) {
        def workstationInstance = Workstation.get(id)
        if (!workstationInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'workstation.label', default: 'Workstation'), id])
            redirect(action: "list")
            return
        }

        try {
            workstationInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'workstation.label', default: 'Workstation'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'workstation.label', default: 'Workstation'), id])
            redirect(action: "show", id: id)
        }
    }
}
