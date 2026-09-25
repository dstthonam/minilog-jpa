package com.onboarding.projects.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MinilogUserDetails implements UserDetails {
	
	private Long id;
	private String username;
	private String password;
	private Collection <? extends GrantedAuthority> authorities;
	
}
