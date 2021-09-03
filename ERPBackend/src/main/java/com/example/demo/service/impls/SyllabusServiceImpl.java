package com.example.demo.service.impls;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.jpa.Syllabus;
import com.example.demo.repos.SyllabusRepo;
import com.example.demo.service.SyllabusService;

@Service
public class SyllabusServiceImpl implements SyllabusService{
	
	@Autowired
	private SyllabusRepo syllabusRepo;
	
	@Override
	public Iterable<Syllabus> findAll() {
		// TODO Auto-generated method stub
		return this.syllabusRepo.findAll();
	}

	@Override
	public Syllabus saveSyllabus(Syllabus syllabus) {
		// TODO Auto-generated method stub
		return this.syllabusRepo.save(syllabus);
	}

	@Override
	public void deleteSyllabusById(Integer id) {
		// TODO Auto-generated method stub
		this.syllabusRepo.deleteById(id);
	}

	@Override
	public Optional<Syllabus> findByStudentId(Integer id) {
		// TODO Auto-generated method stub
		return this.syllabusRepo.findByStudentsId(id);
	}

}
