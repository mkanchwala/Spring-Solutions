package com.mkanchwala.country.beans;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "language")
public class Language extends BaseBean {

	private static final long serialVersionUID = -2955728502693240885L;
	private Integer languageId;
	private String name;
	private String description;
	private Set<User> users = new HashSet<User>(0);
	private Set<LanguageCountry> languageCountries = new HashSet<LanguageCountry>(0);

	public Language() {
	}

	public Language(String name) {
		this.name = name;
	}

	public Language(String name, String description, Set<User> users,
			Set<LanguageCountry> languageCountries) {
		this.name = name;
		this.description = description;
		this.users = users;
		this.languageCountries = languageCountries;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "language_id", unique = true, nullable = false)
	public Integer getLanguageId() {
		return this.languageId;
	}

	public void setLanguageId(Integer languageId) {
		this.languageId = languageId;
	}

	@Column(name = "name", nullable = false, length = 256)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "description", length = 256)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "language")
	public Set<User> getUsers() {
		return this.users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "language")
	public Set<LanguageCountry> getLanguageCountries() {
		return this.languageCountries;
	}

	public void setLanguageCountries(Set<LanguageCountry> languageCountries) {
		this.languageCountries = languageCountries;
	}
}