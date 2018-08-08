package edu.coreUtil.barCodeQrcode;

import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.BarcodeFactory;

public class TipoCodigoBarras {

	protected static Barcode padraoCodigo(PadraoCodigoDebarras padraoCodigoDebarras, String codigoBarrasValor) throws BarcodeException {

		Barcode barcode = null;

		switch (padraoCodigoDebarras.getCodigo()) {

		case "128":
			barcode = BarcodeFactory.createCode128(codigoBarrasValor);
			break;

		case "128A":
			barcode = BarcodeFactory.createCode128A(codigoBarrasValor);
			break;

		case "128B":
			barcode = BarcodeFactory.createCode128B(codigoBarrasValor);
			break;

		case "128C":
			barcode = BarcodeFactory.createCode128C(codigoBarrasValor);
			break;

		case "EAN128":
			barcode = BarcodeFactory.createEAN128(codigoBarrasValor);
			break;

		case "USPS":
			barcode = BarcodeFactory.createUSPS(codigoBarrasValor);
			break;

		case "SSCC18":
			barcode = BarcodeFactory.createSSCC18(codigoBarrasValor);
			break;

		case "SCC14ShippingCode":
			barcode = BarcodeFactory.createSCC14ShippingCode(codigoBarrasValor);
			break;

		case "GlobalTradeItemNumber":
			barcode = BarcodeFactory.createGlobalTradeItemNumber(codigoBarrasValor);
			break;

		case "EAN13":
			barcode = BarcodeFactory.createEAN13(codigoBarrasValor);
			break;

		case "Bookland":
			barcode = BarcodeFactory.createBookland(codigoBarrasValor);
			break;

		case "UPCA":
			barcode = BarcodeFactory.createUPCA(codigoBarrasValor);
			break;

		case "Std2of5":
			barcode = BarcodeFactory.createStd2of5(codigoBarrasValor);
			break;

		case "PDF417":
			barcode = BarcodeFactory.createPDF417(codigoBarrasValor);
			break;

		case "39":
			barcode = BarcodeFactory.createCode39(codigoBarrasValor, true);
			break;

		case "3of9":
			barcode = BarcodeFactory.create3of9(codigoBarrasValor, true);
			break;

		case "USD3":
			barcode = BarcodeFactory.createUSD3(codigoBarrasValor, true);
			break;

		case "Codabar":
			barcode = BarcodeFactory.createCodabar(codigoBarrasValor);
			break;

		case "USD4":
			barcode = BarcodeFactory.createUSD4(codigoBarrasValor);
			break;

		case "NW7":
			barcode = BarcodeFactory.createNW7(codigoBarrasValor);
			break;

		case "Monarch":
			barcode = BarcodeFactory.createMonarch(codigoBarrasValor);
			break;

		case "2of7":
			barcode = BarcodeFactory.create2of7(codigoBarrasValor);
			break;

		default:
			throw new IllegalArgumentException("codigo de barras invalido " + padraoCodigoDebarras.getCodigo());
		}
		return barcode;

	}
}
