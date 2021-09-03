package com.example.demo.service;

import java.util.Optional;

import com.example.demo.jpa.Syllabus;

public interface SyllabusService {
	
	public Iterable<Syllabus> findAll();
	public Syllabus saveSyllabus(Syllabus syllabus);
	public void deleteSyllabusById(Integer id);
	public Optional<Syllabus> findByStudentId(Integer id);
}
