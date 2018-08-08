package edu.coreUtil.ocrPDF;

import java.io.IOException;

import org.ghost4j.document.DocumentException;
import org.ghost4j.renderer.RendererException;

public class teste {

	public static void main(String[] args){
		
		ServicoOcrPdf s = new ServicoOcrPdf();
		
		try {
			
			s.mapearPdfCarregaImagemNoFrame("C:\\Users\\eduardo.sa\\Downloads\\GerarPDF_10052017161731.pdf", "C:\\Users\\eduardo\\Desktop", 0, "testeRod");
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RendererException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
