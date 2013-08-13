package foodprint

import org.springframework.dao.DataIntegrityViolationException

class ItemRouteRouteController {

    static allowedMethods = [create: "POST", update: "PUT", delete: "DELETE"]
/*
    def findMax() {
        def itemRouteInstance= new ItemRoute(params)
        def_item getValue(itemRouteInstance.id)
        // save
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
*/

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [itemRouteInstanceList: ItemRoute.list(params), itemRouteInstanceTotal: ItemRoute.count()]
    }

    def listJson(Integer max) {
        render (contentType: 'text/json') {
            list(max)        
        }
    }

    def create(){
        println"ItemRouteRouteController--create"
        def itemRouteInstance= new ItemRoute(params)
        itemRouteInstance.item=Item.findById(params.item_id)
        itemRouteInstance.sequence=ItemRoute.findMax(sequence)+1
        itemRouteInstance.workstation=Workstation.findById(params.workstation_id)
        render (contentType: 'text/json') {
            save(itemRouteInstance)
        }
    }

    def save(ItemRoute itemRouteInstance) {
        println"ItemRouteRouteController--save"
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
        [itemRouteInstance: new ItemRoute(params)]
    }

    def save() { // origin
        def itemRouteInstance = new ItemRoute(params)
        if (!itemRouteInstance.save(flush: true)) {
            render(view: "create", model: [itemRouteInstance: itemRouteInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'item.label', default: 'ItemRoute'), itemRouteInstance.id])
        redirect(action: "show", id: itemRouteInstance.id)
    }
    */

    def show(Long id) {
        def itemRouteInstance = ItemRoute.get(id)
        if (!itemRouteInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'item.label', default: 'ItemRoute'), id])
            redirect(action: "list")
            return
        }

        [itemRouteInstance: itemRouteInstance]
    }

    def edit(Long id) {
        def itemRouteInstance = ItemRoute.get(id)
        if (!itemRouteInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'item.label', default: 'ItemRoute'), id])
            redirect(action: "list")
            return
        }
        [itemRouteInstance: itemRouteInstance]
    }

     def update(){
        println"ItemRouteRouteController--update"
        def itemRouteInstance=ItemRoute.get(params.id)
        
        if(!itemRouteInstance) {
            log.warning "${controllerName}--${actionName}--itemRouteInstance not found"
            return render (contentType: 'text/json') {
                [success:false]
            }
        }
        itemRouteInstance.properties = params
        log.info "sequence = ${itemRouteInstance.sequence}"

        render (contentType: 'text/json') {
            save(itemRouteInstance)
        }
    }
    /*
    def update(Long id, Long version) {
        def itemRouteInstance = ItemRoute.get(id)
        if (!itemRouteInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'item.label', default: 'ItemRoute'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (itemRouteInstance.version > version) {
                itemRouteInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'item.label', default: 'ItemRoute')] as Object[],
                          "Another user has updated this ItemRoute while you were editing")
                render(view: "edit", model: [itemRouteInstance: itemRouteInstance])
                return
            }
        }

        itemRouteInstance.properties = params

        if (!itemRouteInstance.save(flush: true)) {
            render(view: "edit", model: [itemRouteInstance: itemRouteInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'item.label', default: 'ItemRoute'), itemRouteInstance.id])
        redirect(action: "show", id: itemRouteInstance.id)
    }
    */
     def delete() {
        println"ItemRouteRouteController--delete"
        def itemRouteInstance = ItemRoute.get(params.id)
        if (!itemRouteInstance) {
            println "ItemRouteRouteController--delete--Cant find itemRouteInstance"
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
        def itemRouteInstance = ItemRoute.get(id)
        if (!itemRouteInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'item.label', default: 'ItemRoute'), id])
            redirect(action: "list")
            return
        }

        try {
            itemRouteInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'item.label', default: 'ItemRoute'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'item.label', default: 'ItemRoute'), id])
            redirect(action: "show", id: id)
        }
    }
    */
}
