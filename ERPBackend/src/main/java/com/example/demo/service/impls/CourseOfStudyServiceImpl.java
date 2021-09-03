package com.example.demo.service.impls;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.jpa.CourseOfStudy;
import com.example.demo.repos.CourseOfStudyRepo;
import com.example.demo.service.CourseOfStudyService;

@Service
public class CourseOfStudyServiceImpl implements CourseOfStudyService{

	@Autowired
	private CourseOfStudyRepo courseOfStudyReopo;
	
	@Override
	public Iterable<CourseOfStudy> findAll() {
		// TODO Auto-generated method stub
		return this.courseOfStudyReopo.findAll();
	}

	@Override
	public CourseOfStudy saveCOS(CourseOfStudy cos) {
		// TODO Auto-generated method stub
		return this.courseOfStudyReopo.save(cos);
	}

	@Override
	public void deleteCOSById(Integer id) {
		// TODO Auto-generated method stub
		this.courseOfStudyReopo.deleteById(id);
	}

	@Override
	public Optional<CourseOfStudy> findByStudentIdLastYear(Integer id) {
		// TODO Auto-generated method stub
//		return this.courseOfStudyReopo.findByStudentIdOrderByYearDesc(id);
		return this.courseOfStudyReopo.findFirstByStudentIdOrderByYearDesc(id);
	}

	@Override
	public Iterable<CourseOfStudy> findByStudentId(Integer id) {
		// TODO Auto-generated method stub
		return this.courseOfStudyReopo.findByStudentId(id);
	}

}
