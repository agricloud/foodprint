package extrails



import grails.converters.JSON
import grails.plugins.springsecurity.Secured

class S3AttachmentService {

    def s3Service

    def save={
        try {


            def ri = (InputStream)request.inputStream
            def s3Location="${grailsApplication.config.grails.aws.root}/${params.name}/${params.qqfile}";

            if(params.qqfile.toLowerCase().endsWith(".jpg") || params.qqfile.toLowerCase().endsWith(".jpeg")){
                def oi=imageModiService.sizeMiddle(ri)                
                s3Service.saveObject s3Location, new ByteArrayInputStream(ri)
                oi.close();
                // oi=null
            }else {
                s3Service.saveObject s3Location, ri
                // ri=null
            }


            return render(text: [success:true] as JSON, contentType:'text/json')

        } catch (e) {

            log.error("Failed to upload file.", e)
            return render(text: [success:false] as JSON, contentType:'text/json')

        }

    }


    /**
     * 附件上傳及清單（顯示在 iframe 頁框內）
     */
    @Secured(['ROLE_OPERATOR','ROLE_MANERGER'])
    def list= {
        // File dir = new File("${fileLocation}/${params.name}");

        render (template:"list", model: [
            name: params.name,
            mainImage: params.mainImage,
            // files: dir.listFiles()
            files: s3Service.getObjectList("${grailsApplication.config.grails.aws.root}/${params.name}")
        ])

    }

    /**
     * 讀取附件
     */
    def show= {

        def file = params.file

        // 將已編碼 URL 還原
        file = URLDecoder.decode(file)

        try {

            // File object = new File("${fileLocation}/${post.name}/${file}")

            def object = s3Service.getObject("${grailsApplication.config.grails.aws.root}/${params.name}/${file}")
            response.outputStream << object.dataInputStream
        }
        catch (e) {
            
            e.printStackTrace()
            log.error "Could not read ${file}"
            // response.sendError 404
        }
    }

    @Secured(['ROLE_OPERATOR','ROLE_MANERGER'])
    def delete= {

        // def file = new File(params.file);
        try {
            // file.delete();

            s3Service.deleteObject "${params.file}"

            return render(text: [success:true] as JSON, contentType:'text/json')
        }
        catch (e) {
            log.error "Could not read ${file}"
            e.printStackTrace()
            return render(text: [success:false] as JSON, contentType:'text/json')
        }
        
        
    }

}
