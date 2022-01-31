package com.example.client.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.client.model.Client;


public class UserPrincipal implements UserDetails {

	
	
	private static final long serialVersionUID = 1L;
	
	Client client;
	
	@Autowired
	public UserPrincipal(Client client) {
		super();
		this.client=client;

	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		GrantedAuthority authority= new SimpleGrantedAuthority(this.client.getRole()); 
		authorities.add(authority);
		return authorities;
	}

	@Override
	public String getPassword() {
		
		return this.client.getPassword();
	}

	@Override
	public String getUsername() {
		
		return this.client.getEmail();
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
