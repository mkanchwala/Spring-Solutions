package com.jellybelly.user.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.jellybelly.user.beans.User;
import com.jellybelly.user.dao.UserDAO;

/**
 * @author mkanchwala
 */
public class RepositoryUserDetailsService implements UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RepositoryUserDetailsService.class);

    private UserDAO userDAO;

    @Autowired
    public RepositoryUserDetailsService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LOGGER.debug("Loading user by username: {}", username);

        User user = userDAO.findByEmail(username);
        LOGGER.debug("Found user: {}", user);

        if (user == null) {
            throw new UsernameNotFoundException("No user found with username: " + username);
        }

        UserDetails principal = com.jellybelly.user.dto.UserDetails.getBuilder()
                .firstName(user.getFirstName()).id(user.getId())
                .lastName(user.getLastName()).password(user.getPassword())
                .role(user.getRole()).socialSignInProvider(user.getSignInProvider())
                .username(user.getEmail()).build();

        LOGGER.debug("Returning user details: {}", principal);
        return principal;
    }
}
