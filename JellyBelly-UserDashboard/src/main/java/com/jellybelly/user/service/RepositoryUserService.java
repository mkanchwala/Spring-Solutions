package com.jellybelly.user.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jellybelly.user.beans.User;
import com.jellybelly.user.dao.UserDAO;
import com.jellybelly.user.dto.RegistrationDTO;

/**
 * @author mkanchwala
 */
@Service
public class RepositoryUserService  {

    private static final Logger LOGGER = LoggerFactory.getLogger(RepositoryUserService.class);

    private PasswordEncoder passwordEncoder;

    private UserDAO userDAO;

    @Autowired
    public RepositoryUserService(PasswordEncoder passwordEncoder, UserDAO userDAO) {
        this.passwordEncoder = passwordEncoder;
        this.userDAO = userDAO;
    }

    @Transactional
    public User registerNewUserAccount(RegistrationDTO userAccountData) throws DuplicateEmailException {

        if (emailExist(userAccountData.getEmail())) {
            LOGGER.debug("Email: {} exists. Throwing exception.", userAccountData.getEmail());
            throw new DuplicateEmailException("The email address: " + userAccountData.getEmail() + " is already in use.");
        }

        String encodedPassword = encodePassword(userAccountData);
        User.Builder user = User.getBuilder().email(userAccountData.getEmail()).firstName(userAccountData.getFirstName())
                .lastName(userAccountData.getLastName()).password(encodedPassword);

        if (userAccountData.isSocialSignIn()) {
            user.signInProvider(userAccountData.getSignInProvider());
        }
        User registered = user.build();
        return userDAO.save(registered);
    }
    
    @Transactional
    public User confirmUser(Long userId) throws  UserDoesnotExistException {
    	User registeredUser = userDAO.findOne(userId);
        if (registeredUser == null) {
            throw new UserDoesnotExistException("The user Id : " + userId + " doesn't exist");
        }
        registeredUser.setIsVerified(true);
        return userDAO.save(registeredUser);
    }
    
    private boolean emailExist(String email) {
        User user = userDAO.findByEmail(email);
        if (user != null) {
            LOGGER.debug("User account: {} found with email: {}. Returning true.", user, email);
            return true;
        }
        return false;
    }

    private String encodePassword(RegistrationDTO dto) {
        String encodedPassword = null;
        if (dto.isNormalRegistration()) {
            LOGGER.debug("Registration is normal registration. Encoding password.");
            encodedPassword = passwordEncoder.encode(dto.getPassword());
        }
        return encodedPassword;
    }
}
