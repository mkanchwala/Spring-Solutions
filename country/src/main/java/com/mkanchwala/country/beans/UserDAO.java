package com.mkanchwala.country.beans;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

/**
 * @author mkanchwala
 *
 */
@Transactional
public interface UserDAO extends CrudRepository<User, Long> {

  /**
   * Return the user having the passed email or null if no user is found.
   * 
   * @param email the user email.
   */
  public User findByEmail(String email);
  
  public User findByUsername(String username);

}