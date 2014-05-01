package foodprint

import grails.plugins.rest.client.RestBuilder
import org.springframework.http.converter.StringHttpMessageConverter
import java.nio.charset.Charset
import grails.converters.*

class FoodpaintService {

    def grailsApplication
    def dateService

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

    //逆溯
    def querySaleSheetDetByBatch(String batchName){
        def url = "${grailsApplication.config.grails.foodpaint.service.api.url}/querySaleSheetDetByBatch/?batchName="+batchName
        doCallFoodpaint(url)
    }

    def queryStockInSheetDetByBatch(String batchName){
        def url = "${grailsApplication.config.grails.foodpaint.service.api.url}/queryStockInSheetDetByBatch/?batchName="+batchName
        doCallFoodpaint(url)
    }

    def queryOutSrcPurchaseSheetDetByBatch(String batchName){
        def url = "${grailsApplication.config.grails.foodpaint.service.api.url}/queryOutSrcPurchaseSheetDetByBatch/?batchName="+batchName
        doCallFoodpaint(url)
    }

    def queryPurchaseSheetDetByBatch(String batchName){
        def url = "${grailsApplication.config.grails.foodpaint.service.api.url}/queryPurchaseSheetDetByBatch/?batchName="+batchName
        doCallFoodpaint(url)
    }

    def queryManufactureOrderByBatch(String batchName){
        def url = "${grailsApplication.config.grails.foodpaint.service.api.url}/queryManufactureOrderByBatch/?batchName="+batchName
        doCallFoodpaint(url)
    }

    def queryManufactureOrderFromStockInSheetDetByBatch(String batchName){
        def url = "${grailsApplication.config.grails.foodpaint.service.api.url}/queryManufactureOrderFromStockInSheetDetByBatch/?batchName="+batchName
        doCallFoodpaint(url)
    }

    def queryManufactureOrderFromOutSrcPurchaseSheetDetByBatch(String batchName){
        def url = "${grailsApplication.config.grails.foodpaint.service.api.url}/queryManufactureOrderFromOutSrcPurchaseSheetDetByBatch/?batchName="+batchName
        doCallFoodpaint(url)
    }

    def querySupplierFromPurchaseSheetDetByBatch(String batchName){
        def url = "${grailsApplication.config.grails.foodpaint.service.api.url}/querySupplierFromPurchaseSheetDetByBatch/?batchName="+batchName
        doCallFoodpaint(url)
    }

    def queryPurchaseSheetDetBySupplierAndBatch(String supplierName, String batchName){
        def url = "${grailsApplication.config.grails.foodpaint.service.api.url}/queryPurchaseSheetDetBySupplierAndBatch/?supplierName=${supplierName}&batchName=${batchName}"
        doCallFoodpaint(url)
    }

    def queryBatchFromMaterialSheetDetByManufactureOrder(String typeName, String name){
        def url = "${grailsApplication.config.grails.foodpaint.service.api.url}/queryBatchFromMaterialSheetDetByManufactureOrder/?typeName=${typeName}&name=${name}"
        doCallFoodpaint(url)
    }

    //順溯
    def queryManufactureOrderFromMaterialSheetDetByBatch(String batchName){
        def url = "${grailsApplication.config.grails.foodpaint.service.api.url}/queryManufactureOrderFromMaterialSheetDetByBatch/?batchName="+batchName
        doCallFoodpaint(url)
    }

    def queryCustomerFromSaleSheetDetByBatch(String batchName){
        def url = "${grailsApplication.config.grails.foodpaint.service.api.url}/queryCustomerFromSaleSheetDetByBatch/?batchName="+batchName
        doCallFoodpaint(url)
    }

    def queryInventoryByBatchAndGroupByWarehouse(String batchName){
        def url = "${grailsApplication.config.grails.foodpaint.service.api.url}/queryInventoryByBatchAndGroupByWarehouse/?batchName=${batchName}"
        doCallFoodpaint(url)
    }

    def querySaleSheetDetByCustomerAndBatch(String customerName, String batchName){
        def url = "${grailsApplication.config.grails.foodpaint.service.api.url}/querySaleSheetDetByCustomerAndBatch/?customerName=${customerName}&batchName=${batchName}"
        doCallFoodpaint(url)
    }

    def queryBatchFormStockInSheetDetByManufactureOrder(String typeName, String name){
        def url = "${grailsApplication.config.grails.foodpaint.service.api.url}/queryBatchFormStockInSheetDetByManufactureOrder/?typeName=${typeName}&name=${name}"
        doCallFoodpaint(url)
    }

    def queryBatchFormOutSrcPurchaseSheetDetByManufactureOrder(String typeName, String name){
        def url = "${grailsApplication.config.grails.foodpaint.service.api.url}/queryBatchFormOutSrcPurchaseSheetDetByManufactureOrder/?typeName=${typeName}&name=${name}"
        doCallFoodpaint(url)
    }

    def doCallFoodpaint(url){
        if (!ping()) {
            return
        }

        def rest = new RestBuilder()
        rest.restTemplate.setMessageConverters([new StringHttpMessageConverter(Charset.forName("UTF-8"))])

        def resp = rest.get(url)
        JSON.parse(resp.text)
    }
    

    /**
     * Request data from Foodpaint /api/exportData
     */
    def doDataImport() {

        if (!ping()) {
            return
        }

        def rest = new RestBuilder()
        rest.restTemplate.setMessageConverters([new StringHttpMessageConverter(Charset.forName("UTF-8"))])

        def url = "${grailsApplication.config.grails.foodpaint.service.api.url}/exportData"
        def resp = rest.get(url)


        log.debug resp.text
        //進行資料匯入
        importData(resp.text)

        println "data import finished!"
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
            def className = importClass[0].toUpperCase() + importClass[1..-1]

            def domainClass = grailsApplication.getDomainClass("foodprint."+className)
    
            if(domainClass){
                def fields =domainClass.persistentProperties.collect { it.name }
                records[importClass].each{ domainJson ->
                    def domain = getDomainIntance(importClass, domainJson)
                    domain.save(flush: true, failOnError:true) 
                }
            }

        }

        log.debug "品項清單："
        Item.list().each{
            log.debug it.name+"/"+it.title
        }

    }


    def private processDefaultTable(domainJson){
        domainJson.site = Site.findByName(domainJson.site.name)
        domainJson.lastUpdated = dateService.parseToUTC("yyyy-MM-dd'T'HH:mm:ss",domainJson.lastUpdated)
        domainJson.dateCreated = dateService.parseToUTC("yyyy-MM-dd'T'HH:mm:ss",domainJson.dateCreated)
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
        if(object.site)
        domain.site=object.site
        domain.editor=object.editor
        domain.creator=object.creator
        domain.dateCreated=object.dateCreated
        domain.lastUpdated=object.lastUpdated
        domain.title=object.title
        domain.description=object.description
        domain.spec=object.spec
        domain.unit=object.unit

        domain
    }

    def private getWorkstationInstance(object){

        def domain = Workstation.findByName(object.name)
        if(!domain){
            domain = new Workstation(name:object.name)
        }
        domain.site=object.site
        domain.editor=object.editor
        domain.creator=object.creator
        domain.dateCreated=object.dateCreated
        domain.lastUpdated=object.lastUpdated
        domain.title=object.title
        domain.description=object.description

        domain
    }

    def private getOperationInstance(object){

        def domain = Operation.findByName(object.name)
        if(!domain){
            domain = new Operation(name:object.name)
        }
        domain.site=object.site
        domain.editor=object.editor
        domain.creator=object.creator
        domain.dateCreated=object.dateCreated
        domain.lastUpdated=object.lastUpdated
        domain.title=object.title
        domain.description=object.description

        domain
    }

    def private getSupplierInstance(object){

        def domain = Supplier.findByName(object.name)
        if(!domain){
            domain = new Supplier(name:object.name)
        }
        domain.site=object.site
        domain.editor=object.editor
        domain.creator=object.creator
        domain.dateCreated=object.dateCreated
        domain.lastUpdated=object.lastUpdated
        domain.title=object.title
        domain.country=object.country
        domain.tel=object.tel
        domain.email=object.email
        domain.address=object.address

        domain
    }

    def private getCustomerInstance(object){

        def domain = Customer.findByName(object.name)
        if(!domain){
            domain = new Customer(name:object.name)
        }
        domain.site=object.site
        domain.editor=object.editor
        domain.creator=object.creator
        domain.dateCreated=object.dateCreated
        domain.lastUpdated=object.lastUpdated
        domain.title=object.title
        domain.email=object.email
        domain.address=object.address

        domain
    }

    def private getBatchInstance(object){

        def domain = Batch.findByName(object.name)
        if(!domain){
            domain = new Batch(name:object.name)
        }

        domain.site=object.site
        domain.editor=object.editor
        domain.creator=object.creator
        domain.dateCreated=object.dateCreated
        domain.lastUpdated=object.lastUpdated
        domain.item = Item.findByName(object.item.name)
        domain.supplier = Supplier.findByName(object.supplier.name)
        domain.expectQty = object.expectQty
        domain.batchType = object.batchType
        domain.country = object.country

        log.debug "匯入前domain::batch-manufactureDate"+domain.manufactureDate
        log.debug "傳入資料foodpaint::batch-manufactureDate"+object.manufactureDate
        if(object.manufactureDate && object.manufactureDate!=null){
            domain.manufactureDate = dateService.parseToUTC("yyyy-MM-dd'T'HH:mm:ss",object.manufactureDate)
        }
        log.debug "預計匯入domain::batch-manufactureDate"+domain.manufactureDate
        domain
    }

    def private getBatchRouteInstance(object){

        def batch = Batch.findByName(object.batch.name)

        def domain = BatchRoute.findByBatchAndSequence(batch,object.sequence)
        if(!domain){
            domain = new BatchRoute(batch:batch,sequence:object.sequence)
        }    
        domain.site=object.site
        domain.editor=object.editor
        domain.creator=object.creator
        domain.dateCreated=object.dateCreated
        domain.lastUpdated=object.lastUpdated
        domain.operation = Operation.findByName(object.operation.name)
        domain.workstation = Workstation.findByName(object.workstation.name)
        domain.supplier = Supplier.findByName(object.supplier.name)
        log.debug "目前不匯入製程日期"
        log.debug "匯入前domain::batchRoute-date"+domain.startDate+"/"+domain.endDate
        log.debug "傳入資料foodpaint::batchRoute-date"+object.startDate+"/"+object.endDate
        
        //製程日期以print為主 若print無資料記錄 才匯入paint段
        if(object.startDate && object.startDate!=null && (!domain.startDate||domain.startDate==null))
            domain.startDate = dateService.parseToUTC("yyyy-MM-dd'T'HH:mm:ss",object.startDate)
        if(object.endDate && object.endDate!=null && (!domain.startDate||domain.startDate==null))
            domain.endDate = dateService.parseToUTC("yyyy-MM-dd'T'HH:mm:ss",object.endDate)

        log.debug "預計匯入domain::batchRoute-date"+domain.startDate+"/"+domain.endDate

        domain
    }

    def private getBatchSourceInstance(object){

        //println "建立batchSource"+object.batch.name+"//"+object.childBatch.name
        def batch = Batch.findByName(object.batch.name)
        def childBatch = Batch.findByName(object.childBatch.name)

        def domain = BatchSource.findByBatchAndChildBatch(batch,childBatch)
        if(!domain){
            domain = new BatchSource(batch:batch,childBatch:childBatch)
        }
        domain.site=object.site
        domain.editor=object.editor
        domain.creator=object.creator
        domain.dateCreated=object.dateCreated
        domain.lastUpdated=object.lastUpdated
        
        domain
    }



}
