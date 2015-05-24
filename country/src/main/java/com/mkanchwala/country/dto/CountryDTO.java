package com.mkanchwala.country.dto;

import java.util.List;

/**
 * @author mkanchwala
 *
 */
public class CountryDTO extends BaseDTO {
	private static final long serialVersionUID = -7014333799276444832L;
	private String worldName;
	private String code;
	private Long population;
	private List<CountryTranslationDTO> translations;
	
	public String getWorldName() {
		return worldName;
	}
	public void setWorldName(String worldName) {
		this.worldName = worldName;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Long getPopulation() {
		return population;
	}
	public void setPopulation(Long population) {
		this.population = population;
	}
	public List<CountryTranslationDTO> getTranslations() {
		return translations;
	}
	public void setTranslations(List<CountryTranslationDTO> translations) {
		this.translations = translations;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result	+ ((translations == null) ? 0 : translations.hashCode());
		result = prime * result + ((worldName == null) ? 0 : worldName.hashCode());
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
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (translations == null) {
			if (other.translations != null)
				return false;
		} else if (!translations.equals(other.translations))
			return false;
		if (worldName == null) {
			if (other.worldName != null)
				return false;
		} else if (!worldName.equals(other.worldName))
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
		return "CountryDTO [worldName=" + worldName + ", code=" + code + ", population=" + population + ", translations=" + translations + "]";
	}
	
}
