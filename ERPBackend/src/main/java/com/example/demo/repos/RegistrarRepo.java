package com.example.demo.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.jpa.Registrar;

public interface RegistrarRepo extends JpaRepository<Registrar, Integer>{
	public Boolean existsByEmail(String email);
	public Optional<Registrar> findByEmail(String email);
}
