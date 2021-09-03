package com.example.demo.ctrls;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.jpa.Registrar;
import com.example.demo.service.RegistrarService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class RegistrarController {
	
	@Autowired
	private RegistrarService registrarService;
	
	@GetMapping("/registrar")
	public Iterable<Registrar> getRegistrars() {
		return this.registrarService.findAll();
	}
	
	@GetMapping("/registrar/{id}")
	public Optional<Registrar> getRegistrarById(@PathVariable("id") Integer id) {
		return this.registrarService.findById(id);
	}
	
	@PostMapping("/registrar")
	public Registrar insertRegistrar(@RequestBody Registrar registrar) {
		return this.registrarService.saveRegistrar(registrar);
	}
	
	@PutMapping("/registrar")
	public Registrar updateRegistrar(@RequestBody Registrar registrar) {
		return this.registrarService.saveRegistrar(registrar);
	}
	
	@DeleteMapping("/registrar/{id}")
	public void removeRegistrar(@PathVariable("id") Integer id) {
		this.registrarService.deleteRegistrarById(id);
	}
}
