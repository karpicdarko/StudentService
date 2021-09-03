package com.example.demo.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.jpa.ERole;
import com.example.demo.jpa.Role;

public interface RoleRepo extends JpaRepository<Role, Integer>{
	
	public Optional<Role> findByName(ERole name);
}
