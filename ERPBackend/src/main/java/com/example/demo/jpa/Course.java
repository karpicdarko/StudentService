package com.example.demo.jpa;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Course implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String mark;

	private String name;

	private int semester;

	private int year;
	

	@ManyToOne
	private Professor professor;
	
	@ManyToOne
	private Syllabus syllabus;
	
	@JsonIgnore
	@OneToMany(mappedBy = "course")
	private List<CoursePeriod> coursePeriods;

	public Course() {
		
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSemester() {
		return semester;
	}

	public void setSemester(int semester) {
		this.semester = semester;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public Syllabus getSyllabus() {
		return syllabus;
	}

	public void setSyllabus(Syllabus syllabus) {
		this.syllabus = syllabus;
	}

	public List<CoursePeriod> getCoursePeriods() {
		return coursePeriods;
	}

	public void setCoursePeriods(List<CoursePeriod> coursePeriods) {
		this.coursePeriods = coursePeriods;
	}

//	public List<Syllabus> getSyllabuses() {
//		return syllabuses;
//	}
//
//	public void setSyllabuses(List<Syllabus> syllabuses) {
//		this.syllabuses = syllabuses;
//	}
	
	
}
