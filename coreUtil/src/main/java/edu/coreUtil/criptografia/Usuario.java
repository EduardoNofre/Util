package edu.coreUtil.criptografia;

public class Usuario {

	private String	nome;
	private String	senha;
	private String	salt;

	public Usuario() {

	}

	public Usuario(String nome, String senha, String salt) {
		this.nome = nome;
		this.senha = senha;
		this.salt = salt;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	@Override
	public String toString() {
		return (this.nome + "\tSenha" + ": " + this.senha + "\tsalt: " + this.salt);
	}
}
