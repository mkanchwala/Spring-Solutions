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
import com.mkanchwala.country.beans.User;
import com.mkanchwala.country.beans.UserDAO;
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
		
		if(countryDTO.getLanguages() != null && countryDTO.getLanguages().size() > 0){
			for(LanguageDTO languageDTO : countryDTO.getLanguages()){
				Language existingLanguage = languageDAO.findByNameIgnoreCase(languageDTO.getName());
				if(existingLanguage != null){
					//Updating the Existing one's details
					existingLanguage.setLastUpdated(new Date());
					existingLanguage.setLastUpdatedBy(userDetails.getUsername());
					BeanUtils.copyProperties(existingLanguage, languageDTO);
					System.out.println("Already Exists : " + languageDTO.getName());
				} else {
					//Saving the Langauges
					Language language = new Language();
					BeanUtils.copyProperties(languageDTO, language);
					language.setDateCreated(new Date());
					language.setLastUpdated(new Date());
					language.setCreatedBy(userDetails.getUsername());
					language.setLastUpdatedBy(userDetails.getUsername());
					languageDAO.save(language);
					
					//Saving the Mappings between the Countries and Languages
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
			
			List<LanguageDTO> languages = new ArrayList<LanguageDTO>();
			for (LanguageCountry languageCountry : country.getLanguageCountries()) {
				//Adding All Languages
				if(val != null && val.equalsIgnoreCase("ALL")){
					System.out.println("Adding All");
					LanguageDTO languageDTO = new LanguageDTO();
					BeanUtils.copyProperties(languageCountry.getLanguage(), languageDTO);
					languages.add(languageDTO);
					
				} 
				//Adding User Preferred only
				else if (user.getLanguage().getName().equalsIgnoreCase(languageCountry.getLanguage().getName())){
					System.out.println("Adding Selected");
					LanguageDTO languageDTO = new LanguageDTO();
					BeanUtils.copyProperties(languageCountry.getLanguage(), languageDTO);
					languages.add(languageDTO);
				}
			}
			countryDTO.setLanguages(languages);
			countries.add(countryDTO);
		}
		return countries;
	}
}
