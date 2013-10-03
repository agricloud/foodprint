package foodprint

import org.springframework.dao.DataIntegrityViolationException


class ItemController {

    def domainService

    def index(Integer max) {

        render (contentType: 'application/json') {
            [itemInstanceList: Item.list(params), itemInstanceTotal: Item.count()]
        }
        
    }

 

    def create(){

        def itemInstance=new Item(params)
        
        render (contentType: 'application/json') {
            domainService.save(itemInstance)
        }
    }


    def update(){
        def  itemInstance = Item.findById(params.id)
        itemInstance.properties = params
        render (contentType: 'application/json') {
            domainService.save(itemInstance)
        }         
    }



    def delete(){
        
        def  itemInstance = Item.findById(params.id)

        def result
        try {
            
            result = domainService.delete(itemInstance)
        
        }catch(e){
            log.error e
            def msg = message(code: 'default.message.delete.failed', args: [itemInstance, e])
            result = [success:false, message: msg] 
        }
        
        render (contentType: 'application/json') {
            result
        }
    }
}
