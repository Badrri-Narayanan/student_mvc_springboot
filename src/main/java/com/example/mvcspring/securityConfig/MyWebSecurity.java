package com.example.mvcspring.securityConfig;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@EnableWebSecurity
public class MyWebSecurity extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication()
			.passwordEncoder(NoOpPasswordEncoder.getInstance())
			.dataSource(dataSource);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/").permitAll()
			.antMatchers("/student", "/edit_student").hasAnyRole("TEACHER", "PRINCIPAL", "ADMIN")
			.antMatchers(HttpMethod.POST, "/save_student").hasAnyRole("TEACHER", "PRINCIPAL", "ADMIN")
			.antMatchers("/add_student").hasAnyRole("PRINCIPAL", "ADMIN")
			.antMatchers(HttpMethod.POST,"/remove_student").hasAnyRole("ADMIN")
			.and().formLogin()
			.and().logout().permitAll();
	}

	
}
