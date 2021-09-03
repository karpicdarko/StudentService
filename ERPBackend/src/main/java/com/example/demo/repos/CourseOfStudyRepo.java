package com.example.demo.repos;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.jpa.CourseOfStudy;

public interface CourseOfStudyRepo extends JpaRepository<CourseOfStudy, Integer>{
//	public Iterable<CourseOfStudy> findByStudentIdOrderByYearDesc(Integer id);
	public Optional<CourseOfStudy> findFirstByStudentIdOrderByYearDesc(Integer id);
	public Iterable<CourseOfStudy> findByStudentId(Integer id);

}
