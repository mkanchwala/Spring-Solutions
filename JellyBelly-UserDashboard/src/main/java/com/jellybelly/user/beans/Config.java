package com.jellybelly.user.beans;

public enum Config {
	DISPATCHER_SERVLET_NAME("dispatcher"),
	DISPATCHER_SERVLET_MAPPING("/"),
	MESSAGE_BASE_NAME("i18n/messages"),
	VIEW_RESOLVER_PREFIX("/WEB-INF/jsp/"),
    VIEW_RESOLVER_SUFFIX(".jsp");

	public final String parameterName;

	Config (String aParameterName)
	{
		this.parameterName = aParameterName;
	}
}
