/*
 * Copyright 2014-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mkanchwala.country;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

@Configuration
public class OAuth2ServerConfiguration {

    private static final String RESOURCE_ID = "rest_api";

    @Configuration
    @EnableResourceServer
    protected static class ResourceServerConfiguration extends
            ResourceServerConfigurerAdapter {

        @Override
        public void configure(ResourceServerSecurityConfigurer resources) {
            resources.resourceId(RESOURCE_ID);
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests()
                    .antMatchers("/country").hasRole("ADMIN")
                    .antMatchers("/countries").authenticated()/*
                    .antMatchers("/oauth/token").authenticated()*/
                    .and().csrf().csrfTokenRepository(csrfTokenRepository()).and().addFilterAfter(csrfHeaderFilter(), CsrfFilter.class);
            }

            private Filter csrfHeaderFilter() {
                return new OncePerRequestFilter() {

                    @Override
                    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

                        CsrfToken csrf = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
                        //CsrfToken csrf = (CsrfToken) request.getAttribute("_csrf");
                        response.setHeader("TOKEN", "MKANCHWALA");
                         if (csrf != null) {
                            Cookie cookie = WebUtils.getCookie(request, "XSRF-TOKEN");
                            String token = csrf.getToken();
                            if (cookie == null || token != null && !token.equals(cookie.getValue())) {
                                cookie = new Cookie("XSRF-TOKEN", token);
                                cookie.setPath("/");
                                response.addCookie(cookie);
                            }
                            
                            // Spring Security will allow the Token to be included in this header name
                            response.setHeader("X-CSRF-HEADER", csrf.getHeaderName());
                            // Spring Security will allow the token to be included in this parameter name
                            response.setHeader("X-CSRF-PARAM", csrf.getParameterName());
                            // this is the value of the token to be included as either a header or an HTTP parameter
                            response.setHeader("X-CSRF-TOKEN", csrf.getToken());
                        }
                        filterChain.doFilter(request, response);
                    }
                };
            }
            
            private CsrfTokenRepository csrfTokenRepository() {
                HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
                repository.setHeaderName("X-XSRF-TOKEN");
                return repository;
            }
        }


    @Configuration
    @EnableAuthorizationServer
    protected static class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {


        @Autowired
        @Qualifier("authenticationManagerBean")
        private AuthenticationManager authenticationManager;

        @Autowired
        DataSource dataSource;

        @Override
        public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
            endpoints.tokenStore(new JdbcTokenStore(dataSource)).authenticationManager(this.authenticationManager);
        }

        @Override
        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
            clients.jdbc(dataSource).withClient("clientapp")
				.authorizedGrantTypes("password", "refresh_token")
				.authorities("USER")
				.scopes("read", "write")
				.resourceIds(RESOURCE_ID)
				.secret("123456");
        }

        @Bean
        @Primary
        public DefaultTokenServices tokenServices() {
            DefaultTokenServices tokenServices = new DefaultTokenServices();
            tokenServices.setSupportRefreshToken(true);
            tokenServices.setAccessTokenValiditySeconds(300);
            tokenServices.setRefreshTokenValiditySeconds(6000);
            tokenServices.setTokenStore(new JdbcTokenStore(dataSource));
            return tokenServices;
        }
    }
}
