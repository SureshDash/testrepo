package com.sg.emp.config;

import javax.ws.rs.HttpMethod;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author Suresh Dash
 *
 */
@EnableWebSecurity
@Configuration
public class EmployeeConfig extends WebSecurityConfigurerAdapter {

	/**
	 * @param empDTO
	 * @return sb Other validator utils not usingddd
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/").permitAll()
        .antMatchers(HttpMethod.POST,"/emp/register").permitAll();
		http.csrf().disable();
	}
}
