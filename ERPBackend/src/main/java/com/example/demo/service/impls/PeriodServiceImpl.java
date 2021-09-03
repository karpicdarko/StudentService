package com.example.demo.service.impls;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.jpa.Period;
import com.example.demo.repos.PeriodRepo;
import com.example.demo.service.PeriodService;

@Service
public class PeriodServiceImpl implements PeriodService{

	@Autowired
	private PeriodRepo periodRepo;
	
	@Override
	public Iterable<Period> findAll() {
		// TODO Auto-generated method stub
		return this.periodRepo.findAll();
	}

	@Override
	public Iterable<Period> findByActiveTrue(Byte b) {
		// TODO Auto-generated method stub
		return this.periodRepo.findFirst3ByActiveOrderByStartDate(b);
	}

	@Override
	public Period savePeriod(Period period) {
		// TODO Auto-generated method stub
		return this.periodRepo.save(period);
	}

	@Override
	public void deletePeriodById(Integer id) {
		// TODO Auto-generated method stub
		this.periodRepo.deleteById(id);
	}

	@Override
	public Iterable<Period> findAllByActive(Byte b) {
		// TODO Auto-generated method stub
		return this.periodRepo.findByActive(b);
	}

	@Override
	public Optional<Period> findById(Integer id) {
		// TODO Auto-generated method stub
		return this.periodRepo.findById(id);
	}

}
