package edu.coreUtil.imagensUtil;

import java.io.File;
import java.io.IOException;

public class ServicoImagensUtil {

	/**
	 * 
	 * @RedimensionarImagemEmPixel : redimenciona a imagem pegando o getWidth/2
	 *                             e imagem.getHeight()/2.
	 * 
	 * @param fileEntra
	 * @param fileDestino
	 * @param tipoImagem
	 * @throws IOException
	 */

	public void RedimensionarImagem(File fileEntra,File fileDestino,TipoImagem tipoImagem){

		ImagensUtil util = new ImagensUtil();

		try {

			util.RedimensionarImagem(fileEntra, fileDestino, tipoImagem.JPG);

		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @RedimensionarImagemEmPixel : redimenciona a imagem passando os pixel por
	 *                             parametro
	 * @param fileEntra
	 * @param fileDestino
	 * @param tipoImagem
	 * @param pixelW
	 * @param pixelH
	 * @throws IOException
	 */

	public void RedimensionarImagemEmPixel(File fileEntra,File fileDestino,TipoImagem tipoImagem,int pixelW,int pixelH){

		ImagensUtil util = new ImagensUtil();

		try {

			util.RedimensionarImagemEmPixel(fileEntra, fileDestino, tipoImagem, pixelW, pixelH);

		} catch (IOException e) {

			e.printStackTrace();
		}
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

	public boolean escreveImagem(File fileEscreveImage,String strTexto,TipoImagem tipoImagem){

		ImagensUtil util = new ImagensUtil();

		boolean desenhou = false;

		try {

			desenhou = util.escreveImagem(fileEscreveImage, strTexto, tipoImagem);

		} catch (IOException e) {

			e.printStackTrace();
		}

		return desenhou;
	}

	/**
	 * Metodo: calcular a proproção da imagem e gerar uma nova imagem
	 * 
	 * 
	 * formula : LI / AI * NAI = NLI
	 * 
	 * LI: Largura Original da Imagem AI: Altura Original da Imagem NLI: Nova
	 * Largura da Imagem NAI: Nova Altura da Imagem
	 * 
	 * @param arquivo
	 * @param pixel
	 * @throws IOException
	 */

	public void calcularProporcaoImagem(File entraArquivo,File saidaArquivo,double width,double height,
			TipoImagem tipoImagem){

		ImagensUtil util = new ImagensUtil();

		try {

			util.calcularProporcaoImagem(entraArquivo, saidaArquivo, width, height, tipoImagem);

		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	/**
	 * metodo: para rotacionar imagem
	 * 
	 * Exemplo: angulo em graus 90º,180º,270º,360
	 * 
	 * @param rotacionarImagem
	 * @param angle
	 * 
	 */

	public void rotacionarImagem(File rotacionarImagem,File destino,double angulo,TipoImagem tipoImagem){

		ImagensUtil util = new ImagensUtil();

		try {

			util.rotacionarImagem(rotacionarImagem, destino, angulo, tipoImagem);

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public void recortaImagem(File entrada,File Saida,TipoImagem tipoImagem){

		ImagensUtil util = new ImagensUtil();

		try {

			util.recortaImagem(entrada, Saida, tipoImagem);

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public static void main(String[] args){

		ServicoImagensUtil servico = new ServicoImagensUtil();

		TipoImagem tipo;

		File en = new File("C:\\Users\\eduardo\\Desktop\\003.jpg");

		File sa = new File("C:\\Users\\eduardo\\Desktop\\");

		servico.recortaImagem(en, sa,TipoImagem.PNG);
	}

}
