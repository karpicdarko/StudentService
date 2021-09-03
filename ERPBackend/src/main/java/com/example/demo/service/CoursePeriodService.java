package com.example.demo.service;

import com.example.demo.jpa.CoursePeriod;

public interface CoursePeriodService {
	
	public Iterable<CoursePeriod> findAll();
	public CoursePeriod saveCP(CoursePeriod cp);
	public void deleteCPById(Integer id);
	public Iterable<CoursePeriod> findBySylId(Integer id);
	public Iterable<CoursePeriod> findBySylIdAndPeriod(Integer id,  String name);
	public Iterable<CoursePeriod> findNotRegistered(Integer studentId, Integer syllabusId, String periodName, Integer courseYear);
}
