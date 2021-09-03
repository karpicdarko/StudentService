package com.example.demo.service.impls;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.jpa.Course;
import com.example.demo.repos.CourseRepo;
import com.example.demo.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService{

	@Autowired
	private CourseRepo courseRepo;
	
	@Override
	public Iterable<Course> findAll() {
		// TODO Auto-generated method stub
		return this.courseRepo.findAll();
	}

	@Override
	public Course saveCourse(Course course) {
		// TODO Auto-generated method stub
		return this.courseRepo.save(course);
	}

	@Override
	public void deleteCourseById(Integer id) {
		// TODO Auto-generated method stub
		this.courseRepo.deleteById(id);
	}

	@Override
	public Optional<Course> findById(Integer id) {
		// TODO Auto-generated method stub
		return this.courseRepo.findById(id);
	}

	@Override
	public Iterable<Course> findBySyllabusId(Integer id) {
		// TODO Auto-generated method stub
		return this.courseRepo.findBySyllabusId(id);
	}

}
