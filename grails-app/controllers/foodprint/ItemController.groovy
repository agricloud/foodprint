package foodprint

import org.springframework.dao.DataIntegrityViolationException

class ItemController {

    static allowedMethods = [create: "POST", update: "PUT", delete: "DELETE"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(params) {
        //params.max = Math.min(max ?: 10, 100)
        [itemInstanceList: Item.list(params), itemInstanceTotal: Item.count()]
    }

    def listJson(params) {
        render (contentType: 'text/json') {
            list(max)        
        }
    }

    def create() {
        println"ItemController--create"
        def itemInstance= new Item(params)
        render (contentType: 'text/json') {
            save(itemInstance)
        }
    }

    def save(Item itemInstance) {
        println"ItemController--save"
        if(!itemInstance.validate()) { // validate id
            itemInstance.errors.each {
               println it
            }
            return [success: false]
        }
        if (!itemInstance.save(failOnError: true)) {
            return [success: false]
        }
        else{
            return [success:true]
        }
    }
    /*
    def create() { // origin
        [itemInstance: new Item(params)]
    }

    def save() { // origin
        def itemInstance = new Item(params)
        if (!itemInstance.save(flush: true)) {
            render(view: "create", model: [itemInstance: itemInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'item.label', default: 'Item'), itemInstance.id])
        redirect(action: "show", id: itemInstance.id)
    }
    */

    def show(Long id) {
        def itemInstance = Item.get(id)
        if (!itemInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'item.label', default: 'Item'), id])
            redirect(action: "list")
            return
        }

        [itemInstance: itemInstance]
    }

    def edit(Long id) {
        def itemInstance = Item.get(id)
        if (!itemInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'item.label', default: 'Item'), id])
            redirect(action: "list")
            return
        }
        [itemInstance: itemInstance]
    }

    def update(){
        println"ItemController--update"
        def itemInstance=Item.get(params.id)
        if(!itemInstance) {
            println"ItemController--update--cant find itemInstance"
            return render (contentType: 'text/json') {[success: false]}
        }
        itemInstance.properties = params
        render (contentType: 'text/json') {
            save(itemInstance);
        }         
    }
    /*
    def update(Long id, Long version) {
        def itemInstance = Item.get(id)
        if (!itemInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'item.label', default: 'Item'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (itemInstance.version > version) {
                itemInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'item.label', default: 'Item')] as Object[],
                          "Another user has updated this Item while you were editing")
                render(view: "edit", model: [itemInstance: itemInstance])
                return
            }
        }

        itemInstance.properties = params

        if (!itemInstance.save(flush: true)) {
            render(view: "edit", model: [itemInstance: itemInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'item.label', default: 'Item'), itemInstance.id])
        redirect(action: "show", id: itemInstance.id)
    }
    */
     def delete() {
        println"ItemController--delete"
        def itemInstance = Item.get(params.id)
        if (!itemInstance) {
            println "ItemController--delete--Cant find itemInstance"
            render(contentType: 'text/json') {
                return [success: false]
            }
        }
        itemInstance.delete(failOnError: true)
            render(contentType: 'text/json') {
                return [success: true]
            }
        try {
            itemInstance.delete(failOnError: true)
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
        def itemInstance = Item.get(id)
        if (!itemInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'item.label', default: 'Item'), id])
            redirect(action: "list")
            return
        }

        try {
            itemInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'item.label', default: 'Item'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'item.label', default: 'Item'), id])
            redirect(action: "show", id: id)
        }
    }
    */

    def imageUpload(){
        
    }
}
