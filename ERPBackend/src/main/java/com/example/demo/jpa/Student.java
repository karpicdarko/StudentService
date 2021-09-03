package com.example.demo.jpa;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(uniqueConstraints = {
		@UniqueConstraint(columnNames = "index_number"),
		@UniqueConstraint(columnNames = "email")
})
public class Student implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String address;

	@Temporal(TemporalType.DATE)
	private Date birth_date;
	
	@Temporal(TemporalType.DATE)
	private Date enroll_date;
	
	private String cellphone;

	private String city;

	private String email;

	private String gender;

	private String high_school;
	
	@Column(name = "index_number")
	private String indexNumber;

	private String landline;

	private String name;

	private String parents_name;

	private String password;

	private String surname;
	
	@ManyToOne
	private Syllabus syllabus;
	
	@JsonIgnore
	@OneToMany(mappedBy = "student", cascade = CascadeType.REMOVE)
	private List<FinancialCard> financialCards;
	
	@JsonIgnore
	@OneToMany(mappedBy = "student", cascade = CascadeType.REMOVE)
	private List<CourseOfStudy> coursesOfStudy;
	
	@JsonIgnore
	@OneToMany(mappedBy = "student", cascade = CascadeType.REMOVE)
	private List<Registration> registrations;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(	name = "student_roles", 
				joinColumns = @JoinColumn(name = "student_id"), 
				inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> role = new HashSet<>();
	
	
	public Student() {
		
	}
	

	public Student(String address, Date birth_date, String cellphone, String city, String email, String gender,
			String high_school, String index_number, String landline, String name, String parents_name, String password,
			String surname, Syllabus syllabus, Date enroll_date) {
		super();
		this.address = address;
		this.birth_date = birth_date;
		this.cellphone = cellphone;
		this.city = city;
		this.email = email;
		this.gender = gender;
		this.high_school = high_school;
		this.indexNumber = index_number;
		this.landline = landline;
		this.name = name;
		this.parents_name = parents_name;
		this.password = password;
		this.surname = surname;
		this.syllabus = syllabus;
		this.enroll_date = enroll_date;
	}




	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getBirth_date() {
		return birth_date;
	}

	public void setBirth_date(Date birth_date) {
		this.birth_date = birth_date;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	
	public String getEmail() {
		return email;
	}
	

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getHigh_school() {
		return high_school;
	}

	public void setHigh_school(String high_school) {
		this.high_school = high_school;
	}

	public String getIndex_number() {
		return indexNumber;
	}

	public void setIndex_number(String index_number) {
		this.indexNumber = index_number;
	}

	public String getLandline() {
		return landline;
	}

	public void setLandline(String landline) {
		this.landline = landline;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParents_name() {
		return parents_name;
	}

	public void setParents_name(String parents_name) {
		this.parents_name = parents_name;
	}
	
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Syllabus getSyllabus() {
		return syllabus;
	}

	public void setSyllabus(Syllabus syllabus) {
		this.syllabus = syllabus;
	}

	public List<FinancialCard> getFinancialCards() {
		return financialCards;
	}

	public void setFinancialCards(List<FinancialCard> financialCards) {
		this.financialCards = financialCards;
	}



	public List<CourseOfStudy> getCoursesOfStudy() {
		return coursesOfStudy;
	}



	public void setCoursesOfStudy(List<CourseOfStudy> coursesOfStudy) {
		this.coursesOfStudy = coursesOfStudy;
	}



	public List<Registration> getRegistrations() {
		return registrations;
	}



	public void setRegistrations(List<Registration> registrations) {
		this.registrations = registrations;
	}


	public Set<Role> getRole() {
		return role;
	}


	public void setRole(Set<Role> role) {
		this.role = role;
	}


	public Date getEnroll_date() {
		return enroll_date;
	}


	public void setEnroll_date(Date enroll_date) {
		this.enroll_date = enroll_date;
	}


	public String getIndexNumber() {
		return indexNumber;
	}


	public void setIndexNumber(String indexNumber) {
		this.indexNumber = indexNumber;
	}


	

}
