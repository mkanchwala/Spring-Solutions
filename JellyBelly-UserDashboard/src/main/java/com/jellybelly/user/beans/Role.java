package com.jellybelly.user.beans;

/**
 * @author mkanchwala
 */
public enum Role {
    ROLE_USER("ROLE_USER");
    
    public final String parameterName;

    Role (String aParameterName) {
		this.parameterName = aParameterName;
	}
}
