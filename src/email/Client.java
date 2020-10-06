package email;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import frames.LogFrame;

public class Client {

	public void sendEmail() {

		try {
			ConfigureMail();

		} catch (MessagingException ex) {
			Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
			LogFrame.addLog("Can't send message \n ");

		}
	}

	public static void ConfigureMail() throws MessagingException {
		Properties props = new Properties();
		final String login = "narutoarenaP2@hotmail.com.br";
		final String password = "aqui coloca a senha";

		// Substring email and choose provider
		String provider = login.substring(login.indexOf("@") + 1, login.indexOf(".", login.indexOf("@")));
		switch (provider) {
		case "hotmail":
			// smtp and port
			props.put("mail.smtp.host", "smtp.live.com");
			props.put("mail.smtp.port", "587");
			break;

		case "gmail":
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.port", "587");
			break;

		case "live":
			props.put("mail.smtp.host", "smtp.live.com");
			props.put("mail.smtp.port", "587");
			break;

		case "yahoo":
			props.put("mail.smtp.host", "smtp.mail.yahoo.com");
			props.put("mail.smtp.port", "465");
			break;

		default:

			break;
		}

		// Enable Authentication
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");

		// Initializes Session
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(login, password);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(login));

			// Destinatary
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("edu.fick@gmail.com"));

			// Title
			message.setSubject("Naruto Log : ");

			// Subject (The Log of Game)
			message.setText(LogFrame.getLog());

			// Send message
			Transport.send(message);
			
			LogFrame.addLog("Send message\n ");
		} catch (MessagingException e) {
			LogFrame.addLog("Can't send message 2\n ");

			throw new RuntimeException(e);
		}
	}// end of ConfigureMail()
}// end of Client Class