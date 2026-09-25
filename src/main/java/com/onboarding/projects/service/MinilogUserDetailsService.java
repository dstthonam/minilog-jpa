package com.onboarding.projects.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.onboarding.projects.entity.User;
import com.onboarding.projects.repository.UserRepository;
import com.onboarding.projects.security.MinilogGrantedAuthority;
import com.onboarding.projects.security.MinilogUserDetails;

@Service
public class MinilogUserDetailsService implements UserDetailsService {
	
	private final UserRepository userRepository;
	
	@Autowired
	public MinilogUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRepository.findByUserName(username)
									.orElseThrow(() -> new UsernameNotFoundException("User not found with username : " + username));
		
		List<GrantedAuthority> authorities = user.getUserRoles()
												.stream().map(MinilogGrantedAuthority::new).collect(Collectors.toList());
		
			return new MinilogUserDetails(user.getUserId(), username, user.getUserPassword(), authorities);		
	}
	
}
