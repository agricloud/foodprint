package foodprint

import org.springframework.dao.DataIntegrityViolationException

class UserController {

    static allowedMethods = [create:"POST",update: "POST",  delete: "POST"]
    def domainService
    def springSecurityService

    def index() {

        def list = User.createCriteria().list(params,params.criteria)


        render (contentType: 'application/json') {
            [data: list, total: list.totalCount]
        }
        
    }

    def show(Long id){

        def user=User.get(id);  
        if(user){   
            render (contentType: 'application/json') {
                [success: true,data:user]
            }
        }else {
            render (contentType: 'application/json') {
                [success: false,message:message(code: 'default.message.show.failed')]
            }          
        }
    }
    def create(){

        def user=new User()
        user.site =  springSecurityService?.currentUser?.site
        render (contentType: 'application/json') {
            [success: true,data:user]
        }
    }
 
    def save(){

        if(!params?.site?.id){
            def site =new Site(name:params.username,title:params.fullName)
            domainService.save(site)
            params["site.id"] = site.id
        }

        if(params.password ==~ /^[a-zA-Z0-9]*$/){
            def userInstance=new User(params)
        
            render (contentType: 'application/json') {
                domainService.save(userInstance)
            }
        }
        else{
            render (contentType: 'application/json') {
                [success: false,message:message(code: 'user.password.not.valid')]
            }  
        }
    }

    def update(){

        if(!params.password || params.password ==~ /^[a-zA-Z0-9]*$/){
            def userInstance = User.get(params.id)
            userInstance.properties=params
        
            render (contentType: 'application/json') {
                domainService.save(userInstance)
            }
        }
        else{
            render (contentType: 'application/json') {
                [success: false,message:message(code: 'user.password.not.valid')]
            }  
        }       
    }


    def delete(){
        def userInstance = User.get(params.id)
        def result
        try {
            
            result = domainService.delete(userInstance)
        
        }catch(e){
            log.error e
            def msg = message(code: 'default.message.delete.failed', args: [userInstance, e.getMessage()])
            result = [success:false, message: msg] 
        }
        
        render (contentType: 'application/json') {
            result
        }
    }
    
}
