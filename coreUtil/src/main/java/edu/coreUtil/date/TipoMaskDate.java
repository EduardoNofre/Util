package edu.coreUtil.date;

public enum TipoMaskDate {
	/**
	 * formato da data DDMMYYYY = dd-MM-yyyyy:
	 */

	DDMMYYYY("dd-MM-yyyy"),

	/**
	 * formato da data YYYYMMDD = yyyy-MM-dd:
	 */

	YYYYMMDD("yyyy-MM-dd"),

	/**
	 * formato da data DDMMYYYYHHMMSS = dd-MM-yyyyy HH:mm:ss:
	 */
	DDMMYYYYHHMMSS("dd-MM-yyyy HH:mm:ss"),

	/**
	 * formato da data YYYYMMDDHHMMSS = yyyy-MM-dd HH:mm:ss:
	 */
	YYYYMMDDHHMMSS("yyyy-MM-dd HH:mm:ss"),

	/**
	 * formato da data HHMMSS24H = HH:mm:ss:
	 */
	HHMMSS24H("HH:mm:ss"),

	/**
	 * formato da data HHMMSSPMAM = hh:mm:ss: obs: AM/PM
	 */
	HHMMSSPMAM("hh:mm:ss");

	private String maskData;

	TipoMaskDate(String maskData) {
		this.maskData = maskData;
	}

	public String getMaskData(){
		return maskData;
	}
}
