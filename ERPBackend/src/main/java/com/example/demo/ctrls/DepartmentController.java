package com.example.demo.ctrls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.jpa.Department;
import com.example.demo.service.DepartmentService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class DepartmentController {

	@Autowired
	private DepartmentService departmentService;
	
	@GetMapping("/department")
	public Iterable<Department> getDepartments() {
		return this.departmentService.findAll();
	}
	
	@PostMapping("/department")
	public Department insertDepartment(@RequestBody Department department) {
		return this.departmentService.saveDepartment(department);
	}
	
	@PutMapping("/department")
	public Department updateDepartment(@RequestBody Department department) {
		return this.departmentService.saveDepartment(department);
	}
	
	@DeleteMapping("/department/{id}")
	public void removeDepartment(@PathVariable("id") Integer id) {
		this.departmentService.deleteDepartmentById(id);
	}
}
