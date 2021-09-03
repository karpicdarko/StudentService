package com.example.demo.service;

import com.example.demo.jpa.Classroom;

public interface ClassroomService {
	
	public Iterable<Classroom> findAll();
	public Classroom saveClassroom(Classroom classroom);
	public void removeClassroomById(Integer id);
}
