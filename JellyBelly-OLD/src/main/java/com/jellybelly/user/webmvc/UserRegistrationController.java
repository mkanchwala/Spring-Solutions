package com.jellybelly.user.webmvc;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.jellybelly.user.beans.User;
import com.jellybelly.user.manager.UserManager;


@RestController
public class UserRegistrationController {
private static Logger logger = Logger.getLogger(UserRegistrationController.class);
	
	@Autowired
	private UserManager userManager;

	@Transactional
	@ResponseStatus(value = HttpStatus.CREATED)
	@RequestMapping(value = "/user/register", method = RequestMethod.POST)
	public User saveUser(@RequestBody User user) throws Exception {
		logger.info("USER-API : Call to Save a User ");
		return userManager.saveUser(user);
	}
	
	@RequestMapping("/register")
	public ModelAndView login() {
		return new ModelAndView("register", "user", new User());
	}
}
