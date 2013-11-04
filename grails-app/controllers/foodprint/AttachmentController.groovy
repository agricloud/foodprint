package foodprint

import grails.converters.JSON
import grails.plugins.springsecurity.Secured

class AttachmentController {

    def attachmentService

    def save={
        params.file = request.getFile('file')
        def result = attachmentService.save(params) 

        render(contentType:'application/json'){
            result
        }
    }

    def show= { Long id ->

        def result = attachmentService.show(params) 
        response.outputStream << result
    }


    def delete= { Long id ->
        params.id = id
        def result = attachmentService.delete(params) 
        
        render(contentType:'application/json'){
            result
        }
    }

}
