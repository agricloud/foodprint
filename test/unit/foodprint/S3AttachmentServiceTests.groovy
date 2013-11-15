package foodprint

import grails.test.mixin.*
import org.junit.*
import common.*


@TestFor(S3AttachmentService)
@Mock([S3Service, ImageModiService])
class S3AttachmentServiceTests {
	

    void testS3Save() {
    	service.fileLocation = "test"

		def params =[:]
		params.file = [:]
		params.file.inputStream = new FileInputStream("test.jpg");
		params.domainName = "unitTest"
		params.domainId = "1"
        
        assert service.save(params).success
    }
    void testS3ShowAndScaleToFile() {
    	service.fileLocation = "test"

		def params =[:]
		params.domainName = "unitTest"
		params.id = "1"
        
        def s3Image = service.show(params) //dataInputStream
        assert s3Image	


        def imageModiService = new ImageModiService();
        InputStream inputStream = s3Image;
        def byteArrayOutputStream=imageModiService.sizeNormal(inputStream)
        OutputStream outputStream = new FileOutputStream ("S3-normal.jpg"); 
        byteArrayOutputStream.writeTo(outputStream);

    }
    void testS3ShowAndScaleToS3() {
    	service.fileLocation = "test"

		def params =[:]
		params.domainName = "unitTest"
		params.id = "1"
        
        def s3Image = service.show(params) //dataInputStream
        assert s3Image	


        def imageModiService = new ImageModiService();
        InputStream inputStream = s3Image;
        def byteArrayOutputStream=imageModiService.sizeNormal(inputStream)



		ByteArrayInputStream inputStreamScaled = new ByteArrayInputStream(byteArrayOutputStream.toByteArray())
		params.file = [:]
		params.file.inputStream = inputStreamScaled
		params.domainName = "unitTest"
		params.domainId = "1-normal"
        
        assert service.save(params).success

    }

}