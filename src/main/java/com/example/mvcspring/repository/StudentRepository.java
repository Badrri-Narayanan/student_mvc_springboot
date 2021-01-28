package com.example.mvcspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mvcspring.model.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

}
