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
@Table(name = "country")
public class Country extends BaseBean {

	private static final long serialVersionUID = -2759644396316012303L;
	private String code;
	private String name;
	private String description;
	private Long population;
	private String capital;
	private Date dateCreated;
	private Date lastUpdated;
	private String createdBy;
	private String lastUpdatedBy;
	private Set<LanguageCountry> languageCountries = new HashSet<LanguageCountry>(0);

	public Country() {
	}

	public Country(String name, String code) {
		this.name = name;
		this.code = code;
	}

	public Country(String code, String name, String description, Long population, String capital, Date dateCreated, 
			Date lastUpdated,String createdBy, String lastUpdatedBy, Set<LanguageCountry> languageCountries) {
		this.code = code;
		this.name = name;
		this.description = description;
		this.population = population;
		this.capital = capital;
		this.dateCreated = dateCreated;
		this.lastUpdated = lastUpdated;
		this.createdBy = createdBy;
		this.lastUpdatedBy = lastUpdatedBy;
		this.languageCountries = languageCountries;
	}

	
	@Id
	@Column(name = "code", unique = true, nullable = false, length = 20)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
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

	@Column(name = "population")
	public Long getPopulation() {
		return this.population;
	}

	public void setPopulation(Long population) {
		this.population = population;
	}

	@Column(name = "capital", length = 256)
	public String getCapital() {
		return this.capital;
	}

	public void setCapital(String capital) {
		this.capital = capital;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "country")
	public Set<LanguageCountry> getLanguageCountries() {
		return this.languageCountries;
	}

	public void setLanguageCountries(Set<LanguageCountry> languageCountries) {
		this.languageCountries = languageCountries;
	}
}