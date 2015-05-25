package com.jellybelly.user.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jellybelly.user.beans.User;


/**
 * @author mkanchwala
 */
public interface UserDAO extends JpaRepository<User, Long> {

    public User findByEmail(String email);
}
