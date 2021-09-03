package com.example.demo.service;

import com.example.demo.jpa.Department;

public interface DepartmentService {
	
	public Iterable<Department> findAll();
	public Department saveDepartment(Department department);
	public void deleteDepartmentById(Integer id);
}
