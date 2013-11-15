package common

import grails.test.mixin.*
import org.junit.*


@TestFor(ImageModiService)
class ImageModiServiceTests {


    void testImageScaleNormal() {
		InputStream inputStream = new FileInputStream("test.jpg");
        
        def byteArrayOutputStream=service.sizeNormal(inputStream)
        OutputStream outputStream = new FileOutputStream ("normal.jpg"); 
        byteArrayOutputStream.writeTo(outputStream);
    }
    void testImageScaleThumbnail() {
		InputStream inputStream = new FileInputStream("test.jpg");
        
        def byteArrayOutputStream=service.sizeThumbnail(inputStream)
        OutputStream outputStream = new FileOutputStream ("thumbnail.jpg"); 
        byteArrayOutputStream.writeTo(outputStream);
    }
}