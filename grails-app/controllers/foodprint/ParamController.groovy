package foodprint
import grails.converters.JSON

class ParamController {

    static allowedMethods = [create:"POST",update: "POST",  delete: "POST"]
    def domainService

    def index() {

        def list = Param.createCriteria().list(params,params.criteria)


        render (contentType: 'application/json') {
            [paramInstanceList: list, paramInstanceTotal: list.totalCount]
        }
        
    }
     def show(Long id){

        def param=Param.findById(id);  
        if(param){ 
            def paramJson = JSON.parse((param as JSON).toString())

            log.info param.paramType.name()
            paramJson.paramType = param.paramType.name()
            render (contentType: 'application/json') {
                [success: true,data:paramJson]
            }
        }else {
            render (contentType: 'application/json') {
                [success: false,message:message(code: 'default.message.show.failed')]
            }          
        }
    }

    def save(){

        def paramInstance=new Param(params)
        
        render (contentType: 'application/json') {
            domainService.save(paramInstance)
        }
    }

    def create(){

        def param=new Param()        
        render (contentType: 'application/json') {
            [success: true,data:param]
        }
    }

    def update(){
        def  paramInstance = Param.findById(params.id)
        paramInstance.properties=params
        render (contentType: 'application/json') {
            domainService.save(paramInstance)
        }         
    }


    def delete(){
        def  paramInstance = Param.findById(params.id)
        def result
        try {
            
            result = domainService.delete(paramInstance)
        
        }catch(e){
            log.error e
            def msg = message(code: 'default.message.delete.failed', args: [paramInstance, e])
            result = [success:false, message: msg] 
        }
        
        render (contentType: 'application/json') {
            result
        }
    }



    /*
    * 將定義在 param domain 中的 enum ParamType 轉換為 json
    */
    def indexType(){

        render (contentType: 'application/json') {
            return [ParamType:foodprint.ParamType.values()]
        }
    }
}
