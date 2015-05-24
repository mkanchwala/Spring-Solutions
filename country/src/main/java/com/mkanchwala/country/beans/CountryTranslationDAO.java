package com.mkanchwala.country.beans;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

/**
 * @author mkanchwala
 *
 */
@Transactional
public interface CountryTranslationDAO extends CrudRepository<CountryTranslation, Long> {
	public CountryTranslation save(CountryTranslation languageCountry);
	public CountryTranslation findByLanguageNameAndCountryCode(String languageName, String code);
}
