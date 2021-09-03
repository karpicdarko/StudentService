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

import com.example.demo.jpa.ClassroomCoursePeriod;
import com.example.demo.service.ClassroomCoursePeriodService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class ClassroomCoursePeriodController {
	
	@Autowired
	private ClassroomCoursePeriodService classroomCoursePeriodService;
	
	@GetMapping("/classroomCoursePeriod")
	public Iterable<ClassroomCoursePeriod> getclassroomCoursePeriods() {
		return this.classroomCoursePeriodService.findAll();
	}
	
	@PostMapping("/classroomCoursePeriod")
	public ClassroomCoursePeriod insertCCP(@RequestBody ClassroomCoursePeriod classroomCoursePeriod) {
		return this.classroomCoursePeriodService.saveCCP(classroomCoursePeriod);
	}
	
	@PutMapping("/classroomCoursePeriod")
	public ClassroomCoursePeriod updateCCP(@RequestBody ClassroomCoursePeriod classroomCoursePeriod) {
		return this.classroomCoursePeriodService.saveCCP(classroomCoursePeriod);
	}
	
	@DeleteMapping("/classroomCoursePeriod/{id}") 
	public void removeCCP(@PathVariable("id") Integer id) {
		this.classroomCoursePeriodService.deleteCCPById(id);
	}
	
}
