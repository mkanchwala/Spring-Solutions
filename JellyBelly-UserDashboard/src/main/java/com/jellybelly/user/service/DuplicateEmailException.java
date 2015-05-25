package com.jellybelly.user.service;

/**
 * @author mkanchwala
 */
public class DuplicateEmailException extends Exception {

    public DuplicateEmailException(String message) {
        super(message);
    }
}
