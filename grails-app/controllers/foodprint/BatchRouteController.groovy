package foodprint

import grails.converters.JSON
import grails.transaction.Transactional

@Transactional(readOnly = true)
class BatchRouteController {

    static allowedMethods = [create:"POST",update: "POST",  delete: "POST"]
    def domainService


    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        def batchRoute=Batch.findById(params.batch.id).batchRoutes
        /** 
	*[batchRouteInstanceList:batchRoute.collect(), batchRouteInstanceTotal: batchRoute.size()]
	*or use
	*[batchRouteInstanceList: BatchRoute.list(params), batchRouteInstanceTotal: BatchRoute.count()]
        **/
        [batchRouteInstanceList:batchRoute.collect(), batchRouteInstanceTotal: batchRoute.size()]
    }

    def index(Integer max) {

        JSON.use('deep')
        def converter=list(max) as JSON
        converter.render(response)
    }

    @Transactional
    def create() {
        def batchRouteInstance = new BatchRoute(params)
        render (contentType: 'text/json') {
            domainService.save(batchRouteInstance)
        }
    }

    @Transactional
    def update(BatchRoute batchRouteInstance) {

        render (contentType: 'text/json') {
            domainService.save(batchRouteInstance)
        }

    }

    @Transactional
    def delete(BatchRoute batchRouteInstance){
        def result
        try {
            
            result = domainService.delete(batchRouteInstance)
        
        }catch(e){
            log.error e
            def msg = message(code: 'default.message.delete.failed', args: [batchRouteInstance, e.getMessage()])
            result = [success:false, message: msg] 
        }
        
        render (contentType: 'text/json') {
            result
        }
    }
}
