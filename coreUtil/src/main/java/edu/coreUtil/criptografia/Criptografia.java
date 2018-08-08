package edu.coreUtil.criptografia;

import net.sourceforge.barbecue.output.OutputException;

public class Criptografia {

	public String criptografiaAlgoritmo(TipoAlgoritmo tipoAlgoritmo) throws OutputException {

		switch (tipoAlgoritmo) {

		case MD5:
			tipoAlgoritmo.getAlgoritmo();
			break;

		case MD2:
			tipoAlgoritmo.getAlgoritmo();
			break;

		case SHA1:
			tipoAlgoritmo.getAlgoritmo();
			break;

		case SHA224:
			tipoAlgoritmo.getAlgoritmo();
			break;

		case SHA256:
			tipoAlgoritmo.getAlgoritmo();
			break;

		case SHA384:
			tipoAlgoritmo.getAlgoritmo();
			break;

		case SHA512:
			tipoAlgoritmo.getAlgoritmo();
			break;

		default:
			throw new IllegalArgumentException(" Criptografia " + tipoAlgoritmo);
		}
		return tipoAlgoritmo.getAlgoritmo();
	}
}
