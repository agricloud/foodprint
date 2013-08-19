package foodprint

import grails.converters.JSON
import grails.plugins.springsecurity.Secured

class AttachmentController {

    // def s3Service
    // def imageModiService
    def fileHandleService

    // @Secured(['ROLE_OPERATOR','ROLE_MANERGER'])
    def save={

        def fileLocation=grailsApplication.config.upload.files.path;
        
        fileHandleService.checkAndCreate(new File("${fileLocation}/${params.domainName}"));

        File uploadedFile = new File("${fileLocation}/${params.domainName}/${params.domainId}.jpg")

        def f = request.getFile('file')
        if (f.empty) {
            flash.message = 'file cannot be empty'
            return render(text: [success:false] as JSON, contentType:'text/json')
        }

        f.transferTo(uploadedFile)
        // response.sendError(200, 'Done')

        return render(text: [success:true] as JSON, contentType:'text/json')

        // try {
        //     def fileLocation=grailsApplication.config.upload.files.path;
            
        //     log.info "Attachment file location: ${fileLocation}/${params.name}"

        //     //檢查路徑是否存在，若不存在則產生資料夾
        //     fileHandleService.checkAndCreate(new File("${fileLocation}/${params.name}"));

        //     // 定義上傳的檔案名稱
        //     File uploadedFile = new File("${fileLocation}/${params.name}/${params.file}")
        //     // File compressed = new File("${fileLocation}/${params.name}/${params.qqfile}_compressed")

        //     // 將使用者上傳檔案的 inputStream 指定給 uploaded 完成檔案儲存
        //     // ajaxUploaderService.upload(request.inputStream, uploaded)
        //     try {
	       //      uploadedFile << request.inputStream
	       //  } catch (e) {
        //         log.error("Failed to inputStream trans File", e)
	       //      throw e
	       //  }




        //     return render(text: [success:true] as JSON, contentType:'text/json')

        // } catch (e) {

        //     log.error("Failed to upload file.", e)
        //     return render(text: [success:false] as JSON, contentType:'text/json')

        // }

    }


    /**
     * 附件上傳及清單（顯示在 iframe 頁框內）
     */
    // @Secured(['ROLE_OPERATOR','ROLE_MANERGER'])
    // def list= {
    //     // File dir = new File("${fileLocation}/${params.name}");

    //     render (template:"list", model: [
    //         name: params.name,
    //         mainImage: params.mainImage,
    //         // files: dir.listFiles()
    //         files: s3Service.getObjectList("${grailsApplication.config.grails.aws.root}/${params.name}")
    //     ])

    //     // [
    //     //     content: content,
    //     //     files: s3Service.getObjectList("attachment/${content.lesson?.course?.id}/${content.lesson?.id}/${content.id}")
    //     // ]
        
    // }

    /**
     * 讀取附件
     */
    def show= { Long id ->

        def fileLocation=grailsApplication.config.upload.files.path;

        // def file = params.file

        // // 將已編碼 URL 還原
        // file = URLDecoder.decode(file)

        try {

            log.info "read file: ${fileLocation}/${params.domainName}/${id}.jpg"

            File object = new File("${fileLocation}/${params.domainName}/${id}.jpg")

            // def object = s3Service.getObject("${grailsApplication.config.grails.aws.root}/${params.name}/${file}")
            response.outputStream << new FileInputStream(object);

        }
        catch (e) {
            
            e.printStackTrace()
            log.error "Could not read ${file}"
            // response.sendError 404
        }
    }

    // @Secured(['ROLE_OPERATOR','ROLE_MANERGER'])
    def delete= {

        def file = new File(params.file);
        try {
            file.delete();

            // s3Service.deleteObject "${params.file}"

            return render(text: [success:true] as JSON, contentType:'text/json')
        }
        catch (e) {
            log.error "Could not read ${file}"
            e.printStackTrace()
            return render(text: [success:false] as JSON, contentType:'text/json')
        }
        
        
    }

}
