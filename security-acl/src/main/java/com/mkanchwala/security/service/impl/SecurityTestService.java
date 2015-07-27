package com.mkanchwala.security.service.impl;

import com.mkanchwala.security.model.Employee;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class SecurityTestService {

	private static final Logger log = LoggerFactory.getLogger(SecurityTestService.class);

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public boolean testHasRoleAdmin() {
		log.info("access granted to hasRole('ROLE_ADMIN')");
		return true;
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	public boolean testHasRoleUser() {
		log.info("access granted to hasRole('ROLE_USER')");
		return true;
	}
	
	@PreAuthorize("hasRole('ROLE_GROUP')")
	public boolean testHasRoleGroup() {
		log.info("access granted to hasRole('ROLE_GROUP')");
		return true;
	}
	
	@PreAuthorize("hasPermission(#Employee, 'administration')")
	public boolean testHasPermissionAdministrationOnEmployee(Employee Employee) {
		log.info("access granted to hasPermission(#Employee, 'administration')");
		return true;
	}
	
	@PreAuthorize("hasPermission(#Employee, 'read')")
	public boolean testHasPermissionReadOnEmployee(Employee Employee) {
		log.info("access granted to hasPermission(#Employee, 'read')");
		return true;
	}
}
