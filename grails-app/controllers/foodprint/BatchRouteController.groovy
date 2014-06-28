
package foodprint

import common.*

class BatchRouteController {

    static allowedMethods = [create:"POST",update: "POST",  delete: "POST"]
    def domainService

    def index = {

        def batch=Batch.get(params.batch.id)
        
        if(batch){
            
            def batchRoute = BatchRoute.findAllByBatch(batch)

            render (contentType: 'application/json') {
               [success: true, data:batchRoute, total: batchRoute.size()]
            }           
        }
        else{
            render (contentType: 'application/json') {
                [success: false,message:message(code: 'default.message.show.failed')]
            }            
        }


    }

    def show = {
        def batchRoute=BatchRoute.get(params.id);
        if(batchRoute){
            render (contentType: 'application/json') {
                [success: true,data:batchRoute]
            }
        }else {
            render (contentType: 'application/json') {
                [success: false,message:message(code: 'default.message.show.failed')]
            }            
        } 
    }



    def create = {

        if(params.batch.id){

            def batchRoute= new BatchRoute(params)

            if(batchRoute.batch.batchRoutes)
                batchRoute.sequence = batchRoute.batch.batchRoutes*.sequence.max()+1
            else batchRoute.sequence = 1

            render (contentType: 'application/json') {
                [success: true,data:batchRoute]
            }
        }else {
            render (contentType: 'application/json') {
                [success: false,message:message(code: 'batchRoute.message.create.failed')]
            }            
        }   
    }

    def save = {
        if((!params.workstation.id && !params.supplier.id)||(params.workstation.id && params.supplier.id)){
            render (contentType: 'application/json') {
                [success: false,message:message(code: 'batchRoute.workstation.supplier.should.exists.one')]
            }
            return
        }
        def batchRoute= new BatchRoute(params)
        render (contentType: 'application/json') {
            domainService.save(batchRoute)
        }
    }


    def update = {

        if((!params.workstation.id && !params.supplier.id)||(params.workstation.id && params.supplier.id)){
            render (contentType: 'application/json') {
                [success: false,message:message(code: 'batchRoute.workstation.supplier.should.exists.one')]
            }
            return
        }

        def  batchRoute = BatchRoute.get(params.id)
        batchRoute.properties=params   
        render (contentType: 'application/json') {
            domainService.save(batchRoute)
        }

    }

    def delete = {
        def  batchRoute = BatchRoute.get(params.id)
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
