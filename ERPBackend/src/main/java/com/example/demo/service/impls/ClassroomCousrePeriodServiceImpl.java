package com.example.demo.service.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.jpa.ClassroomCoursePeriod;
import com.example.demo.repos.ClassroomCoursePeriodRepo;
import com.example.demo.service.ClassroomCoursePeriodService;

@Service
public class ClassroomCousrePeriodServiceImpl implements ClassroomCoursePeriodService{

	@Autowired
	private  ClassroomCoursePeriodRepo classroomCoursePeriodRepo;
	
	@Override
	public Iterable<ClassroomCoursePeriod> findAll() {
		// TODO Auto-generated method stub
		return this.classroomCoursePeriodRepo.findAll();
	}

	@Override
	public ClassroomCoursePeriod saveCCP(ClassroomCoursePeriod ccp) {
		// TODO Auto-generated method stub
		return this.classroomCoursePeriodRepo.save(ccp);
	}

	@Override
	public void deleteCCPById(Integer id) {
		// TODO Auto-generated method stub
		this.classroomCoursePeriodRepo.deleteById(id);
	}

}
