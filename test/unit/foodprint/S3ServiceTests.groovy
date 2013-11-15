package foodprint

import grails.test.mixin.*
import org.junit.*


@TestFor(S3Service)
class S3ServiceTests {
	

    void testS3List() {

    	def filelist = service.getObjectList("attachment/item")
    	filelist.each(){
    		println it.name
    	}

    }
    
}