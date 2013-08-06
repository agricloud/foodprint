package foodprint

import org.springframework.dao.DataIntegrityViolationException

class ItemRouteController {

    static allowedMethods = [create: "POST", update: "PUT", delete: "DELETE"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [itemRouteInstanceList: Item.list(params), itemRouteInstanceTotal: Item.count()]
    }

    def listJson(Integer max) {

        render (contentType: 'text/json') {
            list(max)        
        }
    }

    def create() {
        println"ItemRouteController--create"
        def itemRouteInstance= new Item(params)
        render (contentType: 'text/json') {
            save(itemRouteInstance)
        }
    }

    def save(Item itemRouteInstance) {
        println"ItemRouteController--save"
        if(!itemRouteInstance.validate()) { // validate id
            itemRouteInstance.errors.each {
               println it
            }
            return [success: false]
        }
        if (!itemRouteInstance.save(failOnError: true)) {
            return [success: false]
        }
        else{
            return [success:true]
        }
    }
    /*
    def create() { // origin
        [itemRouteInstance: new Item(params)]
    }

    def save() { // origin
        def itemRouteInstance = new Item(params)
        if (!itemRouteInstance.save(flush: true)) {
            render(view: "create", model: [itemRouteInstance: itemRouteInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'item.label', default: 'Item'), itemRouteInstance.id])
        redirect(action: "show", id: itemRouteInstance.id)
    }
    */

    def show(Long id) {
        def itemRouteInstance = Item.get(id)
        if (!itemRouteInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'item.label', default: 'Item'), id])
            redirect(action: "list")
            return
        }

        [itemRouteInstance: itemRouteInstance]
    }

    def edit(Long id) {
        def itemRouteInstance = Item.get(id)
        if (!itemRouteInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'item.label', default: 'Item'), id])
            redirect(action: "list")
            return
        }
        [itemRouteInstance: itemRouteInstance]
    }

    def update(){
        println"ItemRouteController--update"
        def itemRouteInstance=Item.get(params.id)
        if(!itemRouteInstance) {
            println"ItemRouteController--update--cant find itemRouteInstance"
            return render (contentType: 'text/json') {[success: false]}
        }
        itemRouteInstance.properties = params
        render (contentType: 'text/json') {
            save(itemRouteInstance);
        }         
    }
    /*
    def update(Long id, Long version) {
        def itemRouteInstance = Item.get(id)
        if (!itemRouteInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'item.label', default: 'Item'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (itemRouteInstance.version > version) {
                itemRouteInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'item.label', default: 'Item')] as Object[],
                          "Another user has updated this Item while you were editing")
                render(view: "edit", model: [itemRouteInstance: itemRouteInstance])
                return
            }
        }

        itemRouteInstance.properties = params

        if (!itemRouteInstance.save(flush: true)) {
            render(view: "edit", model: [itemRouteInstance: itemRouteInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'item.label', default: 'Item'), itemRouteInstance.id])
        redirect(action: "show", id: itemRouteInstance.id)
    }
    */
     def delete() {
        println"ItemRouteController--delete"
        def itemRouteInstance = Item.get(params.id)
        if (!itemRouteInstance) {
            println "ItemRouteController--delete--Cant find itemRouteInstance"
            render(contentType: 'text/json') {
                return [success: false]
            }
        }
        itemRouteInstance.delete(failOnError: true)
            render(contentType: 'text/json') {
                return [success: true]
            }
        try {
            itemRouteInstance.delete(failOnError: true)
            render(contentType: 'text/json') {
                return [success: true]
            }
        }
        catch (e) {
            render (contentType: 'text/json') {
                return [success: false]
            }
        }
    }
    /*
    def delete(Long id) { // origin
        def itemRouteInstance = Item.get(id)
        if (!itemRouteInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'item.label', default: 'Item'), id])
            redirect(action: "list")
            return
        }

        try {
            itemRouteInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'item.label', default: 'Item'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'item.label', default: 'Item'), id])
            redirect(action: "show", id: id)
        }
    }
    */
}
