package foodprint

import org.springframework.dao.DataIntegrityViolationException
import grails.converters.JSON
import org.apache.commons.lang.exception.ExceptionUtils
import grails.converters.XML

import grails.transaction.Transactional

@Transactional(readOnly = true)

class BatchController {

    static allowedMethods = [create:"POST",update: "POST",  delete: "POST"]

    def domainService

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [batchInstanceList: Batch.list(params), batchInstanceTotal: Batch.count()]
    }

    def index(Integer max) {
        JSON.use('deep')
        def converter = list() as JSON
        converter.render(response)
    }

    def listXml(Integer max) {

        def converter = list() as XML
        converter.render(response)

    }

    @Transactional
    def create(){

        def batchInstance=new Batch(params)
        render (contentType: 'text/json') {
            domainService.save(batchInstance)
        }
    }

    @Transactional
    def update(){

        def batchInstance=Batch.get(params.id)
        render (contentType: 'text/json') {
            domainService.save(batchInstance, params)
        }
    }

    @Transactional
    def delete(Batch batchInstance){
        
        render (contentType: 'text/json') {
            domainService.delete(batchInstance)
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
        redirect(controller: "ItemRoute",action: "listJson" ,params:["item.id":Batch.get(params.id).item.id])
        //old version
        // JSON.use('deep')
        // def converter= [itemRouteList:Batch.get(params.id).item.itemRoutes.collect()] as JSON
        // converter.render(response)
    }

    def show(Long id) {
        def batchInstance = Batch.get(id)
        if (!batchInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'batch.label', default: 'Batch'), id])
            redirect(action: "list")
        }

        render (contentType: 'text/json') {
            [batchInstanceList:batchInstance]
        }
    }

    def edit(Long id) {
        def batchInstance = Batch.get(id)
        if (!batchInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'batch.label', default: 'Batch'), id])
            redirect(action: "list")
            return
        }

        [batchInstance: batchInstance]
    }
}
