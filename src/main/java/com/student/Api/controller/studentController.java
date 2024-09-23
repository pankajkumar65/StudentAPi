package com.student.Api.controller;
import java.util.*;

import java.util.List;

import com.student.Api.repository.StudentSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import com.student.Api.repository.StudentRepository;
import com.student.Api.entity.Student;
import org.springframework.web.server.ResponseStatusException;

@CrossOrigin
@Controller
@RequestMapping
public class studentController {

	@Autowired
	StudentRepository repo;

	//get all the students
	//Localhost:8080/students
	@GetMapping("/students")
	public ResponseEntity<List<Student>> getAllStudents() {
		try {
			List<Student> students = repo.findAll();
			return new ResponseEntity<>(students, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	//Localhost:8080/students/1
	@GetMapping("/student/{id}")
	public ResponseEntity<Student> getStudent(@PathVariable int id) {
		return repo.findById(id)
				.map(student -> ResponseEntity.ok().body(student))
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found"));
	}

	@PostMapping("/student/add")
	@ResponseStatus(code = HttpStatus.CREATED)
	public void createStudent(@RequestBody Student student) {
		repo.save(student);
	}

	@PutMapping("/student/update/{id}")
	public ResponseEntity<Student> updateStudent(@PathVariable int id, @RequestBody Student updatedStudent) {
		return repo.findById(id)
				.map(student -> {
					student.setFirst_Name(updatedStudent.getFirst_Name());
					student.setLast_Name(updatedStudent.getLast_Name());
					student.setAge(updatedStudent.getAge());
					student.setGender(updatedStudent.getGender());
					student.setAddress(updatedStudent.getAddress());
					student.setMobile_no(updatedStudent.getMobile_no());
					repo.save(student);
					return ResponseEntity.ok().body(student);
				})
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found"));
	}

	@DeleteMapping("student/delete/{id}")
	public ResponseEntity<String> removeStudent(@PathVariable int id) {
		return repo.findById(id)
				.map(student -> {
					repo.delete(student);
					return ResponseEntity.ok("Student deleted successfully");
				})
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found"));
	}
	@GetMapping("/students/search")
	public ResponseEntity<List<Student>> searchStudents(
			@RequestParam(required = false) String firstName,
			@RequestParam(required = false) String lastName,
			@RequestParam(required = false) String gender,
			@RequestParam(required = false) Integer minAge,
			@RequestParam(required = false) Integer maxAge) {

		Specification<Student> spec = Specification.where(StudentSpecifications.hasFirstName(firstName))
				.and(StudentSpecifications.hasLastName(lastName))
				.and(StudentSpecifications.hasGender(gender))
				.and(StudentSpecifications.hasAgeBetween(minAge, maxAge));

		List<Student> students = repo.findAll(spec);
		return new ResponseEntity<>(students, HttpStatus.OK);
	}
	@GetMapping("/studentsList")
	public String studentListView() {
		return "studentList";  // This maps to the students.html template
	}


}