package com.jellybelly.user.service;

import com.jellybelly.user.beans.User;
import com.jellybelly.user.dto.RegistrationDTO;

/**
 * @author mkanchwala
 */
public interface UserService {

    /**
     * Creates a new user account to the service.
     * @param userAccountData   The information of the created user account.
     * @return  The information of the created user account.
     * @throws DuplicateEmailException Thrown when the email address is found from the database.
     */
    public User registerNewUserAccount(RegistrationDTO userAccountData) throws DuplicateEmailException;
}
