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

import com.example.demo.jpa.Period;
import com.example.demo.service.PeriodService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class PeriodController {

	@Autowired
	private PeriodService periodService;
	
	@GetMapping("/period")
	public Iterable<Period> getPeriods() {
		return this.periodService.findAll();
	}
	
	@GetMapping("/period/{id}")
	public Optional<Period> getPeriodById(@PathVariable("id") Integer id) {
		return this.periodService.findById(id);
	}
	
	
	@GetMapping("/periodActive")
	public Iterable<Period> getActivePeriods() {
		byte b1 = 1;
		return this.periodService.findByActiveTrue(b1);
	}
	@GetMapping("/allPeriodsActive")
	public Iterable<Period> getAllActivePeriods() {
		byte b1 = 1;
		return this.periodService.findAllByActive(b1);
	}
	
	@PostMapping("/period")
	public Period insertPeriod(@RequestBody Period period) {
		return this.periodService.savePeriod(period);
	}
	
	@PutMapping("/period")
	public Period updatePeriod(@RequestBody Period period) {
		return this.periodService.savePeriod(period);
	}
	
	@DeleteMapping("/period/{id}")
	public void removePeriod(@PathVariable("id") Integer id) {
		this.periodService.deletePeriodById(id);
	}
}
