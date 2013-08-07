package foodprint

import org.springframework.dao.DataIntegrityViolationException

class ReportParamsController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [reportParamsInstanceList: ReportParams.list(params), reportParamsInstanceTotal: ReportParams.count()]
    }
    def listJson(Integer max) {
        render (contentType: 'text/json') {
            list(max)        
        }
        
    }


    def create() {
        [reportParamsInstance: new ReportParams(params)]
    }

    def save() {
        def reportParamsInstance = new ReportParams(params)
        if (!reportParamsInstance.save(flush: true)) {
            render(view: "create", model: [reportParamsInstance: reportParamsInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'reportParams.label', default: 'ReportParams'), reportParamsInstance.id])
        redirect(action: "show", id: reportParamsInstance.id)
    }

    def show(Long id) {
        def reportParamsInstance = ReportParams.get(id)
        if (!reportParamsInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'reportParams.label', default: 'ReportParams'), id])
            redirect(action: "list")
            return
        }

        [reportParamsInstance: reportParamsInstance]
    }

    def edit(Long id) {
        def reportParamsInstance = ReportParams.get(id)
        if (!reportParamsInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'reportParams.label', default: 'ReportParams'), id])
            redirect(action: "list")
            return
        }

        [reportParamsInstance: reportParamsInstance]
    }

    def update(Long id, Long version) {
        def reportParamsInstance = ReportParams.get(id)
        if (!reportParamsInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'reportParams.label', default: 'ReportParams'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (reportParamsInstance.version > version) {
                reportParamsInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'reportParams.label', default: 'ReportParams')] as Object[],
                          "Another user has updated this ReportParams while you were editing")
                render(view: "edit", model: [reportParamsInstance: reportParamsInstance])
                return
            }
        }

        reportParamsInstance.properties = params

        if (!reportParamsInstance.save(flush: true)) {
            render(view: "edit", model: [reportParamsInstance: reportParamsInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'reportParams.label', default: 'ReportParams'), reportParamsInstance.id])
        redirect(action: "show", id: reportParamsInstance.id)
    }

    def delete(Long id) {
        def reportParamsInstance = ReportParams.get(id)
        if (!reportParamsInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'reportParams.label', default: 'ReportParams'), id])
            redirect(action: "list")
            return
        }

        try {
            reportParamsInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'reportParams.label', default: 'ReportParams'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'reportParams.label', default: 'ReportParams'), id])
            redirect(action: "show", id: id)
        }
    }
}
