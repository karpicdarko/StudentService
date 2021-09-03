package com.example.demo.service.impls;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.jpa.Student;
import com.example.demo.repos.StudentRepo;
import com.example.demo.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService{

	@Autowired
	private StudentRepo studentRepo;
	
	@Override
	public Iterable<Student> findAll() {
		// TODO Auto-generated method stub
		return this.studentRepo.findAll();
	}

	@Override
	public Optional<Student> findById(Integer id) {
		// TODO Auto-generated method stub
		return this.studentRepo.findById(id);
	}

	@Override
	public Student saveStudent(Student student) {
		// TODO Auto-generated method stub
		return this.studentRepo.save(student);
	}

	@Override
	public void deleteStudentById(Integer id) {
		// TODO Auto-generated method stub
		this.studentRepo.deleteById(id);
	}

	@Override
	public Boolean existsByEmail(String email) {
		// TODO Auto-generated method stub
		return this.studentRepo.existsByEmail(email);
	}

	@Override
	public Optional<Student> findByEmail(String email) {
		// TODO Auto-generated method stub
		return this.studentRepo.findByEmail(email);
	}

	@Override
	public Optional<Student> findByIndexNumber(String index) {
		// TODO Auto-generated method stub
		return this.studentRepo.findByIndexNumber(index);
	}

	@Override
	public Boolean existsByIndexNumber(String index) {
		// TODO Auto-generated method stub
		return this.studentRepo.existsByIndexNumber(index);
	}

	@Override
	public Integer findMaxId() {
		// TODO Auto-generated method stub
		return this.studentRepo.findMaxId();
	}


}
