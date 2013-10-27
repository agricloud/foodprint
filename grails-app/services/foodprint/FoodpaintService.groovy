package foodprint

import grails.plugins.rest.client.RestBuilder
import org.springframework.http.converter.StringHttpMessageConverter
import java.nio.charset.Charset
import grails.converters.*

class FoodpaintService {

    final static String __FOODPAINT_SERVICE_SERVER_URL = "http://localhost:8180"
    final static String __FOODPAINT_SERVICE_API_URL = "http://localhost:8180/api"

    /**
     * Ping to /api/ping to check service available
     */
    boolean ping() {

        boolean result = false

        log.info "PING: foodpaint service"

        try {
            withHttp(uri: __FOODPAINT_SERVICE_SERVER_URL) {
                def html = get(path : '/api/ping', query : [version: '1.0'])

                log.debug html
                result = true
            }

        }
        catch (e) {
            log.info "PING: service not exists"
            log.error e.message
        }

        return result
    }

    /**
     * Request data from /api/queryBatchReport
     */
    def doDataImport() {

        if (!ping()) {
            return
        }

        def rest = new RestBuilder()
        rest.restTemplate.setMessageConverters([new StringHttpMessageConverter(Charset.forName("UTF-8"))])

        def url = "${__FOODPAINT_SERVICE_API_URL}/queryBatchReport"
        
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
