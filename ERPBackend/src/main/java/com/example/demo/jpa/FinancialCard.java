package com.example.demo.jpa;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

@Entity
@Table(name="financial_card")
public class FinancialCard implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private BigDecimal account_balance;

	private String giro_number;
	
	private String model;

	private String reference_number;
	
	@ManyToOne
	private Student student;
	
	public FinancialCard() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigDecimal getAccount_balance() {
		return account_balance;
	}

	public void setAccount_balance(BigDecimal account_balance) {
		this.account_balance = account_balance;
	}

	public String getGiro_number() {
		return giro_number;
	}

	public void setGiro_number(String giro_number) {
		this.giro_number = giro_number;
	}

	public String getReference_number() {
		return reference_number;
	}

	public void setReference_number(String reference_number) {
		this.reference_number = reference_number;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}
	
	
}
