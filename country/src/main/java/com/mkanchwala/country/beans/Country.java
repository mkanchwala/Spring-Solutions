package com.mkanchwala.country.beans;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "country")
public class Country extends BaseBean {

	private static final long serialVersionUID = -2759644396316012303L;
	private Integer countryId;
	private String name;
	private String code;
	private String description;
	private Long population;
	private String capital;
	private Set<LanguageCountry> languageCountries = new HashSet<LanguageCountry>(0);

	public Country() {
	}

	public Country(String name, String code) {
		this.name = name;
		this.code = code;
	}

	public Country(String name, String code, String description,
			Long population, String capital,
			Set<LanguageCountry> languageCountries) {
		this.name = name;
		this.code = code;
		this.description = description;
		this.population = population;
		this.capital = capital;
		this.languageCountries = languageCountries;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "country_id", unique = true, nullable = false)
	public Integer getCountryId() {
		return this.countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	@Column(name = "name", nullable = false, length = 256)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "code", nullable = false, length = 20)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "country")
	public Set<LanguageCountry> getLanguageCountries() {
		return this.languageCountries;
	}

	public void setLanguageCountries(Set<LanguageCountry> languageCountries) {
		this.languageCountries = languageCountries;
	}
}