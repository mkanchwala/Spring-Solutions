package com.jellybelly.user.webmvc;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.jellybelly.user.beans.User;
import com.jellybelly.user.manager.UserManager;

@RestController
public class UserController {
	private static Logger logger = Logger.getLogger(UserController.class);
	
	@Autowired
	private UserManager userManager;

	@Transactional(readOnly = true)
	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public List<User> getUsers() {
		logger.info("USER-API : Call to Retrieve All the Users ");
		return userManager.getUsers();
	}
	
	@RequestMapping("/login")
	public ModelAndView login() {
		return new ModelAndView("login", "user", new User());
	}
}