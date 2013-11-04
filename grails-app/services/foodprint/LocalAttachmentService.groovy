package foodprint



class LocalAttachmentService {

	def fileLocation 
	def blankImg

    def save={ def params ->

        def result = [:]
        result.text = [:]

        
        
        checkAndCreate(new File("${fileLocation}/${params.domainName}"));

        File uploadedFile = new File("${fileLocation}/${params.domainName}/${params.domainId}.jpg")

        def f = params.file
        if (f.empty) {
            flash.message = 'file cannot be empty'
            result.text.success=false
        }else {

	        f.transferTo(uploadedFile)
	        // response.sendError(200, 'Done')
	        result.text.success=true
	    }

        return result
    }



    def show= { def params ->

        try {

            println "read file: ${fileLocation}/${params.domainName}/${params.id}.jpg"

            File object = new File("${fileLocation}/${params.domainName}/${params.id}.jpg")

            if(object.exists())
                 return new FileInputStream(object);    
            else {
            	return new FileInputStream(blankImg);  
            }
        }
        catch (e) {
            
            e.printStackTrace()
            log.error "Could not read ${file}"
            return new FileInputStream(blankImg);
            
        }
    }

    def delete= { def params ->

    	def result = [:]
        result.text=[:]

        def file = new File("${fileLocation}/${params.domainName}/${params.id}.jpg");
        try {
            file.delete();
            result.text.success=true
        }
        catch (e) {
            log.error "Could not read ${file}"
            e.printStackTrace()
            result.text.success=false
           
        }

        return result
        
        
    }

     private def checkAndCreate(File storagePathDirectory) {
      if (!storagePathDirectory.exists()) {
        if (storagePathDirectory.mkdirs()) {
          log.info " folder create SUCCESS"
        } else {
          log.info " folder create FAILED"
        }
      }
    }
}
