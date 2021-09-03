package com.example.demo.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.jpa.Course;

public interface CourseRepo extends JpaRepository<Course, Integer>{
	public Iterable<Course> findBySyllabusId(Integer id);

}
