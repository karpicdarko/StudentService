package com.example.demo.ctrls;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.jpa.Student;
import com.example.demo.service.StudentService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@GetMapping("/student")
	@PreAuthorize("hasRole('REGISTRAR')")
	public Iterable<Student> getStudents() {
		return this.studentService.findAll();
	}
	
	@GetMapping("/student/{id}")
//	@PreAuthorize("hasRole('STUDENT')")
	public Optional<Student> getStudentById(@PathVariable("id") Integer id) {
		return this.studentService.findById(id);
	}
	
	@GetMapping("/studentMaxId")
	public Integer getMaxId() {
		return this.studentService.findMaxId();
	}
	
	@PostMapping("/student")
	public Student insertStudent(@RequestBody Student student) {
		return this.studentService.saveStudent(student);
	}
	
	@PutMapping("/student")
	public Student updateStudent(@RequestBody Student student) {
		return this.studentService.saveStudent(student);
	}
	
	@DeleteMapping("/student/{id}")
	public void removeStudent(@PathVariable("id") Integer id) {
		this.studentService.deleteStudentById(id);
	}
}
