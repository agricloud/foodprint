package foodprint


import org.springframework.dao.DataIntegrityViolationException

class BatchSourceController {

    def domainService

    def index = {

        def batch=Batch.findById(params.batch.id)
        if(batch){
            def list=BatchSource.findAllByBatch(batch)
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

    def show = {
        def batchSourceInstance=BatchSource.get(params.id);  
        if(batchSourceInstance){   
            render (contentType: 'application/json') {
                [success: true,data:batchSourceInstance]
            }
        }else {
            render (contentType: 'application/json') {
                [success: false,mexssage:message(code: 'default.message.show.failed')]
            }          
        }
    }
    
    def create = {
        if(params.batch.id){

            def batchSourceInstance=new BatchSource(params)

            // if(batchSource.batch.batchRoutes)
            //     batchRoute.sequence = batchRoute.batch.batchRoutes*.sequence.max()+1
            // else batchRoute.sequence = 1

            render (contentType: 'application/json') {
                [success: true,data:batchSourceInstance]
            }
        }else {
            render (contentType: 'application/json') {
                [success: false,message:message(code: 'batchSource.message.create.failed')]
            }            
        }   

    }

    def save = {
        def batchSourceInstance=new BatchSource(params)
        render (contentType: 'application/json') {
            domainService.save(batchSourceInstance)
        }
    }

    def update = {
        def  batchSourceInstance = BatchSource.get(params.id)
        batchSourceInstance.properties = params
        render (contentType: 'application/json') {
            domainService.save(batchSourceInstance)
        }         
    }

    def delete = {
        
        def batchSourceInstance = BatchSource.get(params.id)
        def result
        try {
            
            result = domainService.delete(batchSourceInstance)
        
        }catch(DataIntegrityViolationException e){
            log.error e
            def msg = message(code: 'default.message.delete.failed', args: [itemInstance, e])
            result = [success:false, message: msg] 
        }
        
        render (contentType: 'application/json') {
            result
        }
    }
}
