package foodprint

import org.springframework.dao.DataIntegrityViolationException

class UserController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [userInstanceList: User.list(params), userInstanceTotal: User.count()]
    }

    def listJson(Integer maxa) {
        println"UserController--listJson"
        render (contentType: 'text/json') {
            list(max)        
        }
    }

    def create() {
        println"UserController--create"

        def userInstance=new User(params)
        render (contentType: 'text/json') {
            save(userInstance);
        }
    }

    def save(User userInstance){
        if (!userInstance.validate()) {
                userInstance.errors.each {
                println it
            }
            return [success:false]
        }
        if (!userInstance.save(failOnError: true)) {//flush:true?
                return [success:false]
        }
        else{
                return [success:true]
        }
    }

    def show(Long id) {
        def userInstance = User.get(id)
        if (!userInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), id])
            redirect(action: "list")
            return
        }

        [userInstance: userInstance]
    }

    def edit(Long id) {
        def userInstance = User.get(id)
        if (!userInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), id])
            redirect(action: "list")
            return
        }

        [userInstance: userInstance]
    }

    def update(){
        println"UserController--update"
        def userInstance=User.get(params.id)

        if(!userInstance){
            println"UserController--update--cant find userInstance"
            return render (contentType: 'text/json') {[success:false]}
        }

       userInstance.properties = params
        render (contentType: 'text/json') {
            save(userInstance);
        }         
    }
/*
    def update(Long id, Long version) {
        println"UserController--update"+"---"+id+"---"+version
        def userInstance = User.get(id)
        if (!userInstance) {
            println"***1"
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            println"***2"
            if (userInstance.version > version) {
                userInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'user.label', default: 'User')] as Object[],
                          "Another user has updated this User while you were editing")
                render(view: "edit", model: [userInstance: userInstance])
                return
            }
        }

        userInstance.properties = params

        if (!userInstance.save(flush: true)) {
            println"***3"
            render(view: "edit", model: [userInstance: userInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'user.label', default: 'User'), userInstance.id])
        redirect(action: "show", id: userInstance.id)
    }
    */

    def delete(){
        println"UserController--delete"
        def userInstance=User.get(params.id)
        if (!userInstance) {
            println"UserController--delete--Cant find userInstance"
            render (contentType: 'text/json') {
                return [success:false]
            }
        }
        try {
            userInstance.delete()
            render (contentType: 'text/json') {
                return [success:true]
            }
        }
        catch (e) {
            render (contentType: 'text/json') {
                return [success:false]
            }
        }

    }
}
