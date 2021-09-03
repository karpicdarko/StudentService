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

import com.example.demo.jpa.CoursePeriod;
import com.example.demo.service.CoursePeriodService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class CoursePeriodController {

	@Autowired
	private CoursePeriodService coursePeriodService;
	
	@GetMapping("/coursePeriod")
	public Iterable<CoursePeriod> getCoursePeriods() {
		return this.coursePeriodService.findAll();
	}
	@GetMapping("/coursePeriodTest/{student}/{syllabus}/{period}/{year}")
	public Iterable<CoursePeriod> getCoursePeriodsTest(@PathVariable("student") Integer studentId,
			@PathVariable("syllabus") Integer syllabusId,
			@PathVariable("period") String periodName,
			@PathVariable("year") Integer year) {
		return this.coursePeriodService.findNotRegistered(studentId, syllabusId, periodName, year);
	}
	@GetMapping("/coursePeriodBySyl/{id}")
	public Iterable<CoursePeriod> getCoursePeriodsForSyl(@PathVariable("id") Integer id) {
		return this.coursePeriodService.findBySylId(id);
	}
	@GetMapping("/coursePeriodBySylPer/{id}/{name}")
	public Iterable<CoursePeriod> getCoursePeriodsForSyl(@PathVariable("id") Integer id, @PathVariable("name") String name) {
		return this.coursePeriodService.findBySylIdAndPeriod(id, name);
	}
	
	@PostMapping("/coursePeriod")
	public CoursePeriod insertCP(@RequestBody CoursePeriod cp) {
		return this.coursePeriodService.saveCP(cp);
	}
	
	@PutMapping("/coursePeriod")
	public CoursePeriod updateCP(@RequestBody CoursePeriod cp) {
		return this.coursePeriodService.saveCP(cp);
	}
	
	@DeleteMapping("/coursePeriod/{id}")
	public void removeCP(@PathVariable("id") Integer id) {
		this.coursePeriodService.deleteCPById(id);
	}
}
