package edu.coreUtil.criptografia;

public enum TipoAlgoritmo {

	MD5("MD5"), 
	MD2("MD2"),
	SHA1("SHA-1"),
	SHA224("SHA-224"),
	SHA256("SHA-256"),
	SHA384("SHA-384"),
	SHA512("SHA-512");

	private String	algoritmo;

	TipoAlgoritmo(String algoritmo) {
		this.algoritmo = algoritmo;
	}

	public String getAlgoritmo() {
		return algoritmo;
	}

}
