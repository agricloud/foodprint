package foodprint

import org.springframework.dao.DataIntegrityViolationException
import grails.converters.JSON

class ItemRouteController {

    static allowedMethods = [create:"POST",update: "POST",  delete: "POST"]
    def domainService




    def index() {
        //找出指定品項的相關途程。
        /*
        * [Deep properties]
        *
        * itemRouteInstanceList::
        *   -workstation
        *   -operation
        */
        JSON.use('deep')
        def itemRoute=Item.findById(params.item.id).itemRoutes
        def converter=[itemRouteInstanceList:itemRoute.collect(), itemRouteInstanceTotal: itemRoute.size()] as JSON
        JSON.use('default')

        converter.render(response)

    }

    def show(Long id){
        def itemRouteInstance=ItemRoute.findById(id);
        
        def itemRouteJson =  JSON.parse((itemRouteInstance as JSON).toString()) 
        itemRouteJson["item.id"] = itemRouteInstance.item.id

        render (contentType: 'application/json') {
            [success: true,data:itemRouteJson]
        }
    }

    def create() {

        if(params.item.id){

            def itemRouteInstance= new ItemRoute(params)

            if(itemRouteInstance.item.itemRoutes)
                itemRouteInstance.sequence = itemRouteInstance.item.itemRoutes*.sequence.max()+1
            else itemRouteInstance.sequence = 1

            def itemRouteJson =  JSON.parse((itemRouteInstance as JSON).toString()) 
            itemRouteJson["item.id"] = itemRouteInstance.item.id


            render (contentType: 'application/json') {
                [success: true,data:itemRouteJson]
            }
        }else {
            render (contentType: 'application/json') {
                [success: false,message:message(code: 'itemRoute.message.create.failed')]
            }            
        }   
    }

    def save() {
        def itemRouteInstance= new ItemRoute(params)
        render (contentType: 'application/json') {
            domainService.save(itemRouteInstance)
        }
    }


    def update() {
        def itemRouteInstance = ItemRoute.findById(params.id)
        itemRouteInstance.properties = params
        render (contentType: 'application/json') {
            domainService.save(itemRouteInstance)
        }

    }

    def delete(){
        def itemRouteInstance = ItemRoute.findById(params.id)
        def result
        try {
            
            result = domainService.delete(itemRouteInstance)
        
        }catch(e){
            log.error e
            def msg = message(code: 'default.message.delete.failed', args: [itemRouteInstance, e.getMessage()])
            result = [success:false, message: msg] 
        }
        
        render (contentType: 'application/json') {
            result
        }
    }

}
