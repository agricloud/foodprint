package foodprint

import grails.plugins.rest.client.RestBuilder
import org.springframework.http.converter.StringHttpMessageConverter
import java.nio.charset.Charset
import grails.converters.*

class FoodpaintService {

    def grailsApplication

    /**
     * Ping to /api/ping to check service available
     */
    boolean ping() {

        boolean result = false

        println "PING: foodpaint service ${grailsApplication.config.grails.foodpaint.service.api.url}"

        try {
            withHttp(uri: grailsApplication.config.grails.foodpaint.service.api.url+"/ping") {
                def html = get(query : [version: '1.0'])

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

        def url = "${grailsApplication.config.grails.foodpaint.service.api.url}/queryBatchReport"
        def resp = rest.get(url)


        //進行資料匯入
        importData(resp.text)

        return [pass:"pass"]
    }

    private importData(jsonString){
        log.debug "ReportViewerController--importData"

        def records=JSON.parse(jsonString)

        println "records.item "+records.item.size()
        //匯入品項
        records.item.each{ item ->
            item.site = Site.findByName(item.site.name)
            item.lastUpdated = null
            item.dateCreated = null
            new Item(item).save(flush: true, failOnError:true)
        }

        log.debug "品項清單："
        Item.list().each{
            log.debug it.name+"/"+it.title
        }

        //匯入批號
        records.batch.each{ batch ->
            batch.site=Site.findByName(batch.site.name)
            batch.item=Item.findByName(batch.item.name)
            batch.lastUpdated = null
            batch.dateCreated = null
            new Batch(batch).save(flush: true, failOnError:true)
        }
        //匯入批號關聯
        records.batchSources.each{ batchSource ->
            batchSource.batch=Batch.findByName(batchSource.batch.name)
            batchSource.childBatch=Batch.findByName(batchSource.childBatch.name)
            batchSource.lastUpdated = null
            batchSource.dateCreated = null
            new BatchSource(batchSource).save(flush: true, failOnError:true)
        }
    }
}
