package com.mkanchwala.country.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mkanchwala.country.beans.User;
import com.mkanchwala.country.dto.CountryDTO;
import com.mkanchwala.country.manager.CountryManager;

@RestController
public class CountryController {
	
	@Autowired
	private CountryManager countryManager;

	@Transactional(readOnly = true)
	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "/countries", method = RequestMethod.GET)
	public List<CountryDTO> getCountryByCode(@AuthenticationPrincipal User user, String[] codes) {
		System.out.println("REST-API : Call to Retrieve the Countries Details by codes " + Arrays.toString(codes) + " | " + user.getUsername());
		return countryManager.findByCodes(codes);
    }

	@Transactional
	@ResponseStatus(value = HttpStatus.CREATED)
	@RequestMapping(value = "/country/add", method = RequestMethod.POST, headers = "Accept=application/json")
	public CountryDTO save(@RequestBody CountryDTO countryDTO) {
		System.out.println("REST-API : Call to save the Country details ");
		return countryManager.save(countryDTO);
    }
}
