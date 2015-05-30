package com.jellybelly.user.webmvc;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;

import com.jellybelly.user.beans.User;
import com.jellybelly.user.beans.VM;
import com.jellybelly.user.beans.Views;
import com.jellybelly.user.dto.RegistrationDTO;
import com.jellybelly.user.manager.UserManager;
import com.jellybelly.user.service.MailSender;
import com.jellybelly.user.validation.SecurityUtil;

/**
 * @author mkanchwala
 */
@Controller
@SessionAttributes("user")
public class RegistrationController {

	private static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);

    @Autowired
    private MailSender mailSender;
    
    @Autowired
    private UserManager userManager;
    
    /**
     * Handles the Registration Form Page and Returns the View
     * 
     * @param request
     * @param model
     * @return View
     */
    @RequestMapping(value = "/user/register", method = RequestMethod.GET)
    public String registrationForm(WebRequest request, Model model) {
    	logger.debug("View : Registration Form");
        Connection<?> connection = ProviderSignInUtils.getConnection(request);
        RegistrationDTO registration = userManager.createRegistrationDTO(connection);
        model.addAttribute(Views.MODEL_REGISTRATION.parameterName, registration);
        return Views.VIEW_REGISTRATION.parameterName;
    }

    /**
     * Handles the Registration Process and Returns the Login View
     * 
     * @param userAccountData
     * @param result
     * @param request
     * @return View
     * @throws Exception
     */
    @RequestMapping(value ="/user/register", method = RequestMethod.POST)
    public String registerUserAccount(@Valid @ModelAttribute("user") RegistrationDTO userAccountData, BindingResult result, WebRequest request) throws Exception {
        //Validating
    	if (result.hasErrors()) {
            return Views.VIEW_REGISTRATION.parameterName;
        }

        User registered = userManager.createUserAccount(userAccountData, result);

        //If email address was already found from the database, render the form view.
        if (registered == null) {
        	logger.debug("Email Was not Found");
            return Views.VIEW_REGISTRATION.parameterName;
        }

        //Logs the user in.
        SecurityUtil.logInUser(registered);
        logger.debug("User got Signed In with : ", registered.getEmail());
        
        //If the user is signing in by using a social provider, this method call stores
        //the connection to the UserConnection table. Otherwise, this method does not
        //do anything.
        mailSender.sendMessage(registered, VM.REGISTER.parameterName);
        ProviderSignInUtils.handlePostSignUp(registered.getEmail(), request);
        return Views.REDIRECT_HOME.parameterName;
    }
    
    @Transactional
	@ResponseStatus(value = HttpStatus.CREATED)
	@RequestMapping(value = "/user/confirmed", method = RequestMethod.GET)
	public User confirmUser(Long userId) throws Exception {
		logger.info("REST-API : Confirm User " + userId);
		User user = userManager.confirmUser(userId);
		mailSender.sendMessage(user, VM.CONFIRM.parameterName);
		return user;
	}
}
