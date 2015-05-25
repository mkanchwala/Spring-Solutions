package com.jellybelly.user.service;

import java.io.File;
import java.io.StringWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;

import javax.annotation.Resource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.tools.ToolContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.jellybelly.user.beans.User;

@Service
public class MailSender {
	private Logger logger = Logger.getLogger(MailSender.class);
	
	@Resource
    private Environment env;

	@Autowired
	private VelocityEngine velocityEngine;

	private ToolContext toolContext;
	
	/**
	 * @param templateParamDTO
	 * @return hashMap
	 */
	public HashMap<String, Object> setTemplateParam(User user) {
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		if (user != null) {
			hashMap.put("firstName", user.getFirstName());
			hashMap.put("lastName", user.getLastName());
			hashMap.put("email", user.getEmail());
		}
		hashMap.put("baseUrl", env.getProperty("baseUrl"));
		return hashMap;
	}

	public boolean sendMessage(User user) throws Exception {
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.host", env.getProperty("smtpHost"));
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.port", env.getProperty("smtpPort"));
		
		Session session = null;

		if (env.getProperty("authenticatorEmail") != null && !env.getProperty("authenticatorEmail").trim().equals("")) {
			Authenticator auth = new PopupAuthenticator(env.getProperty("authenticatorEmail"), env.getProperty("authenticatorPassword"));
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

		String emailTitle = env.getProperty("fromName");
		mimeMessage.setFrom(new InternetAddress(env.getProperty("fromAddress"), emailTitle));

		logger.debug("From Address = " + emailTitle + " " + env.getProperty("fromAddress"));
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

		if (env.getProperty("bccAddress") != null) {
			logger.debug("BCC Address = " + env.getProperty("bccAddress"));
			if (env.getProperty("bccAddress").contains(",")) {
				String[] emails = env.getProperty("bccAddress").split(",");
				InternetAddress[] addresses = new InternetAddress[emails.length];
				for (int i = 0; i < emails.length; i++) {
					addresses[i] = new InternetAddress(emails[i].trim(), "");
				}
				mimeMessage.addRecipients(javax.mail.Message.RecipientType.BCC,	addresses);
			} else {
				mimeMessage.addRecipient(javax.mail.Message.RecipientType.BCC,	new InternetAddress(env.getProperty("bccAddress").trim(), ""));
			}
		}

		MimeMultipart mimeMultipart = new MimeMultipart("related");

		boolean hasBodyPart = false;
		String msgBody = this.findMessageTemplate("register.vm", this.setTemplateParam(user));
		logger.debug(msgBody);
		
		if (msgBody != null && !msgBody.trim().equals("")) {
			BodyPart html = new MimeBodyPart();
			html.setContent(msgBody, "text/html");
			mimeMultipart.addBodyPart(html);
			mimeMessage.setText(msgBody);
			hasBodyPart = true;
		}

		if (!hasBodyPart) {
			return false;
		}
		logger.debug("Sending Now......");
		mimeMessage.setContent(mimeMultipart);
		mimeMessage.setSubject(env.getProperty("subject"));
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
	
	@SuppressWarnings("rawtypes")
	public String findMessageTemplate(String templateName, HashMap parameters) throws Exception {
		logger.debug("MailSender : findMessageWithTemplate is invoked");
		if(this.checkingTemplate(templateName)){
			StringWriter writer = new StringWriter();
			VelocityContext velocityContext = new VelocityContext(parameters, toolContext);
			logger.debug("Template Exists ");
			velocityEngine.mergeTemplate(templateName, "UTF-8", velocityContext, writer);
			String resultString = writer.toString();
			logger.debug("Template : "+resultString);
			return resultString;
		} else {
			logger.debug("Vm file not found");
			return null ;
		}
	}
	
	/**
     * @param messageType
     * @return
     * @throws Exception
     */
    public boolean checkingTemplate(String templateName) throws Exception{
		String path = env.getProperty("templateBasePath") + templateName;
		logger.debug(path);
		File file = new File(path);
		if (file.exists()) {
			return true;
		}
		return false;
	 }
}
