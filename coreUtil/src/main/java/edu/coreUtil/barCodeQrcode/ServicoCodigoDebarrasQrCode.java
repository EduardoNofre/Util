package edu.coreUtil.barCodeQrcode;

import java.awt.Frame;
import java.awt.ScrollPane;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.output.OutputException;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.WriterException;
import com.itextpdf.text.DocumentException;

public class ServicoCodigoDebarrasQrCode extends Frame {

	private static final long				serialVersionUID								= 1L;

	DesenharRetanguloQrCode					desenharRetanguloQrCode					= null;

	DesenharRetanguloCodigoDebarras	desenharRetanguloCodigoDebarras	= null;

	BarCodeQrCode										barCodeQrCode										= null;

	/**
	 * 
	 * Leitura do codigo de barras as posiçoes int rectPosx, int rectPosy, int
	 * rectW, int recth serve para criar uma sub Imagem da imagem original
	 * 
	 * @param caminhoDoArquivoStr
	 * @param rectPosx
	 * @param rectPosy
	 * @param rectW
	 * @param recth
	 * @throws IOException
	 * @throws NotFoundException
	 * @throws ChecksumException
	 * @throws FormatException
	 * 
	 */

	public ServicoCodigoDebarrasQrCode() {
	}

	/**
	 * 
	 * leitura do leituraBarCode
	 * 
	 * @TesteStatus: OK
	 * 
	 * @param caminhoDoArquivoStr
	 * @param rectPosx
	 * @param rectPosy
	 * @param rectW
	 * @param recth
	 * @throws IOException
	 */

	public void leituraCodigoDebarras(String caminhoDoArquivoStr, int rectPosx, int rectPosy, int rectW, int recth) throws IOException, NotFoundException, ChecksumException, FormatException {

		BufferedImage image = ImageIO.read(new File(caminhoDoArquivoStr));

		BufferedImage cropedImage = image.getSubimage(rectPosx, rectPosy, rectW, recth);

		barCodeQrCode = new BarCodeQrCode();

		barCodeQrCode.codigoDeBarras(cropedImage);

	}

	/**
	 * 
	 * leitura do QrCode
	 * 
	 * @TesteStatus: OK
	 * 
	 * @param caminhoDoArquivoStr
	 * @param rectPosx
	 * @param rectPosy
	 * @param rectW
	 * @param recth
	 * @throws IOException
	 * 
	 */

	public void leituraCodicoQRCode(String caminhoDoArquivoStr, int rectPosx, int rectPosy, int rectW, int recth) throws IOException {

		BufferedImage image = ImageIO.read(new File(caminhoDoArquivoStr));

		BufferedImage cropedImage = image.getSubimage(rectPosx, rectPosy, rectW, recth);

		barCodeQrCode = new BarCodeQrCode();

		barCodeQrCode.QRcode(cropedImage);

	}

	/**
	 * @Mapeamento Exibe a imagem em um Jframe para fazer o retangulo da image
	 *             codigo de barras
	 * 
	 * @TesteStatus: OK
	 * 
	 * @param caminhoDoArquivoStr
	 * @throws IOException
	 * 
	 */

	public void mapearCodigoDebarrasCarregaImagemNoFrame(String caminhoDoArquivoStr) throws IOException {

		BufferedImage image = ImageIO.read(new File(caminhoDoArquivoStr));

		desenharRetanguloCodigoDebarras = new DesenharRetanguloCodigoDebarras(image);

		ScrollPane scrollPane = new ScrollPane();

		scrollPane.add(desenharRetanguloCodigoDebarras);

		setSize(image.getWidth(), image.getHeight());

		setResizable(true);

		add(scrollPane);

		setResizable(false);

		setVisible(true);

		setLocation(200, 200);

		addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent e) {

				dispose();

				System.exit(0);
			}
		});
	}

	/**
	 * @Mapeamento Exibe a imagem em um Jframe para fazer o retangulo da image
	 *             QRcode
	 * 
	 * @TesteStatus: OK
	 * 
	 * @param caminhoDoArquivoStr
	 * @throws IOException
	 * 
	 */

	public void mapearQrCodeCarregaImagemNoFrame(String caminhoDoArquivoStr) throws IOException {

		BufferedImage image = ImageIO.read(new File(caminhoDoArquivoStr));

		desenharRetanguloQrCode = new DesenharRetanguloQrCode(image);

		ScrollPane scrollPane = new ScrollPane();

		scrollPane.add(desenharRetanguloQrCode);

		setSize(image.getWidth(), image.getHeight());

		add(scrollPane);

		setResizable(false);

		setVisible(true);

		setLocation(200, 200);

		addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent e) {

				dispose();

				System.exit(0);
			}
		});
	}

	/**
	 * 
	 * Gera o QrCode o mesmo retorna um BufferedImage
	 * 
	 * @TesteStatus: OK
	 * 
	 * @param valorQr
	 * @param tipoQrCodeEnum
	 * @param width
	 * @param height
	 * @param destinoSaidaImage
	 * @param nomeImagem
	 * @param tipoImagem
	 * @throws WriterException
	 * @throws IOException
	 * 
	 * 
	 */

	public void gerarQrcodeImageSaida(String valorQr, BarcodeFormat tipoQrCodeEnum, int width, int height, String destinoSaidaImage, String nomeImagem, TipoImagem tipoImagem) throws WriterException, IOException {

		barCodeQrCode = new BarCodeQrCode();

		String caminhoImg = destinoSaidaImage + nomeImagem + "." + tipoImagem;

		File file = new File(caminhoImg);

		file.createNewFile();

		File arquivoQrCode = new File(caminhoImg);

		BufferedImage bfi = barCodeQrCode.gerarQrCodeImageSaida(valorQr, tipoQrCodeEnum, width, height);

		ImageIO.write(bfi, tipoImagem.toString(), arquivoQrCode);

	}

	/**
	 * gera o codigo de barras
	 * 
	 * @param codigoBarrasValor
	 * @param padraoCodigoDebarras
	 * @throws OutputException
	 * @throws BarcodeException
	 * @throws DocumentException
	 * @throws IOException
	 * 
	 */

	public void gerarCodigoDeBarrasImageSaida(String codigoBarrasValor, PadraoCodigoDebarras padraoCodigoDebarras, int widthCodigoDebarras, int heighCodigoDebarras, String saidaImagemCodigoDebarras, String nomeArquivo, TipoImagem tipoArquivo)
			throws IOException, DocumentException, BarcodeException, OutputException {

		barCodeQrCode = new BarCodeQrCode();

		barCodeQrCode.gerarCodigoDebarrasImageSaida(codigoBarrasValor, padraoCodigoDebarras, widthCodigoDebarras, heighCodigoDebarras, saidaImagemCodigoDebarras, nomeArquivo, tipoArquivo.GIF);

	}

}
