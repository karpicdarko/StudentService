package com.example.demo.jpa;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Period implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private byte active;

	@Temporal(TemporalType.DATE)
	@Column(name = "end_date")
	private Date endDate;

	private String name;

	@Temporal(TemporalType.DATE)
	@Column(name = "start_date")
	private Date startDate;
	
	@JsonIgnore
	@OneToMany(mappedBy= "period", cascade = CascadeType.REMOVE)
	private List<CoursePeriod> coursePeriods;
	
	public Period() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public byte getActive() {
		return active;
	}

	public void setActive(byte active) {
		this.active = active;
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

	public List<CoursePeriod> getCoursePeriods() {
		return coursePeriods;
	}

	public void setCoursePeriods(List<CoursePeriod> coursePeriods) {
		this.coursePeriods = coursePeriods;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	
	
}
