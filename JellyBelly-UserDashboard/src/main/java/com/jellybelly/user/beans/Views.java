package com.jellybelly.user.beans;

public enum Views {
	 ERROR_EMAIL_EXIST ("NotExist.user.email"),
	 MODEL_REGISTRATION("user"),
	 VIEW_REGISTRATION("user/registrationForm"),
	 VIEW_LOGIN("user/login"),
	 REDIRECT_HOME("redirect:/jellybelly"),
	 REDIRECT_USER_REGISTRATION("redirect:/user/register");

	public final String parameterName;

	Views (String aParameterName)
	{
		this.parameterName = aParameterName;
	}
}
