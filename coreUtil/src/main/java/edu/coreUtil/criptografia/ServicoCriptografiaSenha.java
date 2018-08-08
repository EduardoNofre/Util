package edu.coreUtil.criptografia;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * 
 * @author eduardo 30-06-2016
 *
 */

public class ServicoCriptografiaSenha {

	public ServicoCriptografiaSenha() {
	}

	/**
	 * 
	 * @Metodo:Usuario
	 * 
	 *                 Para este metodo passamos os dois parametros nomeDeUsuario
	 *                 e senhaDeUsuario o retorno desse metodo é a senha
	 *                 criptografada com o salt o salt é uma chave de sua senha
	 *                 criptografada
	 *
	 * @param nomeDeUsuario
	 * @param senhaDeUsuario
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */

	public Usuario popularUsuario(String nome, String senha, TipoAlgoritmo algoritmo) throws NoSuchAlgorithmException, UnsupportedEncodingException {

		CriptografiaUtil criptografiaUtil = new CriptografiaUtil();

		return criptografiaUtil.popularUsuario(nome, senha, algoritmo);

	}

	/**
	 * @Metodo:
	 * 
	 *          Para este metodo passamos os dois parametros usuario e senhaView
	 * 
	 * @Observação: Neste metodo o @param usuario é o usuario que retorna do banco
	 *              de dados
	 * 
	 * @param usuario
	 * @param senhaView
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	
	public Usuario autenticar(Usuario usuarioDao, String senhaView, TipoAlgoritmo tipoAlgoritmo) throws NoSuchAlgorithmException, UnsupportedEncodingException {

		CriptografiaUtil criptografiaUtil = new CriptografiaUtil();

		return criptografiaUtil.autenticar(usuarioDao, senhaView, tipoAlgoritmo);
	}

}
