/**
 * 
 */
package edu.coreUtil.thumbs;

import java.awt.Container;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * @author eduardo
 *
 */
public class GerarThumbs {

	/**
	 * 
	 * 
	 * @param imagemOriginal
	 * @return
	 * @throws InterruptedException
	 * @throws IOException
	 */

	protected byte[] geraThumbs(File imagemOriginal) throws InterruptedException,IOException{

		GerarThumbs thumb = new GerarThumbs();

		Image imagem = Toolkit.getDefaultToolkit().createImage(thumb.converterArquivoEmArrayDeBytes(imagemOriginal));

		MediaTracker mediaTracker = new MediaTracker(new Container());

		mediaTracker.addImage(imagem, 0);

		mediaTracker.waitForID(0);

		// define a qualidade da imagem
		int qualidade = 100; // 100%

		// define a largura e altura do thumbnail
		int largura = 150;

		int altura = 100;

		double thumbRatio = (double) largura / (double) altura;

		int larguraImagem = imagem.getWidth(null);

		int alturaImagem = imagem.getHeight(null);

		double imageRatio = (double) larguraImagem / (double) alturaImagem;

		if (thumbRatio < imageRatio) {

			altura = (int) (largura / imageRatio);
		}
		else {

			largura = (int) (altura * imageRatio);
		}
		// Desenha a imagem original para o thumbnail e
		// redimensiona para o novo tamanho

		BufferedImage thumbImage = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_RGB);

		Graphics2D graphics2D = thumbImage.createGraphics();

		graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

		graphics2D.drawImage(imagem, 0, 0, largura, altura, null);

		// Salva a nova imagem
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);

		JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(thumbImage);

		param.setQuality((float) qualidade / 100.0f, false);

		encoder.setJPEGEncodeParam(param);

		encoder.encode(thumbImage);

		byte[] imagemThumb = out.toByteArray();

		out.close();

		return imagemThumb;
	}

	protected byte[] converterArquivoEmArrayDeBytes(File arquivo) throws IOException{

		FileInputStream fileInputStream = null;

		byte[] arrayDeBytesFile = new byte[(int) arquivo.length()];

		fileInputStream = new FileInputStream(arquivo);

		fileInputStream.read(arrayDeBytesFile);

		fileInputStream.close();

		return arrayDeBytesFile;

	}

}
