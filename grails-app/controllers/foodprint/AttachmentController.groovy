package foodprint

import grails.converters.JSON
import grails.plugins.springsecurity.Secured

class AttachmentController {

    def attachmentService

    def save={

        def result = attachmentService.save(params) 
        return render(result as JSON, contentType:'application/json')
    }

    def show= { Long id ->
        log.debug "attachmentBean === "+attachmentService
        params.id = id
        def result = attachmentService.show(params) 
        response.outputStream << result
    }


    def delete= { Long id ->
        params.id = id
        def result = attachmentService.delete(params) 
        return render(result as JSON, contentType:'application/json')
    }

}
