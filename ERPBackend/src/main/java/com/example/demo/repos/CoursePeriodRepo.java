package com.example.demo.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.jpa.CoursePeriod;

public interface CoursePeriodRepo extends JpaRepository<CoursePeriod, Integer>{
	public Iterable<CoursePeriod> findByCourseSyllabusId(Integer id);
	public Iterable<CoursePeriod> findByCourseSyllabusIdAndPeriodName(Integer id, String name);
	
	@Query(value="Select cp from CoursePeriod cp where not exists(select r from Registration r where r.student.id = ?1 "
			+ "and cp.course.id = r.coursePeriod.course.id and cp.period.id = r.coursePeriod.period.id) and course.syllabus.id = ?2 and cp.period.name = ?3  and course.year = ?4")
	public Iterable<CoursePeriod> findNotRegistered(Integer studentId, Integer syllabusId, String periodName, Integer courseYear);
}
