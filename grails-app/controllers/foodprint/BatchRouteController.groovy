
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
        def batch=Batch.findById(params.batch.id)
        if(batch){
            def batchRoute=batch.batchRoutes
            def converter=[success: true, data:batchRoute.collect(), total: batchRoute.size()] as JSON
            JSON.use('default')
            converter.render(response)
        }
        else{
            render (contentType: 'application/json') {
                [success: false,message:message(code: 'default.message.show.failed')]
            }            
        } 
    }

    def show(Long id){
        def batchRoute=BatchRoute.findById(id);
        if(batchRoute){
            
            def batchRouteJson =  JSON.parse((batchRoute as JSON).toString()) 
            batchRouteJson["batch.id"] = batchRoute.batch.id
            batchRouteJson["operation.id"] = batchRoute.operation.id
            batchRouteJson["operation.name"] = batchRoute.operation.name
            batchRouteJson["operation.title"] = batchRoute.operation.title
            if(batchRoute.workstation){
                batchRouteJson["workstation.id"] = batchRoute.workstation.id
                batchRouteJson["workstation.name"] = batchRoute.workstation.name
                batchRouteJson["workstation.title"] = batchRoute.workstation.title
            }
            if(batchRoute.supplier){
                batchRouteJson["supplier.id"] = batchRoute.supplier.id
                batchRouteJson["supplier.name"] = batchRoute.supplier.name
                batchRouteJson["supplier.title"] = batchRoute.supplier.title
            }

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

}
