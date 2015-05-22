package com.mkanchwala.country.beans;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

@Transactional
public interface LanguageCountryDAO extends CrudRepository<LanguageCountry, Long> {
	public LanguageCountry save(LanguageCountry languageCountry);
}
