package com.example.demo.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.jpa.Syllabus;

public interface SyllabusRepo extends JpaRepository<Syllabus, Integer>{
	public Optional<Syllabus> findByStudentsId(Integer id);

}
