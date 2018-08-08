package edu.coreUtil.ocrPDF;

import java.awt.Frame;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.ScrollPane;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.util.PDFTextStripperByArea;
import org.ghost4j.document.PDFDocument;
import org.ghost4j.renderer.RendererException;
import org.ghost4j.renderer.SimpleRenderer;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * @author eduardo
 *
 */
public class PdfUtil extends Frame {

	private static final long	serialVersionUID	= 1L;

	protected PdfUtil() {
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

	protected String coordenadasPdf(int x, int y, int width, int height, PDDocument document, int indexPagina) throws IOException {

		PDFTextStripperByArea stripper = new PDFTextStripperByArea();

		stripper.setSortByPosition(true);

		String dados = null;

		Rectangle retornoCoordenadasPdf = new Rectangle(x, y, width, height);

		stripper.addRegion("dadosPdf", retornoCoordenadasPdf);

		List<?> arrayDepaginasPdf = document.getDocumentCatalog().getAllPages();

		PDPage paginasPdf = (PDPage) arrayDepaginasPdf.get(indexPagina);

		stripper.extractRegions(paginasPdf);

		dados = stripper.getTextForRegion("dadosPdf").toUpperCase();

		System.out.println(" Dados Pdf : " + dados);
		System.out.println("\n " + Normalizer.normalize(dados, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""));

		return dados;

	}

	/**
	 * Metodo: splitPdf separa os pdf
	 * 
	 * @TesteStatus: OK
	 * 
	 * @param pdfEntrada
	 * @param saida
	 * @param nomeArquivo
	 * @param indexPagina
	 * @throws IOException
	 * @throws DocumentException
	 */

	protected void separarPdf(File pdfEntrada, String saida, String nomeArquivo, int indexPagina) throws IOException, DocumentException {

		File saidaArquivo = new File(saida + "\\" + nomeArquivo + ".PDF");

		PdfReader readerPdf = new PdfReader(pdfEntrada.getAbsolutePath());

		Document document = new Document(readerPdf.getPageSizeWithRotation(1));

		PdfCopy writer = new PdfCopy(document, new FileOutputStream(saidaArquivo));

		document.open();

		PdfImportedPage page = writer.getImportedPage(readerPdf, indexPagina);

		System.out.println(" Tipo Arquivo " + "PDF");

		System.out.println(" Saida arquivo " + saidaArquivo.getPath());

		writer.addPage(page);

		document.close();

		writer.close();

	}

	/**
	 * Metodo: mergePdf junta os pdf aparti do mesmo pdf array
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

	protected void juntaPdf(File filePdf, List<Integer> indexPaginaLista, String saida, String nomeArquivo) throws DocumentException, IOException {

		Document document = new Document();

		PdfReader readerPdf = new PdfReader(filePdf.getAbsolutePath());

		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("C:\\MetaAlbumFoto\\" + "\\" + "teste" + ".PDF"));

		document.open();

		PdfContentByte cb = writer.getDirectContent();

		for (int i : indexPaginaLista) {

			document.newPage();

			PdfImportedPage page = writer.getImportedPage(readerPdf, i + 1);

			cb.addTemplate(page, 0, 0);
		}

		document.close();

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
	protected int contaNumeroPaginasPdf(File pdfPaginas) {

		PDDocument documentCapa = this.objetoPDDocument(pdfPaginas);
		int capaPaginas = documentCapa.getNumberOfPages();

		return capaPaginas;

	}

	/**
	 * 
	 * Esse metodo e utilizado para retorna o objeto PDDocument Esse mesmo é
	 * utilizado para pegar a quantidade de paginas e etc
	 * 
	 * 
	 * @TesteStatus:
	 * 
	 * @param pdf
	 * @return
	 */

	protected PDDocument objetoPDDocument(File pdf) {

		PDDocument document = null;

		try {

			document = PDDocument.load(pdf);

			if (document.isEncrypted()) {

				document.decrypt("");
			}
		} catch (Exception e) {

			e.printStackTrace();

		}

		return document;

	}

	/**
	 * 
	 * Para a utilização do deste metodo é necessario fazer a instalação do
	 * GhostScript
	 * 
	 * @TesteStatus:
	 * 
	 * @Site: referencia onde fazer o download
	 * 
	 *        Observação: O GhostScript é nativo das maquina Linux
	 * 
	 *        Não esquecer de colocar as dlls no projeto.
	 * 
	 *        gsdll32.dll
	 * 
	 *        gsdll64.dll
	 * 
	 * @param pdfFile
	 * @param destinoSaida
	 * @param pagina
	 * @param nomeArquivo
	 * @param tipoFile
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @throws org.ghost4j.document.DocumentException
	 * @throws RendererException
	 * 
	 */

	protected String convertePdfParaOutrosFormtatos(File pdfFile, String destinoSaida, int paginaIndex, String nomeArquivo, PdfTipoImagem pdfTipoImagem) throws FileNotFoundException, IOException, RendererException,
			org.ghost4j.document.DocumentException {

		// load PDF document
		PDFDocument document = new PDFDocument();

		document.load(pdfFile);

		// create renderer
		SimpleRenderer renderer = new SimpleRenderer();

		// render
		List<Image> images = renderer.render(document);

		ImageIO.write((RenderedImage) images.get(paginaIndex), pdfTipoImagem.toString(), new File(destinoSaida + "\\" + nomeArquivo + (paginaIndex) + "." + pdfTipoImagem.toString()));

		System.out.println(" Tipo Arquivo :" + pdfTipoImagem.toString());

		System.out.println(" Nome arquivo :" + nomeArquivo);

		System.out.println(" Destino :" + destinoSaida + "\\" + nomeArquivo + (paginaIndex) + "." + pdfTipoImagem);

		return destinoSaida + "\\" + nomeArquivo + (paginaIndex) + "." + pdfTipoImagem;

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

	protected void mapearPdfCarregaImagemNoFrame(String arquivoEntrada, String destinoSaida, int paginaIndex, String nomeArquivo) throws IOException, RendererException, org.ghost4j.document.DocumentException {

		PdfTipoImagem pdfTipoImagem = null;		
		
		File filePdf = new File(arquivoEntrada);

		PDDocument document = this.objetoPDDocument(filePdf);

		BufferedImage image = ImageIO.read(new File(this.convertePdfParaOutrosFormtatos(filePdf, destinoSaida, paginaIndex, nomeArquivo, pdfTipoImagem.PNG)));

		DesenharRetanguloPdf desenharRetanguloPdf = new DesenharRetanguloPdf(image, document, paginaIndex);

		ScrollPane scrollPane = new ScrollPane();

		scrollPane.add(desenharRetanguloPdf);

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

}
