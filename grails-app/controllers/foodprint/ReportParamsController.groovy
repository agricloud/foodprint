package foodprint

import grails.converters.JSON

class ReportParamsController {

    def domainService

    def index() {
        log.debug "${controllerName}-${actionName}"
        log.debug params

        def report=Report.findById(params.report.id);
        def reportParams = ReportParams.findAllByReport(report)

        if(report){   
            def reportParamsJson =  JSON.parse((reportParams as JSON).toString()) 
                reportParamsJson.eachWithIndex{ rpj, i ->
                    rpj.report.name = reportParams[i].report.name
                    if(reportParams[i].item){
                        rpj.item.name = reportParams[i].item.name
                        rpj.item.title = reportParams[i].item.title
                    }
                    rpj.param.name = reportParams[i].param.name
                    rpj.param.title = reportParams[i].param.title
                    if(reportParams[i].workstation){
                        rpj.workstation.name = reportParams[i].workstation.name
                        rpj.workstation.title = reportParams[i].workstation.title
                    }
                    if(reportParams[i].operation){
                        rpj.operation.name = reportParams[i].operation.name
                        rpj.operation.title = reportParams[i].operation.title
                    }
                    if(reportParams[i].supplier){
                        rpj.supplier.name = reportParams[i].supplier.name
                        rpj.supplier.title = reportParams[i].supplier.title
                    }
                }
        
            render (contentType: 'application/json') {
                [success: true,reportParamsInstanceList: reportParamsJson, reportParamsInstanceTotal: reportParamsJson.size()]
            }
        }
        else{
            render (contentType: 'application/json') {
                [success: false, message:message(code: 'default.message.show.failed')]
            }          
        }
        
    }

    def show(Long id){

        log.debug "${controllerName}-${actionName}"

        def reportParams=ReportParams.findById(id);

        if(reportParams){   
            def reportParamsJson =  JSON.parse((reportParams as JSON).toString()) 
                reportParamsJson["report.id"] = reportParams.report.id
                reportParamsJson["report.name"] = reportParams.report.name        
                reportParamsJson["item.id"] = reportParams.item.id
                reportParamsJson["item.name"] = reportParams.item.name
                reportParamsJson["item.title"] = reportParams.item.title
                reportParamsJson["param.id"] = reportParams.param.id
                reportParamsJson["param.name"] = reportParams.param.name
                reportParamsJson["param.title"] = reportParams.param.title
                if(reportParams.workstation){
                    reportParamsJson["workstation.id"] = reportParams.workstation.id
                    reportParamsJson["workstation.name"] = reportParams.workstation.name
                    reportParamsJson["workstation.title"] = reportParams.workstation.title
                }
                reportParamsJson["operation.id"] = reportParams.operation.id
                reportParamsJson["operation.name"] = reportParams.operation.name
                reportParamsJson["operation.title"] = reportParams.operation.title
                if(reportParams.supplier){
                    reportParamsJson["supplier.id"] = reportParams.supplier.id
                    reportParamsJson["supplier.name"] = reportParams.supplier.name
                    reportParamsJson["supplier.title"] = reportParams.supplier.title
                }

            render (contentType: 'application/json') {
                [success: true, data:reportParamsJson]
            }
        }else {
            render (contentType: 'application/json') {
                [success: false, message:message(code: 'default.message.show.failed')]
            }          
        }
    }

    def create(){
        if(params.report.id){

            def reportParams=new ReportParams(params)

            def reportParamsJson =  JSON.parse((reportParams as JSON).toString()) 
            reportParamsJson["report.id"] = reportParams.report.id

            render (contentType: 'application/json') {
                [success: true,data:reportParamsJson]
            }
        }else {
            render (contentType: 'application/json') {
                [success: false,message:message(code: 'batchRoute.message.create.failed')]
            }            
        }   
    }

    def save(){
        log.debug "${controllerName}-${actionName}"
        def reportParams=new ReportParams(params)
        
        render (contentType: 'application/json') {
            domainService.save(reportParams)
        }
    }

    def update(){

        log.debug "${controllerName}-${actionName}"

        def reportParams = ReportParams.findById(params.id)
        if(params.workstation.id==null || !params.workstation.id.trim()){
            params.remove("workstation.id")
            params.remove("workstation.title")   
        }
        if(params.supplier.id==null || !params.supplier.id.trim()){
            params.remove("supplier.id")
            params.remove("supplier.title")
        }

        reportParams.properties=params
        render (contentType: 'application/json') {
            domainService.save(reportParams)
        }
    }

    def delete(){
        log.debug "${controllerName}-${actionName}"
        def reportParams = ReportParams.findById(params.id)
        def result
        try {
            result = domainService.delete(reportParams)
        }catch(e){
            log.error e
            def msg = message(code: 'default.message.delete.failed', args: [reportParams, e.getMessage()])
            result = [success:false, message: msg] 
        }
        
        render (contentType: 'application/json') {
            result
        }
    }
}
