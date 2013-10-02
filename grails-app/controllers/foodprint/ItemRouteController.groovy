package foodprint

import org.springframework.dao.DataIntegrityViolationException
import grails.converters.JSON
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
class ItemRouteController {

    static allowedMethods = [create:"POST",update: "POST",  delete: "POST"]
    def domainService




    def index() {
        JSON.use('deep')
        def itemRoute=Item.findById(params.item.id).itemRoutes

        def converter=[itemRouteInstanceList:itemRoute.collect(), itemRouteInstanceTotal: itemRoute.size()] as JSON
        converter.render(response)

    }

    @Transactional
    def create() {
        def itemRouteInstance= new ItemRoute(params)
        render (contentType: 'text/json') {
            domainService.save(itemRouteInstance)
        }
    }

    @Transactional
    def update(ItemRoute itemRouteInstance) {
        log.info itemRouteInstance.workstation.name

        render (contentType: 'text/json') {
            domainService.save(itemRouteInstance)
        }

    }

    @Transactional
    def delete(ItemRoute itemRouteInstance){
        
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
