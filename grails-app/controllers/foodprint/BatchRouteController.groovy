
package foodprint

import grails.converters.JSON

class BatchRouteController {

    static allowedMethods = [create:"POST",update: "POST",  delete: "POST"]
    def domainService

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
        def batchRoute=Batch.findById(params.batch.id).batchRoutes
        def converter=[batchRouteInstanceList:batchRoute.collect(), batchRouteInstanceTotal: batchRoute.size()] as JSON
        JSON.use('default')

        converter.render(response)
    }

    def show(Long id){
        def batchRoute=BatchRoute.findById(id);
        if(batchRoute){
            
            
            def batchRouteJson =  JSON.parse((batchRoute as JSON).toString()) 
            batchRouteJson["batch.id"] = batchRoute.batch.id
            batchRouteJson["workstation.id"] = batchRoute.workstation.id
            batchRouteJson["workstation.title"] = batchRoute.workstation.title
            batchRouteJson["operation.id"] = batchRoute.operation.id
            batchRouteJson["operation.title"] = batchRoute.operation.title

            render (contentType: 'application/json') {
                [success: true,data:batchRouteJson]
            }
        }else {
            render (contentType: 'application/json') {
                [success: false,message:message(code: 'default.message.show.failed')]
            }            
        } 
    }



    def create() {

        if(params.batch.id){

            def batchRoute= new BatchRoute(params)

            if(batchRoute.batch.batchRoutes)
                batchRoute.sequence = batchRoute.batch.batchRoutes*.sequence.max()+1
            else batchRoute.sequence = 1

            def batchRouteJson =  JSON.parse((batchRoute as JSON).toString()) 
            batchRouteJson["batch.id"] = batchRoute.batch.id


            render (contentType: 'application/json') {
                [success: true,data:batchRouteJson]
            }
        }else {
            render (contentType: 'application/json') {
                [success: false,message:message(code: 'batchRoute.message.create.failed')]
            }            
        }   
    }

    def save() {
        def batchRoute= new BatchRoute(params)
        render (contentType: 'application/json') {
            domainService.save(batchRoute)
        }
    }


    def update() {
        def  batchRoute = BatchRoute.findById(params.id)
        batchRoute.properties=params   
        render (contentType: 'application/json') {
            domainService.save(batchRoute)
        }

    }

    def delete(){
        def  batchRoute = BatchRoute.findById(params.id)
        def result
        try {
            
            result = domainService.delete(batchRoute)
        
        }catch(e){
            log.error e
            def msg = message(code: 'default.message.delete.failed', args: [batchRoute, e.getMessage()])
            result = [success:false, message: msg] 
        }
        
        render (contentType: 'application/json') {
            result
        }
    }

    /* 功能測試正常無誤後可刪除
    def create() {
        def batchRouteInstance = new BatchRoute(params)
        render (contentType: 'application/json') {
            domainService.save(batchRouteInstance)
        }
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        def batchRoute=Batch.findById(params.batch.id).batchRoutes
        /** 
    *[batchRouteInstanceList:batchRoute.collect(), batchRouteInstanceTotal: batchRoute.size()]
    *or use
    *[batchRouteInstanceList: BatchRoute.list(params), batchRouteInstanceTotal: BatchRoute.count()]
        **/
    /*
        [batchRouteInstanceList:batchRoute.collect(), batchRouteInstanceTotal: batchRoute.size()]
    }
    */
}
