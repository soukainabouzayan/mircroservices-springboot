package com.example.client.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.client.model.Client;
import com.example.client.repository.ClientRepository;



@Service
public class UserPrincipalDetailsService implements UserDetailsService {

	@Autowired
	ClientRepository rep;
	

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		Client utilisateur=rep.findClientByEmail(email);
		UserPrincipal userPrincipal= new UserPrincipal(utilisateur);
		return userPrincipal;
	}

}
