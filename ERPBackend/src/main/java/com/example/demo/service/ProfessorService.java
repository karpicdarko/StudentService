package com.example.demo.service;

import com.example.demo.jpa.Professor;

public interface ProfessorService {
	
	public Iterable<Professor> findAll();
	public Professor saveProfessor(Professor professor);
	public void deleteProfessorById(Integer id);
}
