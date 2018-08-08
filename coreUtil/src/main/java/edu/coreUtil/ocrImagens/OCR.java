package edu.coreUtil.ocrImagens;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.swing.JOptionPane;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class OCR {

	public void doOcr(BufferedImage bufferedImage, Rectangle rect) {

		@SuppressWarnings("deprecation")
		Tesseract instance = Tesseract.getInstance();

		instance.setLanguage("por");

		instance.setDatapath("C:\\Program Files (x86)\\Tesseract-OCR\\tessdata");

		try {

			String result = instance.doOCR(bufferedImage, rect);

			JOptionPane.showMessageDialog(null, result);

		} catch (TesseractException e) {

			System.err.println(e.getMessage());

		}
	}
}
