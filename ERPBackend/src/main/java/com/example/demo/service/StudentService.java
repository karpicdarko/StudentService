package com.example.demo.service;

import java.util.Optional;

import com.example.demo.jpa.Student;

public interface StudentService {
	
	public Iterable<Student> findAll();
	public Optional<Student> findById(Integer id);
	public Student saveStudent(Student student);
	public void deleteStudentById(Integer id);
	public Boolean existsByEmail(String email);
	public Optional<Student> findByEmail(String email);
	public Optional<Student> findByIndexNumber(String index);
	public Boolean existsByIndexNumber(String index);
	public Integer findMaxId();
}
