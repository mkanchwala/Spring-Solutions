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
import com.mkanchwala.country.beans.CountryTranslation;
import com.mkanchwala.country.beans.CountryTranslationDAO;
import com.mkanchwala.country.beans.Language;
import com.mkanchwala.country.beans.LanguageDAO;
import com.mkanchwala.country.beans.User;
import com.mkanchwala.country.beans.UserDAO;
import com.mkanchwala.country.dto.CountryDTO;
import com.mkanchwala.country.dto.CountryTranslationDTO;

/**
 * @author mkanchwala
 *
 */
@Service
public class CountryManager {
	
	@Autowired
	private CountryDAO countryDAO;
	
	@Autowired
	private LanguageDAO languageDAO;
	
	@Autowired
	private CountryTranslationDAO languageCountryDAO;
	
	@Autowired
	private UserDAO userDAO;

	/**
	 * To Save the Country Details 
	 * 
	 * @param userDetails
	 * @param countryDTO
	 * @return countryDTO
	 */
	public CountryDTO save(UserDetails userDetails, CountryDTO countryDTO){
		
		//Saving the country details 
		Country country = new Country();
		BeanUtils.copyProperties(countryDTO, country);
		country.setDateCreated(new Date());
		country.setLastUpdated(new Date());
		//Maintaining the history for creation and updations
		country.setCreatedBy(userDetails.getUsername());
		country.setLastUpdatedBy(userDetails.getUsername());
		countryDAO.save(country);
		
		if(countryDTO.getTranslations() != null && countryDTO.getTranslations().size() > 0){
			for(CountryTranslationDTO languageDTO : countryDTO.getTranslations()){
				Language existingLanguage = languageDAO.findByNameIgnoreCase(languageDTO.getName());
				//Saving the Mappings between the Countries and Languages
				CountryTranslation languageCountry = new CountryTranslation();
				languageCountry.setCountry(country);
				System.out.println(languageDTO.getLocalName() + " | " + languageDTO.getCapital());
				languageCountry.setLocalName(languageDTO.getLocalName());
				languageCountry.setCapital(languageDTO.getCapital());
				languageCountry.setDescription(languageDTO.getDetails());
				if(existingLanguage != null){
					
					//Updating the Existing one's details
					existingLanguage.setLastUpdated(new Date());
					existingLanguage.setLastUpdatedBy(userDetails.getUsername());
					languageDAO.save(existingLanguage);
					
					//Updating the Language's Mapping
					languageCountry.setLanguage(existingLanguage);
					languageCountry.setCreatedBy(existingLanguage.getCreatedBy());
					languageCountry.setLastUpdatedBy(existingLanguage.getLastUpdatedBy());
					BeanUtils.copyProperties(existingLanguage, languageDTO);
					System.out.println("Already Exists : " + languageDTO.getName());
				} else {
					//Saving the Langauges
					Language language = new Language();
					System.out.println("Adding new : " + languageDTO.getName());
					BeanUtils.copyProperties(languageDTO, language);
					language.setDateCreated(new Date());
					language.setLastUpdated(new Date());
					language.setCreatedBy(userDetails.getUsername());
					language.setLastUpdatedBy(userDetails.getUsername());
					languageDAO.save(language);
					
					//LanguagecountryMapping
					languageCountry.setLanguage(language);
					languageCountry.setCreatedBy(language.getCreatedBy());
					languageCountry.setLastUpdatedBy(language.getLastUpdatedBy());
				}
				
				//Save if there is none existing
				if(languageCountryDAO.findByLanguageNameAndCountryCode(languageDTO.getName(), countryDTO.getCode()) == null){
					languageCountry.setDateCreated(new Date());
					languageCountry.setLastUpdated(new Date());
					languageCountryDAO.save(languageCountry);
				}
			}
		}
		return countryDTO;
	}
	
	/**
	 * To Return User Queried results for Country search 
	 * 
	 * @param userDetails
	 * @param val
	 * @param codes
	 * @return List<CountryDTO>
	 */
	public List<CountryDTO> findByCodes(UserDetails userDetails, String val, String[] codes) {
		//Looking for User preference
		User user = userDAO.findByUsername(userDetails.getUsername());
		List<CountryDTO> countries = new ArrayList<CountryDTO>();
		List<Country> listCountries = null;
		
		//Checking if Codes provided then returning Selective Results, else sending everything else
		if (codes != null && codes.length > 0) {
			listCountries = countryDAO.findByCodes(codes);
		} else {
			listCountries = countryDAO.findAll();
		}
		for (Country country : listCountries) {
			CountryDTO countryDTO = new CountryDTO();
			BeanUtils.copyProperties(country, countryDTO);
			
			List<CountryTranslationDTO> languages = new ArrayList<CountryTranslationDTO>();
			for (CountryTranslation languageCountry : country.getCountryTranslations()) {
				//Adding All Languages
				if(val != null && val.equalsIgnoreCase("ALL")){
					CountryTranslationDTO languageDTO = new CountryTranslationDTO();
					BeanUtils.copyProperties(languageCountry.getLanguage(), languageDTO);
					languageDTO.setLocalName(languageCountry.getLocalName());
					languageDTO.setCapital(languageCountry.getCapital());
					languageDTO.setDetails(languageCountry.getDescription());
					languages.add(languageDTO);
				} 
				//Adding User Preferred only
				else if (user.getLanguage().getName().equalsIgnoreCase(languageCountry.getLanguage().getName())){
					CountryTranslationDTO languageDTO = new CountryTranslationDTO();
					BeanUtils.copyProperties(languageCountry.getLanguage(), languageDTO);
					languageDTO.setLocalName(languageCountry.getLocalName());
					languageDTO.setCapital(languageCountry.getCapital());
					languageDTO.setDetails(languageCountry.getDescription());
					languages.add(languageDTO);
				}
			}
			countryDTO.setTranslations(languages);
			countries.add(countryDTO);
		}
		return countries;
	}
}
