package edu.coreUtil.imagensUtil;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 * 
 * @author eduardo
 *
 */
public class ImagensUtil {

	/**
	 * 
	 * @RedimensionarImagemEmPixel : redimenciona a imagem pegando o getWidth/2
	 *                             e imagem.getHeight()/2.
	 * 
	 * @param fileEntra
	 * @param fileDestinoO
	 * @param tipoImagem
	 * @throws IOException
	 */

	protected void RedimensionarImagem(File fileEntra,File fileDestino,TipoImagem tipoImagem) throws IOException{

		BufferedImage imagem = ImageIO.read(new File(fileEntra.getAbsolutePath()));

		BufferedImage novaImagem = new BufferedImage(imagem.getWidth() / 2, imagem.getHeight() / 2, imagem.getType());

		Graphics2D g2d = novaImagem.createGraphics();

		g2d.drawImage(imagem, 0, 0, imagem.getWidth() / 2, imagem.getHeight() / 2, null);

		g2d.dispose();

		ImageIO.write(novaImagem, tipoImagem.toString(), new File(fileDestino.getAbsolutePath()));
	}

	/**
	 * 
	 * @RedimensionarImagemEmPixel : redimenciona a imagem passando os pixel por
	 *                             parametro
	 * 
	 * @param fileEntra
	 * @param fileDestino
	 * @param tipoImagem
	 * @param pixelW
	 * @param pixelH
	 * @throws IOException
	 */

	protected void RedimensionarImagemEmPixel(File fileEntra,File fileDestino,TipoImagem tipoImagem,int pixelW,int pixelH) throws IOException{

		BufferedImage imagem = ImageIO.read(new File(fileEntra.getAbsolutePath()));

		AffineTransform transform = AffineTransform.getScaleInstance(pixelW, pixelH);

		BufferedImage novaImagem = new BufferedImage(pixelW, pixelH, imagem.getType());

		Graphics2D g2d = novaImagem.createGraphics();

		g2d.drawImage(imagem, transform, null);

		g2d.dispose();

		ImageIO.write(novaImagem, tipoImagem.toString(), new File(fileDestino.getAbsolutePath()));
	}

	/**
	 * Metodo: que escreve na Imagem um texto qualquer
	 * 
	 * @param fileEscreveImage
	 * @param job
	 * @param numeroPagina
	 * @return
	 * @throws IOException
	 */

	protected boolean escreveImagem(File fileEscreveImage,String strTexto,TipoImagem tipoImagem) throws IOException{

		BufferedImage image = ImageIO.read(new File(fileEscreveImage.getCanonicalPath()));

		Graphics g = image.getGraphics();

		g.setFont(g.getFont().deriveFont(30f));

		g.setColor(Color.WHITE);

		g.drawString(strTexto, image.getWidth() / 6, image.getHeight() / 6);

		g.dispose();

		File f = new File("C:\\Users\\eduardo\\Desktop\\xxxx." + tipoImagem);

		if (ImageIO.write(image, tipoImagem.toString(), f)) {

			return true;

		}
		else {

			return false;
		}
	}

	/**
	 * Metodo: calcular a proproção da imagem e gerar uma nova imagem
	 * 
	 * 
	 * @param arquivo
	 * @param pixel
	 * @throws IOException
	 */

	protected void calcularProporcaoImagem(File arquivo,File destino,double width,double height,TipoImagem tipoImagem)
			throws IOException{

		BufferedImage imagem = ImageIO.read(new File(arquivo.getAbsolutePath()));

		double originalWidth = imagem.getWidth();

		double originalHeight = imagem.getHeight();

		double newWidth = 0;

		double newHeight = 0;

		double diff = 0;

		if (width == 0) {

			width = width;
		}

		if (height == 0) {

			height = height;
		}

		if (originalWidth < width && originalHeight < height) {

			read(arquivo.getAbsolutePath());
		}

		if (width == 0 && height == 0) {

			read(arquivo.getAbsolutePath());
		}

		if (originalWidth > originalHeight) {

			diff = originalWidth - originalHeight;

			newWidth = width;

			diff = diff / originalWidth;

			newHeight = newWidth - (newWidth * diff);

		}
		else if (originalWidth < originalHeight) {

			diff = originalHeight - originalWidth;

			newHeight = height;

			diff = diff / originalHeight;

			newWidth = newHeight - (newHeight * diff);

		}
		else {

			if (height > width) {

				width = width;

			}
			else if (height < width) {

				width = width;
			}

			newHeight = height;

			newWidth = width;
		}
		int type = BufferedImage.TYPE_INT_RGB;

		boolean isPng = arquivo.getName().toUpperCase().endsWith("PNG");

		if (isPng) {

			type = BufferedImage.BITMASK;
		}

		BufferedImage new_img = new BufferedImage((int) newWidth, (int) newHeight, type);

		Graphics2D g = new_img.createGraphics();

		g.setComposite(AlphaComposite.Src);

		g.drawImage(imagem, 0, 0, (int) newWidth, (int) newHeight, null);

		File saida = new File(destino.getAbsolutePath() + "\\new" + arquivo.getName());

		ImageIO.write(new_img, tipoImagem.toString(), saida);

	}

	private void read(String file) throws IOException{

		byte[] buffer = new byte[1024];

		InputStream is = new FileInputStream(file);

		ByteArrayOutputStream out = new ByteArrayOutputStream();

		while (is.read(buffer) != -1) {

			out.write(buffer);
		}

		is.close();

		out.close();

	}

	/**
	 * metodo: rotacionarImagem
	 * 
	 * @param rotacionarImagem
	 * @param angulo
	 * @throws IOException
	 * 
	 */
	protected void rotacionarImagem(File rotacionarImagem,File destino,double angulo,TipoImagem tipoImagem)
			throws IOException{

		BufferedImage image = ImageIO.read(new File(rotacionarImagem.getCanonicalPath()));

		int w = image.getWidth(), h = image.getHeight();

		GraphicsConfiguration gc = getDefaultConfiguration();

		BufferedImage result = gc.createCompatibleImage(w, h);

		Graphics2D g = result.createGraphics();

		g.rotate(Math.toRadians(angulo), w / 2, h / 2);

		g.drawRenderedImage(image, null);

		g.dispose();

		File saida = new File(destino.getAbsolutePath() + "\\new" + rotacionarImagem.getName());

		ImageIO.write(result, tipoImagem.toString(), saida);

	}

	private static GraphicsConfiguration getDefaultConfiguration(){

		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

		GraphicsDevice gd = ge.getDefaultScreenDevice();

		return gd.getDefaultConfiguration();
	}

	protected void recortaImagem(File entrada,File Saida,TipoImagem tipoImagem) throws IOException{

		BufferedImage originalImgage = ImageIO.read(entrada);

		System.out.println("Original Image Dimension: "+originalImgage.getWidth()+"x"+originalImgage.getHeight());
		
		BufferedImage SubImgage = originalImgage.getSubimage(1408, 1081, 375, 189);

		File outputfile = new File(Saida.getAbsolutePath() + "\\new_" + entrada.getName());

		ImageIO.write(SubImgage, TipoImagem.PNG.toString(), outputfile);

	}
}
