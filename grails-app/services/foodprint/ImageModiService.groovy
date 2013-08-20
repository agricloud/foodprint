package foodprint
import java.awt.image.BufferedImage 
import javax.imageio.*
import javax.imageio.stream.ImageOutputStream;



 



class ImageModiService {

    def ByteArrayOutputStream sizeMiddle(InputStream uploaded) {


        BufferedImage originalImage = ImageIO.read( uploaded )

        return compress(originalImage)

        // BufferedImage originalImage = ImageIO.read( uploaded )
        // BufferedImage thumbnail = Thumbnails.of(originalImage)
	       //  .scale(0.2f)
	       //  .outputQuality(0.1)
	       //  .outputFormat("gif")
	       //  .asBufferedImage();

        // ImageIO.write( thumbnail, "png", tagetFile)
    }

    def ByteArrayOutputStream compress (BufferedImage image ){
		
    	ByteArrayOutputStream os = new ByteArrayOutputStream();
		
		float quality = 0.1f;
		
		// create a BufferedImage as the result of decoding the supplied InputStream
		// BufferedImage image = ImageIO.read(is);
		
		// get all image writers for JPG format
		Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName("jpg");
		
		if (!writers.hasNext())
			throw new IllegalStateException("No writers found");
		
		ImageWriter writer = (ImageWriter) writers.next();
		ImageOutputStream ios = ImageIO.createImageOutputStream(os);
		writer.setOutput(ios);
		
		ImageWriteParam param = writer.getDefaultWriteParam();
		
		// compress to a given quality
		param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
		param.setCompressionQuality(quality);
		
		// appends a complete image stream containing a single image and
	    //associated stream and image metadata and thumbnails to the output
		writer.write(null, new IIOImage(image, null, null), param);
		
		// log.info os.size

		return os
    }
}
