package com.example.demo.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.jpa.Classroom;

public interface ClassroomRepo extends JpaRepository<Classroom, Integer>{

}
