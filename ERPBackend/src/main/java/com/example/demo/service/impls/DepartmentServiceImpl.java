package com.example.demo.service.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.jpa.Department;
import com.example.demo.repos.DepartmentRepo;
import com.example.demo.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService{

	@Autowired
	private DepartmentRepo departmentRepo;
	
	@Override
	public Iterable<Department> findAll() {
		// TODO Auto-generated method stub
		return this.departmentRepo.findAll();
	}

	@Override
	public Department saveDepartment(Department department) {
		// TODO Auto-generated method stub
		return this.departmentRepo.save(department);
	}

	@Override
	public void deleteDepartmentById(Integer id) {
		// TODO Auto-generated method stub
		this.departmentRepo.deleteById(id);
	}

}
