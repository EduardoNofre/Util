package edu.coreUtil.criptografia;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Base64.Encoder;

/**
 * 
 * @author eduardo 30-06-2016
 * @Referencia: http://blog.desenvolvedor.org/criptografia-de-senhas
 *
 */

public class CriptografiaUtil {

	/**
	 * 
	 * Popula o banco de dados controlado pelo DAO.
	 * 
	 * 
	 * 
	 * 
	 * @param usuarioNome
	 *            :Recebe o nome do usuario
	 * @param usuarioSenha
	 *            :Recebe a senha do usuario
	 * @param algoritmo
	 *            :tipos de criptografia suportado esta no enum TipoAlgoritmo
	 * 
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */

	protected Usuario popularUsuario(String usuarioNome,String usuarioSenha,TipoAlgoritmo algoritmo)
			throws NoSuchAlgorithmException,UnsupportedEncodingException{

		String salt = this.getSaltAleatorio();

		String senha = this.getHash(usuarioSenha, salt, algoritmo);

		Usuario usuario = new Usuario(usuarioNome, senha, salt);

		return usuario;

	}

	/**
	 * 
	 * @getSaltAleatorio :Gera uma chave "salt" aleatório de 64 bit. para a sua
	 *                   senha
	 * 
	 **/

	protected String getSaltAleatorio() throws NoSuchAlgorithmException{

		byte[] salt = new byte[8];

		String algoritmo = "SHA1PRNG";

		SecureRandom random = SecureRandom.getInstance(algoritmo);

		random.nextBytes(salt);

		return this.byteToBase64(salt);
	}

	/**
	 * getHash: metodo que gera o hash da senha do usuario apartir do algoritmo
	 * passado como parametro
	 * 
	 * @param senha
	 *            : senha informada pelo usuario
	 * 
	 * @param salt
	 *            : Chave criptografada da senha
	 * 
	 * @param algoritmo
	 *            :tipos de criptografia suportado esta no enum TipoAlgoritmo
	 * 
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */

	protected String getHash(String senha,String salt,TipoAlgoritmo algoritmo)
			throws NoSuchAlgorithmException,UnsupportedEncodingException{

		MessageDigest condensador = MessageDigest.getInstance(algoritmo.getAlgoritmo());

		condensador.reset();

		condensador.update(this.base64ToByte(salt));

		byte[] hash = condensador.digest(senha.getBytes("UTF-8"));

		for (int i = 0; i < 1000; i++) {

			condensador.reset();

			hash = condensador.digest(hash);
		}

		return this.byteToBase64(hash);
	}

	/**
	 * @autenticar:Autentica o usuario
	 * 
	 * @param usuarioDao
	 *            : Esse usuario que vem do banco de dados apos a consulta do
	 *            mesmo por algums atributo
	 * 
	 * @param senhaView
	 *            :A senhaView é a senha que o usuario digita da Tela
	 * 
	 * @param algoritmo
	 *            :O algoritmo e tipo de cirptografia a ser utilizada
	 *            exemplo:MD,SHA SHA224 e etc todas os tipos de criptografia
	 *            suportado esta no enum TipoAlgoritmo
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	protected Usuario autenticar(Usuario usuarioDao,String senhaView,TipoAlgoritmo algoritmo)
			throws NoSuchAlgorithmException,UnsupportedEncodingException{

		if (usuarioDao == null) {

			return null;
		}

		String hashDaSenhaInformada = this.getHash(senhaView, usuarioDao.getSalt(), algoritmo);

		if (hashDaSenhaInformada.equals(usuarioDao.getSenha())) {

			return usuarioDao;

		}
		else {

			return null;
		}
	}

	/**
	 * 
	 * @see Encoder#encodeToString(byte[])
	 * 
	 */

	protected String byteToBase64(byte[] bytes){

		return Base64.getEncoder().encodeToString(bytes);

	}

	protected byte[] base64ToByte(String base64){

		return Base64.getDecoder().decode(base64);
	}

}
