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
public class SignUpController {

    private static final Logger logger = LoggerFactory.getLogger(SignUpController.class);

    /**
     * Handles the Request and Redirects request to the registration page. 
     * 
     * @return View
     */
    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String redirectRequestToRegistrationPage() {
    	logger.debug("View : Redirected to Registration Page");
        return Views.REDIRECT_USER_REGISTRATION.parameterName;
    }
}
