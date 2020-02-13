package ru.pavlov.MetrologicalManagement.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import ru.pavlov.MetrologicalManagement.domain.User;
import ru.pavlov.MetrologicalManagement.repos.UserRepo;

public class CustomUserDetails implements UserDetails{

	private List<GrantedAuthority> authorities;
	private User myUser;
	
	public CustomUserDetails(User user, List<String> userRoles) {
		this.myUser = user;
		authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {		 
		for (GrantedAuthority ga : this.authorities) System.out.println(ga.toString());
		return this.authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.myUser.getPass();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.myUser.getName();
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
	
	public User getUser() {
		return this.myUser;
	}

}
