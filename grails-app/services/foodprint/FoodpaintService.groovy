package foodprint
import grails.plugins.rest.client.RestBuilder
import org.springframework.http.converter.StringHttpMessageConverter
import java.nio.charset.Charset
import grails.converters.*
class FoodpaintService {

    def doDataImport(){
        def rest = new RestBuilder()
        rest.restTemplate.setMessageConverters([new StringHttpMessageConverter(Charset.forName("UTF-8"))])
        def url = "http://localhost:8180/api/queryBatchReport"
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
