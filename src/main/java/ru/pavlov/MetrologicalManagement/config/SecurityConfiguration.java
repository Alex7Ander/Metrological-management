package ru.pavlov.MetrologicalManagement.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import ru.pavlov.MetrologicalManagement.domain.AppUserPermission;
import ru.pavlov.MetrologicalManagement.domain.User;
import ru.pavlov.MetrologicalManagement.security.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Autowired 
	private UserDetailsService userDetailsService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(getPasswordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/main").hasAnyRole("ADMIN", "USER")
			.antMatchers(HttpMethod.GET, "/toAddVerificatoion").hasAuthority(AppUserPermission.VERIFICATIONS_ADD.name())
			.antMatchers("/toAdmin").hasRole("ADMIN").and()		
			.csrf().disable()
			.formLogin();
	}
	
	@Bean 
	public PasswordEncoder getPasswordEncoder(){ 
		return NoOpPasswordEncoder.getInstance(); 
	} 

}