package com.mkanchwala.country.beans;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

/**
 * @author mkanchwala
 *
 */
@Transactional
public interface LanguageCountryDAO extends CrudRepository<LanguageCountry, Long> {
	public LanguageCountry save(LanguageCountry languageCountry);
	public LanguageCountry findByLanguageNameAndCountryCode(String languageName, String code);
}
