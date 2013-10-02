package foodprint

import org.springframework.dao.DataIntegrityViolationException
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
class ParamController {

    static allowedMethods = [create:"POST",update: "POST",  delete: "POST"]
    def domainService

    def index() {

        render (contentType: 'text/json') {
            [paramInstanceList: Param.list(params), paramInstanceTotal: Param.count()]
    
        }
        
    }

 
    @Transactional
    def create(){

        def paramInstance=new Param(params)
        
        render (contentType: 'text/json') {
            domainService.save(paramInstance)
        }
    }

    @Transactional
    def update(Param paramInstance){
        render (contentType: 'text/json') {
            domainService.save(paramInstance)
        }         
    }


    @Transactional
    def delete(Param paramInstance){
        
        def result
        try {
            
            result = domainService.delete(paramInstance)
        
        }catch(e){
            log.error e
            def msg = message(code: 'default.message.delete.failed', args: [paramInstance, e])
            result = [success:false, message: msg] 
        }
        
        render (contentType: 'text/json') {
            result
        }
    }



    /*
    * 將定義在 param domain 中的 enum ParamType 轉換為 json
    */
    def paramTypeJson(){

        render (contentType: 'text/json') {
            return [ParamTypeValue:foodprint.ParamType.values()]
        }
    }
}
