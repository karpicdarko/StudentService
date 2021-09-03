package com.example.demo.service.impls;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.jpa.Registrar;
import com.example.demo.repos.RegistrarRepo;
import com.example.demo.service.RegistrarService;

@Service
public class RegistrarServiceImpl implements RegistrarService{

	@Autowired
	private RegistrarRepo registrarRepo;
	
	@Override
	public Iterable<Registrar> findAll() {
		// TODO Auto-generated method stub
		return this.registrarRepo.findAll();
	}

	@Override
	public Registrar saveRegistrar(Registrar registrar) {
		// TODO Auto-generated method stub
		return this.registrarRepo.save(registrar);
	}

	@Override
	public void deleteRegistrarById(Integer id) {
		// TODO Auto-generated method stub
		this.registrarRepo.deleteById(id);
	}

	@Override
	public Boolean existsByEmail(String email) {
		// TODO Auto-generated method stub
		return this.registrarRepo.existsByEmail(email);
	}

	@Override
	public Optional<Registrar> findByEmail(String email) {
		// TODO Auto-generated method stub
		return this.registrarRepo.findByEmail(email);
	}

	@Override
	public Optional<Registrar> findById(Integer id) {
		// TODO Auto-generated method stub
		return this.registrarRepo.findById(id);
	}

}
