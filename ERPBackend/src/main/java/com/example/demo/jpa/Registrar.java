package com.example.demo.jpa;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Registrar implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String address;

	@Temporal(TemporalType.DATE)
	private Date birth_date;

	private String cellphone;

	private String city;

	private String email;

	private String gender;

	private String landline;

	private String name;

	private String password;

	private String surname;
	
	//bi-directional many-to-one association to Syllabus
	@JsonIgnore
	@OneToMany(mappedBy="registrar")
	private List<Syllabus> syllabuses;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(	name = "registrar_roles", 
				joinColumns = @JoinColumn(name = "registrar_id"), 
				inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> role = new HashSet<>();
	
	public Registrar() {
	}
	
	public Registrar(String address, Date birth_date, String cellphone, String city, String email, String gender,
			  String landline, String name,  String password,
			String surname) {
		super();
		this.address = address;
		this.birth_date = birth_date;
		this.cellphone = cellphone;
		this.city = city;
		this.email = email;
		this.gender = gender;
		this.landline = landline;
		this.name = name;
		this.password = password;
		this.surname = surname;
	}
	
	
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getBirth_date() {
		return this.birth_date;
	}

	public void setBirth_date(Date birth_date) {
		this.birth_date = birth_date;
	}

	public String getCellphone() {
		return this.cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public List<Syllabus> getSyllabuses() {
		return this.syllabuses;
	}

	public void setSyllabuses(List<Syllabus> syllabuses) {
		this.syllabuses = syllabuses;
	}

	public Syllabus addSyllabus(Syllabus syllabus) {
		getSyllabuses().add(syllabus);
		syllabus.setRegistrar(this);

		return syllabus;
	}

	public Syllabus removeSyllabus(Syllabus syllabus) {
		getSyllabuses().remove(syllabus);
		syllabus.setRegistrar(null);

		return syllabus;
	}
	
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getLandline() {
		return this.landline;
	}

	public void setLandline(String landline) {
		this.landline = landline;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Role> getRole() {
		return role;
	}

	public void setRole(Set<Role> role) {
		this.role = role;
	}
	
}
