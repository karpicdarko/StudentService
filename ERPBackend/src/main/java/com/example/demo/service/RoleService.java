package com.example.demo.service;

import java.util.Optional;

import com.example.demo.jpa.ERole;
import com.example.demo.jpa.Role;

public interface RoleService {
	
	public Optional<Role> findByName(ERole name);
}
