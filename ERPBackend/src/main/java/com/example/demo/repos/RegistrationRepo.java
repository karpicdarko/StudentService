package com.example.demo.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.jpa.Registration;

public interface RegistrationRepo extends JpaRepository<Registration, Integer>{
	
	@Query(value = "select count(r.id) from Registration r where student_id = ?1")
	public Optional<Integer> findPriceByStudent(Integer id);
	
	@Query(value = "select * from registration r inner join course c on c.id = r.course_id "
			+ "inner join student s on s.id = r.student_id "
			+ "inner join period p on r.period_id = p.id "
			+ "where s.id = :id and c.year = :year and p.name = :period", nativeQuery = true)
	public Iterable<Registration> findReg(@Param("id") Integer id, @Param("year") Integer year, @Param("period") String period);


}
