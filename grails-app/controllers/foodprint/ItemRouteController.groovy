package foodprint

import org.springframework.dao.DataIntegrityViolationException
import grails.converters.JSON

class ItemRouteController {

    def domainService

    def index = {
        //找出指定品項的相關途程。
        /*
        * [Deep properties]
        *
        * itemRouteInstanceList::
        *   -workstation
        *   -operation
        */

        def itemRoute=Item.findById(params.item.id).itemRoutes
        def converter=[itemRouteInstanceList:itemRoute.collect(), itemRouteInstanceTotal: itemRoute.size()] as JSON

        converter.render(response)

    }

    def show = {
        def itemRouteInstance=ItemRoute.findById(params.id);
        if(itemRouteInstance){
            
            
            def itemRouteJson =  JSON.parse((itemRouteInstance as JSON).toString()) 
            itemRouteJson["item.id"] = itemRouteInstance.item.id
            itemRouteJson["workstation.id"] = itemRouteInstance.workstation.id
            itemRouteJson["workstation.title"] = itemRouteInstance.workstation.title
            itemRouteJson["operation.id"] = itemRouteInstance.operation.id
            itemRouteJson["operation.title"] = itemRouteInstance.operation.title

            render (contentType: 'application/json') {
                [success: true,data:itemRouteJson]
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
        def itemRouteInstance= new ItemRoute(params)
        render (contentType: 'application/json') {
            domainService.save(itemRouteInstance)
        }
    }


    def update = {
        def itemRouteInstance = ItemRoute.findById(params.id)
        
        if(params?.workstation?.id || !params.workstation.id.trim()){
            params.remove("workstation.id")
            params.remove("workstation.title")
            params.put("workstation",null) 
        }
        if(params?.supplier?.id || !params.supplier.id.trim()){
            params.remove("supplier.id")
            params.remove("supplier.title")
            params.put("supplier",null)
        }

        itemRouteInstance.properties = params
        render (contentType: 'application/json') {
            domainService.save(itemRouteInstance)
        }

    }

    def delete = {
        def itemRouteInstance = ItemRoute.findById(params.id)
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
