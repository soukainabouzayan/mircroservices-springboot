package com.example.agent.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.agent.model.Agent;
import com.example.agent.repository.AgentRepository;;



@Service
public class UserPrincipalDetailsService implements UserDetailsService {

	@Autowired
	AgentRepository rep;
	

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		Agent utilisateur=rep.findAgentByEmail(email);
		UserPrincipal userPrincipal= new UserPrincipal(utilisateur);
		return userPrincipal;
	}

}
