package ru.pavlov.MetrologicalManagement.security;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import ru.pavlov.MetrologicalManagement.domain.UserRepo;

public class CustomUserDetails implements UserDetails{

	private String userName;
	private String pass;
	private List<String> userRoles;
	
	public CustomUserDetails(String name, String pass, List<String> userRoles) {
		this.userName = name;
		this.pass = pass;
		this.userRoles = userRoles;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {		
		String roles = StringUtils.collectionToCommaDelimitedString(userRoles);             
		return AuthorityUtils.commaSeparatedStringToAuthorityList(roles); 
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.pass;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
