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

        // if (!ping()) {
        //     return
        // }

        def rest = new RestBuilder()
        rest.restTemplate.setMessageConverters([new StringHttpMessageConverter(Charset.forName("UTF-8"))])

        def url = "${grailsApplication.config.grails.foodpaint.service.api.url}/queryBatchReport"
        url = "http://localhost:8180/api/queryBatchReport"
        def resp = rest.get(url)


        //進行資料匯入
        importData(resp.text)

        return [pass:"pass"]
    }

    private importData(jsonString){
        log.debug "FoodpaintService--importData"

        def records=JSON.parse(jsonString)

        def importClassList = [
                'item',
                'workstation',
                'operation',
                'supplier',
                'customer',
                'batch',
                'batchSource'
            ]

        
        importClassList.each{

            println "records.${it}資料筆數 == "+records[it].size()

            records[it].each{ object ->

                println object as JSON

                object = processDefaultTable(object)

                def domain

                if(it == "item")
                    domain=getItemInstance(object)
                if(it == "workstation")
                    domain=getWorkstationInstance(object)
                if(it == "operation")
                    domain=getOperationInstance(object)
                if(it == "supplier")
                    domain=getSupplierInstance(object)
                if(it == "customer")
                    domain=getCustomerInstance(object)
                if(it == "batch"){
                    object.item = Item.findByName(object.item.name)
                    object.supplier = Supplier.findByName(object.supplier.name)
                    domain=getBatchInstance(object)
                }
                if(it == "batchSource"){
                    object.batch = Batch.findByName(object.batch.name)
                    object.childBatch = Batch.findByName(object.childBatch.name)
                    domain=getBatchSourceInstance(object)
                }

                domain.properties = object
                domain.save(flush: true, failOnError:true) 
            }

        }//end importClassList

        log.debug "品項清單："
        Item.list().each{
            log.debug it.name+"/"+it.title
        }

    }
    def private getItemInstance(object){

        def domain = Item.findByName(object.name)
        if(!domain){
            domain = new Item(name:object.name)
        }
        domain
    }

    def private getWorkstationInstance(object){

        def domain = Workstation.findByName(object.name)
        if(!domain){
            domain = new Workstation(name:object.name)
        }    
        domain
    }

    def private getOperationInstance(object){

        def domain = Operation.findByName(object.name)
        if(!domain){
            domain = new Operation(name:object.name)
        }    
        domain
    }

    def private getSupplierInstance(object){

        def domain = Supplier.findByName(object.name)
        if(!domain){
            domain = new Supplier(name:object.name)
        }   
        domain
    }

    def private getCustomerInstance(object){

        def domain = Customer.findByName(object.name)
        if(!domain){
            domain = new Customer(name:object.name)
        }    
        domain
    }

    def private getBatchInstance(object){

        def domain = Batch.findByName(object.name)
        if(!domain){
            domain = new Batch(name:object.name)
        }    
        domain
    }

    def private getBatchSourceInstance(object){

        def domain = BatchSource.findByBatchAndChildBatch(object.batch,object.childBatch)
        if(!domain){
            domain = new BatchSource(batch:object.batch,childBatch:object.childBatch)
        }
        domain
    }

    def private processDefaultTable(object){
        object.site = Site.findByName(object.site.name)

        object.lastUpdated = Date.parse("yyyy-MM-dd'T'HH:mm:ss'Z'",object.lastUpdated)//Date.parse('yyyyMMdd',record.incomingDate.text())
        object.dateCreated = Date.parse("yyyy-MM-dd'T'HH:mm:ss'Z'",object.dateCreated)

        object
    }

    /*

        log.debug "records.item =="+records.item.size()
        log.debug "records.workstation =="+records.workstation.size()

        //匯入品項
        records.item.each{ object ->
            object.site = Site.findByName(object.site.name)

            object.lastUpdated = Date.parse("yyyy-MM-dd'T'HH:mm:ss'Z'",object.lastUpdated)//Date.parse('yyyyMMdd',record.incomingDate.text())
            object.dateCreated = Date.parse("yyyy-MM-dd'T'HH:mm:ss'Z'",object.dateCreated)

            def domain = Item.findByName(object.name)
            if(!domain){
                domain = new Item(name:object.name)
            }    
            
            domain.properties = object
            domain.save(flush: true, failOnError:true)
        }

        log.debug "品項清單："
        Item.list().each{
            log.debug it.name+"/"+it.title
        }

        records.workstation.each{ object ->
            object.site = Site.findByName(object.site.name)
            def domain = Workstation.findByName(object.name)
            if(!domain){
                domain = new Workstation(name:object.name)
            }    
            
            domain.properties = object
            domain.save(flush: true, failOnError:true)
        }

        records.operation.each{ object ->
            object.site = Site.findByName(object.site.name)
            def domain = Operation.findByName(object.name)
            if(!domain){
                domain = new Operation(name:object.name)
            }    
            
            domain.properties = object
            domain.save(flush: true, failOnError:true)
        }

        records.supplier.each{ object ->
            object.site = Site.findByName(object.site.name)
            def domain = Supplier.findByName(object.name)
            if(!domain){
                domain = new Supplier(name:object.name)
            }    
            
            domain.properties = object
            domain.save(flush: true, failOnError:true)
        }

        records.customer.each{ object ->
            object.site = Site.findByName(object.site.name)
            def domain = Customer.findByName(object.name)
            if(!domain){
                domain = new Customer(name:object.name)
            }    
            
            domain.properties = object
            domain.save(flush: true, failOnError:true)
        }

        //匯入批號
        records.batch.each{ object ->
            object.site = Site.findByName(object.site.name)
            object.item = Item.findByName(object.item.name)
            
            def domain = Batch.findByName(object.name)
            if(!domain){
                domain = new Batch(name:object.name)
            }    
            
            domain.properties = object
            domain.save(flush: true, failOnError:true)
        }
        //匯入批號關聯
        records.batchSource.each{ object ->
            object.batch = Batch.findByName(object.batch.name)
            object.childBatch = Batch.findByName(object.childBatch.name)

            def domain = BatchSource.findByBatchAndChildBatch(object.batch,object.childBatch)

            if(!domain){
                domain = new BatchSource(batch:object.batch,childBatch:object.childBatch)
            }    
            
            domain.properties = object
            domain.save(flush: true, failOnError:true)
        }

    */
}
