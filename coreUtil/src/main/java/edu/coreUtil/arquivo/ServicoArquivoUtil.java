package edu.coreUtil.arquivo;

import java.io.File;
import java.io.IOException;
import java.nio.file.attribute.BasicFileAttributes;

public class ServicoArquivoUtil {

	/**
	 * Metodo finalidade fazer somente a copia de um arquivo.
	 * 
	 * @param origem
	 * @param destino
	 * @throws IOException
	 * 
	 */

	public void copiaArquivo(File origem,File destino) throws IOException{

		ArquivoUtil arquivo = new ArquivoUtil();

		arquivo.copiaArquivo(origem, destino);

	}

	/**
	 * 
	 * Esse metodo move somente o arquivos.
	 * 
	 * @param origemPasta
	 * @param destinoPasta
	 * @param nomePdlFdl
	 * @return boolean
	 * 
	 */

	public boolean moverArquivo(String origemPasta,String destinoPasta,String nomePdlFdl){

		ArquivoUtil arquivo = new ArquivoUtil();

		return arquivo.moverArquivo(origemPasta, destinoPasta, nomePdlFdl);

	}

	/**
	 * metodo: remove Extensao arquivo.
	 * 
	 * @param nomeArquivo
	 * @return String
	 * 
	 */

	public String removerExtensaoDoArquivo(String nomeDoArquivo){

		ArquivoUtil arquivo = new ArquivoUtil();

		return arquivo.removerExtensaoDoArquivo(nomeDoArquivo);

	}

	/**
	 * 
	 * Metodo: Mover o diretorio.
	 * 
	 * @param destinoPasta
	 * @param PdfServico
	 * @return boolean
	 * 
	 */

	public boolean moverDiretorio(String destinoPasta,File Pdf){

		ArquivoUtil arquivo = new ArquivoUtil();

		return arquivo.moverDiretorio(destinoPasta, Pdf);

	}

	/**
	 * 
	 * metodo: que faz a copia de direotrio.
	 * 
	 * @param srcDir
	 * @param dstDir
	 * @throws IOException
	 * 
	 */

	public void capiaDireotrio(File diretorioOrigem,File diretorioDestino) throws IOException{

		ArquivoUtil arquivo = new ArquivoUtil();

		arquivo.capiaDireotrio(diretorioOrigem, diretorioDestino);

	}

	/**
	 * 
	 * Leitor de arquivos
	 * 
	 * @param caminhoArquivo
	 * @return ArquivoBean
	 * @throws IOException
	 * 
	 */

	public ArquivoBean lerArquivo(String caminhoArquivo) throws IOException{

		ArquivoUtil arquivo = new ArquivoUtil();

		return arquivo.lerArquivo(caminhoArquivo);

	}

	/**
	 * 
	 * Criação de arquivo
	 * 
	 * @param SaidaArquivo
	 * @param nomeArquivo
	 * @param extensaoArquivo
	 * @return
	 * @throws IOException
	 * 
	 * 
	 */

	public String criarArquivo(String SaidaArquivo,String nomeArquivo,String extensaoArquivo) throws IOException{

		ArquivoUtil arquivo = new ArquivoUtil();

		return arquivo.criarArquivo(SaidaArquivo, nomeArquivo, extensaoArquivo);

	}

	/**
	 * 
	 * Escrever arquivo
	 * 
	 * @param arquivoEntrada
	 * @param texto
	 * @return
	 * @throws IOException
	 * 
	 */

	public String escreverNoArquivo(String arquivoEntrada,String texto) throws IOException{

		ArquivoUtil arquivo = new ArquivoUtil();

		return arquivo.escreverNoArquivo(arquivoEntrada, texto);

	}

	/**
	 * @informaçoesDoArquivo: Retorna as informaçoes do arquivo Data Criação,
	 *                        Data modificação, Ultimo acesso ao arquivo,
	 * 
	 * @param arquivo
	 * @return
	 * @throws IOException
	 * 
	 */
	
	public BasicFileAttributes informaçoesDoArquivo(File arquivo) throws IOException{

		ArquivoUtil arquivos = new ArquivoUtil();

		return arquivos.informacoesDoArquivo(arquivo);
	}

}
