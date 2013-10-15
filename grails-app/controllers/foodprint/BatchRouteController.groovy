package foodprint

import grails.converters.JSON

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
        //找出指定批號的相關途程。
        /*
        * [Deep properties]
        *
        * batchRouteInstanceList::
        *   -workstation
        *   -operation
        */
        JSON.use('deep')
        def converter=list(max) as JSON
        JSON.use('default')
        converter.render(response)
    }

    def create() {
        def batchRouteInstance = new BatchRoute(params)
        render (contentType: 'application/json') {
            domainService.save(batchRouteInstance)
        }
    }

    def update() {
        def  batchRouteInstance = BatchRoute.findById(params.id)
        batchRouteInstance.properties=params   
        render (contentType: 'application/json') {
            domainService.save(batchRouteInstance)
        }

    }

    def delete(){
        def  batchRouteInstance = BatchRoute.findById(params.id)
        def result
        try {
            
            result = domainService.delete(batchRouteInstance)
        
        }catch(e){
            log.error e
            def msg = message(code: 'default.message.delete.failed', args: [batchRouteInstance, e.getMessage()])
            result = [success:false, message: msg] 
        }
        
        render (contentType: 'application/json') {
            result
        }
    }
}
