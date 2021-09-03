package com.example.demo.service;

import java.util.Optional;

import com.example.demo.jpa.Period;

public interface PeriodService {
	
	public Iterable<Period> findAll();
	public Iterable<Period> findByActiveTrue(Byte b);
	public Period savePeriod(Period period);
	public void deletePeriodById(Integer id);
	public Iterable<Period> findAllByActive(Byte b);
	public Optional<Period> findById(Integer id);
}
