<%=packageName ? "package ${packageName}\n\n" : ''%>import org.springframework.dao.DataIntegrityViolationException

class ${className}Controller {

    static allowedMethods = [create: "POST", update: "PUT", delete: "DELETE"]
    def domainService

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [${propertyName}List: ${className}.list(params), ${propertyName}Total: ${className}.count()]
    }

    def listJson(Integer max) {
        JSON.use('deep')
        def converter=list(max) as JSON
        converter.render(response)
    }

    def create() {
        [${propertyName}: new ${className}(params)]
    }

    def save() {
        def ${propertyName} = new ${className}(params)
        if (!${propertyName}.save(flush: true)) {
            render(view: "create", model: [${propertyName}: ${propertyName}])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: '${domainClass.propertyName}.label', default: '${className}'), ${propertyName}.id])
        redirect(action: "show", id: ${propertyName}.id)
    }

    def show(Long id) {
        def ${propertyName} = ${className}.get(id)
        if (!${propertyName}) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: '${domainClass.propertyName}.label', default: '${className}'), id])
            redirect(action: "list")
            return
        }

        [${propertyName}: ${propertyName}]
    }

    def edit(Long id) {
        def ${propertyName} = ${className}.get(id)
        if (!${propertyName}) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: '${domainClass.propertyName}.label', default: '${className}'), id])
            redirect(action: "list")
            return
        }

        [${propertyName}: ${propertyName}]
    }


    def create() {
        def ${propertyName} = ${className}.get(id)
        render (contentType: 'text/json') {
            domainService.save(${propertyName})
        }
    }


    def update() {

        def ${propertyName} = ${className}.get(id)
        render (contentType: 'text/json') {
            domainService.save(${propertyName}, params)
        }

    }
    def delete(Long id) {

        def result
        def ${propertyName} = ${className}.get(id)
        try {
            
            result = domainService.delete(${propertyName})
        
        }catch(e){
            log.error e
            def msg = message(code: 'default.message.delete.failed', args: [${propertyName}, e.getMessage()])
            result = [success:false, message: msg] 
        }
        
        render (contentType: 'text/json') {
            result
        }


    }
}
