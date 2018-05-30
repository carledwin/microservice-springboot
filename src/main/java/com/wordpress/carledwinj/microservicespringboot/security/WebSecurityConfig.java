package com.wordpress.carledwinj.microservicespringboot.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity httpSecurity)throws Exception{
		httpSecurity.csrf().disable().authorizeRequests()
		.antMatchers(HttpMethod.GET, "/")
		.permitAll().anyRequest()
		.authenticated()
		.and().formLogin().permitAll()
		.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception{
		authenticationManagerBuilder.inMemoryAuthentication()
		.withUser("@carl").password("123").roles("ADMIN");
	}
	
	@Override
	public void configure(WebSecurity webSecurity) throws Exception{
		webSecurity.ignoring().antMatchers("/style/**");//utilizado para nao bloquear os arquivos estaticos
	}
}
