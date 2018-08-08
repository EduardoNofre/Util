package edu.coreUtil.properties;

import java.io.IOException;

/**
 * 
 * @author eduardo
 *
 */

public class ServicoProperties {

	/**
	 * 
	 * static Metodo utilizado para fazer a leitura de arquivo Properties
	 * 
	 * exemplo para recuperar o valor
	 * 
	 * prop.getProperty(aqui se coloca o noma da propriedade")
	 * 
	 * exemplo: Properties prop = getProp(caminho,arquivo);
	 * prop.getProperty("prop.usuario")
	 * 
	 * caminho windows exemplo: c:\\direotrio\\arquivo.Properties
	 * 
	 * caminho Linux exemplo: home/properties/arquivo.Properties
	 * 
	 * @param chave
	 *          : passe a chave como parametro e receba como retorno o valor
	 * 
	 * @param caminhoArquivoProperties
	 *          : caminho onde se encontra o caminho da arquivo Properties
	 * @return: O valor referente a chave passada
	 * 
	 * @throws IOException
	 */

	public static String lerProperties(String chave, String caminhoArquivoProperties) throws IOException {

		PropertiesUtil propertiesUtil = new PropertiesUtil();

		return propertiesUtil.lerProperties(chave, caminhoArquivoProperties);

	}

}
