package com.example.demo.service;

import java.util.Optional;

import com.example.demo.jpa.Registrar;

public interface RegistrarService {
	
	public Iterable<Registrar> findAll();
	public Registrar saveRegistrar(Registrar registrar);
	public void deleteRegistrarById(Integer id);
	public Boolean existsByEmail(String email);
	public Optional<Registrar> findByEmail(String email);
	public Optional<Registrar> findById(Integer id);
}
