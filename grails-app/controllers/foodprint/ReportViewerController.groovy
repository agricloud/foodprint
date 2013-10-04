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
      				cell.img[0].'@src'	= [imgUrl+ cell.img.'@src'[0]]
    			}
      	}

      		return [reportData: records]

    }

    def index(){
      restFoodpaint()
      
      def batch = Batch.findByName(params.batch.name)
      def product = [:]

      product["batch.name"] = batch.name
      product["item.name"] = batch.item.name
      product["item.title"] = batch.item.title
      product["item.spec"] = batch.item.spec
      product["item.unit"] = batch.item.unit
      product["item.description"] = batch.item.description
      


      def reports=[]


      def batchReportDets = BatchReportDet.findAllByBatch(batch)

      def domainReports = batchReportDets.reportParams*.report.unique()
      domainReports.each(){ report ->

        def reportMap = [:]
        reportMap.title = report.title
        reportMap.reportType = report.reportType

        reportMap.params=[]

        if(reportMap.reportType == ReportType.NUTRITION){
          batchReportDets.each(){ batchReportDet ->
            if(batchReportDet.reportParams.report == report){
              def param = [:]

              param["param.name"] = batchReportDet.reportParams.param.name
              param["param.title"] = batchReportDet.reportParams.param.title
              param["param.description"] = batchReportDet.reportParams.param.description
              param["param.unit"] = batchReportDet.reportParams.param.unit
              param["batchReportDet.value"] = batchReportDet.value
              param["batchReportDet.value"] = batchReportDet.value
            }
          }        
        }

        else if(reportMap.reportType == ReportType.INSPECT){
          batchReportDets.each(){ batchReportDet ->
            if(batchReportDet.reportParams.report == report){
              def param = [:]

              param["param.name"] = batchReportDet.reportParams.param.name
              param["param.title"] = batchReportDet.reportParams.param.title
              param["param.defaultValue"] = batchReportDet.reportParams.param.defaultValue
              param["param.description"] = batchReportDet.reportParams.param.description
              param["param.lower"] = batchReportDet.reportParams.param.lower
              param["param.upper"] = batchReportDet.reportParams.param.upper
              param["param.unit"] = batchReportDet.reportParams.param.unit
              param["batchReportDet.value"] = batchReportDet.value


              reportMap.params << param

            }
          }
        }

        else{
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
        }

        reports << reportMap

      }

      // 生產履歷

      def batchRouteReportMap = [:]
      batchRouteReportMap.title = "生產履歷"

      batchRouteReportMap.params=[]



      batch.batchRoutes.each(){ batchRoute ->
        def param = [:]
        param["batchRoute.id"] = batchRoute.id
        param["batchRoute.sequence"] = batchRoute.sequence

        param["operation.name"] = batchRoute.operation.name
        param["operation.title"] = batchRoute.operation.title

        param["workstation.name"] = batchRoute.workstation.name
        param["workstation.title"] = batchRoute.workstation.title

        param["batchRoute.startDate"] = batchRoute.startDate
        param["batchRoute.endDate"] = batchRoute.endDate
        param["default.image"] = "/attachment/show/${batchRoute.id}?domainName=batchRoute"

        batchRouteReportMap.params << param

      }

      reports << batchRouteReportMap


      def batchSourceReportMap = [:]
      batchSourceReportMap.title = "原料履歷"
      batchSourceReportMap.params=[]

      log.info "!!!!!!!!!"
      log.info batch.batchSources

      def batchSourcesFinal=batchAnalyzeService.backwardTraceToFinal(batch).batchSourcesFinal
      batchSourcesFinal.each(){ childBatch ->
      //batch.batchSources.each(){ batchSource ->
        def param = [:]
        param["batch.name"] = childBatch.name
        param["item.name"] = childBatch.item.name
        param["item.title"] = childBatch.item.title
        param["item.spec"] = childBatch.item.spec
        param["batch.country"] = childBatch.country
        param["item.description"] = childBatch.item.description
        // param["batch.name"] = batchSource.childBatch.name
        // param["item.name"] = batchSource.childBatch.item.name
        // param["item.title"] = batchSource.childBatch.item.title
        // param["item.spec"] = batchSource.childBatch.item.spec
        // param["batch.country"] = batchSource.childBatch.country
        // param["item.description"] = batchSource.childBatch.item.description

        batchSourceReportMap.params << param

      }

      reports << batchSourceReportMap

      render (view:'index' ,model:[product: product, reports:reports]) 




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

        Item.list().each{
            println it.name+"/"+it.title
        }

        //匯入批號
        records.batch.each{ batch ->
            batch.site=Site.findByName(batch.site.name)
            println batch.item.name
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
