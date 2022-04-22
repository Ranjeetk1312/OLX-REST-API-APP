package com.olxlogin.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {//User for Authentication
		auth.userDetailsService(userDetailsService);
		/*
		auth.inMemoryAuthentication()
		.withUser("tom").password(this.passwordEncoder.encode("tom123")).roles("USER")
		.and()
		.withUser("jerry").password("jerry123").roles("ADMIN");
		 */
	}
	@Bean
	public AuthenticationManager getAuthenticationManager() throws Exception {
	return super.authenticationManager();
	}
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	public void configure(HttpSecurity http) throws Exception {//User for Authorization
		http.csrf().disable()
		.authorizeRequests()
		.antMatchers("/user").hasAnyRole("USER","ADMIN")
		.antMatchers("/admin").hasRole("ADMIN")
		.antMatchers("/olx/login/user/authonticate").permitAll()
		.and()
		.formLogin();
	}

}
