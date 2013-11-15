package foodprint

import org.springframework.dao.DataIntegrityViolationException
import org.apache.commons.lang.exception.ExceptionUtils
import grails.converters.JSON

class BatchController {

    def domainService


    def index() {

        def list = Batch.createCriteria().list(params,params.criteria)

        // def listJson =  JSON.parse((list as JSON).toString())

        // list.each{
        //     batch ->
        //     batch["item.id"] = batch.item.id
        // }          

        render (contentType: 'application/json') {
            [batchInstanceList: list, batchInstanceTotal: list.totalCount]
        }
    }

    def typeahead() {

        def list = Batch.createCriteria().list(params,{
                 like('name',params.query+"%")
        })


        def typeaheadList = []

        list.each{ batch ->
            def typeahead = [:]
            typeahead.value = batch.name
            typeahead.itemName = batch.item.name
            typeahead.itemTitle = batch.item.title
            typeahead.itemDescription = batch.item.description
            typeaheadList << typeahead
        }          

        render (contentType: 'application/json') {
            typeaheadList
        }
    }

    def show(Long id){
        //找出指定批號。
        /*
        * [Deep properties]
        *
        * batch::
        *   -item
        *   -country
        *   -supplier
        */

        log.info "${controllerName}-${actionName}"

        def batch=Batch.findById(id);

        if(batch){   

            def batchJson =  JSON.parse((batch as JSON).toString())            
            // batchJson["item.id"] = batch.item.id
            // batchJson["item.name"] = batch.item.name
            // batchJson["item.title"] = batch.item.title
            // batchJson["item.spec"] = batch.item.spec
            // batchJson["item.unit"] = batch.item.unit
            // batchJson["item.description"] = batch.item.description
            if(batch.supplier){
                batchJson["supplier.id"] = batch.supplier.id
                batchJson["supplier.name"] = batch.supplier.name
                batchJson["supplier.title"] = batch.supplier.title
            }
            if(batch.country){
                batchJson["country"] = batch.country.name()
            }

            render (contentType: 'application/json') {
                [success: true, data:batchJson]
            }
        }else {
            render (contentType: 'application/json') {
                [success: false, message:message(code: 'default.message.show.failed')]
            }          
        }
    }

    def create(){

        def batch=new Batch()   
        def batchJson =  JSON.parse((batch as JSON).toString())  
        batchJson["country"] = batch.country.name()     
        render (contentType: 'application/json') {
            [success: true,data:batchJson]
        }
    }
 

    def save(){

        def batch=new Batch(params)
        
        render (contentType: 'application/json') {
            domainService.save(batch)
        }
    }

    def update(){

        def batchInstance = Batch.findById(params.id)
        batchInstance.properties=params
        render (contentType: 'application/json') {
            domainService.save(batchInstance)
        }
    }

    def delete(){
        def batchInstance = Batch.findById(params.id)
        def result
        try {
            
            result = domainService.delete(batchInstance)
        
        }catch(e){
            log.error e
            def msg = message(code: 'default.message.delete.failed', args: [batchInstance, e.getMessage()])
            result = [success:false, message: msg] 
        }
        
        render (contentType: 'application/json') {
            result
        }
    }


    /**
    * 此方法已不使用 僅供參考寫法
    * @param batch.id
    * 取得Batch對應之Item的ItemRoute
    * 可直接呼叫ItemRoute中已有的listJson方法
    * 由於ItemRoute篩選的params為item.id
    * 而Batch接收的params為batch.id
    * 因此需method中找出item.id才可call ItemRoute.listJson
    * 使用redirect重新導向ItemRoute較為精簡
    * 也可避免params中屬性命名相同等衝突
    */
    def itemRouteList(){
        log.debug "BatchController--itemRouteList"
        redirect(controller: "ItemRoute",action: "index" ,params:["item.id":Batch.get(params.id).item.id])
    }

}
