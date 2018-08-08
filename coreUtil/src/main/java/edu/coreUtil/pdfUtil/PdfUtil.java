/**
 * 
 */
package edu.coreUtil.pdfUtil;

import java.awt.Color;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.common.PDStream;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

/**
 * @author eduardo
 *
 */
public class PdfUtil {
	/**
	 * 
	 * @param filePdf
	 * @param insereTexto
	 * @param fontSizes
	 * @param rotacaoTexto
	 * @param posX
	 * @param posY
	 */
	protected void insereTextoNoPdf(String filePdf,String insereTexto,float fontSizes,int rotacaoTexto,double posX,double posY){

		PDDocument document = null;

		try {
			document = PDDocument.load(filePdf);

			if (document.isEncrypted()) {

				document.decrypt("");
			}

			List allPages = document.getDocumentCatalog().getAllPages();

			PDPage page = (PDPage) allPages.get(0);
			// Remove eventuais datas se houver, sempre na primeira pagina
			PDStream contents = page.getContents();

			PDFont font = PDType1Font.HELVETICA;

			String messageToWrite = insereTexto;

			if (contents != null) {

				PDPageContentStream contentStream = new PDPageContentStream(document, page, true, true);

				contentStream.setFont(font, fontSizes);

				contentStream.beginText();

				contentStream.setNonStrokingColor(Color.black);

				PDRectangle pageSize = page.findMediaBox();

				float stringWidth = font.getStringWidth(messageToWrite);

				float centeredYPosition = (pageSize.getHeight() - (stringWidth * fontSizes) / 1000f) / 3f;

				contentStream.endText();

				contentStream.beginText();

				contentStream.setTextRotation(rotacaoTexto * Math.PI * 0.25, 608, 760);

				contentStream.drawString(messageToWrite);

				contentStream.endText();

				contentStream.close();

				document.save(filePdf.toString());

				document.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String procuraTextoPdf(String filePdf,String buscaTexto){

		PDDocument document = null;

		try {

			document = PDDocument.load(filePdf);

			if (document.isEncrypted()) {

				document.decrypt("");
			}

			List allPages = document.getDocumentCatalog().getAllPages();

			for (int pagina = 0; pagina < allPages.size(); pagina++) {

				PDPage page = (PDPage) allPages.get(pagina);
				
				
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}
