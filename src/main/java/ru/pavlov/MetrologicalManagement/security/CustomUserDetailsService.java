package ru.pavlov.MetrologicalManagement.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ru.pavlov.MetrologicalManagement.domain.User;
import ru.pavlov.MetrologicalManagement.repos.UserRepo;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private UserRepo userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByName(username);
		if (user == null) {
			throw new UsernameNotFoundException("No user present with username: "+username);
		} else {
			List<String> userRoles = new ArrayList<String>();
			String currentRole = user.getRole();
			userRoles.add(currentRole);
			return new CustomUserDetails(user, userRoles);
		}
	}

}
