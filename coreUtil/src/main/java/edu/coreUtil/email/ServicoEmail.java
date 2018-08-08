package edu.coreUtil.email;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.Message;
import javax.mail.MessagingException;

public class ServicoEmail {

	public static void main(String[] args) throws UnsupportedEncodingException,MessagingException{

		String protocol = "smtp";
		String host = "email-smtp.us-east-1.amazonaws.com";
		String porta = "465";
		boolean isAuth = true;
		String usuario = "AKIAIKWZLSWURL2KBPXQ";
		String senha = "ArR2q3OSUOKS+dXPdmUsKOPKefI9Bo8wPZHiMEMeeheg";

		EmailUtil email = new EmailUtil(protocol, host, porta, isAuth, usuario, senha);

		email.corpoEmail("Teste", "Assunto Teste", "xxx - Teste", "text/html; charset=iso-8859-1", "MetaGedQualicorp@metaslt.com.br","Ged");
		
		List<MessageEmail> destinatarios  = new ArrayList<MessageEmail>();
		
		destinatarios.add(new MessageEmail("eduardo@metaslt.com.br", Message.RecipientType.TO, "Eduardo Nofre Reis de Sá"));
		destinatarios.add(new MessageEmail("ronaldo@metaslt.com.br", Message.RecipientType.TO, "Ronaldo Bean"));

		email.enviarEmail(destinatarios);

	}

	public void corpoEmail(String mesagem,String assuntoEmail,String Html,String tipo,String enderecoInternoNoreply,
			String aliasEmail) throws MessagingException,UnsupportedEncodingException{

	}

}
