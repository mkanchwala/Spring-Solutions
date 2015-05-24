package com.mkanchwala.country.beans;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * @author mkanchwala
 *
 */
@Transactional
public interface CountryDAO extends CrudRepository<Country, Long> {
	  public Country save(Country country);
	  public List<Country> findAll();
	  
	  @Query("SELECT c FROM Country c WHERE c.code IN :codes")
	  public List<Country> findByCodes(@Param("codes")String[] codes);
	  
	  public Country findByCodeAndWorldNameAllIgnoreCase(String code, String worldName);
	 
}
