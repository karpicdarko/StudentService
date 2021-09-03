package com.example.demo.service.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.jpa.Professor;
import com.example.demo.repos.ProfessorRepo;
import com.example.demo.service.ProfessorService;

@Service
public class ProfessorServiceImpl implements ProfessorService{

	@Autowired
	private ProfessorRepo professorRepo;
	
	@Override
	public Iterable<Professor> findAll() {
		// TODO Auto-generated method stub
		return this.professorRepo.findAll();
	}

	@Override
	public Professor saveProfessor(Professor professor) {
		// TODO Auto-generated method stub
		return this.professorRepo.save(professor);
	}

	@Override
	public void deleteProfessorById(Integer id) {
		// TODO Auto-generated method stub
		this.professorRepo.deleteById(id);
	}

}
