package com.mkanchwala.security.test.security;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import com.mkanchwala.security.model.Employee;
import com.mkanchwala.security.service.AclManager;
import com.mkanchwala.security.service.EmployeeService;
import com.mkanchwala.security.service.UserGroupManager;
import com.mkanchwala.security.service.impl.SecurityTestService;
import com.mkanchwala.security.test.util.AbstractSecurityTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.acls.domain.BasePermission;
import org.springframework.security.acls.domain.GrantedAuthoritySid;
import org.springframework.security.acls.domain.PrincipalSid;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

public class SecurityAclTest extends AbstractSecurityTest {
	
	@Autowired private EmployeeService EmployeeService;
	@Autowired private UserGroupManager userGroupManager;
	@Autowired private SecurityTestService securityTestService;
	@Autowired private JdbcUserDetailsManager jdbcUserDetailsManager;
	@Autowired private AclManager aclManager;
	
	private static final String USER_ADMIN = "admin";
	private static final String USER_USER = "user";
	
	private Employee Employee = null;
	
	@Rule public ExpectedException exception = ExpectedException.none();
	
	@Before
	public void setup() {
		userGroupManager.createUserWithAuthoriy(USER_ADMIN, "ROLE_ADMIN");
		userGroupManager.createUserWithAuthoriy(USER_USER, "ROLE_USER");
		
    	Employee p1 = new Employee();
		p1.setName("Employee");
		p1.setPath("/employee");
		Employee = EmployeeService.saveOrUpdate(p1);
		
		userGroupManager.setAuthentication(USER_ADMIN);
		aclManager.addPermission(Employee.class, Employee.getId(), new PrincipalSid(USER_ADMIN), BasePermission.ADMINISTRATION);
	}

	@After
	public void tearDown() {
		jdbcUserDetailsManager.deleteUser(USER_ADMIN);
		jdbcUserDetailsManager.deleteUser(USER_USER);
		EmployeeService.deleteAll();
		aclManager.deleteAllGrantedAcl();
		SecurityContextHolder.getContext().setAuthentication(null);
	}
	
	@Test
	public void testUserHasNoAccessToEmployee() {
		boolean isGranted = aclManager.isPermissionGranted(Employee.class, Employee.getId(), new PrincipalSid(USER_USER), BasePermission.READ);
		assertThat(isGranted, is(false));
	}
	
	@Test
	public void testAdminHasNoAccessToEmployeeAsRead() {
		boolean isGranted = aclManager.isPermissionGranted(Employee.class, Employee.getId(), new PrincipalSid(USER_ADMIN), BasePermission.READ);
		assertThat(isGranted, is(false));
	}
	
	@Test
	public void testAdminHasAccessToEmployeeAsAdministration() {
		boolean isGranted = aclManager.isPermissionGranted(Employee.class, Employee.getId(), new PrincipalSid(USER_ADMIN), BasePermission.ADMINISTRATION);
		assertThat(isGranted, is(true));
	}
	
	@Test
	public void testAdminHasAccessToMethodHasRoleAdmin() {
		userGroupManager.setAuthentication(USER_ADMIN);
		assertThat(securityTestService.testHasRoleAdmin(), is(true));
	}
	
	@Test
	public void testUserHasNoAccessToMethodHasRoleAdmin() {
		userGroupManager.setAuthentication(USER_USER);
		exception.expect(AccessDeniedException.class);
		securityTestService.testHasRoleAdmin();
	}
	
	@Test
	public void testAdminHasAccessToMethodHasPermissionAdministration() {
		userGroupManager.setAuthentication(USER_ADMIN);
		assertThat(securityTestService.testHasPermissionAdministrationOnEmployee(Employee), is(true));
	}
	
	@Test
	public void testUserHasNoAccessToMethodHasPermissionAdministration() {
		userGroupManager.setAuthentication(USER_USER);
		exception.expect(AccessDeniedException.class);
		securityTestService.testHasPermissionAdministrationOnEmployee(Employee);
	}
	
	@Test
	public void testUserHasNoAccessToMethodHasPermissionRead() {
		userGroupManager.setAuthentication(USER_USER);
		exception.expect(AccessDeniedException.class);
		securityTestService.testHasPermissionReadOnEmployee(Employee);
	}
	
	@Test
	public void testAdminHasNoAccessToMethodPermissionRead() {
		userGroupManager.setAuthentication(USER_ADMIN);
		exception.expect(AccessDeniedException.class);
		securityTestService.testHasPermissionReadOnEmployee(Employee);
	}
	
	@Test
	public void testUserHasAclPermissionBasedOnRole() {
		aclManager.addPermission(Employee.class, Employee.getId(), new GrantedAuthoritySid("ROLE_USER"), BasePermission.READ);
		userGroupManager.setAuthentication(USER_USER);
		assertThat(securityTestService.testHasPermissionReadOnEmployee(Employee), is(true));
	}
	
	@Test
	public void testRemoveAclPermissionFromUser() {
		aclManager.addPermission(Employee.class, Employee.getId(), new GrantedAuthoritySid("ROLE_USER"), BasePermission.READ);
		userGroupManager.setAuthentication(USER_USER);
		assertThat(securityTestService.testHasPermissionReadOnEmployee(Employee), is(true));
		
		userGroupManager.setAuthentication(USER_ADMIN);
		aclManager.removePermission(Employee.class, Employee.getId(), new GrantedAuthoritySid("ROLE_USER"), BasePermission.READ);
		
		userGroupManager.setAuthentication(USER_USER);
		exception.expect(AccessDeniedException.class);
		securityTestService.testHasPermissionReadOnEmployee(Employee);
	}

	@Test
	public void testFilterList() {

		EmployeeService.deleteAll();
		aclManager.deleteAllGrantedAcl();

		for (int i = 0; i < 5; i++) {
			Employee m = new Employee();
			m.setName("Employee " + i);
			m.setPath("/employee/" + i);

			Employee newEmployee = EmployeeService.saveOrUpdate(m);

			if (i < 2) {
				aclManager.addPermission(Employee.class, newEmployee.getId(), new GrantedAuthoritySid("ROLE_ADMIN"), BasePermission.ADMINISTRATION);
			} else {
				aclManager.addPermission(Employee.class, newEmployee.getId(), new GrantedAuthoritySid("ROLE_USER"), BasePermission.READ);
			}
		}

		userGroupManager.setAuthentication(USER_ADMIN);
		assertThat(EmployeeService.filterEmployee(EmployeeService.findAll()).size(), is(equalTo(2)));

		userGroupManager.setAuthentication(USER_USER);
		exception.expect(AccessDeniedException.class);
		EmployeeService.filterEmployee(EmployeeService.findAll());
	}
	
	@Test
	public void encodePassword() {
		StandardPasswordEncoder encoder = new StandardPasswordEncoder("test");
		String result = encoder.encode(USER_ADMIN);
		assertTrue(encoder.matches(USER_ADMIN, result));
	}
}