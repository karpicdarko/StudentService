package com.example.demo.service;

import java.util.Optional;

import com.example.demo.jpa.CourseOfStudy;

public interface CourseOfStudyService {
	
	public Iterable<CourseOfStudy> findAll();
	public CourseOfStudy saveCOS(CourseOfStudy cos);
	public void deleteCOSById(Integer id);
//	public Iterable<CourseOfStudy> findByStudentId(Integer id);
	public Optional<CourseOfStudy> findByStudentIdLastYear(Integer id);
	public Iterable<CourseOfStudy> findByStudentId(Integer id);
}
