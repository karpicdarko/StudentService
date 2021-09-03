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

import com.example.demo.jpa.Classroom;
import com.example.demo.service.ClassroomService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class ClassroomController {

	@Autowired
	private ClassroomService classroomService;
	
	@GetMapping("/classroom")
	public Iterable<Classroom> getClassrooms() {
		return this.classroomService.findAll();
	}
	
	@PostMapping("/classroom")
	public Classroom insertClassroom(@RequestBody Classroom classroom) {
		return this.classroomService.saveClassroom(classroom);
	}
	
	@PutMapping("/classroom")
	public Classroom updateClassroom(@RequestBody Classroom classroom) {
		return this.classroomService.saveClassroom(classroom);
	}
	
	@DeleteMapping("/classroom/{id}")
	public void removeClassroom(@PathVariable("id") Integer id) {
		this.classroomService.removeClassroomById(id);
	}
}
