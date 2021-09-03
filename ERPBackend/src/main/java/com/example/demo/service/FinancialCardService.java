package com.example.demo.service;

import java.util.Optional;

import com.example.demo.jpa.FinancialCard;

public interface FinancialCardService {
	
	public Iterable<FinancialCard> findAll();
	public Optional<FinancialCard> findByStudentId(Integer id);
	public FinancialCard saveFC(FinancialCard fc);
	public void deleteFCById(Integer id);
	public void insertMoneyInAccout(Integer id);
}
