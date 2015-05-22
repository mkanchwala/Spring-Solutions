package com.mkanchwala.country.dto;

import java.util.List;

public class CountryDTO extends BaseDTO {
	private static final long serialVersionUID = -7014333799276444832L;
	private String name;
	private String code;
	private String description;
	private Long population;
	private String capital;
	private List<LanguageDTO> languages;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getPopulation() {
		return population;
	}
	public void setPopulation(Long population) {
		this.population = population;
	}
	public String getCapital() {
		return capital;
	}
	public void setCapital(String capital) {
		this.capital = capital;
	}
	public List<LanguageDTO> getLanguages() {
		return languages;
	}
	public void setLanguages(List<LanguageDTO> languages) {
		this.languages = languages;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((capital == null) ? 0 : capital.hashCode());
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result	+ ((description == null) ? 0 : description.hashCode());
		result = prime * result	+ ((languages == null) ? 0 : languages.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result	+ ((population == null) ? 0 : population.hashCode());
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
		CountryDTO other = (CountryDTO) obj;
		if (capital == null) {
			if (other.capital != null)
				return false;
		} else if (!capital.equals(other.capital))
			return false;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (languages == null) {
			if (other.languages != null)
				return false;
		} else if (!languages.equals(other.languages))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (population == null) {
			if (other.population != null)
				return false;
		} else if (!population.equals(other.population))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "CountryDTO [name=" + name + ", code=" + code + ", description="	+ description + ", population=" + population + ", capital="	+ capital + ", languages=" + languages + "]";
	}
	
}
