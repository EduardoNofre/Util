import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.sun.media.jai.codec.FileSeekableStream;
import com.sun.media.jai.codec.ImageCodec;
import com.sun.media.jai.codec.ImageDecoder;
import com.sun.media.jai.codec.ImageEncoder;
import com.sun.media.jai.codec.TIFFEncodeParam;
import com.sun.media.jai.codec.SeekableStream;
import com.sun.media.jai.codec.BMPEncodeParam;

/**
 * @author eduardo.sa
 *
 */

public class BMPtoTIFF {

	public static void main(String[] args) throws IOException {

		File inFile = new File("C:\\Users\\eduardo.sa\\Desktop\\bmp\\001233.bmp");
		
		File otPath = new File("C:\\Users\\eduardo.sa\\Desktop\\bmp\\"+inFile.getName().replace(".bmp", "")+".tiff");

		SeekableStream s = new FileSeekableStream(inFile);
		
		BMPEncodeParam param = null;
		
		ImageDecoder dec = ImageCodec.createImageDecoder("bmp", s, param);
		
		RenderedImage op = dec.decodeAsRenderedImage(0);

		FileOutputStream fos = new FileOutputStream(otPath);
		
		TIFFEncodeParam tiffParam = new TIFFEncodeParam();
		
		ImageEncoder en = ImageCodec.createImageEncoder("TIFF", fos, tiffParam);
		
		
		en.encode(op);
		
		fos.flush();
		
		fos.close();
	}
}
