package com.jellybelly.user.webmvc;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jellybelly.user.beans.User;
import com.jellybelly.user.manager.UserManager;

@RestController
public class UserConfirmationController {
	private static Logger logger = Logger.getLogger(UserConfirmationController.class);
	
	@Autowired
	private UserManager userManager;

	@Transactional
	@ResponseStatus(value = HttpStatus.CREATED)
	@RequestMapping(value = "/user/confirmed", method = RequestMethod.POST)
	public User confirmUser(@RequestBody User user) throws Exception {
		logger.info("USER-API : Call to Save a User ");
		return userManager.confirmUser(user.getUserId());
	}
}
