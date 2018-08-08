package edu.coreUtil.arquivo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe ArquivoBean uma classe simples com seus gettes e setteres.
 * 
 * @author Eduardo Nofre
 * @version 1.00
 */
public class ArquivoUtil {

	/**
	 * @finalidade_fazer_somente_a_copia_de_um_arquivo.
	 * 
	 * @param origem
	 * @param destino
	 * @throws IOException
	 */

	protected void copiaArquivo(File origem,File destino) throws IOException{

		InputStream in = new FileInputStream(origem);

		OutputStream out = new FileOutputStream(destino);

		byte[] buf = new byte[1024];

		int len;

		while ((len = in.read(buf)) > 0) {

			out.write(buf, 0, len);
		}

		in.close();

		out.close();

	}

	/**
	 * 
	 * @Esse_metodo_move_somente_o_arquivos.
	 * 
	 * @param origemPasta
	 * @param destinoPasta
	 * @param nomePdlFdl
	 * @return boolean
	 */

	protected boolean moverArquivo(String origemPasta,String destinoPasta,String nomePdlFdl){

		File origem = new File(origemPasta);

		File destino = new File(destinoPasta);

		boolean ok = false;

		if (destino.isDirectory()) {

			for (File dirFileOrigem : origem.listFiles()) {

				if (dirFileOrigem.isFile()) {

					if (dirFileOrigem.getName().equals(nomePdlFdl)) {

						if (dirFileOrigem.renameTo(new File(destinoPasta + dirFileOrigem.getName()))) {

							System.out.println(" Arquivo " + dirFileOrigem.getName());

							ok = true;

						}
						else {

							System.out.println("Erro ao mover o arquivo! \n Diretorio ja existente " + destino + "\\"
									+ dirFileOrigem.getName());
						}
					}
				}
			}
		}
		else {

			System.out.println(" Não é um diretorio ");
		}

		return ok;
	}

	/**
	 * @remove_extensao_arquivo.
	 * 
	 * @param nomeArquivo
	 * @return String
	 */

	protected String removerExtensaoDoArquivo(String nomeDoArquivo){

		String semExtensao = "";

		String array[] = nomeDoArquivo.split("\\.");

		for (int i = 0; i < array.length - 1; i++) {

			semExtensao += array[i].toString();
			semExtensao += ".";
		}

		return semExtensao;
	}

	/**
	 * 
	 * @Mover_o_diretorio.
	 * 
	 * @param destinoPasta
	 * @param PdfServico
	 * @return boolean
	 */

	protected boolean moverDiretorio(String destinoPasta,File Pdf){

		File destino = new File(destinoPasta);

		boolean ok = false;

		if (destino.isDirectory()) {

			destino = new File(destino + "\\" + Pdf.getParentFile().getName());

			if (destino.mkdir()) {

				for (File arq : Pdf.getParentFile().listFiles()) {

					if (arq.renameTo(new File(destino.toString() + "\\" + arq.getName()))) {

						System.out.println("Lote " + " - " + arq.getName());

						ok = true;
					}
				}

				// delete o direotrio vazio
				if (Pdf.getParentFile().isDirectory()) {

					String[] dirIsVoid = Pdf.getParentFile().list();

					if (dirIsVoid.length == 0) {

						if (Pdf.getParentFile().delete()) {

							System.out.println("Pasta deleta " + Pdf.getParentFile());
						}
					}

				}
			}
		}
		else {

			ok = false;
		}
		return ok;
	}

	/**
	 * 
	 * @Que_faz_a_copia_de_direotrio.
	 * 
	 * @param srcDir
	 * @param dstDir
	 * @throws IOException
	 * 
	 */

	protected void capiaDireotrio(File diretorioOrigem,File diretorioDestino) throws IOException{

		if (diretorioOrigem.isDirectory()) {

			if (!diretorioDestino.exists()) {

				diretorioDestino.mkdir();
			}
			String[] children = diretorioOrigem.list();

			for (int i = 0; i < children.length; i++) {

				capiaDireotrio(new File(diretorioOrigem, children[i]), new File(diretorioDestino, children[i]));
			}
		}
		else {

			copiaArquivo(diretorioOrigem, diretorioDestino);
		}

	}

	/**
	 * 
	 * @Leitor_de_arquivos
	 * 
	 * @param caminhoArquivo
	 * @return ArquivoBean
	 * @throws IOException
	 */
	@SuppressWarnings("resource")
	protected ArquivoBean lerArquivo(String caminhoArquivo) throws IOException{

		String linhaAtual = null;

		int linhasEmBranco = 0;

		BufferedReader lendoLinha = null;

		List<String> linhaDoArquivo = new ArrayList<String>();

		ArquivoBean beanArquivo = new ArquivoBean();

		File arquivo = new File(caminhoArquivo);

		FileInputStream lerArquivo = new FileInputStream(arquivo.getAbsolutePath());

		InputStreamReader ler = new InputStreamReader((lerArquivo), "ISO-8859-1");

		lendoLinha = new BufferedReader(ler);

		while ((linhaAtual = lendoLinha.readLine()) != null) {

			if (linhaAtual.trim().length() > 0) {

				linhaDoArquivo.add(linhaAtual);

			}
			else {

				linhasEmBranco++;
			}

		}

		beanArquivo.setLinhasDoArquivo(linhaDoArquivo);

		beanArquivo.setTotalDeLinhasComConteudo(linhaDoArquivo.size());

		beanArquivo.setTotalDeLinhasEmBranco(linhasEmBranco);

		beanArquivo.setLinhasDoArquivo(linhaDoArquivo);

		beanArquivo.setTamanhoDoArquivoMegas(arquivo.length() / 1024 / 1024);

		beanArquivo.setNomeDoArquvo(arquivo.getName());

		return beanArquivo;
	}

	/**
	 * 
	 * @Criação_de_arquivo
	 * 
	 * 
	 * @param SaidaArquivo
	 * @param nomeArquivo
	 * @param extensaoArquivo
	 * @return
	 * @throws IOException
	 * 
	 * 
	 */

	protected String criarArquivo(String SaidaArquivo,String nomeArquivo,String extensaoArquivo) throws IOException{

		File file = new File(SaidaArquivo + nomeArquivo + extensaoArquivo);

		if (file.createNewFile()) {

			return file.getAbsoluteFile().toString();

		}
		else {

			return "não foi possivel criar.\n talvez o arquivo ja exista";
		}

	}

	/**
	 * @Escrever_arquivo
	 * 
	 * @param arquivo
	 * @param texto
	 * @return
	 * @throws IOException
	 */
	protected String escreverNoArquivo(String arquivo,String texto) throws IOException{

		File escreveArquivo = new File(arquivo);

		if (!escreveArquivo.exists()) {

			return "O arquivo não existe";

		}

		FileWriter fw = new FileWriter(escreveArquivo, true);

		BufferedWriter bw = new BufferedWriter(fw);

		bw.write(texto);

		bw.newLine();

		bw.close();

		fw.close();

		return arquivo;

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

	protected BasicFileAttributes informacoesDoArquivo(File arquivo) throws IOException{

		Path filePath = arquivo.toPath();

		BasicFileAttributes attributes = Files.readAttributes(filePath, BasicFileAttributes.class);

		return attributes;

	}
}
