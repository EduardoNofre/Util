package edu.coreUtil.barCodeQrcode;

/**
 * 
 * @author Eduardo Nofre
 * 
 *         Essa classe PadraoCodigoDebarras tem os padrão de codigo de barras
 *         bem definido
 *
 */

 enum PadraoCodigoDebarras {
	
	CREATE_128("128"),     
	CREATE_128A("128A"),     
	CREATE_128B("128B"),     
	CREATE_128C("128C"),         
	CREATE_EAN128("EAN128"),     
	CREATE_USPS("USPS"),     
	CREATE_SSCC18("SSCC18"),     
	CREATE_SCC14SHIPPINGCODE("SCC14ShippingCode"),    
	CREATE_GLOBALTRADEITEMNUMBER("GlobalTradeItemNumber"),     
	CREATE_EAN13("EAN13"),     
	CREATE_BOOKLAND("Bookland"),     
	CREATE_UPCA("UPCA"),    
	CREATE_STD2OF5("Std2of5"),              
	CREATE_PDF417("PDF417"),     
	CREATE_CODE39("39"),     
	CREATE_3OF9("3of9"),     
	CREATE_USD3("USD3"),     
	CREATE_CODABAR("Codabar"),     
	CREATE_USD4("USD4"),     
	CREATE_NW7("NW7"),     
	CREATE_MONARCH("Monarch"),     
	CREATE_2OF7("2of7");

  private String codigo;

  PadraoCodigoDebarras(String codigo) {
      this.codigo = codigo;
  }

	public String getCodigo() {
		return codigo;
	}	
}
