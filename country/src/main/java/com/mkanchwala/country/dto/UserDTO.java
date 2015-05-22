package com.mkanchwala.country.dto;


public class UserDTO extends BaseDTO {
	
	private static final long serialVersionUID = -2781414792066801296L;
	private Integer userId;
	private String username;
	private String password;
	private String email;
	private LanguageDTO preferredLanguage;
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public LanguageDTO getPreferredLanguage() {
		return preferredLanguage;
	}
	public void setPreferredLanguage(LanguageDTO preferredLanguage) {
		this.preferredLanguage = preferredLanguage;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result	+ ((password == null) ? 0 : password.hashCode());
		result = prime * result	+ ((preferredLanguage == null) ? 0 : preferredLanguage.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		result = prime * result	+ ((username == null) ? 0 : username.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDTO other = (UserDTO) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (preferredLanguage == null) {
			if (other.preferredLanguage != null)
				return false;
		} else if (!preferredLanguage.equals(other.preferredLanguage))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserDTO [userId=" + userId + ", username=" + username + ", password=" + password + ", email=" + email + ", preferredLanguage=" + preferredLanguage + "]";
	}
}
