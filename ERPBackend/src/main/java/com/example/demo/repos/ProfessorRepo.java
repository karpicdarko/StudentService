package com.example.demo.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.jpa.Professor;

public interface ProfessorRepo extends JpaRepository<Professor, Integer>{

}
