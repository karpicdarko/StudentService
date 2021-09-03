package com.example.demo.service;

import java.util.Optional;

import com.example.demo.jpa.Course;

public interface CourseService {
	
	public Iterable<Course> findAll();
	public Course saveCourse(Course course);
	public void deleteCourseById(Integer id);
	public Optional<Course> findById(Integer id);
	public Iterable<Course> findBySyllabusId(Integer id);
}
