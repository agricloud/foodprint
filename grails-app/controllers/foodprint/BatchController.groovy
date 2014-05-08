package foodprint

import org.springframework.dao.DataIntegrityViolationException

class BatchController {

    def domainService


    def index = {

        def list = Batch.createCriteria().list(params,params.criteria)        

        render (contentType: 'application/json') {
            [data: list, total: list.totalCount]
        }
    }

    def indexByItem = {
        def item = Item.get(params.item.id)
        def list = Batch.findAllByItem(item)     

        render (contentType: 'application/json') {
            [data: list, total: list.size()]
        }
    }

    def typeahead = {

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

    def show = {
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

        def batch=Batch.get(params.id);

        if(batch){   

            render (contentType: 'application/json') {
                [success: true, data:batch]
            }
        }else {
            render (contentType: 'application/json') {
                [success: false, message:message(code: 'default.message.show.failed')]
            }          
        }
    }

    def create = {

        def batch=new Batch()   
        render (contentType: 'application/json') {
            [success: true,data:batch]
        }
    }
 

    def save = {

        def batch=new Batch(params)
        
        render (contentType: 'application/json') {
            domainService.save(batch)
        }
    }

    def update = {

        
        def batchInstance = Batch.get(params.id)
        batchInstance.properties=params
        
        render (contentType: 'application/json') {
            domainService.save(batchInstance)
        }
    }

    def delete = {
        def batchInstance = Batch.get(params.id)
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
    def itemRouteList = {
        log.debug "BatchController--itemRouteList"
        redirect(controller: "ItemRoute",action: "index" ,params:["item.id":Batch.get(params.id).item.id])
    }

}
