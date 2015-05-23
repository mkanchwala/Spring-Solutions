package com.mkanchwala.country.beans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "language_country")
public class LanguageCountry extends BaseBean {

	private static final long serialVersionUID = -8422730367845186754L;
	private Integer languageCountryId;
	private Language language;
	private Country country;
	private Date dateCreated;
	private Date lastUpdated;
	private String createdBy;
	private String lastUpdatedBy;
	
	public LanguageCountry() {
	}

	public LanguageCountry(Language language, Country country, Date dateCreated, 
			Date lastUpdated,String createdBy, String lastUpdatedBy) {
		this.language = language;
		this.country = country;
		this.dateCreated = dateCreated;
		this.lastUpdated = lastUpdated;
		this.createdBy = createdBy;
		this.lastUpdatedBy = lastUpdatedBy;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "language_country_id", unique = true, nullable = false)
	public Integer getLanguageCountryId() {
		return this.languageCountryId;
	}

	public void setLanguageCountryId(Integer languageCountryId) {
		this.languageCountryId = languageCountryId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "name", nullable = false)
	public Language getLanguage() {
		return this.language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "code", nullable = false)
	public Country getCountry() {
		return this.country;
	}

	public void setCountry(Country country) {
		this.country = country;
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
}