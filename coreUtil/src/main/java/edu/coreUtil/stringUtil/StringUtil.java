package edu.coreUtil.stringUtil;

import java.text.Normalizer;

/**
 * @author eduardo
 *
 */
public class StringUtil {

	/**
	 * 
	 * Metodo: remover espaços da esquerda de uma string
	 * 
	 * @param str
	 * @return
	 */

	protected String removerEspacosEsquerda(String str) {

		String espacosEsquerda = str.replaceAll("\\s+$", "");

		return espacosEsquerda;

	}

	/**
	 * 
	 * Metodo: remover espaços da direita de uma string
	 * 
	 * @param str
	 * @return
	 */

	protected String removerEspacosDireita(String str) {

		String espacosDireita = str.replaceAll("^\\s+", "");

		return espacosDireita;
	}

	/**
	 * 
	 * 
	 * metodo: remover o execesso de espacos de uma string
	 * 
	 * @param str
	 * @return
	 */

	protected String removerExecessoEspacos(String str) {

		StringBuilder sb = new StringBuilder();

		for (int contStr = 0; contStr < str.length(); contStr++) {

			char strCharIndex = str.charAt(contStr);

			if (!(String.valueOf(strCharIndex).equals(" "))) {

				sb.append(strCharIndex).toString();

				if (!(contStr + 2 >= str.length())) {

					if (!(contStr + 1 > str.length())) {

						if ((contStr + 1) < str.length()) {

							if ((String.valueOf(str.charAt(contStr + 1)).equals(" ") && (!String.valueOf(str.charAt(contStr + 2)).equals(" ")))) {

								sb.append(str.charAt(contStr + 1)).toString();

							} else if (String.valueOf(str.charAt(contStr + 1)).equals(" ")) {

								sb.append(str.charAt(contStr + 1)).toString();
							}
						}
					}
				}
			}

		}
		return sb.toString().toUpperCase();
	}

	/**
	 * 
	 * Metodo: Remover os numeros de uma String.
	 * 
	 * @param paramStr
	 * @return
	 */

	protected String removeNumeros(String paramStr) {

		String ans = null;

		if (null != paramStr) {

			ans = paramStr.replaceAll("[0-9 ]", "");

			return ans;
		}

		return null;
	}

	/**
	 * 
	 * Metodo: Remover as letras de uma String.
	 * 
	 * @param paramStr
	 * @return
	 */

	protected String removeLetras(String paramStr) {

		String ans = null;

		if (null != paramStr) {

			ans = paramStr.toLowerCase().replaceAll("[a-z]", "");

			return ans;
		}

		return null;
	}

	/**
	 * 
	 * Metodo: Remover as letras de uma String.
	 * 
	 * @param paramStr
	 * @return
	 */

	protected String removeLetrasCaracteres(String paramStr) {

		String ans = null;

		if (null != paramStr) {

			ans = paramStr.replaceAll("[^0-9 ]", "");

			return ans;
		}
		return null;

	}

	/**
	 * 
	 * Metodo: Remove quebra de linha.
	 * 
	 * @param paramStr
	 * @return
	 */

	protected String removeQuebraDelinhas(String paramStr) {

		String str = null;

		if (null != paramStr) {

			str = paramStr.replace("\r", "").replace("\n", "");

			return str;
		}
		return null;

	}

	/**
	 * 
	 * Metodo: Remove Acentos.
	 * 
	 * @param paramStr
	 * @return
	 */

	protected String removerAcentos(String str) {
		return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
	}

	/**
	 * 
	 * Metodo: Remove aspas.
	 * 
	 * @param paramStr
	 * @return
	 */
	protected String removeAspas(String paramStr) {

		String str = null;

		if (null != paramStr) {

			str = paramStr.replaceAll("\"", "");

			return str;
		}
		return null;

	}

}
