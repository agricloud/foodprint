package foodprint

import org.springframework.dao.DataIntegrityViolationException
import grails.converters.JSON

class ItemRouteController {

    def domainService

    def index = {

        def item = Item.get(params.item.id)

        if(item){

            def itemRoute = ItemRoute.findAllByItem(item)

            render (contentType: 'application/json') {
               [success: true, data:itemRoute, total: itemRoute.size()]
            }           
        }
        else{
            render (contentType: 'application/json') {
                [success: false,message:message(code: 'default.message.show.failed')]
            }            
        }

    }

    def show = {
        def itemRouteInstance=ItemRoute.get(params.id);
        if(itemRouteInstance){

            render (contentType: 'application/json') {
                [success: true,data:itemRouteInstance]
            }
        }else {
            render (contentType: 'application/json') {
                [success: false,message:message(code: 'default.message.show.failed')]
            }            
        }
    }

    def create = {

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

    def save = {

        if((!params.workstation.id && !params.supplier.id)||(params.workstation.id && params.supplier.id)){
            render (contentType: 'application/json') {
                [success: false,message:message(code: 'itemRoute.workstation.supplier.should.exists.one')]
            }
            return
        }

        def itemRouteInstance= new ItemRoute(params)
        render (contentType: 'application/json') {
            domainService.save(itemRouteInstance)
        }
    }


    def update = {
        def itemRouteInstance = ItemRoute.get(params.id)
        
        if((!params.workstation.id && !params.supplier.id)||(params.workstation.id && params.supplier.id)){
            render (contentType: 'application/json') {
                [success: false,message:message(code: 'itemRoute.workstation.supplier.should.exists.one')]
            }
            return
        }

        itemRouteInstance.properties = params
        render (contentType: 'application/json') {
            domainService.save(itemRouteInstance)
        }

    }

    def delete = {
        def itemRouteInstance = ItemRoute.get(params.id)
        def result
        try {
            
            result = domainService.delete(itemRouteInstance)
        
        }catch(DataIntegrityViolationException e){
            log.error e
            def msg = message(code: 'default.message.delete.failed', args: [itemRouteInstance, e.getMessage()])
            result = [success:false, message: msg] 
        }
        
        render (contentType: 'application/json') {
            result
        }
    }

}
