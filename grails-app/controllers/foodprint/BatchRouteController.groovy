
package foodprint

import grails.converters.JSON
import common.*

class BatchRouteController {

    static allowedMethods = [create:"POST",update: "POST",  delete: "POST"]
    def domainService

    def index(Integer max) {

        def batch=Batch.findById(params.batch.id)
        if(batch){
            def list=BatchRoute.findAllByBatch(batch)
            render (contentType: 'application/json') {
               [success: true, data:list, total: list.size()]
            }           
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
            render (contentType: 'application/json') {
                [success: true,data:batchRoute]
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

            render (contentType: 'application/json') {
                [success: true,data:batchRoute]
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

        if(params.workstation.id==null || !params.workstation.id.trim()){
            params.remove("workstation.id")
            params.remove("workstation.title")  
            params.put("workstation",null) 
        }
        if(params.supplier.id==null || !params.supplier.id.trim()){
            params.remove("supplier.id")
            params.remove("supplier.title")
            params.put("supplier",null)
        }

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
