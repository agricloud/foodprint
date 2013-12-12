package foodprint

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

        response.contentType = "image/jpeg"
        
        response.outputStream << result
        // response.outputStream.flush()

    }
    def showPdf= { Long id ->

        def result = attachmentService.showPdf(params) 
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
