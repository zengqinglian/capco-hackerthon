package com.capco.mondo_sdk.config;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.Component;

@Configuration
@Component
@EnableOAuth2Sso
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		
		http.antMatcher("/dashboard/**").authorizeRequests()
				.antMatchers("/dashboard/sleep/**").permitAll()
				.anyRequest().authenticated().and()
				.csrf().disable()
				.logout().logoutUrl("/dashboard/logout").permitAll()
				.logoutSuccessUrl("/");
	}

}
