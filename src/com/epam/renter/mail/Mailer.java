package com.epam.renter.mail;

import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mailer {
	Session session; // session
	Properties properties; // properties
	Transport transport; // transport
	MimeMessage message; // message


	public Mailer() {
		properties = System.getProperties(); // get properties instance
		properties.put("mail.smtp.port", // put SMTP port to properties
				"587");
		properties.put("mail.smtp.auth", "true");// authorization - true
		properties.put("mail.smtp.starttls.enable", "true"); // tls - true

		session = Session.getDefaultInstance(properties, null);

		message = new MimeMessage(session); // new message with properties
	}
	
	public void sendEmail(String toEmail, String subject, String body)
			throws NoSuchProviderException, MessagingException {

		/* set parameters for connection to gmail SMTP */
		String fromUser = "testing2java@gmail.com";
		String fromUserEmailPassword = "javajava";
		String emailHost = "smtp.gmail.com";
		/* get parameters for message from message.properties */
	

		message.setSubject(subject);
		message.setFrom("support@renter.com");
		message.setText(body);
		
		message.addRecipient(javax.mail.Message.RecipientType.TO, 
                new InternetAddress(toEmail));


		/* create transport and send prepared message */
		transport = session.getTransport("smtp");
		transport.connect(emailHost, fromUser, fromUserEmailPassword);
		transport.sendMessage(message, message.getAllRecipients());
		transport.close();
	
	}
	public static void main(String[] args) {
		Mailer m = new Mailer();
		try {
			m.sendEmail("igor0ser@gmail.com", "test", "test body");
			System.out.println("ok");
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			System.out.println("no");
			e.printStackTrace();
		}
		System.out.println("close");
	}
}
