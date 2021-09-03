package com.example.demo.service;

import java.util.Optional;

import com.example.demo.jpa.Registration;

public interface RegistrationService {
	
	public Iterable<Registration> findAll();
	public Optional<Integer> findPriceByStudent(Integer id);
	public Registration saveRegistration(Registration registration);
	public void deleteRegistrationById(Integer id);
	public Iterable<Registration> findForStudentInPeriod(Integer id, Integer Year, String period);
}
