package edu.coreUtil.sftp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

/**
 * @author eduardo
 *
 */
public class SftpUtil {

	private String			host;
	private String			user;
	private String			pass;
	private int					porta;
	private String			diretorioAtual;
	private Session			session;
	private Channel			channel;
	private ChannelSftp	channelSftp;

	/**
	 * Exemplo da utilização
	 * 
	 * 
	 * public static void main(String[] args) {
	 * 
	 * SFTP sftp = new SFTP("192.168.0.110", "testeSftp", "teste1234", 22);
	 * 
	 * if (sftp.conectar()) {
	 * 
	 * for (String arquivo : sftp.listarSomenteArquivos()) {
	 * 
	 * if (sftp.moverArquivo(sftp.getDiretorioAtual() + "/" + arquivo, arquivo)) {
	 * 
	 * } } } sftp.desconectar(); System.exit(0); }
	 */

	public SftpUtil() {
	}

	/**
	 * 
	 * Metodo: construtor inicio da sftp
	 * 
	 * @param host
	 * @param user
	 * @param pass
	 * @param porta
	 * 
	 */

	public SftpUtil(String host, String user, String pass, int porta) {
		this.host = host;
		this.user = user;
		this.pass = pass;
		this.porta = porta;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public void setPorta(int porta) {
		this.porta = porta;
	}

	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * Metodo: que indica qual o diretorio será usado
	 * 
	 * @param diretorioAtual
	 */

	public void setDiretorioAtual(String diretorioAtual) {

		this.diretorioAtual = diretorioAtual;

		if (this.channelSftp != null) {

			try {

				this.channelSftp.cd(this.diretorioAtual);

			} catch (SftpException ex) {

				ex.printStackTrace();
			}
		}
	}

	/**
	 * 
	 * Metodo: Usuado para estabelecer a conexão com o banco de dados
	 * 
	 * @return
	 */

	public boolean conectar() {
		try {

			java.util.Properties config = new java.util.Properties();
			config.put("StrictHostKeyChecking", "no");

			JSch jsch = new JSch();
			this.session = jsch.getSession(this.user, this.host, this.porta);
			this.session.setPassword(this.pass);
			this.session.setConfig(config);
			this.session.connect();

			this.channel = this.session.openChannel("sftp");
			this.channel.connect();

			this.channelSftp = (ChannelSftp) this.channel;

			// this.channelSftp.setMebaBitPorSegundo(maximoBandaMBit);

			if (this.diretorioAtual != null) {
				this.channelSftp.cd(this.diretorioAtual);
			}

			return true;

		} catch (Exception ex) {

			ex.printStackTrace();

			return false;

		}
	}

	/**
	 * 
	 * Metodo: Que verifica o status da conexão
	 * 
	 * @return
	 */

	public boolean estaConetado() {

		if (channelSftp.isConnected()) {

			return true;
		}

		return false;
	}

	/**
	 * 
	 * Metodo: Para finalizar a conexão com o seu SFTP
	 * 
	 * 
	 */
	public void desconectar() {

		try {

			this.channelSftp.exit();

			this.channelSftp.disconnect();

			this.channel.disconnect();

			this.session.disconnect();

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	/**
	 * Metodo: que recupera o direotrio atual
	 * 
	 * @return
	 */

	public String getDiretorioAtual() {
		try {

			return this.channelSftp.pwd();

		} catch (SftpException ex) {

			System.out.println("SftpException : " + ex.getMessage());

			return null;
		}
	}

	/**
	 * 
	 * Metodo: que faz o download do arquivo direto para o disco.
	 * 
	 * @param doArquivo
	 * @param caminhoNoDiscoLocal
	 * @return
	 */

	public boolean fazerDownloadParaDisco(String doArquivo, String caminhoNoDiscoLocal) {

		BufferedInputStream bis = null;

		BufferedOutputStream bos = null;

		try {

			byte[] buffer = new byte[1024];

			bis = new BufferedInputStream(this.channelSftp.get(doArquivo));

			File newFile = new File(caminhoNoDiscoLocal, doArquivo);

			OutputStream os = new FileOutputStream(newFile);

			bos = new BufferedOutputStream(os);

			int readCount;
			while ((readCount = bis.read(buffer)) > 0) {
				bos.write(buffer, 0, readCount);
			}

		} catch (Exception e) {

			System.out.println("SftpException : " + e.getMessage());

			return false;

		} finally {

			try {

				bis.close();

				bos.close();

			} catch (Exception e) {

				e.printStackTrace();

			}
		}
		return true;
	}

	/**
	 * metodo: que faz o download do arquivo em memoria
	 * 
	 * @param doArquivo
	 * @return
	 */

	public byte[] fazerDownloadParaMemoria(String doArquivo) {
		BufferedInputStream bis = null;
		ByteArrayOutputStream out = null;
		byte[] buffer = new byte[1024];
		byte[] result = null;
		try {

			bis = new BufferedInputStream(this.channelSftp.get(doArquivo));
			out = new ByteArrayOutputStream();

			int readCount;
			while ((readCount = bis.read(buffer)) > 0) {
				out.write(buffer, 0, readCount);
			}

			out.flush();
			result = out.toByteArray();

		} catch (Exception e) {

			System.out.println("SftpException : " + e.getMessage());

			return null;

		} finally {

			try {

				out.close();

				bis.close();

			} catch (Exception e) {

				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 
	 * metodo 1 upload : que faz upload de arquivo
	 * 
	 * 
	 * @param arquivo
	 * @return
	 */

	public boolean fazerUpload(File arquivo) {

		try {
			FileInputStream fis = new FileInputStream(arquivo);

			channelSftp.put(fis, arquivo.getName());

			fis.close();

		} catch (Exception e) {

			System.out.println("SftpException : " + e.getMessage());

			return false;
		}
		return true;
	}

	/**
	 * metodo 2 upload : que faz upload de arquivo
	 * 
	 * @param contArquivo
	 * @param nomeArquivo
	 * @return
	 */

	public boolean fazerUpload(byte[] contArquivo, String nomeArquivo) {

		ByteArrayInputStream bs = null;

		try {
			bs = new ByteArrayInputStream(contArquivo);

			this.channelSftp.put(bs, nomeArquivo);

			bs.close();

		} catch (Exception ex) {

			System.out.println("SftpException : " + ex.getMessage());

			return false;
		}

		bs = null;

		return true;
	}

	/**
	 * Metodo: que mover o arquivo
	 * 
	 * @param caminhoComNomeArquivoOrigem
	 * @param destinoDownload
	 * @param file
	 * @return
	 */
	public boolean moverArquivo(String caminhoComNomeArquivoOrigem, String destinoDownload, String file) {

		try {

			byte[] buffer = new byte[1024];

			BufferedInputStream bis = new BufferedInputStream(channelSftp.get(caminhoComNomeArquivoOrigem));

			File tmpArquivo = new File(destinoDownload + file);

			FileOutputStream fos = new FileOutputStream(tmpArquivo);

			BufferedOutputStream bos = new BufferedOutputStream(fos);

			int readCount;

			while ((readCount = bis.read(buffer)) > 0) {

				bos.write(buffer, 0, readCount);
			}

			bos.close();

			bis.close();

			fos.close();

			this.channelSftp.rm(file);

		} catch (SftpException e) {

			System.out.println("SftpException : " + e.getMessage());

			return false;

		} catch (Exception e) {

			System.out.println("Exception : " + e.getMessage());

			return false;
		}
		return true;
	}

	/**
	 * 
	 * Metodo: que lista os arquivo
	 * 
	 * @return
	 */
	public List<String> listarSomenteArquivos() {

		List<String> arquivos = new ArrayList<String>();

		try {

			List ls = this.channelSftp.ls("./");

			for (Object obj : ls) {

				if (obj instanceof ChannelSftp.LsEntry) {

					ChannelSftp.LsEntry registro = (ChannelSftp.LsEntry) obj;

					if (!registro.getFilename().equals(".") && !registro.getFilename().equals("..")) {

						try {

							this.channelSftp.cd(registro.getFilename());

							this.channelSftp.cd("../");

						} catch (Exception ex) {

							arquivos.add(registro.getFilename());

						}
					}
				}
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		return arquivos;
	}

	/**
	 * 
	 * Metodo: que lista os arquivos e pastas.
	 * 
	 * @return
	 */
	public List<String> listarTudo() {

		List<String> arquivos = new ArrayList<String>();

		try {
			List ls = this.channelSftp.ls("./");

			for (Object obj : ls) {

				if (obj instanceof ChannelSftp.LsEntry) {

					ChannelSftp.LsEntry registro = (ChannelSftp.LsEntry) obj;

					arquivos.add(registro.getFilename());
				}
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		return arquivos;
	}

	/**
	 * Metodo: criar direotrio
	 * 
	 * @param nomeDiretorio
	 * 
	 */
	public void criarDiretorio(String nomeDiretorio) {
		try {

			this.channelSftp.mkdir(nomeDiretorio);

		} catch (SftpException ex) {

			ex.printStackTrace();
		}
	}

	/**
	 * Metodo: que cria arquivo temporario
	 * 
	 * @param caminhoComNomeArquivoDestino
	 * @param nomeArquivo
	 * @param tipoArquivo
	 * @throws FileNotFoundException
	 * @throws IOException
	 * 
	 */

	public void criarArquivoTemporario(String caminhoComNomeArquivoDestino, String nomeArquivo, String tipoArquivo) throws FileNotFoundException, IOException {

		try {

			File temp = File.createTempFile(nomeArquivo, tipoArquivo);

			String tmpNome = new Date().getTime() + "_" + caminhoComNomeArquivoDestino.replace("/", "").replace("\\", "");

			File tmpArquivo = new File("./", tmpNome);

			FileInputStream fis = new FileInputStream(temp);

			this.channelSftp.put(fis, caminhoComNomeArquivoDestino + "//" + nomeArquivo, new SFTPMonitor());

		} catch (SftpException ex) {

			ex.printStackTrace();
		}
	}

	/**
	 * Metodo: que verifica se existe arquivo
	 * 
	 * @return
	 */

	public List<String> fileExists() {

		List<String> listaArquivos = listarSomenteArquivos();

		return listaArquivos;
	}

	public void diretoryExiste(String dir) throws SftpException {

		System.out.println(" pwd " + this.channelSftp.pwd());

	}
}
