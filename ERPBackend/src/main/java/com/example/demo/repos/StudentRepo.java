package com.example.demo.repos;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.jpa.Student;

public interface StudentRepo extends JpaRepository<Student, Integer>{
	public Boolean existsByEmail(String email);
	public Optional<Student> findByEmail(String email);
	public Optional<Student> findByIndexNumber(String index);
	public Boolean existsByIndexNumber(String index);
	
	@Query(value ="select max(id) from Student")
	public Integer findMaxId();
	

}
