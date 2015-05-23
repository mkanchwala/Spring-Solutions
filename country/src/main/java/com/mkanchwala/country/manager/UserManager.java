package com.mkanchwala.country.manager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mkanchwala.country.beans.Role;
import com.mkanchwala.country.beans.User;
import com.mkanchwala.country.beans.UserDAO;

/**
 * @author mkanchwala
 *
 */
@Service
public class UserManager implements UserDetailsService {
	
	private final UserDAO userDAO;

	@Autowired
	public UserManager(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDAO.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException(String.format("User %s does not exist!", username));
		}
		System.out.println(user.getUsername() + " | " + user.getPassword());
		UserDetails userDe = new org.springframework.security.core.userdetails.User(user .getUsername(),user.getPassword().toLowerCase(),
				user.getEnabled(), true, true, true, getAuthorities(new ArrayList<Role>(user.getRoles())));
		return userDe ;
	}
	
	public Collection<? extends GrantedAuthority> getAuthorities(List<Role> roles) {
	    List<GrantedAuthority> authList = getGrantedAuthorities(roles);
	    return authList;
	}

	public static List<GrantedAuthority> getGrantedAuthorities(List<Role> roles) {
	    List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
	    for (Role role : roles) {
	        authorities.add(new SimpleGrantedAuthority(role.getName()));
	    }
	    return authorities;
	}
}
