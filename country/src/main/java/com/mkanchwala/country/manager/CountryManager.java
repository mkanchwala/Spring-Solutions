package com.mkanchwala.country.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.mkanchwala.country.beans.Country;
import com.mkanchwala.country.beans.CountryDAO;
import com.mkanchwala.country.beans.Language;
import com.mkanchwala.country.beans.LanguageCountry;
import com.mkanchwala.country.beans.LanguageCountryDAO;
import com.mkanchwala.country.beans.LanguageDAO;
import com.mkanchwala.country.dto.CountryDTO;
import com.mkanchwala.country.dto.LanguageDTO;

@Service
public class CountryManager {
	
	@Autowired
	private CountryDAO countryDAO;
	
	@Autowired
	private LanguageDAO languageDAO;
	
	@Autowired
	private LanguageCountryDAO languageCountryDAO;

	public CountryDTO save(CountryDTO countryDTO){
		/*Country existingCountry = countryDAO.findByCodeAndNameAllIgnoreCase(countryDTO.getCode(), countryDTO.getName());
		if(existingCountry != null){
			BeanUtils.copyProperties(existingCountry, countryDTO);
			return countryDTO;
		}*/
		Country country = new Country();
		BeanUtils.copyProperties(countryDTO, country);
		country.setDateCreated(new Date());
		country.setLastUpdated(new Date());
		countryDAO.save(country);
		if(countryDTO.getLanguages() != null && countryDTO.getLanguages().size() > 0){
			for(LanguageDTO languageDTO : countryDTO.getLanguages()){
				Language existingLanguage = languageDAO.findByNameIgnoreCase(languageDTO.getName());
				if(existingLanguage != null){
					existingLanguage.setLastUpdated(new Date());
					existingLanguage.setLastUpdatedBy("1");
					BeanUtils.copyProperties(existingLanguage, languageDTO);
					System.out.println("Already Exists : " + languageDTO.getName());
				} else {
					Language language = new Language();
					BeanUtils.copyProperties(languageDTO, language);
					language.setDateCreated(new Date());
					language.setLastUpdated(new Date());
					languageDAO.save(language);
					LanguageCountry languageCountry = new LanguageCountry();
					languageCountry.setCountry(country);
					languageCountry.setLanguage(language);
					languageCountry.setDateCreated(new Date());
					languageCountry.setLastUpdated(new Date());
					languageCountry.setCreatedBy(language.getCreatedBy());
					languageCountry.setLastUpdatedBy(language.getLastUpdatedBy());
					languageCountryDAO.save(languageCountry);
				}
			}
		}
		return countryDTO;
	}
	
	public List<CountryDTO> findByCodes(UserDetails userDetails, String[] codes) {
		List<CountryDTO> countries = new ArrayList<CountryDTO>();
		List<Country> listCountries = null;
		if (codes != null && codes.length > 0) {
			listCountries = countryDAO.findByCodes(codes);
		} else {
			listCountries = countryDAO.findAll();
		}
		for (Country country : listCountries) {
			List<LanguageDTO> languages = new ArrayList<LanguageDTO>();
			for (LanguageCountry languageCountry : country.getLanguageCountries()) {
				LanguageDTO languageDTO = new LanguageDTO();
				BeanUtils.copyProperties(languageCountry.getLanguage(), languageDTO);
				languages.add(languageDTO);
			}
			CountryDTO countryDTO = new CountryDTO();
			BeanUtils.copyProperties(country, countryDTO);
			countryDTO.setLanguages(languages);
			countries.add(countryDTO);
		}
		return countries;
	}
}
