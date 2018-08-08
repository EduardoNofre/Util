package edu.coreUtil.email;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * @author eduardo
 *
 */
public class EmailUtil {

	private Session		sessao;
	private MimeMessage	message;
	private Transport	transport;

	/**
	 * 
	 * @param protocol
	 *            : smtp ou ssl
	 * 
	 * @param host
	 *            : O host varia de acordo com seu servidor de email utilizado
	 *            email-smtp.us-east-1.amazonaws.com
	 * 
	 * @param porta
	 *            : A porta varia de acordo com seu servidor 465
	 * 
	 * @param isAuth
	 *            : boolean indica se será autenticado sim ou não se sim True,
	 *            não false
	 * 
	 * @param usuario
	 *            : usuario do email
	 * 
	 * @param senha
	 *            : senha do e-mail
	 * 
	 * @throws NoSuchProviderException
	 * 
	 */
	public EmailUtil(String protocol, String host, String porta, boolean isAuth, String usuario, String senha)throws NoSuchProviderException {

		getSession(protocol, host, porta, isAuth, usuario, senha);

	}

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

	private void getSession(String protocol,String host,String porta,boolean isAuth,String usuario,String senha)
			throws NoSuchProviderException{

		sessao = null;

		Properties props = new Properties();
		props.setProperty("mail.transport.protocol", protocol);
		props.setProperty("mail.host", host);
		props.put("mail.smtp.socketFactory.port", porta);
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", isAuth);
		props.put("mail.smtp.port", porta);
		// create some properties and get the default Session
		Authenticator auth = new SMTPAuthenticator(usuario, senha);
		sessao = Session.getDefaultInstance(props, auth);
		sessao.setDebug(true);
		transport = sessao.getTransport();
		message = new MimeMessage(sessao);

	}

	/**
	 * 
	 * @param mesagem
	 *            : mesagem do e-mail
	 * 
	 * @param assuntoEmail
	 *            : assunto do email
	 * 
	 * @param Html
	 *            : o Html a ser usado no e-mail
	 * 
	 * @param tipo
	 *            : "text/html; charset=iso-8859-1"
	 * 
	 * @param enderecoInternoNoreply
	 *            : exemplo o e-mail do noreply@metaslt.com.br esse e-mail são
	 *            cadastrado pela equipe de segurança ou responsavel pelo os
	 *            e-mails
	 * 
	 * @param aliasEmail
	 *            : O alias do e-mail geralmente vem antes do e-mail exemplo:
	 *            Equipe MetaSLT <MetaGedQualicorp@metaslt.com.br> neste exemplo
	 *            o alias é Equipe MetaSLT.
	 * 
	 * @throws MessagingException
	 * @throws UnsupportedEncodingException
	 * 
	 * 
	 */

	public void corpoEmail(String mesagem,String assuntoEmail,String html,String tipo,String enderecoInternoNoreply,String aliasEmail) throws MessagingException,UnsupportedEncodingException{

		
		message.setSubject(assuntoEmail);

		message.setSentDate(new Date());

		InternetAddress addressFrom = new InternetAddress(enderecoInternoNoreply, aliasEmail);

		message.setFrom(addressFrom);

		Multipart mp = new MimeMultipart();

		MimeBodyPart htmlPart = new MimeBodyPart();

		htmlPart.setContent(html, "text/html");
		
		mp.addBodyPart(htmlPart);

		message.setContent(mp);
		
		message.saveChanges();

	}

	/**
	 * 
	 * enviarEmail :Enviar o e-mail
	 * 
	 * @return
	 * @throws MessagingException
	 * 
	 */

	public Boolean enviarEmail(List<MessageEmail> destinatarios) throws MessagingException{

		transport.connect();

		for (MessageEmail destino : destinatarios) {

			try {

				message.addRecipient(destino.getRecipiente(),new InternetAddress((String) destino.getEmail(), (String) destino.getNome()));

			} catch (UnsupportedEncodingException e) {
				
				e.printStackTrace();
			}

		}
		
		transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
		
		transport.close();

		return true;
	}
}
