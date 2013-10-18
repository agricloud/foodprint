package foodprint

import org.springframework.dao.DataIntegrityViolationException

class ItemController {

    def domainService

    def index(Integer max) {

        // render (contentType: 'application/json') {
        //     [itemInstanceList: Item.list(params), itemInstanceTotal: Item.count()]
        // }


        def list = Item.createCriteria().list(params,params.criteria)


        render (contentType: 'application/json') {
            [itemInstanceList: list, itemInstanceTotal: list.totalCount]
        }


        
    }
    def show(Long id){

        def item=Item.findById(id);  
        if(item){   
            render (contentType: 'application/json') {
                [success: true,data:item]
            }
        }else {
            render (contentType: 'application/json') {
                [success: false,message:message(code: 'default.message.show.failed')]
            }          
        }
    }
    def create(){

        def item=new Item()        
        render (contentType: 'application/json') {
            [success: true,data:item]
        }
    }
 

    def save(){

        def itemInstance=new Item(params)
        
        render (contentType: 'application/json') {
            domainService.save(itemInstance)
        }
    }


    def update(){
        log.info params.effectStartDate
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
