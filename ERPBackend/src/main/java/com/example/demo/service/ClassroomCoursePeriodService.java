package com.example.demo.service;

import com.example.demo.jpa.ClassroomCoursePeriod;

public interface ClassroomCoursePeriodService {
	
	public Iterable<ClassroomCoursePeriod> findAll();
	public ClassroomCoursePeriod saveCCP(ClassroomCoursePeriod ccp);
	public void deleteCCPById(Integer id);
}