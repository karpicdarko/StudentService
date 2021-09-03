package com.example.demo.jpa;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "course_period")
public class CoursePeriod implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Temporal(TemporalType.DATE)
	private Date date;
	
	private int price;

	private Time time_of;
	
	private int max_registrations;
	
	@ManyToOne
	private Course course;
	
	@ManyToOne
	private Period period;
	
	@JsonIgnore
	@OneToMany(mappedBy = "coursePeriod")
	private List<Registration> registrations;
	
	/*@ManyToMany(mappedBy = "ones")
	private List<Classroom> classrooms;*/
	
	/*@ManyToMany
	@JoinTable(name = "course_period_classroom", 
			   joinColumns = {
					   @JoinColumn(name = "course_id"),
					   @JoinColumn(name = "period_id")
			   }, inverseJoinColumns = {
					   @JoinColumn(name = "classroom_id", referencedColumnName = "id")
			   })
	private List<Classroom> classrooms;*/
	
	@JsonIgnore
	@OneToMany(mappedBy = "coursePeriod")
	private List<ClassroomCoursePeriod> coursePeriods;
	
	public CoursePeriod() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getMax_registrations() {
		return max_registrations;
	}

	public void setMax_registrations(int max_registrations) {
		this.max_registrations = max_registrations;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Period getPeriod() {
		return period;
	}

	public void setPeriod(Period period) {
		this.period = period;
	}

	public List<Registration> getRegistrations() {
		return registrations;
	}

	public void setRegistrations(List<Registration> registrations) {
		this.registrations = registrations;
	}

	public List<ClassroomCoursePeriod> getCoursePeriods() {
		return coursePeriods;
	}

	public void setCoursePeriods(List<ClassroomCoursePeriod> coursePeriods) {
		this.coursePeriods = coursePeriods;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Time getTime_of() {
		return time_of;
	}

	public void setTime_of(Time time_of) {
		this.time_of = time_of;
	}


	

	/*public List<Classroom> getClassrooms() {
		return classrooms;
	}

	public void setClassrooms(List<Classroom> classrooms) {
		this.classrooms = classrooms;
	}*/

}
