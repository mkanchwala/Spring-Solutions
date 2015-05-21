package com.mkanchwala.country.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mkanchwala.country.beans.Country;
import com.mkanchwala.country.beans.CountryDAO;

@RestController
public class CountryController {
	
	@Autowired
	private CountryDAO countryDAO;

	@Transactional(readOnly = true)
	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "/countries", method = RequestMethod.GET)
	public List<Country> getCountriesByCode(String code) {
		System.out.println("REST-API : Call to Retrieve the Countries Details ");
		return countryDAO.findByCode(code);
    }
	
	@Transactional
	@ResponseStatus(value = HttpStatus.CREATED)
	@RequestMapping(value = "/country/add", method = RequestMethod.POST, headers = "Accept=application/json")
	public Country save(@RequestBody Country country) {
		System.out.println("REST-API : Call to save the Country details ");
		return countryDAO.save(country);
    }
}
