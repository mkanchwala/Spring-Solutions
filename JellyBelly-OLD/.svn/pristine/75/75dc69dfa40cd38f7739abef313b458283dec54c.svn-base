package com.jellybelly.user.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jellybelly.user.beans.User;
import com.jellybelly.user.dao.UserDAO;
import com.jellybelly.user.service.UserService;

@Service
public class UserManager {

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private UserService userService;

	public List<User> getUsers() {
		return userDAO.getUsers(null);
	}

}
