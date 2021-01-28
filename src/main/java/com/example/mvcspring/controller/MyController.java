package com.example.mvcspring.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.mvcspring.model.Student;
import com.example.mvcspring.service.StudentService;

@Controller
public class MyController {
	static Logger log = LoggerFactory.getLogger("MyController");

	@Autowired
	private StudentService studentService;
	
	@GetMapping("/")
	public String getHomePage() {
		//log.info("get method index");
		return "index";
	}
	
	@GetMapping("/student")
	public String getStudentPage(Model model) {
		//log.info("get method all students");
		List<Student> studentList = studentService.getStudents();
		model.addAttribute("students", studentList);
		return "student_records";
	}
	
	@GetMapping("/edit_student/{Id}")
	public String getStudentById(Model model, @PathVariable int Id) {
		//log.info("get method single student");
		Student student = studentService.getStudent(Id);
		model.addAttribute("student",student);
		return "student_editor";
	}
	
	@PostMapping("/save_student")
	public String saveStudent(Model model, @ModelAttribute("student") Student student) {
		
		//log.info(student.toString());
		studentService.saveStudent(student);
		return "redirect:/student";
	}
	
	@GetMapping("/add_student")
	public String getStudentById(Model model, @ModelAttribute("student") Student student) {
		//log.info("get method add student");
		return "add_student_to_db";
	}
	
	@GetMapping("/remove_student/{Id}")
	public String deleteStudentById(Model model, @PathVariable int Id) {
		//log.info("get method single student");
		
		if(!studentService.isStudentPresent(Id)) {
			return "error";
		}
		
		studentService.deleteStudent(Id);
		return "redirect:/student";
	}
}
