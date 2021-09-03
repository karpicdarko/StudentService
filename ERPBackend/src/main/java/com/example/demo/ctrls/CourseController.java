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

import com.example.demo.jpa.Course;
import com.example.demo.service.CourseService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class CourseController {

	@Autowired
	private CourseService courseService;
	
	@GetMapping("/course")
	public Iterable<Course> getCourses() {
		return this.courseService.findAll();
	}
	
	@GetMapping("/courseById/{id}")
	public Optional<Course> getCoursesById(@PathVariable("id") Integer id) {
		return this.courseService.findById(id);
	}
	
	@GetMapping("/courseBySylId/{id}")
	public Iterable<Course> getCoursesForSyllabus(@PathVariable("id") Integer id) {
		return this.courseService.findBySyllabusId(id);
	}
	
	
	@PostMapping("/course")
	public Course insertCourse(@RequestBody Course course) {
		return this.courseService.saveCourse(course);
	}
	
	@PutMapping("/course")
	public Course updateCourse(@RequestBody Course course) {
		return this.courseService.saveCourse(course);
	}
	
	@DeleteMapping("/course/{id}")
	public void removeCourse(@PathVariable("id") Integer id) {
		this.courseService.deleteCourseById(id);
	}
}
