package edu.coreUtil.properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author eduardo
 *
 */
public class PropertiesUtil {

	private Properties getProp(String caminhoArquivoProperties) throws IOException {

		Properties props = new Properties();

		FileInputStream file = new FileInputStream(caminhoArquivoProperties);

		props.load(file);

		return props;

	}

	/**
	 * 
	 * Metodo utilizado para fazer a leitura de arquivo Properties
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
	
	protected String lerProperties(String chave, String caminhoArquivoProperties) throws IOException {

		String valor;

		Properties prop = this.getProp(caminhoArquivoProperties);

		valor = prop.getProperty(chave);

		return valor;

	}

}
