package foodprint

import org.springframework.dao.DataIntegrityViolationException

class CustomerOrderController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [customerOrderInstanceList: CustomerOrder.list(params), customerOrderInstanceTotal: CustomerOrder.count()]
    }

    def create() {
        [customerOrderInstance: new CustomerOrder(params)]
    }

    def save() {
        def customerOrderInstance = new CustomerOrder(params)
        if (!customerOrderInstance.save(flush: true)) {
            render(view: "create", model: [customerOrderInstance: customerOrderInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'customerOrder.label', default: 'CustomerOrder'), customerOrderInstance.id])
        redirect(action: "show", id: customerOrderInstance.id)
    }

    def show(Long id) {
        def customerOrderInstance = CustomerOrder.get(id)
        if (!customerOrderInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'customerOrder.label', default: 'CustomerOrder'), id])
            redirect(action: "list")
            return
        }

        [customerOrderInstance: customerOrderInstance]
    }

    def edit(Long id) {
        def customerOrderInstance = CustomerOrder.get(id)
        if (!customerOrderInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'customerOrder.label', default: 'CustomerOrder'), id])
            redirect(action: "list")
            return
        }

        [customerOrderInstance: customerOrderInstance]
    }

    def update(Long id, Long version) {
        def customerOrderInstance = CustomerOrder.get(id)
        if (!customerOrderInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'customerOrder.label', default: 'CustomerOrder'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (customerOrderInstance.version > version) {
                customerOrderInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'customerOrder.label', default: 'CustomerOrder')] as Object[],
                          "Another user has updated this CustomerOrder while you were editing")
                render(view: "edit", model: [customerOrderInstance: customerOrderInstance])
                return
            }
        }

        customerOrderInstance.properties = params

        if (!customerOrderInstance.save(flush: true)) {
            render(view: "edit", model: [customerOrderInstance: customerOrderInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'customerOrder.label', default: 'CustomerOrder'), customerOrderInstance.id])
        redirect(action: "show", id: customerOrderInstance.id)
    }

    def delete(Long id) {
        def customerOrderInstance = CustomerOrder.get(id)
        if (!customerOrderInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'customerOrder.label', default: 'CustomerOrder'), id])
            redirect(action: "list")
            return
        }

        try {
            customerOrderInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'customerOrder.label', default: 'CustomerOrder'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'customerOrder.label', default: 'CustomerOrder'), id])
            redirect(action: "show", id: id)
        }
    }
}
