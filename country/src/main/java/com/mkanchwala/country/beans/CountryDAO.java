package com.mkanchwala.country.beans;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

@Transactional
public interface CountryDAO extends CrudRepository<Country, Long> {

	  public List<Country> findByCode(String code);
	  
	  public Country save(Country country);
}
