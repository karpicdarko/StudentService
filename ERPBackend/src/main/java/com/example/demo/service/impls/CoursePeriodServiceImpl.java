package com.example.demo.service.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.jpa.CoursePeriod;
import com.example.demo.repos.CoursePeriodRepo;
import com.example.demo.service.CoursePeriodService;


@Service
public class CoursePeriodServiceImpl implements CoursePeriodService{

	@Autowired
	private CoursePeriodRepo coursePeriodRepo;
	
	@Override
	public Iterable<CoursePeriod> findAll() {
		// TODO Auto-generated method stub
		return this.coursePeriodRepo.findAll();
	}

	@Override
	public CoursePeriod saveCP(CoursePeriod cp) {
		// TODO Auto-generated method stub
		return this.coursePeriodRepo.save(cp);
	}

	@Override
	public void deleteCPById(Integer id) {
		// TODO Auto-generated method stub
		this.coursePeriodRepo.deleteById(id);
	}

	@Override
	public Iterable<CoursePeriod> findBySylId(Integer id) {
		// TODO Auto-generated method stub
		return this.coursePeriodRepo.findByCourseSyllabusId(id);
	}

	@Override
	public Iterable<CoursePeriod> findBySylIdAndPeriod(Integer id, String name) {
		// TODO Auto-generated method stub
		return this.coursePeriodRepo.findByCourseSyllabusIdAndPeriodName(id, name);
	}

	@Override
	public Iterable<CoursePeriod> findNotRegistered(Integer studentId, Integer syllabusId, String periodName,
			Integer courseYear) {
		// TODO Auto-generated method stub
		return this.coursePeriodRepo.findNotRegistered(studentId, syllabusId, periodName, courseYear);
	}

	

}
