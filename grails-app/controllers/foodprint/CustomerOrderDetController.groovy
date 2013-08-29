package foodprint

import org.springframework.dao.DataIntegrityViolationException

class CustomerOrderDetController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [customerOrderDetInstanceList: CustomerOrderDet.list(params), customerOrderDetInstanceTotal: CustomerOrderDet.count()]
    }

    def create() {
        [customerOrderDetInstance: new CustomerOrderDet(params)]
    }

    def save() {
        def customerOrderDetInstance = new CustomerOrderDet(params)
        if (!customerOrderDetInstance.save(flush: true)) {
            render(view: "create", model: [customerOrderDetInstance: customerOrderDetInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'customerOrderDet.label', default: 'CustomerOrderDet'), customerOrderDetInstance.id])
        redirect(action: "show", id: customerOrderDetInstance.id)
    }

    def show(Long id) {
        def customerOrderDetInstance = CustomerOrderDet.get(id)
        if (!customerOrderDetInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'customerOrderDet.label', default: 'CustomerOrderDet'), id])
            redirect(action: "list")
            return
        }

        [customerOrderDetInstance: customerOrderDetInstance]
    }

    def edit(Long id) {
        def customerOrderDetInstance = CustomerOrderDet.get(id)
        if (!customerOrderDetInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'customerOrderDet.label', default: 'CustomerOrderDet'), id])
            redirect(action: "list")
            return
        }

        [customerOrderDetInstance: customerOrderDetInstance]
    }

    def update(Long id, Long version) {
        def customerOrderDetInstance = CustomerOrderDet.get(id)
        if (!customerOrderDetInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'customerOrderDet.label', default: 'CustomerOrderDet'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (customerOrderDetInstance.version > version) {
                customerOrderDetInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'customerOrderDet.label', default: 'CustomerOrderDet')] as Object[],
                          "Another user has updated this CustomerOrderDet while you were editing")
                render(view: "edit", model: [customerOrderDetInstance: customerOrderDetInstance])
                return
            }
        }

        customerOrderDetInstance.properties = params

        if (!customerOrderDetInstance.save(flush: true)) {
            render(view: "edit", model: [customerOrderDetInstance: customerOrderDetInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'customerOrderDet.label', default: 'CustomerOrderDet'), customerOrderDetInstance.id])
        redirect(action: "show", id: customerOrderDetInstance.id)
    }

    def delete(Long id) {
        def customerOrderDetInstance = CustomerOrderDet.get(id)
        if (!customerOrderDetInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'customerOrderDet.label', default: 'CustomerOrderDet'), id])
            redirect(action: "list")
            return
        }

        try {
            customerOrderDetInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'customerOrderDet.label', default: 'CustomerOrderDet'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'customerOrderDet.label', default: 'CustomerOrderDet'), id])
            redirect(action: "show", id: id)
        }
    }
}
