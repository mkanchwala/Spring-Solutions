package com.jellybelly.user.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author mkanchwala
 */
@Entity
@Table(name = "user_accounts")
public class User extends BaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "email", length = 100, nullable = false, unique = true)
    private String email;

    @Column(name = "first_name", length = 100,nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 100, nullable = false)
    private String lastName;

    @Column(name = "password", length = 255)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", length = 20, nullable = false)
    private Role role;

    @Enumerated(EnumType.STRING)
    @Column(name = "sign_in_provider", length = 20)
    private SocialMedia signInProvider;
    
    @Column(name = "is_verified", columnDefinition = "TINYINT")
	private Boolean isVerified;
    
    @Column(name = "sms_code")
	private Integer smsCode;

    public User() {

    }

    public static Builder getBuilder() {
        return new Builder();
    }

    @Override
    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public SocialMedia getSignInProvider() {
        return signInProvider;
    }
    
    public Boolean getIsVerified() {
        return isVerified;
    }
    
    public void setIsVerified(Boolean isVerified) {
        this.isVerified = isVerified;
    }
    
    public Integer getSmsCode() {
        return smsCode;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("dateCreated", this.getDateCreated())
                .append("email", email)
                .append("firstName", firstName)
                .append("lastName", lastName)
                .append("lastUpdated", this.getLastUpdated())
                .append("signInProvider", this.getSignInProvider())
                .append("isVerified", this.getIsVerified())
                .append("smsCode", this.getSmsCode())
                .toString();
    }

    public static class Builder {

        private User user;

        public Builder() {
            user = new User();
            user.role = Role.ROLE_USER;
        }

        public Builder email(String email) {
            user.email = email;
            return this;
        }

        public Builder firstName(String firstName) {
            user.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            user.lastName = lastName;
            return this;
        }

        public Builder password(String password) {
            user.password = password;
            return this;
        }

        public Builder signInProvider(SocialMedia signInProvider) {
            user.signInProvider = signInProvider;
            return this;
        }
        
        public Builder isVerified(Boolean isVerified) {
            user.isVerified = isVerified;
            return this;
        }
        
        public Builder smsCode(Integer smsCode) {
            user.smsCode = smsCode;
            return this;
        }

        public User build() {
            return user;
        }
    }
}