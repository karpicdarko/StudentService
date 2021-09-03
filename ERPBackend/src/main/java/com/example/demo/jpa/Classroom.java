package com.example.demo.jpa;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Classroom implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String mark;

	private int size;
	
	/*@ManyToMany
	@JoinTable(name = "classroom_course_period", 
			   joinColumns = {
					   @JoinColumn(name = "classroom_id")
			   }, inverseJoinColumns = {
					   @JoinColumn(name = "course_id", referencedColumnName = "course_id"),
					   @JoinColumn(name = "period_id", referencedColumnName = "period_id" )
			   })
	private List<CoursePeriod> ones;*/
	
	/*@ManyToMany(mappedBy = "classrooms")
	private List<CoursePeriod> coursePeriods;*/
	
	@JsonIgnore
	@OneToMany(mappedBy = "classroom")
	private List<ClassroomCoursePeriod> coursePeriods;
	
	public Classroom() {
		
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

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public List<ClassroomCoursePeriod> getCoursePeriods() {
		return coursePeriods;
	}

	public void setCoursePeriods(List<ClassroomCoursePeriod> coursePeriods) {
		this.coursePeriods = coursePeriods;
	}

	/*public List<CoursePeriod> getOnes() {
		return ones;
	}

	public void setOnes(List<CoursePeriod> ones) {
		this.ones = ones;
	}
	*/
	

}
