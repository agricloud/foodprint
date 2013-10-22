package foodprint

import org.springframework.http.converter.StringHttpMessageConverter

import grails.plugins.rest.client.RestBuilder
import java.nio.charset.Charset
import grails.converters.*
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
class ReportViewerController {

    def batchAnalyzeService

    def demo() { 

        // 勿刪， 無 http://192.168.2.104:8100/SFT 測試用
        // def webRootDir = servletContext.getRealPath ("/")
        // def f = new File (webRootDir + "/xmlSample/report.xml")
        // def records = new XmlParser().parseText(f.text)

        def rest = new RestBuilder()
        rest.restTemplate.setMessageConverters([new StringHttpMessageConverter(Charset.forName("UTF-8"))])
        def url = "http://192.168.2.104:8100/SFT/"
        def imgUrl = url+"PDA/"
        def resp = rest.get(url+"ws/demo/records/GinPin/410002/981009-410002")
        def records = new XmlParser().parseText(resp.text)



        def formImg=records.form.field.findAll{ field->
                field.'@label'=="圖片"
        }
        formImg.each{
                it.value=imgUrl+it.text()
        }

        records.tabs.tab.detail.row.cell.each{ cell->
                if(cell.img.size()>0){
                    cell.img[0].'@src'  = [imgUrl+ cell.img.'@src'[0]]
            }
        }

        return [reportData: records]

    }

    def index(){
        restFoodpaint()
        
        def batch = Batch.findByName(params.batch.name)
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
        
        [batch: batch, product: product]


    }

    def material(){
        def batch = Batch.findByName(params.batch.name)
        

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

        def batch = Batch.findByName(params.batch.name)
        
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

        def batch = Batch.findByName(params.batch.name)
        


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

    def restFoodpaint(){
        def rest = new RestBuilder()
        rest.restTemplate.setMessageConverters([new StringHttpMessageConverter(Charset.forName("UTF-8"))])
        def url = "http://localhost:8180/foodprint/queryBatchReport/?batch.name="+params.batch.name
        def resp = rest.get(url)

        //進行資料匯入
        importData(resp.text)

        return [pass:"pass"]
    }

    private importData(jsonString){
        log.debug "ReportViewerController--importData"

        def records=JSON.parse(jsonString)
        //匯入品項
        records.item.each{ item ->
            item.site = Site.findByName(item.site.name)
            new Item(item).save( flush: true)
        }

        log.debug "品項清單："
        Item.list().each{
            log.debug it.name+"/"+it.title
        }

        //匯入批號
        records.batch.each{ batch ->
            batch.site=Site.findByName(batch.site.name)
            batch.item=Item.findByName(batch.item.name)
            new Batch(batch).save( flush: true)
        }
        //匯入批號關聯
        records.batchSources.each{ batchSource ->
            batchSource.batch=Batch.findByName(batchSource.batch.name)
            batchSource.childBatch=Batch.findByName(batchSource.childBatch.name)
            new BatchSource(batchSource).save(flush: true)
        }
    }
}
