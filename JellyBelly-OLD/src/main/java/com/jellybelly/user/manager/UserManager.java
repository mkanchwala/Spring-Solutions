package com.jellybelly.user.manager;

import java.io.IOException;
import java.util.List;

import javax.mail.MessagingException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jellybelly.user.beans.User;
import com.jellybelly.user.dao.UserDAO;
import com.jellybelly.user.service.MailSender;
import com.jellybelly.user.service.UserService;

@Service
public class UserManager {
	private static Logger logger = Logger.getLogger(UserManager.class);

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private UserService userService;
	
	@Autowired
	private MailSender mailSender;

	public List<User> getUsers() {
		return userDAO.getUsers(null, null);
	}

	public User saveUser(User user) throws MessagingException, IOException {
		List<User> existinguser = userDAO.getUsers(null, user.getEmail());
		if (existinguser.size() > 0) {
			logger.error("User Already Exists!!!");
		} else {
			userDAO.insert(user);
		//	emailSender.sendEmail(user.getEmail(), "neeta.chandra@upwork.com", "Welcome to Dashboard!");
			mailSender.sendMessage(user);
			return user;
		}
		return null;
	}
}
