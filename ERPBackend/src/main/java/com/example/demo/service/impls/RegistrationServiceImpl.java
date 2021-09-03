package com.example.demo.service.impls;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.jpa.Registration;
import com.example.demo.repos.RegistrationRepo;
import com.example.demo.service.RegistrationService;


@Service
public class RegistrationServiceImpl implements RegistrationService{

	@Autowired
	private RegistrationRepo registrationRepo;
	
	@Override
	public Iterable<Registration> findAll() {
		// TODO Auto-generated method stub
		return this.registrationRepo.findAll();
	}

	@Override
	public Optional<Integer> findPriceByStudent(Integer id) {
		// TODO Auto-generated method stub
		return this.registrationRepo.findPriceByStudent(id);
	}

	@Override
	public Registration saveRegistration(Registration registration) {
		// TODO Auto-generated method stub
		return this.registrationRepo.save(registration);
	}

	@Override
	public void deleteRegistrationById(Integer id) {
		// TODO Auto-generated method stub
		this.registrationRepo.deleteById(id);
	}

	@Override
	public Iterable<Registration> findForStudentInPeriod(Integer id, Integer year, String period) {
		// TODO Auto-generated method stub
		
		return this.registrationRepo.findReg(id, year,  period);
	}

}
