package com.accenture.abts.spring.configs;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
/*
@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;

	// @Autowired
	// AuthenticationSuccessHandler successHandler;

	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource)
				.usersByUsernameQuery("select Username,Password, Active from user where username=?")
				.authoritiesByUsernameQuery(
						"select u1.username, u2.type_name from user u1, user_type u2 where u1.ID = u2.User_id and u1.Username =?");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable(); // TODO add csrf if have enough time
		http.authorizeRequests().antMatchers("/", "/login", "/logout").permitAll();
		http.authorizeRequests().antMatchers("/application/**").access("hasAnyAuthority('applicant')");
		http.authorizeRequests().antMatchers("/reviewer/**").access("hasAuthority('reviewer')");
		http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");
		http.authorizeRequests().and().formLogin()//
				.loginProcessingUrl("/j_spring_security_check")//
				.loginPage("/login")//
				// .successHandler(successHandler)//
				.defaultSuccessUrl("/aaaaaaaaa")//
				.failureUrl("/login?error=true")//
				.usernameParameter("username")//
				.passwordParameter("password")
				// Config for Logout Page
				.and().logout().logoutUrl("/logout").logoutSuccessUrl("/logoutSuccessful");
	}
}*/