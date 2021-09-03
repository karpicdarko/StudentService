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

import com.example.demo.jpa.CourseOfStudy;
import com.example.demo.service.CourseOfStudyService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class CourseOfStudyController {

	@Autowired
	private CourseOfStudyService courseOfStudyService;
	
	@GetMapping("/courseOfStudy")
	public Iterable<CourseOfStudy> getCourseOfStudies() {
		return this.courseOfStudyService.findAll();
	}
	
	@GetMapping("/courseOfStudySIdYear/{id}")
	public Optional<CourseOfStudy> getCourseByStudentIdByYear(@PathVariable("id") Integer id) {
		return this.courseOfStudyService.findByStudentIdLastYear(id);
	}
	@GetMapping("/courseOfStudySId/{id}")
	public Iterable<CourseOfStudy> getCourseByStudentId(@PathVariable("id") Integer id) {
		return this.courseOfStudyService.findByStudentId(id);
	}
	
	@PostMapping("/courseOfStudy")
	public CourseOfStudy insertCOS(@RequestBody CourseOfStudy cos) {
		return this.courseOfStudyService.saveCOS(cos);
	}
	
	@PutMapping("/courseOfStudy")
	public CourseOfStudy updateCOS(@RequestBody CourseOfStudy cos) {
		return this.courseOfStudyService.saveCOS(cos);
	}
	
	@DeleteMapping("/courseOfStudy/{id}")
	public void removeCOS(@PathVariable("id") Integer id) {
		this.courseOfStudyService.deleteCOSById(id);
	}
}
