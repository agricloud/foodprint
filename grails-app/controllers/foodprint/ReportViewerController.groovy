package foodprint

import grails.converters.*


class ReportViewerController {

    def batchAnalyzeService


    def index(){
        //restFoodpaint()

        println !params?.name
        println params?.name == 'null'
        println params?.name == null

        if(!params?.name || params?.name == 'null'){
            println 'redirect'
            redirect (uri: 'report/')
            return
        }

        def batch = Batch.findByName(params.name)
        def product = [:]
        product.head = [:]
        product.body = [:]
        product.title = " 產品說明"


        product.head["batch.name"] = batch.name
        product.head["item.title"] = batch.item.title
        product.head["item.description"] = batch.item.description


        product.body["item.name"] = batch.item.name
        product.body["batch.manufactureDate"] = batch.manufactureDate
        product.body["batch.expirationDate"] = batch.expirationDate
        product.body["item.spec"] = batch.item.spec
        
        def otherReports=[]
        def batchReportDets = BatchReportDet.findAllByBatch(batch)
        def domainReports = batchReportDets.reportParams*.report.unique()




        domainReports.each(){ report ->
            def reportMap = [:]
            reportMap.params=[]
            reportMap.title = report.title
            reportMap.reportType = report.reportType


            if(reportMap.reportType == ReportType.NUTRITION){
                batchReportDets.each(){ batchReportDet ->
                    if(batchReportDet.reportParams.report == report){
                        def param = [:]

                        param["param.name"] = batchReportDet.reportParams.param.name
                        param["param.title"] = batchReportDet.reportParams.param.title
                        param["param.description"] = batchReportDet.reportParams.param.description
                        param["param.unit"] = batchReportDet.reportParams.param.unit
                        param["batchReportDet.value"] = batchReportDet.value

                        reportMap.params << param
                    }
                    
                }
                otherReports << reportMap

            } else if(reportMap.reportType == ReportType.OTHER){
                batchReportDets.each(){ batchReportDet ->
                    if(batchReportDet.reportParams.report == report){
                        def param = [:]

                        param["param.name"] = batchReportDet.reportParams.param.name
                        param["param.title"] = batchReportDet.reportParams.param.title
                        param["param.description"] = batchReportDet.reportParams.param.description
                        param["batchReportDet.value"] = batchReportDet.value

                        reportMap.params << param
                    }
                }
                otherReports << reportMap 
            }

        }


        [batch: batch, product: product,reports: otherReports]



    }

    def material(){

        if(!params?.name || params?.name == 'null'){
            println 'redirect'
            redirect (uri: 'report/')
            return
        }

        def batch = Batch.findByName(params.name)
        

        def batchSourceReportMap = [:]
        batchSourceReportMap.title = "原料履歷"
        batchSourceReportMap.params=[]


        def batchChilds=batchAnalyzeService.backwardTraceToFinal(batch).batchChilds
        batchChilds.each(){ childBatch ->

            def param = [:]
            // param["batch.name"] = childBatch.name
            // param["item.name"] = childBatch.item.name
            param["item.title"] = childBatch.item.title
            param["item.spec"] = childBatch.item.spec
            param["supplier.title"] = childBatch?.supplier?.title
            param["batch.country"] = childBatch.country
            param["item.description"] = childBatch.item.description
            param["default.image"] = "/attachment/show/${childBatch.item.id}?domainName=item"


            batchSourceReportMap.params << param

        }

        [batch: batch, report: batchSourceReportMap]

    }
    def cultivate(){

        if(!params?.name || params?.name == 'null'){
            println 'redirect'
            redirect (uri: 'report/')
            return
        }

        def batch = Batch.findByName(params.name)
        
        def batchRouteReportMap = [:]
        batchRouteReportMap.title = "栽種履歷"

        batchRouteReportMap.params=[]



        batch.batchRoutes.each(){ batchRoute ->
            def param = [:]
            // param["batchRoute.id"] = batchRoute.id
            // param["batchRoute.sequence"] = batchRoute.sequence


            
            param["agriculture.operation.title"] = batchRoute.operation.title

            param["agriculture.batchRoute.endDate"] = batchRoute.endDate
            param["agriculture.workstation.title"] = batchRoute.workstation.title
            param["operation.description"] = batchRoute.operation.description

            param["default.image"] = "/attachment/show/${batchRoute.id}?domainName=batchRoute"

            batchRouteReportMap.params << param

        }

        [batch: batch, report: batchRouteReportMap]

    }
    def quality(){

        if(!params?.name || params?.name == 'null'){
            println 'redirect'
            redirect (uri: 'report/')
            return
        }

        def batch = Batch.findByName(params.name)
        


        def batchReportDets = BatchReportDet.findAllByBatch(batch)
        def domainReports = batchReportDets.reportParams*.report.unique()

        def reportMap = [:]


        reportMap.params=[]

        domainReports.each(){ report ->
            reportMap.title = report.title
            reportMap.reportType = report.reportType

            if(reportMap.reportType == ReportType.INSPECT){
                batchReportDets.each(){ batchReportDet ->
                    if(batchReportDet.reportParams.report == report){
                        def param = [:]

                        param["inspect.param.title"] = batchReportDet.reportParams.param.title
                        param["inspect.batchReportDet.value"] = batchReportDet.value 
                        param["inspect.qualified"] = false
                        param["param.upper"] = batchReportDet.reportParams.param.upper
                        param["inspect.dateCreated"] = batchReportDet.dateCreated.format('yyyy-MM-dd')
                        param["inspect.param.unit"] = batchReportDet.reportParams.param.unit

                        reportMap.params << param

                    }
                }
            }

        }

        [batch: batch, report: reportMap]

    }

    def search(){

    }    

    def query(){
        
        def batch = Batch.findByName(params.name)

        if(batch) {
            redirect (uri: '/report/'+batch.name)
            return 
        }else  {
            flash.message = "查無批號！"
            redirect (uri: '/report')
        }
        
    }


}
