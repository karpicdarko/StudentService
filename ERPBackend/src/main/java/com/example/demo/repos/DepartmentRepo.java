package com.example.demo.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.jpa.Department;

public interface DepartmentRepo extends JpaRepository<Department, Integer>{

}
