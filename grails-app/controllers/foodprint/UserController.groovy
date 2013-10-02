package foodprint

import org.springframework.dao.DataIntegrityViolationException
import grails.transaction.Transactional

@Transactional(readOnly = true)
class UserController {

    static allowedMethods = [create:"POST",update: "POST",  delete: "POST"]
    def domainService

    def index(params) {

        render (contentType: 'text/json') {
            [userInstanceList: User.list(params), userInstanceTotal: User.count()]
    
        }
        
    }

 
    @Transactional
    def create(){

        def userInstance=new User(params)
        
        render (contentType: 'text/json') {
            domainService.save(userInstance)
        }
    }

    @Transactional
    def update(User userInstance){

        render (contentType: 'text/json') {
            domainService.save(userInstance)
        }         
    }


    @Transactional
    def delete(User userInstance){
        
        render (contentType: 'text/json') {
            domainService.delete(userInstance)
        }
    }
    
}
