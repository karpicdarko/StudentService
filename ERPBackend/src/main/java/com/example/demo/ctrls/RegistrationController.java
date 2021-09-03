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

import com.example.demo.jpa.Registration;
import com.example.demo.service.RegistrationService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class RegistrationController {
	
	@Autowired
	private RegistrationService registrationService;
	
	@GetMapping("/registration")
//	@PreAuthorize("hasRole('REGISTRAR')")
	public Iterable<Registration> getRegistrations() {
		return this.registrationService.findAll();
	}
	
	@GetMapping("/registrationPrice/{id}")
	public Optional<Integer> getPriceForStudent(@PathVariable("id") Integer id) {
		return this.registrationService.findPriceByStudent(id);
	}
	
	@GetMapping("/registrationStudent/{id}/{year}/{period}")
	public Iterable<Registration> getRegistrationsForStudent(@PathVariable("id") Integer id, @PathVariable("year") Integer year, @PathVariable("period") String period) {
		System.out.println("AAAA OVO su parametri " + id + year + period);
		return this.registrationService.findForStudentInPeriod(id, year, period);
	}
	
	@PostMapping("/registration")
//	@PreAuthorize("hasRole('REGISTRAR')")
	public Registration insertRegistration(@RequestBody Registration registration) {
		
		return this.registrationService.saveRegistration(registration);	
	}
	
	
	@PutMapping("/registration")
	public Registration updateRegistration(@RequestBody Registration registration) {
		return this.registrationService.saveRegistration(registration);
	}
	
	@DeleteMapping("/registration/{id}")
	public void removeRegistration(@PathVariable("id") Integer id) {
		this.registrationService.deleteRegistrationById(id);
	}
}
