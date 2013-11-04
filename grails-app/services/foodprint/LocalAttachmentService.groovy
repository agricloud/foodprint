package foodprint



class LocalAttachmentService {

	def fileLocation 
	def blankImg

    def save={ def params ->

        def result = [:]        
        
        checkAndCreate(new File("${fileLocation}/${params.domainName}"));

        File uploadedFile = new File("${fileLocation}/${params.domainName}/${params.domainId}.jpg")

        def f = params.file
        if (f.empty) {
            flash.message = 'file cannot be empty'
            result.success=false
        }else {
	        f.transferTo(uploadedFile)
	        result.success=true
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

        try {
        	def file = new File("${fileLocation}/${params.domainName}/${params.id}.jpg");
            file.delete();
            result.success=true
        }
        catch (e) {
            log.error "Could not read ${file}"
            e.printStackTrace()
            result.success=false
           
        }finally{
        	return result
        }

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
