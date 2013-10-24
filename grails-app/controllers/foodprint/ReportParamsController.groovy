package foodprint

import grails.converters.JSON

class ReportParamsController {

    static allowedMethods = [create: "POST",update: "PUT",  delete: "DELETE"]


    def index() {
        log.debug "${controllerName}-${actionName}"
        log.debug params

        def report=Report.findById(params.report.id);
        def reportParams = ReportParams.findAllByReport(report)

        if(reportParams){   
            def reportParamsJson =  JSON.parse((reportParams as JSON).toString()) 
                reportParamsJson.eachWithIndex{ rpj, i ->
                    rpj.report.name = reportParams[i].report.name
                    rpj.item.name = reportParams[i].item.name
                    rpj.item.title = reportParams[i].item.title
                    rpj.param.name = reportParams[i].param.name
                    rpj.param.title = reportParams[i].param.title
                    rpj.workstation.name = reportParams[i].workstation.name
                    rpj.workstation.title = reportParams[i].workstation.title
                    rpj.operation.name = reportParams[i].operation.name
                    rpj.operation.title = reportParams[i].operation.title
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
                reportParamsJson["workstation.id"] = reportParams.workstation.id
                reportParamsJson["workstation.name"] = reportParams.workstation.name
                reportParamsJson["workstation.title"] = reportParams.workstation.title
                reportParamsJson["operation.id"] = reportParams.operation.id
                reportParamsJson["operation.name"] = reportParams.operation.name
                reportParamsJson["operation.title"] = reportParams.operation.title

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

        def reportParamsInstance=new ReportParams(params)
        
        render (contentType: 'application/json') {
            domainService.save(reportParamsInstance)
        }
    }

    def update(){
        def reportParamsInstance = ReportParams.findById(params.id)
        reportParamsInstance.properties=params
        render (contentType: 'application/json') {
            domainService.save(reportParamsInstance)
        }         
    }


    def delete(){
        def reportParamsInstance = ReportParams.findById(params.id)
        render (contentType: 'application/json') {
            domainService.delete(reportParamsInstance)
        }
    }
}
