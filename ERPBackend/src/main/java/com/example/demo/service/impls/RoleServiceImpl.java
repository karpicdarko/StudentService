package com.example.demo.service.impls;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.jpa.ERole;
import com.example.demo.jpa.Role;
import com.example.demo.repos.RoleRepo;
import com.example.demo.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{
	
	@Autowired
	private RoleRepo roleRepo;
	
	@Override
	public Optional<Role> findByName(ERole name) {
		// TODO Auto-generated method stub
		return this.roleRepo.findByName(name);
	}

}
