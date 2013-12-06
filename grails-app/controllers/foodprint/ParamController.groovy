package foodprint

import org.springframework.dao.DataIntegrityViolationException

class ParamController {

    static allowedMethods = [create:"POST",update: "POST",  delete: "POST"]
    def domainService
    def enumService

    def index = {

        def list = Param.createCriteria().list(params,params.criteria)

        render (contentType: 'application/json') {
            [paramInstanceList: list, paramInstanceTotal: list.totalCount]
        }
        
    }
     def show = {

        def param=Param.get(params.id);  
        if(param){ 

            render (contentType: 'application/json') {
                [success: true,data:param]
            }
        }else {
            render (contentType: 'application/json') {
                [success: false,message:message(code: 'default.message.show.failed')]
            }          
        }
    }

    def save = {

        def paramInstance=new Param(params)
        
        render (contentType: 'application/json') {
            domainService.save(paramInstance)
        }
    }

    def create = {

        def param=new Param()        
        render (contentType: 'application/json') {
            [success: true,data:param]
        }
    }

    def update = {
        def  paramInstance = Param.get(params.id)
        paramInstance.properties=params
        render (contentType: 'application/json') {
            domainService.save(paramInstance)
        }         
    }


    def delete = {
        def  paramInstance = Param.get(params.id)
        def result
        try {
            
            result = domainService.delete(paramInstance)
        
        }catch(DataIntegrityViolationException e){
            log.error e
            def msg = message(code: 'default.message.delete.failed', args: [paramInstance, e])
            result = [success:false, message: msg] 
        }
        
        render (contentType: 'application/json') {
            result
        }
    }

}
