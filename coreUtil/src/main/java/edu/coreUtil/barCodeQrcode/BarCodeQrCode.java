package edu.coreUtil.barCodeQrcode;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.output.OutputException;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.EncodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Reader;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.itextpdf.text.DocumentException;

/**
 * @author eduardo 30-06-2016
 *
 */

public class BarCodeQrCode {

	/**
	 * 
	 * Metodo - lerCodigoDeBarras: A finalidade deste metedo e fazer a leiturra do
	 * codigo de barras
	 * 
	 * 
	 * @param caminhoDir
	 * @return
	 * @throws IOException
	 * @throws NotFoundException
	 * @throws ChecksumException
	 * @throws FormatException
	 * 
	 */

	protected Result lerCodigoDeBarras(BufferedImage image, Rectangle rect) throws IOException, NotFoundException, ChecksumException, FormatException {

		int rectW = (int) rect.getWidth();

		int recth = (int) rect.getHeight();

		int rectPosx = (int) rect.getX();

		int rectPosy = (int) rect.getY();

		System.out.println("rectPosx = " + rectPosx + " rectPosy = " + rectPosy + " rectW = " + rectW + " recth " + recth);

		BufferedImage cropedImage = image.getSubimage(rectPosx, rectPosy, rectW, recth);

		Result result = this.codigoDeBarras(cropedImage);

		System.out.println(result);

		return result;

	}

	/**
	 * Leitura do codigo de codigoDeBarras
	 * 
	 * @TesteStatus: OK
	 * 
	 * @param arquivoQrCode
	 */

	protected Result codigoDeBarras(BufferedImage cropedImage) throws IOException, NotFoundException, ChecksumException, FormatException {

		LuminanceSource source = new BufferedImageLuminanceSource(cropedImage);

		BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

		Reader reader = new MultiFormatReader();

		Result result = null;

		result = reader.decode(bitmap);

		System.out.println(result);

		return result;

	}

	/**
	 * Leitura do codigo de QRCode
	 * 
	 * @param arquivoQrCode
	 */

	protected Result lerQRcode(BufferedImage image, Rectangle rect) throws IOException, NotFoundException, ChecksumException, FormatException {

		int rectW = (int) rect.getWidth();

		int recth = (int) rect.getHeight();

		int rectPosx = (int) rect.getX();

		int rectPosy = (int) rect.getY();

		System.out.println("rectPosx = " + rectPosx + " rectPosy = " + rectPosy + " rectW = " + rectW + " recth " + recth);

		BufferedImage cropedImage = image.getSubimage(rectPosx, rectPosy, rectW, recth);

		Result result = this.codigoDeBarras(cropedImage);

		System.out.println(result);

		return result;

	}

	protected String QRcode(BufferedImage cropedImage) {

		Result result = null;

		BinaryBitmap binaryBitmap;

		try {

			binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(cropedImage)));

			result = new MultiFormatReader().decode(binaryBitmap);

			System.out.println("Valor = " + result);

			return result.getText();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;

	}

	/**
	 * 
	 * 
	 * Metodo gerarCodigoDebarrasImage : Esse metodo gera codigo de barras a
	 * apartir do codigo passado como parametro e o mesmo gera uma imagem na
	 * saida, a saida e indicada pelo proprio usuario o usuario tambem informa o
	 * tipo de codifo de barras a ser usado
	 * 
	 * metodo importantes BarcodeImageHandler.saveGIF(barcode, f2);
	 * BarcodeImageHandler.writeGIF(barcode, os);
	 * 
	 * 
	 * @param codigoBarrasValor
	 * @param padraoCodigoDebarras
	 * @param widthCodigoDebarras
	 * @param heighCodigoDebarras
	 * @param saidaImagemCodigoDebarras
	 * @return
	 * @throws IOException
	 * @throws DocumentException
	 * @throws BarcodeException
	 * @throws OutputException
	 * 
	 */

	protected File gerarCodigoDebarrasImageSaida(String codigoBarrasValor, PadraoCodigoDebarras padraoCodigoDebarras, int widthCodigoDebarras, int heighCodigoDebarras, String saidaImagemCodigoDebarras, String nomeArquivo, TipoImagem tipoArquivo)
			throws IOException, DocumentException, BarcodeException, OutputException {

		Barcode barcode = TipoCodigoBarras.padraoCodigo(padraoCodigoDebarras, codigoBarrasValor);

		barcode.setDrawingText(false);

		BufferedImage image = new BufferedImage(100, 150, BufferedImage.TYPE_BYTE_GRAY);

		Graphics2D g = (Graphics2D) image.getGraphics();

		g.setBackground(Color.BLUE);

		barcode.setBarWidth(widthCodigoDebarras); // comprimento da imagem codigo de
																							// barras
		barcode.setBarHeight(1000); // Altura da imagem codigo de barras

		barcode.draw(g, 100, 560);

		File file = new File(saidaImagemCodigoDebarras + "\\" + nomeArquivo + "." + tipoArquivo);

		SaveImagem.saveTipoImagem(barcode, file, tipoArquivo);

		return file;
	}

	/**
	 * 
	 * Metodo gerarQrImage
	 * 
	 * metodo que gera o QrCode
	 * 
	 * @param QrValor
	 * @return
	 * @throws WriterException
	 * 
	 * 
	 */

	protected BufferedImage gerarQrCodeImageSaida(String valorQr, BarcodeFormat tipoQrCodeEnum, int width, int height) throws WriterException {

		Hashtable hintMap = new Hashtable();

		BufferedImage bufferedImage = null;

		try {

			hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

			QRCodeWriter qrCodeWriter = new QRCodeWriter();

			BitMatrix byteMatrix = qrCodeWriter.encode(valorQr, tipoQrCodeEnum, width, height, hintMap);

			int matrixWidth = byteMatrix.getWidth();

			bufferedImage = new BufferedImage(matrixWidth, matrixWidth, BufferedImage.TYPE_INT_RGB);

			bufferedImage.createGraphics();

			Graphics2D graphics = (Graphics2D)

			bufferedImage.getGraphics();

			graphics.setColor(Color.WHITE);

			graphics.fillRect(0, 0, matrixWidth, matrixWidth);

			graphics.setColor(Color.BLACK);

			for (int i = 0; i < matrixWidth; i++) {

				for (int j = 0; j < matrixWidth; j++) {

					if (byteMatrix.get(i, j)) {

						graphics.fillRect(i, j, 1, 1);
					}
				}
			}

		} catch (Exception e) {

			e.printStackTrace();

			bufferedImage = null;
		}

		return bufferedImage;

	}

	/**
	 * 
	 * 
	 * Metodo gerarCodigoDebarrasImage : Esse metodo gera codigo de barras a
	 * apartir do codigo passado como parametro e o mesmo gera uma imagem na
	 * saida, a saida e indicada pelo proprio usuario o usuario tambem informa o
	 * tipo de codifo de barras a ser usado
	 * 
	 * metodo importantes BarcodeImageHandler.saveGIF(barcode, f2);
	 * BarcodeImageHandler.writeGIF(barcode, os);
	 * 
	 * 
	 * @param codigoBarrasValor
	 * @param padraoCodigoDebarras
	 * @param widthCodigoDebarras
	 * @param heighCodigoDebarras
	 * @param saidaImagemCodigoDebarras
	 * @return
	 * @throws IOException
	 * @throws DocumentException
	 * @throws BarcodeException
	 * @throws OutputException
	 * 
	 */

	protected File codigoDebarras(String codigoBarrasValor, PadraoCodigoDebarras padraoCodigoDebarras, int widthCodigoDebarras, int heighCodigoDebarras, String saidaImagemCodigoDebarras, String nomeArquivo, TipoImagem tipoArquivo) throws IOException,
			DocumentException, BarcodeException, OutputException {

		Barcode barcode = TipoCodigoBarras.padraoCodigo(padraoCodigoDebarras, codigoBarrasValor);

		barcode.setDrawingText(false);

		BufferedImage image = new BufferedImage(100, 150, BufferedImage.TYPE_BYTE_GRAY);

		Graphics2D g = (Graphics2D) image.getGraphics();

		g.setBackground(Color.BLUE);

		barcode.setBarWidth(widthCodigoDebarras); // comprimento da imagem codigo de
																							// barras
		barcode.setBarHeight(1000); // Altura da imagem codigo de barras

		barcode.draw(g, 100, 560);

		File file = new File(saidaImagemCodigoDebarras + "\\" + nomeArquivo + "." + tipoArquivo);

		SaveImagem.saveTipoImagem(barcode, file, tipoArquivo);

		return file;
	}

}
