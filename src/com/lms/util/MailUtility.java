package com.lms.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.lms.controller.MailController;

public class MailUtility {
	public static void sendEmailWithoutAttachments(String toAddress,
			String ccAddress, String subject, String message)
			throws AddressException, MessagingException, IOException {
		Properties prop = new Properties();
		InputStream inputStream = DbUtil.class.getClassLoader()
				.getResourceAsStream("/mail.properties");
		prop.load(inputStream);

		String host = prop.getProperty("host");
		String port = prop.getProperty("port");
		final String userName = prop.getProperty("mailFrom");
		final String password = prop.getProperty("password");
		Properties properties = new Properties();
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", port);
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.user", userName);
		properties.put("mail.password", password);
		System.out
				.println("Mail Server Properties have been setup successfully..");

		// creates a new session with an authenticator
		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userName, password);
			}
		};

		Session session = Session.getInstance(properties,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(userName, password);
					}
				});
		// creates a new e-mail message
		Message msg = new MimeMessage(session);

		msg.setFrom(new InternetAddress(userName));

		msg.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(toAddress));
		if (!ccAddress.isEmpty()) {
			msg.setRecipients(Message.RecipientType.CC,
					InternetAddress.parse(ccAddress));
		}

		msg.setSubject(subject);
		msg.setSentDate(new Date());

		// creates message part
		MimeBodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setContent(message, "text/html");

		// creates multi-part
		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);
		// sets the multi-part as e-mail's content
		msg.setContent(multipart);

		// sends the e-mail
		Transport.send(msg);

	}

	public static void sendEmailWithAttachments(String toAddress,
			String ccAddress, String subject, String message,
			List<String> attachFiles) throws AddressException,
			MessagingException, IOException {
		Properties prop = new Properties();
		InputStream inputStream = MailController.class.getClassLoader()
				.getResourceAsStream("/mail.properties");
		prop.load(inputStream);

		String host = prop.getProperty("host");
		String port = prop.getProperty("port");
		final String userName = prop.getProperty("mailFrom");
		final String password = prop.getProperty("password");
		
		System.out.println(host+port+userName+password);
		Properties properties = new Properties();
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", port);
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.user", userName);
		properties.put("mail.password", password);
		System.out
				.println("Mail Server Properties have been setup successfully..");

		// creates a new session with an authenticator
		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userName, password);
			}
		};

		Session session = Session.getInstance(properties,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(userName, password);
					}
				});
		// creates a new e-mail message
		Message msg = new MimeMessage(session);

		msg.setFrom(new InternetAddress(userName));

		msg.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(toAddress));
		if (!ccAddress.isEmpty()) {
			msg.setRecipients(Message.RecipientType.CC,
					InternetAddress.parse(ccAddress));
		}

		msg.setSubject(subject);
		msg.setSentDate(new Date());

		// creates message part
		MimeBodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setContent(message, "text/html");

		// creates multi-part
		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);

		// adds attachments
		if (attachFiles != null && attachFiles.size() > 0) {
			for (String filePath : attachFiles) {
				MimeBodyPart attachPart = new MimeBodyPart();

				try {
					attachPart.attachFile(filePath);
				} catch (IOException ex) {
					ex.printStackTrace();
				}

				multipart.addBodyPart(attachPart);
			}
		}

		// sets the multi-part as e-mail's content
		msg.setContent(multipart);

		// sends the e-mail
		System.out.println("Message "+msg);
		Transport.send(msg);

	}
}
