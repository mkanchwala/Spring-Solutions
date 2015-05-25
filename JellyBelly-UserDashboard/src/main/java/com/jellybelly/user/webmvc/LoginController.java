package com.jellybelly.user.webmvc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jellybelly.user.beans.Views;

/**
 * @author mkanchwala
 */
@Controller
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    /**
     * Handles the Request and Returns the Login Page
     * 
     * @return View
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLoginPage() {
    	logger.debug("View : Login Form");
        return Views.VIEW_LOGIN.parameterName;
    }
}
