package com.example.demo.ctrls;

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

import com.example.demo.jpa.Professor;
import com.example.demo.service.ProfessorService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class ProfessorController {
	
	@Autowired
	private ProfessorService professorService;
	
	@GetMapping("/professor")
	public Iterable<Professor> getProfessors() {
		return this.professorService.findAll();
	}
	
	@PostMapping("/professor")
	public Professor insertProfessor(@RequestBody Professor professor) {
		return this.professorService.saveProfessor(professor);
	}
	
	@PutMapping("/professor")
	public Professor updateProfessor(@RequestBody Professor professor) {
		return this.professorService.saveProfessor(professor);
	}
	
	@DeleteMapping("/professor/{id}")
	public void removeProfessor(@PathVariable("id") Integer id) {
		this.professorService.deleteProfessorById(id);
	}
}
