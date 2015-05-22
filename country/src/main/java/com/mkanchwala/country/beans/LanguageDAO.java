package com.mkanchwala.country.beans;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

@Transactional
public interface LanguageDAO extends CrudRepository<Language, Long> {
	public Language save(Language language);
	public Language findByLanguageId(Integer languageId);
}
