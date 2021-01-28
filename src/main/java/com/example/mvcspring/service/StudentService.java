package com.example.mvcspring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.mvcspring.model.Student;
import com.example.mvcspring.repository.StudentRepository;

@Component
public class StudentService {

	@Autowired
	private StudentRepository studentRepository;
	
	public List<Student> getStudents() {
		return studentRepository.findAll();
	}
	
	public Student getStudent(int id) {
		Optional<Student> student = studentRepository.findById(id);
		Student studentResult = null;
		
		if(student.isPresent()) {
			studentResult = student.get();
		}
		
		return studentResult;
	}
	
	public void saveStudent(Student student) {
		studentRepository.save(student);
	}
	
	public void deleteStudent(int id) {
		studentRepository.deleteById(id);
	}
	
	public Boolean isStudentPresent(int id) {
		return studentRepository.existsById(id);
	}
	
}
