package foodprint

import org.springframework.dao.DataIntegrityViolationException
import grails.converters.JSON

class BatchReportDetController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        def batch_id=params.batchid
        //print report_id
        def batchob=Batch.findById(batch_id,params);
        //print reportob
        def resultList= BatchReportDet.findAllByBatch(batchob)
        [batchReportDetInstanceList:resultList, batchReportDetInstanceTotal: BatchReportDet.count()]
    }

    def listJson(Integer max) {
        JSON.use('deep')
        def converter=list()as JSON
        converter.render(response)
        //render (contentType: 'text/json') {
        //    print list(max)
         //   list(max)        
        //}
    }

    def create() {
        [batchReportDetInstance: new BatchReportDet(params)]
    }

    def save() {
        def batchReportDetInstance = new BatchReportDet(params)
        if (!batchReportDetInstance.save(flush: true)) {
            render(view: "create", model: [batchReportDetInstance: batchReportDetInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'batchReportDet.label', default: 'BatchReportDet'), batchReportDetInstance.id])
        redirect(action: "show", id: batchReportDetInstance.id)
    }

    def show(Long id) {
        def batchReportDetInstance = BatchReportDet.get(id)
        if (!batchReportDetInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'batchReportDet.label', default: 'BatchReportDet'), id])
            redirect(action: "list")
            return
        }

        [batchReportDetInstance: batchReportDetInstance]
    }

    def edit(Long id) {
        def batchReportDetInstance = BatchReportDet.get(id)
        if (!batchReportDetInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'batchReportDet.label', default: 'BatchReportDet'), id])
            redirect(action: "list")
            return
        }

        [batchReportDetInstance: batchReportDetInstance]
    }

    def update(Long id, Long version) {
        def batchReportDetInstance = BatchReportDet.get(id)
        if (!batchReportDetInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'batchReportDet.label', default: 'BatchReportDet'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (batchReportDetInstance.version > version) {
                batchReportDetInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'batchReportDet.label', default: 'BatchReportDet')] as Object[],
                          "Another user has updated this BatchReportDet while you were editing")
                render(view: "edit", model: [batchReportDetInstance: batchReportDetInstance])
                return
            }
        }

        batchReportDetInstance.properties = params

        if (!batchReportDetInstance.save(flush: true)) {
            render(view: "edit", model: [batchReportDetInstance: batchReportDetInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'batchReportDet.label', default: 'BatchReportDet'), batchReportDetInstance.id])
        redirect(action: "show", id: batchReportDetInstance.id)
    }

    def delete(Long id) {
        def batchReportDetInstance = BatchReportDet.get(id)
        if (!batchReportDetInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'batchReportDet.label', default: 'BatchReportDet'), id])
            redirect(action: "list")
            return
        }

        try {
            batchReportDetInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'batchReportDet.label', default: 'BatchReportDet'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'batchReportDet.label', default: 'BatchReportDet'), id])
            redirect(action: "show", id: id)
        }
    }
}
