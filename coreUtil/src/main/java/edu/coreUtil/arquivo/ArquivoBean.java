package edu.coreUtil.arquivo;

import java.util.List;

/**
 * Classe ArquivoBean uma classe simples com seus gettes e setteres.
 * 
 * @author Eduardo Nofre
 * @version 1.00
 */

public class ArquivoBean {

	private int						totalDeLinhasComConteudo;
	private int						totalDeLinhasEmBranco;
	private long					TamanhoDoArquivoMegas;
	private String				NomeDoArquvo;
	private List<String>	LinhasDoArquivo;

	/**
	 * @return the totalDeLinhasComConteudo
	 */
	protected int getTotalDeLinhasComConteudo() {
		return totalDeLinhasComConteudo;
	}

	/**
	 * @param totalDeLinhasComConteudo
	 *          the totalDeLinhasComConteudo to set
	 */
	protected void setTotalDeLinhasComConteudo(int totalDeLinhasComConteudo) {
		this.totalDeLinhasComConteudo = totalDeLinhasComConteudo;
	}

	/**
	 * @return the totalDeLinhasEmBranco
	 */
	protected int getTotalDeLinhasEmBranco() {
		return totalDeLinhasEmBranco;
	}

	/**
	 * @param totalDeLinhasEmBranco
	 *          the totalDeLinhasEmBranco to set
	 */
	protected void setTotalDeLinhasEmBranco(int totalDeLinhasEmBranco) {
		this.totalDeLinhasEmBranco = totalDeLinhasEmBranco;
	}

	/**
	 * @return the tamanhoDoArquivoMegas
	 */
	protected long getTamanhoDoArquivoMegas() {
		return TamanhoDoArquivoMegas;
	}

	/**
	 * @param tamanhoDoArquivoMegas
	 *          the tamanhoDoArquivoMegas to set
	 */
	protected void setTamanhoDoArquivoMegas(long tamanhoDoArquivoMegas) {
		TamanhoDoArquivoMegas = tamanhoDoArquivoMegas;
	}

	/**
	 * @return the nomeDoArquvo
	 */
	protected String getNomeDoArquvo() {
		return NomeDoArquvo;
	}

	/**
	 * @param nomeDoArquvo
	 *          the nomeDoArquvo to set
	 */
	protected void setNomeDoArquvo(String nomeDoArquvo) {
		NomeDoArquvo = nomeDoArquvo;
	}

	/**
	 * @return the linhasDoArquivo
	 */
	protected List<String> getLinhasDoArquivo() {
		return LinhasDoArquivo;
	}

	/**
	 * @param linhasDoArquivo
	 *          the linhasDoArquivo to set
	 */
	protected void setLinhasDoArquivo(List<String> linhasDoArquivo) {
		LinhasDoArquivo = linhasDoArquivo;
	}

}
