package com.jellybelly.user.service;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;
import java.util.Scanner;

import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.jellybelly.user.beans.User;

@Service
public class MailSender {
	private Logger logger = Logger.getLogger(MailSender.class);
	
	@Autowired
	@Value("#{appProperties.smtpHost}")
	private String smtpHost;

	@Autowired
	@Value("#{appProperties.smtpPort}")
	private String smtpPort;
	
	@Autowired
	@Value("#{appProperties.fromName}")
	private String fromName;
	
	@Autowired
	@Value("#{appProperties.subject}")
	private String subject;
	
	@Autowired
	@Value("#{appProperties.fromAddress}")
	private String fromAddress;
	
	@Autowired
	@Value("#{appProperties.bccAddress}")
	private String bccAddress;
	
	@Autowired
	@Value("#{appProperties.temaplateBasePath}")
	private String temaplateBasePath;
	
	@Autowired
	@Value("#{appProperties.authenticatorEmail}")
	private String authenticatorEmail;
	
	@Autowired
	@Value("#{appProperties.authenticatorPassword}")
	private String authenticatorPassword;

	public boolean sendMessage(User user) throws MessagingException, IOException {
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.host", smtpHost);
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.port", smtpPort);
		
		Session session = null;

		if (authenticatorEmail != null && !authenticatorEmail.trim().equals("")) {
			Authenticator auth = new PopupAuthenticator(authenticatorEmail, authenticatorPassword);
			props.put("mail.smtp.auth", "true");
			session = Session.getInstance(props, auth);
		} else {
			props.put("mail.smtp.auth", "false");
			session = Session.getInstance(props);
		}
		// session.setDebug(true);
		MimeMessage mimeMessage = new MimeMessage(session);
		mimeMessage.setHeader("X-Priority", "medium");
		mimeMessage.setHeader("X-Mailer", "sendhtml");

		String emailTitle = fromName;
		mimeMessage.setFrom(new InternetAddress(fromAddress, emailTitle));

		logger.debug("From Address = " + emailTitle + " " + fromAddress);
		logger.debug("To Address = " + user.getEmail());
		if (user.getEmail().contains(",")) {
			String[] emails = user.getEmail().split(",");
			InternetAddress[] addresses = new InternetAddress[emails.length];
			for (int i = 0; i < emails.length; i++) {
				addresses[i] = new InternetAddress(emails[i].trim(), "");
			}
			mimeMessage.addRecipients(javax.mail.Message.RecipientType.TO, addresses);
		} else {
			mimeMessage.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(user.getEmail().trim(), ""));
		}

		if (bccAddress != null) {
			logger.debug("BCC Address = " + bccAddress);
			if (bccAddress.contains(",")) {
				String[] emails = bccAddress.split(",");
				InternetAddress[] addresses = new InternetAddress[emails.length];
				for (int i = 0; i < emails.length; i++) {
					addresses[i] = new InternetAddress(emails[i].trim(), "");
				}
				mimeMessage.addRecipients(javax.mail.Message.RecipientType.BCC,	addresses);
			} else {
				mimeMessage.addRecipient(javax.mail.Message.RecipientType.BCC,	new InternetAddress(bccAddress.trim(), ""));
			}
		}

		MimeMultipart mimeMultipart = new MimeMultipart("related");

		boolean hasBodyPart = false;
		String msgBody = this.readFile("/home/mkanchwala/templates/register.vm");

		/*if (message.getMessageContentType().equalsIgnoreCase("text/plain")
				&& msgBody != null && !msgBody.trim().equals("")) {
			BodyPart plainText = new MimeBodyPart();
			plainText.setText(msgBody);
			mimeMessage.setText(msgBody);
			mimeMultipart.addBodyPart(plainText);
			hasBodyPart = true;
		}*/

		if (/*message.getMessageContentType().equalsIgnoreCase("text/html") &&*/ 
				msgBody != null && !msgBody.trim().equals("")) {
			BodyPart html = new MimeBodyPart();
			html.setContent(msgBody, "text/html");
			mimeMultipart.addBodyPart(html);
			mimeMessage.setText(msgBody);
			hasBodyPart = true;
		}

		if (!hasBodyPart) {
			return false;
		}

		mimeMessage.setContent(mimeMultipart);
		mimeMessage.setSubject(subject);
		mimeMessage.setSentDate(new Date());

		Transport.send(mimeMessage);
		logger.debug(msgBody);
		logger.debug("Message Sent!!");
		return true;
	}
	
	private class PopupAuthenticator extends Authenticator {
		String userName = null;

		String password = null;

		PopupAuthenticator(String userName, String password) {
			this.userName = userName;
			this.password = password;
		}

		public PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(userName, password);
		}
	}
	
	private String readFile(String pathname) throws IOException {
	    File file = new File(pathname);
	    StringBuilder fileContents = new StringBuilder((int)file.length());
	    Scanner scanner = new Scanner(file);
	    String lineSeparator = System.getProperty("line.separator");
	    try {
	        while(scanner.hasNextLine()) {        
	            fileContents.append(scanner.nextLine() + lineSeparator);
	        }
	        return fileContents.toString();
	    } finally {
	        scanner.close();
	    }
	}
}
