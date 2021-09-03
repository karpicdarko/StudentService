package com.example.demo.service.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.jpa.Classroom;
import com.example.demo.repos.ClassroomRepo;
import com.example.demo.service.ClassroomService;

@Service
public class ClassroomServiceImpl implements ClassroomService{

	@Autowired
	private ClassroomRepo classroomRepo;
	
	@Override
	public Iterable<Classroom> findAll() {
		// TODO Auto-generated method stub
		return this.classroomRepo.findAll();
	}

	@Override
	public Classroom saveClassroom(Classroom classroom) {
		// TODO Auto-generated method stub
		return this.classroomRepo.save(classroom);
	}

	@Override
	public void removeClassroomById(Integer id) {
		// TODO Auto-generated method stub
		this.classroomRepo.deleteById(id);
	}

}
