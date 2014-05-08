package foodprint


import org.springframework.dao.DataIntegrityViolationException

class SiteController {

    def domainService

    def index = {
        def list = Site.createCriteria().list(params,params.criteria)
        render (contentType: 'application/json') {
            [data: list, total: list.totalCount]
        }
    }

    def show = {
        def siteInstance=Site.get(params.id);  
        if(siteInstance){   
            render (contentType: 'application/json') {
                [success: true,data:siteInstance]
            }
        }else {
            render (contentType: 'application/json') {
                [success: false,message:message(code: 'default.message.show.failed')]
            }          
        }
    }
    
    def create = {
        def siteInstance=new Site()        
        render (contentType: 'application/json') {
            [success: true,data:siteInstance]
        }
    }

    def save = {
        def siteInstance=new Site(params)
        render (contentType: 'application/json') {
            domainService.save(siteInstance)
        }
    }

    def update = {
        def  siteInstance = Site.get(params.id)
        siteInstance.properties = params
        render (contentType: 'application/json') {
            domainService.save(siteInstance)
        }         
    }

    def delete = {
        
        def siteInstance = Site.get(params.id)
        def result
        try {
            
            result = domainService.delete(siteInstance)
        
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
