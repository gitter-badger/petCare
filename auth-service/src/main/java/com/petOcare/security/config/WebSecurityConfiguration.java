package com.petOcare.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.petOcare.security.model.security.CustomUserDetailsService;

@Configuration
@Order(1)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	CustomUserDetailsService userDetailsService;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		    .antMatchers("/uaa/oauth/token").permitAll()
		    .anyRequest()
			.authenticated()
		    .and()
		    .csrf().disable();
	}
	
	  @Override
	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	     //   auth.userDetailsService(userDetailsService)
	            //    .passwordEncoder(passwordEncoder);
		  auth.inMemoryAuthentication()
          .withUser("user")
              .password(passwordEncoder.encode("password"))
              .roles("USER");
	    }

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	

}
