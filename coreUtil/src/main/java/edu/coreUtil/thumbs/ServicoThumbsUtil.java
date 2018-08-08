/**
 * 
 */
package edu.coreUtil.thumbs;

import java.io.File;
import java.io.IOException;

/**
 * @author eduardo
 *
 */
public class ServicoThumbsUtil {

	/**
	 * 
	 * Metodo: para gerar o thumbs
	 * 
	 * @param arquivo
	 * @return
	 * @throws InterruptedException
	 * @throws IOException
	 * 
	 */

	public byte[] geraThumbs(File arquivo) throws InterruptedException,IOException{

		GerarThumbs gerarThumbs = new GerarThumbs();

		return gerarThumbs.geraThumbs(arquivo);
	}

	/**
	 * 
	 * Metodo: converte um file para um array de []bytes
	 * 
	 * @param arquivo
	 * @return
	 * @throws IOException
	 * 
	 */
	
	public byte[] converterArquivoEmArrayDeBytes(File arquivo) throws IOException{

		GerarThumbs gerarThumbs = new GerarThumbs();

		return gerarThumbs.converterArquivoEmArrayDeBytes(arquivo);

	}
}
