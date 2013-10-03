package foodprint

import org.springframework.dao.DataIntegrityViolationException

class UserController {

    static allowedMethods = [create:"POST",update: "POST",  delete: "POST"]
    def domainService

    def index() {

        render (contentType: 'text/json') {
            [userInstanceList: User.list(params), userInstanceTotal: User.count()]
    
        }
        
    }

 
    def create(){

        def userInstance=new User(params)
        
        render (contentType: 'text/json') {
            domainService.save(userInstance)
        }
    }

    def update(){
        def userInstance = User.findById(params.id)
        userInstance.properties=params
        render (contentType: 'text/json') {
            domainService.save(userInstance)
        }         
    }


    def delete(){
        def userInstance = User.findById(params.id)
        render (contentType: 'text/json') {
            domainService.delete(userInstance)
        }
    }
    
}
