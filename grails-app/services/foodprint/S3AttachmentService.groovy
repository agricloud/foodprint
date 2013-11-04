package foodprint



import grails.converters.JSON
import org.jets3t.service.S3ServiceException

class S3AttachmentService {

    def s3Service
    def fileLocation 
    def blankImg

    def save={ def params ->
        def result = [:]
        result.text = [:]

        try {
            def ri = (InputStream)params.file.inputStream

            def s3Location="${fileLocation}/${params.domainName}/${params.domainId}.jpg";


            s3Service.saveObject s3Location, ri
            result.text.success = true
        } catch (e) {
            log.error("Failed to upload file.", e)
            result.text.success = false
        }

        return result
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

        // def file = new File(params.file);
        try {
  
            s3Service.deleteObject "${fileLocation}/${params.domainName}/${params.id}.jpg"

            return result.text.success = true
        }
        catch (e) {
            log.error "Could not read ${file}"
            e.printStackTrace()
            return render(text: [success:false] as JSON, contentType:'text/json')
        }
        
        
    }

}
