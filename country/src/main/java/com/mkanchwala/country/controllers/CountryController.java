package com.mkanchwala.country.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mkanchwala.country.dto.CountryDTO;
import com.mkanchwala.country.manager.CountryManager;

/**
 * @author mkanchwala
 *
 */
@RestController
public class CountryController {
	
	@Autowired
	private CountryManager countryManager;

	/**
	 * REST Service : To Get the Countries with basic paramters :
	 * 
	 * @param user
	 * @param val
	 * @param codes
	 * @return List<CountryDTO>
	 */
	@Transactional(readOnly = true)
	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "/countries", method = RequestMethod.GET)
	public List<CountryDTO> getCountryByCode(@AuthenticationPrincipal UserDetails user, String val, String[] codes) {
		System.out.println("REST-API : Call to Retrieve the Countries Details by codes " + val + " | " + Arrays.toString(codes));
		return countryManager.findByCodes(user, val, codes);
    }

	/**
	 * REST Service : To save a Country detail :
	 * 
	 * @param user
	 * @param countryDTO
	 * @return CountryDTO
	 */
	@Transactional
	@ResponseStatus(value = HttpStatus.CREATED)
	@RequestMapping(value = "/country/add", method = RequestMethod.POST, headers = "Accept=application/json")
	public CountryDTO save(@AuthenticationPrincipal UserDetails user, @RequestBody CountryDTO countryDTO) {
		System.out.println("REST-API : Call to save the Country details ");
		if(countryDTO != null && countryDTO.getCode() != null && countryDTO.getWorldName() != null){
			return countryManager.save(user, countryDTO);
		}
		return null;
    }
}
