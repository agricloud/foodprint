package foodprint

import org.springframework.dao.DataIntegrityViolationException

class ReportController {

    static allowedMethods = [create:"POST",update: "PUT",  delete: "DELETE"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [reportInstanceList: Report.list(params), reportInstanceTotal: Report.count()]
    }

    def listJson(Integer max) {
        render (contentType: 'text/json') {
            list(max)        
        }
        
    }

    def create(){
        println"ReportController--create"

        def reportInstance=new Report(params)
        render (contentType: 'text/json') {
            save(reportInstance);
        }
    }

    def update(){
        println"ReportController--update"
        def reportInstance=Report.get(params.id)

        if(!reportInstance){
            println"ReportController--update--cant find reportInstance"
            return render (contentType: 'text/json') {[success:false]}
        }

       reportInstance.properties = params
        render (contentType: 'text/json') {
            save(reportInstance);
        }         
    }

    def save(Report reportInstance){
        if (!reportInstance.validate()) {
                reportInstance.errors.each {
                println it
            }
            return [success:false]
        }
        if (!reportInstance.save(failOnError: true)) {//flush:true?
                return [success:false]
        }
        else{
                return [success:true]
        }
    }

    def show(Long id) {
        def reportInstance = Report.get(id)
        if (!reportInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'report.label', default: 'Report'), id])
            redirect(action: "list")
            return
        }

        [reportInstance: reportInstance]
    }

    def edit(Long id) {
        def reportInstance = Report.get(id)
        if (!reportInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'report.label', default: 'Report'), id])
            redirect(action: "list")
            return
        }

        [reportInstance: reportInstance]
    }

    def delete(){
        println"ReportController--delete"
        def reportInstance=Report.get(params.id)
        if (!reportInstance) {
            println"ReportController--delete--Cant find reportInstance"
            render (contentType: 'text/json') {
                return [success:false]
            }
        }
        //else
        //    println"BatchController--updateBatch--has find BatchInstance"

        try {
            reportInstance.delete()
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
