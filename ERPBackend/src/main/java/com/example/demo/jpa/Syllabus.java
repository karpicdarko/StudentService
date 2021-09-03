package com.example.demo.jpa;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Syllabus implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String mark;

	private String name;
	
	//bi-directional many-to-one association to Registrar
	@ManyToOne
	@JsonIgnore
	private Registrar registrar;
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "syllabus_department", 
			   joinColumns = {
					   @JoinColumn(name = "syllabus_id")
			   }, inverseJoinColumns = {
					   @JoinColumn(name = "department_id", referencedColumnName = "id")
			   })
	private List<Department> departments; 
	
	@JsonIgnore
	@OneToMany(mappedBy = "syllabus")
	private List<Course> courses;
	
	@OneToMany(mappedBy = "syllabus")
	private List<Student> students;
	
	public Syllabus() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMark() {
		return this.mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Registrar getRegistrar() {
		return this.registrar;
	}

	public void setRegistrar(Registrar registrar) {
		this.registrar = registrar;
	}

	public List<Department> getDepartments() {
		return departments;
	}

	public void setDepartments(List<Department> departments) {
		this.departments = departments;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

}
