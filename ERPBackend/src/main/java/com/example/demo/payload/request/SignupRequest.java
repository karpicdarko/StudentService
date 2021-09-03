package com.example.demo.payload.request;

import java.util.Date;
import java.util.Set;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.example.demo.jpa.Syllabus;

public class SignupRequest {
		@NotBlank
	    @Size(min = 3, max = 20)
	    private String username;
	 
	    @NotBlank
	    @Size(max = 50)
	    @Email
	    private String email;
	    
	    private Set<String> role;
	    
	    @NotBlank
	    @Size(min = 6, max = 40)
	    private String password;
	    
	    @NotBlank
	    private String address;
	    
	    @NotBlank
		@Temporal(TemporalType.DATE)
		private Date birth_date;
	    
	    @NotBlank
		@Temporal(TemporalType.DATE)
		private Date enroll_date;

	    @NotBlank
		private String cellphone;

	    @NotBlank
		private String city;
	    
	    @NotBlank
	    private String gender;

	    @NotBlank
		private String high_school;
	    
	    @NotBlank
	    private String landline;

	    @NotBlank
		private String name;

	    @NotBlank
		private String parents_name;
	    
	    @NotBlank
	    private String surname;
	    
	    @NotBlank
	    private Syllabus syllabus;


		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public Set<String> getRole() {
			return role;
		}

		public void setRole(Set<String> role) {
			this.role = role;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
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

		public Date getEnroll_date() {
			return enroll_date;
		}

		public void setEnroll_date(Date enroll_date) {
			this.enroll_date = enroll_date;
		}
	    
	    
}
