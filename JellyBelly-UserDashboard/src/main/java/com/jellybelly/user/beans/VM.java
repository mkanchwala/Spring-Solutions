package com.jellybelly.user.beans;

public enum VM {
	 REGISTER ("register.vm"),
	 CONFIRM("verification.vm");

	public final String parameterName;

	VM (String aParameterName)
	{
		this.parameterName = aParameterName;
	}
}
