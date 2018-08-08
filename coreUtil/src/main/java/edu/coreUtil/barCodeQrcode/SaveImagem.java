package edu.coreUtil.barCodeQrcode;

import java.io.File;

import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeImageHandler;
import net.sourceforge.barbecue.output.OutputException;

public class SaveImagem {

	public static void saveTipoImagem(Barcode barcode, File file, TipoImagem tipoImagem) throws OutputException {

		switch (tipoImagem) {

		case PNG:
			BarcodeImageHandler.savePNG(barcode, file);
			break;

		case JPEG:
			BarcodeImageHandler.saveJPEG(barcode, file);
			break;

		case GIF:
			BarcodeImageHandler.saveGIF(barcode, file);
			break;

		default:
			throw new IllegalArgumentException(" Tipo imagem " + tipoImagem);
		}
	}

}
