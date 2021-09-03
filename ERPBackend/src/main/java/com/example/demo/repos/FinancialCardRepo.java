package com.example.demo.repos;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.jpa.FinancialCard;

public interface FinancialCardRepo extends JpaRepository<FinancialCard, Integer>{
	
	public Optional<FinancialCard> findByStudentId(Integer id);
	
	@Transactional
	@Modifying
	@Query(value = "update financial_card set account_balance = account_balance + 500 where student_id = ?1", nativeQuery = true)
	public void insertMoneyInAccout(Integer id);
	
	
	
}
