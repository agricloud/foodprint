package foodprint

import grails.converters.JSON
import org.jets3t.service.S3ServiceException

class S3AttachmentService {

    def s3Service
    def fileLocation 
    def blankImg
    def imageModiService

    def save={ def params ->
        def result = [:]
        try {
 
            def inputStream = (InputStream)params.file.inputStream

            if(params.fileType=='jpg'){
                def byteArrayOutputStream=imageModiService.sizeNormal(inputStream)
                ByteArrayInputStream inputStreamScaled = new ByteArrayInputStream(byteArrayOutputStream.toByteArray())
                
                def s3Location="${fileLocation}/${params.domainName}/${params.domainId}.${params.fileType}"

                s3Service.saveObject s3Location, inputStreamScaled
            }
            else{

                def byteArrayOutputStream = new ByteArrayOutputStream()
                int reads = inputStream.read()

                while(reads != -1){ 
                    byteArrayOutputStream.write(reads)
                    reads = inputStream.read()
                }

                ByteArrayInputStream inputStreamScaled = new ByteArrayInputStream(byteArrayOutputStream.toByteArray())

                def s3Location="${fileLocation}/${params.domainName}/${params.domainId}.${params.fileType}"

                s3Service.saveObject s3Location, inputStreamScaled
            }

            result.success = true
        } catch (e) {
            log.error("Failed to upload file.", e)
            result.success = false
        } finally{
            return result
        }

        
    }
    def show= { def params ->

        try {

            def object = s3Service.getObject("${fileLocation}/${params.domainName}/${params.id}.${params.fileType}") 

            if(object){
                return object.dataInputStream
            }
            else return new FileInputStream(blankImg);  
        }
        catch (e) {

            log.error "Could not read ${fileLocation}/${params.domainName}/${params.id}.${params.fileType}"
            return new FileInputStream(blankImg);  
        }
    }
    def showPdf= { def params ->

        try {
            def object = s3Service.getObject("${fileLocation}/${params.domainName}/${params.id}.pdf")
            

            if(object)
                return object.dataInputStream
            else return new FileInputStream(blankImg);  
        }
        catch (e) {

            log.error "Could not read ${fileLocation}/${params.domainName}/${params.id}.jpg"
            return new FileInputStream(blankImg);  
        }
    }
    def delete= { def params ->
        def result = [:]
        try {
            println "${fileLocation}/${params.domainName}/${params.id}.${params.fileType}"
            s3Service.deleteObject "${fileLocation}/${params.domainName}/${params.id}.${params.fileType}"
            result.success = true
        }
        catch (e) {
            log.error "Could not delete ${fileLocation}/${params.domainName}/${params.id}.${params.fileType}"
            e.printStackTrace()
            result.success = false
            
        }finally{
            return result
        }
        
        
    }


}
