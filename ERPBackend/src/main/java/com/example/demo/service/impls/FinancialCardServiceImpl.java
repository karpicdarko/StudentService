package com.example.demo.service.impls;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.jpa.FinancialCard;
import com.example.demo.repos.FinancialCardRepo;
import com.example.demo.service.FinancialCardService;

@Service
public class FinancialCardServiceImpl implements FinancialCardService{

	@Autowired
	private FinancialCardRepo financialCardRepo;
	
	@Override
	public Iterable<FinancialCard> findAll() {
		// TODO Auto-generated method stub
		return this.financialCardRepo.findAll();
	}

	@Override
	public Optional<FinancialCard> findByStudentId(Integer id) {
		// TODO Auto-generated method stub
		return this.financialCardRepo.findByStudentId(id);
	}

	@Override
	public FinancialCard saveFC(FinancialCard fc) {
		// TODO Auto-generated method stub
		return this.financialCardRepo.save(fc);
	}

	@Override
	public void deleteFCById(Integer id) {
		// TODO Auto-generated method stub
		this.financialCardRepo.deleteById(id);
	}

	@Override
	public void insertMoneyInAccout(Integer id) {
		// TODO Auto-generated method stub
		this.financialCardRepo.insertMoneyInAccout(id);
	}

}
