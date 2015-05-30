package com.jellybelly.user.validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.jellybelly.user.beans.User;
import com.jellybelly.user.dto.UserDetails;

/**
 * @author mkanchwala
 */
public class SecurityUtil {

    private static final Logger logger = LoggerFactory.getLogger(SecurityUtil.class);

    public static void logInUser(User user) {
        UserDetails userDetails = UserDetails.getBuilder()
                .firstName(user.getFirstName())
                .id(user.getId()).lastName(user.getLastName())
                .password(user.getPassword()).role(user.getRole())
                .socialSignInProvider(user.getSignInProvider()).username(user.getEmail()).build();

        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        logger.info("User logged in : " + userDetails);
    }
}
