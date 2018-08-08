package edu.coreUtil.ocrPDF;

import java.awt.Frame;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.ghost4j.renderer.RendererException;

import com.itextpdf.text.DocumentException;

public class ServicoOcrPdf extends Frame {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	public ServicoOcrPdf() {
	}

	/**
	 * metodo coordenadasPdf Metodo que retorna a valor de acordo com as
	 * coordenadas passada
	 * 
	 * 
	 * @TesteStatus:
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param document
	 * @param indexPagina
	 * @return
	 * @throws IOException
	 * 
	 */

	public String coordenadasPdf(int x, int y, int width, int height, PDDocument document, int indexPagina) throws IOException {

		PdfUtil pdfUtil = new PdfUtil();

		return pdfUtil.coordenadasPdf(x, y, width, height, document, indexPagina);
	}

	/**
	 * Metodo: separarPdf separa os pdf
	 * 
	 * @TesteStatus:
	 * 
	 * @param readerPdf
	 * @param pdfSplitado
	 * @param indexPagina
	 * @throws IOException
	 * @throws DocumentException
	 * 
	 */

	public void separarPdf(File filePdf, String saida, String nomeArquivo, int indexPagina) throws IOException, DocumentException {

		PdfUtil pdfUtil = new PdfUtil();

		pdfUtil.separarPdf(filePdf, saida, nomeArquivo, indexPagina);

	}

	/**
	 * Metodo: mergePdf junta os pdf aparti do mesmo pdf array
	 * 
	 * 
	 * @TesteStatus:
	 * 
	 * @param readerPdf
	 * @param indexPaginaLista
	 * @throws DocumentException
	 * @throws IOException
	 * 
	 * 
	 */

	public void juntaPdf(File filePdf, List<Integer> indexPaginaLista, String saida, String nomeArquivo) throws DocumentException, IOException {

		PdfUtil pdfUtil = new PdfUtil();

		pdfUtil.juntaPdf(filePdf, indexPaginaLista, saida, nomeArquivo);

	}

	/**
	 * retorna o total de paginas do pdf
	 * 
	 * 
	 * @TesteStatus:
	 * 
	 * @param capa
	 * @param miolo
	 * @param introducao
	 * @return
	 */

	public int contaNumeroPaginasPdf(File pdfPaginas) {

		PdfUtil pdfUtil = new PdfUtil();

		return pdfUtil.contaNumeroPaginasPdf(pdfPaginas);
	}

	public void convertePdfPara(File pdfFile, String destinoSaida, int paginaIndex, String nomeArquivo, PdfTipoImagem pdfTipoImagem) throws FileNotFoundException, IOException, RendererException, org.ghost4j.document.DocumentException {

		PdfUtil pdfUtil = new PdfUtil();

		pdfUtil.convertePdfParaOutrosFormtatos(pdfFile, destinoSaida, paginaIndex, nomeArquivo, pdfTipoImagem);
	}

	/**
	 * @Mapeamento Exibe a imagem em um Jframe para fazer o retangulo da image
	 *             codigo de barras
	 * 
	 * @TesteStatus: OK
	 * 
	 * @param caminhoDoArquivoStr
	 * @throws IOException
	 * @throws org.ghost4j.document.DocumentException
	 * @throws RendererException
	 * 
	 */

	/**
	 * @Mapeamento Exibe a imagem em um Jframe para fazer o retangulo da image
	 *             codigo de barras
	 * 
	 * @TesteStatus: OK
	 * 
	 * @param caminhoDoArquivoStr
	 * @throws IOException
	 * @throws org.ghost4j.document.DocumentException
	 * @throws RendererException
	 * 
	 */

	public void mapearPdfCarregaImagemNoFrame(String arquivoEntrada, String destinoSaida, int paginaIndex, String nomeArquivo) throws IOException, RendererException, org.ghost4j.document.DocumentException {

		PdfUtil pdfUtil = new PdfUtil();

		pdfUtil.mapearPdfCarregaImagemNoFrame(arquivoEntrada, destinoSaida, paginaIndex, nomeArquivo);

	}

}
