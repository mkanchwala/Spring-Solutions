package com.mkanchwala.country.beans;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "language")
public class Language extends BaseBean {

	private static final long serialVersionUID = -2955728502693240885L;
	private String name;
	private String description;
	private Date dateCreated;
	private Date lastUpdated;
	private String createdBy;
	private String lastUpdatedBy;
	private Set<User> users = new HashSet<User>(0);
	private Set<LanguageCountry> languageCountries = new HashSet<LanguageCountry>(0);

	public Language() {
	}

	public Language(String name) {
		this.name = name;
	}

	public Language(String name, String description, String username, Date dateCreated, 
			Date lastUpdated,String createdBy, String lastUpdatedBy, Set<User> users,
			Set<LanguageCountry> languageCountries) {
		this.name = name;
		this.description = description;
		this.dateCreated = dateCreated;
		this.lastUpdated = lastUpdated;
		this.createdBy = createdBy;
		this.lastUpdatedBy = lastUpdatedBy;
		this.users = users;
		this.languageCountries = languageCountries;
	}

	@Id
	@Column(name = "name", unique = true, nullable = false, length = 256)
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
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_created", nullable = false, length = 19)
	public Date getDateCreated() {
		return this.dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_updated", length = 19)
	public Date getLastUpdated() {
		return this.lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	
	@Column(name = "created_by", length = 256)
	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@Column(name = "last_updated_by", length = 256)
	public String getLastUpdatedBy() {
		return this.lastUpdatedBy;
	}

	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
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