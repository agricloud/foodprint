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

    def querySheetByBatch(String batchName){
        if (!ping()) {
            return
        }

        def rest = new RestBuilder()
        rest.restTemplate.setMessageConverters([new StringHttpMessageConverter(Charset.forName("UTF-8"))])

        def url = "${grailsApplication.config.grails.foodpaint.service.api.url}/querySheetByBatch/?batchName="+batchName
        def resp = rest.get(url)

        JSON.parse(resp.text)        

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


        log.debug resp.text
        //進行資料匯入
        importData(resp.text)

        return [pass:"pass"]
    }

    def protected importData(jsonString){
        log.debug "FoodpaintService--importData"

        def records=JSON.parse(jsonString)

        def importClassList = [
                'item',
                'workstation',
                'operation',
                'supplier',
                'customer',
                'batch',
                'batchRoute',
                'batchSource'
            ]

        
        importClassList.each{ importClass ->

            records[importClass].each{ domainJson ->
                def domain = getDomainIntance(importClass, domainJson)
                domain.properties = domainJson
                domain.save(flush: true, failOnError:true) 
            }

        }




        log.debug "品項清單："
        Item.list().each{
            log.debug it.name+"/"+it.title
        }

    }

    def private processDefaultTable(domainJson){
        domainJson.site = Site.findByName(domainJson.site.name)
        domainJson.lastUpdated = Date.parse("yyyy-MM-dd'T'HH:mm:ss'Z'",domainJson.lastUpdated)
        domainJson.dateCreated = Date.parse("yyyy-MM-dd'T'HH:mm:ss'Z'",domainJson.dateCreated)

        domainJson
    }

    def private getDomainIntance(className, domainJson){
        def domain

        domainJson = processDefaultTable(domainJson)

        if(className == "item")
            domain=getItemInstance(domainJson)
        if(className == "workstation")
            domain=getWorkstationInstance(domainJson)
        if(className == "operation")
            domain=getOperationInstance(domainJson)
        if(className == "supplier")
            domain=getSupplierInstance(domainJson)
        if(className == "customer")
            domain=getCustomerInstance(domainJson)
        if(className == "batch"){
            domain=getBatchInstance(domainJson)
        }
        if(className == "batchRoute"){
            domain=getBatchRouteInstance(domainJson)
        }
        if(className == "batchSource"){
            domain=getBatchSourceInstance(domainJson)
        }

        domain
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

        println object.item.name
        domain.item = Item.findByName(object.item.name)
        domain.supplier = Supplier.findByName(object.supplier.name)

        domain
    }

    def private getBatchRouteInstance(object){

        def domain = BatchRoute.findByBatchAndSequence(object.batch,object.sequence)
        if(!domain){
            domain = new BatchRoute(batch:object.batch,sequence:object.sequence)
        }    

        domain.batch = Batch.findByName(object.batch.name)
        domain.operation = Operation.findByName(object.operation.name)
        domain.workstation = Workstation.findByName(object.workstation.name)
        domain.supplier = Supplier.findByName(object.supplier.name)
        domain.startDate = Date.parse("yyyy-MM-dd'T'HH:mm:ss'Z'",object.startDate)
        domain.endDate = Date.parse("yyyy-MM-dd'T'HH:mm:ss'Z'",object.endDate)

        domain
    }

    def private getBatchSourceInstance(object){

        def domain = BatchSource.findByBatchAndChildBatch(object.batch,object.childBatch)
        if(!domain){
            domain = new BatchSource(batch:object.batch,childBatch:object.childBatch)
        }

        domain.batch = Batch.findByName(object.batch.name)
        domain.childBatch = Batch.findByName(object.childBatch.name)
        
        domain
    }



}
