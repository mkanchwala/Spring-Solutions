package com.jellybelly.user.manager;

import java.io.IOException;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionKey;
import org.springframework.social.connect.UserProfile;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.jellybelly.user.beans.SocialMedia;
import com.jellybelly.user.beans.User;
import com.jellybelly.user.beans.Views;
import com.jellybelly.user.dto.RegistrationDTO;
import com.jellybelly.user.service.DuplicateEmailException;
import com.jellybelly.user.service.RepositoryUserService;
import com.jellybelly.user.service.UserDoesnotExistException;
import com.jellybelly.user.validation.ValidatorUtil;

@Service
public class UserManager {
	private static final Logger logger = LoggerFactory.getLogger(UserManager.class);
	
	@Autowired
	private RepositoryUserService userService;
	
	/**
     * Creates the form object used in the registration form.
     * @param connection
     * @return  If a user is signing in by using a social provider, this method returns a form
     *          object populated by the values given by the provider. Otherwise this method returns
     *          an empty form object (normal form registration).
     */
	public RegistrationDTO createRegistrationDTO(Connection<?> connection) {
        RegistrationDTO registrationDTO = new RegistrationDTO();

        if (connection != null) {
            UserProfile socialMediaProfile = connection.fetchUserProfile();
            registrationDTO.setEmail(socialMediaProfile.getEmail());
            registrationDTO.setFirstName(socialMediaProfile.getFirstName());
            registrationDTO.setLastName(socialMediaProfile.getLastName());
            
            ConnectionKey providerKey = connection.getKey();
            registrationDTO.setSignInProvider(SocialMedia.valueOf(providerKey.getProviderId().toUpperCase()));
        }
        return registrationDTO;
    }
    
	/**
     * Creates a new user account by calling the service method. If the email address is found
     * from the database, this method adds a field error to the email field of the form object.
     */
    public User createUserAccount(RegistrationDTO userAccountData, BindingResult result) {
    	logger.debug("Generating a new Account with Email : " + userAccountData.getEmail());
        User registered = null;
        try {
            registered = userService.registerNewUserAccount(userAccountData);
        }
        catch (DuplicateEmailException ex) {
        	logger.debug("This Email Address already exists : " + userAccountData.getEmail());
            ValidatorUtil.addFieldError(Views.MODEL_REGISTRATION.parameterName, RegistrationDTO.FIELD_NAME_EMAIL,
                    userAccountData.getEmail(), Views.ERROR_EMAIL_EXIST.parameterName, result);
        }
        return registered;
    }
    
    /**
     * Confirms a new user account by calling from an email link.
     */
    public User confirmUser(Long userId) throws MessagingException, IOException, UserDoesnotExistException {
		User user = userService.confirmUser(userId);
		return user;
	}
}
