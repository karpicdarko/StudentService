package com.example.demo.repos;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.jpa.Period;

public interface PeriodRepo extends JpaRepository<Period, Integer>{
	
//	@Query(value = "SELECT p FROM Period p WHERE p.active = 1 ORDER BY p.start_date")
	public Iterable<Period> findFirst3ByActiveOrderByStartDate(Byte b);	
	public Iterable<Period> findByActive(Byte b);	

}
