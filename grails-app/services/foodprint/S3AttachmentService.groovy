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
            InputStream inputStream = (InputStream)params.file.inputStream

            def byteArrayOutputStream=imageModiService.sizeNormal(inputStream)
            ByteArrayInputStream inputStreamScaled = new ByteArrayInputStream(byteArrayOutputStream.toByteArray())

            def s3Location="${fileLocation}/${params.domainName}/${params.domainId}.jpg";

            s3Service.saveObject s3Location, inputStreamScaled
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
            def object = s3Service.getObject("${fileLocation}/${params.domainName}/${params.id}.jpg")
            

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
            println "${fileLocation}/${params.domainName}/${params.id}.jpg"
            s3Service.deleteObject "${fileLocation}/${params.domainName}/${params.id}.jpg"
            result.success = true
        }
        catch (e) {
            log.error "Could not delete ${fileLocation}/${params.domainName}/${params.id}.jpg"
            e.printStackTrace()
            result.success = false
            
        }finally{
            return result
        }
        
        
    }


}
