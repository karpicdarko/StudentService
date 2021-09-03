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

import com.example.demo.jpa.Syllabus;
import com.example.demo.service.SyllabusService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class SyllabusController {
	
	@Autowired
	private SyllabusService syllabusService;
	
	@GetMapping("/syllabus")
	public Iterable<Syllabus> getSyllabuses() {
		return this.syllabusService.findAll();
	}
	
	@GetMapping("/syllabusSId/{id}")
	public Optional<Syllabus> getSyllabusByStudentId(@PathVariable("id") Integer id) {
		return this.syllabusService.findByStudentId(id);
	}
	
	@PostMapping("/syllabus")
	public Syllabus insertSyllabus(@RequestBody Syllabus syllabus) {
		return this.syllabusService.saveSyllabus(syllabus);
	}
	
	@PutMapping("/syllabus")
	public Syllabus updateSyllabus(@RequestBody Syllabus syllabus) {
		return this.syllabusService.saveSyllabus(syllabus);
	}
	
	@DeleteMapping("/syllabus/{id}")
	public void removeSyllabus(@PathVariable("id") Integer id) {
		this.syllabusService.deleteSyllabusById(id);
	}

}
