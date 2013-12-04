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
                [success: false,message:message(code: 'default.message.show.failed')]
            }          
        }
    }
    
    def create = {
        def batchSourceInstance=new BatchSource()        
        render (contentType: 'application/json') {
            [success: true,data:batchSourceInstance]
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
