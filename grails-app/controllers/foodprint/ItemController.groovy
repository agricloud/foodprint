package foodprint


import org.springframework.dao.DataIntegrityViolationException

class ItemController {

    def domainService

    def index = {
        def list = Item.createCriteria().list(params,params.criteria)
        render (contentType: 'application/json') {
            [itemInstanceList: list, itemInstanceTotal: list.totalCount]
        }
    }

    def show = {
        def itemInstance=Item.findById(params.id);  
        if(itemInstance){   
            render (contentType: 'application/json') {
                [success: true,data:itemInstance]
            }
        }else {
            render (contentType: 'application/json') {
                [success: false,message:message(code: 'default.message.show.failed')]
            }          
        }
    }
    
    def create = {
        def itemInstance=new Item()        
        render (contentType: 'application/json') {
            [success: true,data:itemInstance]
        }
    }

    def save = {
        def itemInstance=new Item(params)
        render (contentType: 'application/json') {
            domainService.save(itemInstance)
        }
    }

    def update = {
        def  itemInstance = Item.findById(params.id)
        itemInstance.properties = params
        render (contentType: 'application/json') {
            domainService.save(itemInstance)
        }         
    }

    def delete = {
        
        def itemInstance = Item.findById(params.id)
        def result
        try {
            
            result = domainService.delete(itemInstance)
        
        }catch(DataIntegrityViolationException e){
            log.error e
            def msg = message(code: 'default.message.delete.failed', args: [itemInstance, e])
            result = [success:false, message: msg] 
        }
        
        render (contentType: 'application/json') {
            result
        }
    }
}
