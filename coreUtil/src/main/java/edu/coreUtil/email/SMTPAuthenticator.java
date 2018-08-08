package edu.coreUtil.email;

import javax.mail.PasswordAuthentication;

public class SMTPAuthenticator extends javax.mail.Authenticator {
	
	private String	usuario;
	private String	senha;

	public SMTPAuthenticator(String u, String s) {
		usuario = u;
		senha = s;
	}

	public PasswordAuthentication getPasswordAuthentication(){
		return new PasswordAuthentication(usuario, senha);
	}

}
