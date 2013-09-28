package foodprint

import org.springframework.dao.DataIntegrityViolationException
import grails.converters.JSON

class ItemRouteController {

    static allowedMethods = [create: "POST", update: "PUT", delete: "DELETE"]
    def domainService

    def index() {
        redirect(action: "list", params: params)
    }
    
    def list(Integer max) {
        // find itemForm id
        def itemRoute=Item.findById(params.item.id).itemRoutes
        // findAllByItem use item
        //def itemRoute=ItemRoute.findAllByItem(item)
        // return array
        [itemRouteInstanceList:itemRoute.collect(), itemRouteInstanceTotal: itemRoute.size()]
    }

    def show() {
        log.debug "${controllerName}-${actionName}"
    }


    def listJson(Integer max) {
        log.debug "${controllerName}-${actionName}"
        JSON.use('deep')
        def converter=list(max) as JSON
        converter.render(response)

    }

    def create() {
        def itemRouteInstance= new ItemRoute(params)
        render (contentType: 'text/json') {
            domainService.save(itemRouteInstance)
        }
    }


    def update() {

        def itemRouteInstance=ItemRoute.findById(params.id)
        render (contentType: 'text/json') {
            domainService.save(itemRouteInstance, params)
        }

    }


    def delete(){
        
        def result
        def itemRouteInstance=ItemRoute.findById(params.id)
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


    def edit(Long id) {
        def itemRouteInstance = ItemRoute.get(id)
        if (!itemRouteInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'item.label', default: 'ItemRoute'), id])
            redirect(action: "list")
            return
        }
        [itemRouteInstance: itemRouteInstance]
    }

}
