package foodprint

import org.springframework.dao.DataIntegrityViolationException
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ItemController {

    def domainService

    def index(Integer max) {

        render (contentType: 'text/json') {
            [itemInstanceList: Item.list(params), itemInstanceTotal: Item.count()]
        }
        
    }

 
    @Transactional
    def create(){

        def itemInstance=new Item(params)
        
        render (contentType: 'text/json') {
            domainService.save(itemInstance)
        }
    }

    @Transactional
    def update(Item itemInstance){

        render (contentType: 'text/json') {
            domainService.save(itemInstance)
        }         
    }


    @Transactional
    def delete(Item itemInstance){
        
        def result
        try {
            
            result = domainService.delete(itemInstance)
        
        }catch(e){
            log.error e
            def msg = message(code: 'default.message.delete.failed', args: [itemInstance, e])
            result = [success:false, message: msg] 
        }
        
        render (contentType: 'text/json') {
            result
        }
    }
}
