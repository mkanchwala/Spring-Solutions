package com.jellybelly.user.beans;

/**
 * @author mkanchwala
 */
public enum SocialMedia {
    FACEBOOK("Facebook"),
    TWITTER("Twitter");
    
    public final String parameterName;

    SocialMedia (String aParameterName)
	{
		this.parameterName = aParameterName;
	}
}
