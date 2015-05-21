package com.mkanchwala.country.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;

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

	public LanguageCountry() {
	}

	public LanguageCountry(Language language, Country country) {
		this.language = language;
		this.country = country;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "language_id", nullable = false)
	public Language getLanguage() {
		return this.language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "country_id", nullable = false)
	public Country getCountry() {
		return this.country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}
}