package edu.coreUtil.ocrImagens;

import java.awt.Frame;
import java.awt.ScrollPane;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * 
 * @author Eduardo Nofre
 *
 */

public class ServicoOcrImagem extends Frame {

	private static final long	serialVersionUID	= 1L;

	/**
	 * @Step-1 - principal
	 * 
	 * @Step-2 - DesenharRetanguloBarCodeQrCode
	 * 
	 * @Step-3 - OCR
	 * 
	 * @PrincipalDesenharRetanguloImagemOcr: Desenha o retangulo na imagem para
	 *                                       pode capturar as dimensoes do mesmo e
	 *                                       fazer o OCR da região mapeada.
	 *
	 * @carrega: As seguintes imagens png, jpg, jpeg e tif
	 *
	 * @param caminhoDoArquivoStr
	 * @throws IOException
	 * 
	 */

	public ServicoOcrImagem(String caminhoDoArquivoStr) throws IOException {

		BufferedImage image = ImageIO.read(new File(caminhoDoArquivoStr));

		DesenharRetanguloImagemOcr desenharRetanguloImagemOcr = new DesenharRetanguloImagemOcr(image);

		ScrollPane scrollPane = new ScrollPane();

		scrollPane.add(desenharRetanguloImagemOcr);

		setSize(image.getWidth(), image.getHeight());

		add(scrollPane);

		setVisible(true);

		setLocation(200, 200);

		addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent e) {

				dispose();

				System.exit(0);
			}
		});
	}

}
