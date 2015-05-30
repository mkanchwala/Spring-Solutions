package com.jellybelly.user.service;

/**
 * @author mkanchwala
 *
 */
public class UserDoesnotExistException extends Exception {

    public UserDoesnotExistException(String message) {
        super(message);
    }
}
