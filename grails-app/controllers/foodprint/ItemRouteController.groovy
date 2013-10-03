package foodprint

import org.springframework.dao.DataIntegrityViolationException
import grails.converters.JSON

class ItemRouteController {

    static allowedMethods = [create:"POST",update: "POST",  delete: "POST"]
    def domainService




    def index() {
        JSON.use('deep')
        def itemRoute=Item.findById(params.item.id).itemRoutes

        def converter=[itemRouteInstanceList:itemRoute.collect(), itemRouteInstanceTotal: itemRoute.size()] as JSON
        converter.render(response)

    }


    def create() {
        def itemRouteInstance= new ItemRoute(params)
        render (contentType: 'text/json') {
            domainService.save(itemRouteInstance)
        }
    }


    def update() {
        def itemRouteInstance = ItemRoute.findById(params.id)
        itemRouteInstance.properties = params
        render (contentType: 'text/json') {
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
        
        render (contentType: 'text/json') {
            result
        }
    }

}
