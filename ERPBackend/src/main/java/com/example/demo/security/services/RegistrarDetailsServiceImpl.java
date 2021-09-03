package com.example.demo.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.jpa.Registrar;
import com.example.demo.service.RegistrarService;

@Service
public class RegistrarDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	private RegistrarService registrarService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Registrar registrar = registrarService.findByEmail(username)
				.orElseThrow(() -> new UsernameNotFoundException("Registrar Not Found with username: " + username));
		return RegistrarDetailsImpl.build(registrar);
	}

}
