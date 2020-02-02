package ru.pavlov.stydySpringSecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import ru.pavlov.stydySpringSecurity.domain.User;
import ru.pavlov.stydySpringSecurity.security.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Autowired 
	private UserDetailsService userDetailsService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		/*auth.inMemoryAuthentication().withUser("Alex").password("111").roles("Admin").and()
									 .withUser("Bob").password("222").roles("User");*/
//		UserDetails userD = userDetailsService.loadUserByUsername("Alex");
		auth.userDetailsService(userDetailsService).passwordEncoder(getPasswordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/main").permitAll()
			 .antMatchers("/toAdmin").hasRole("Admin").and()		
			 .csrf().disable()
			 .formLogin();
	}
	
	@Bean 
	public PasswordEncoder getPasswordEncoder(){ 
		return NoOpPasswordEncoder.getInstance(); 
	} 

}