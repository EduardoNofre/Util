/**
 * 
 */
package edu.coreUtil.pdfUtil;

/**
 * @author eduardo
 *
 */
public class ServicoPdfUtil {

	/**
	 * Metodo que insere um texto qualquer em um pdf existente
	 * 
	 * @param filePdf
	 * @param insereTexto
	 * @param fontSizes
	 * @param rotacaoTexto
	 */

	public void insereTextoNoPdf(String filePdf,String insereTexto,float fontSizes,int rotacaoTexto,double posX,
			double posY){

		PdfUtil pdfUtil = new PdfUtil();

		pdfUtil.insereTextoNoPdf(filePdf, insereTexto, fontSizes, rotacaoTexto, posX, posY);
	}

	/**
	 * To Do a Fazer
	 * 
	 */
	public void insereImagemNoPdf(){

	}

	public String procuraTextoPdf(String filePdf,String buscaTexto){
		
		return null;

	}

}
