package foodprint

import org.springframework.dao.DataIntegrityViolationException
import grails.converters.JSON
import org.apache.commons.lang.exception.ExceptionUtils

class BatchController {

    static allowedMethods = [create:"POST",update: "POST",  delete: "POST",  show: "get"]

    def domainService


    def index() {
        JSON.use('deep')
        def converter = [batchInstanceList: Batch.list(params), batchInstanceTotal: Batch.count()] as JSON
        converter.render(response)
    }

    def show(Long id){
        def batchInstance = Batch.get(id)
        if (!batchInstance) {
            flash.message = message(code: 'default.message.notfound', args: [message(code: 'batch.label', default: 'Batch'), id])
        }
        else{
            flash.message = message(code: 'default.message.hasfound', args: [message(code: 'batch.label'), id])
        }
        render (contentType: 'text/json') {
            [batchInstanceList:batchInstance, message:flash.message]
        }
    }

    def create(){

        def batchInstance=new Batch(params)
        render (contentType: 'text/json') {
            domainService.save(batchInstance)
        }
    }

    def update(){
        def batchInstance = Batch.findById(params.id)
        batchInstance.properties=params
        render (contentType: 'text/json') {
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
            def msg = message(code: 'default.message.delete.failed', args: [batchInstance, e])
            result = [success:false, message: msg] 
        }
        
        render (contentType: 'text/json') {
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
        //old version
        // JSON.use('deep')
        // def converter= [itemRouteList:Batch.get(params.id).item.itemRoutes.collect()] as JSON
        // converter.render(response)
    }

}
