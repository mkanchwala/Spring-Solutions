package com.mkanchwala.country.beans;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

/**
 * @author mkanchwala
 *
 */
@Transactional
public interface LanguageDAO extends CrudRepository<Language, Long> {
	public Language save(Language language);
	public Language findByNameIgnoreCase(String name);
}
