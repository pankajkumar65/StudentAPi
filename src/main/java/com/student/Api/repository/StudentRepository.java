package com.student.Api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.student.Api.entity.Student;

import java.util.List;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface StudentRepository extends JpaRepository<Student, Integer>, JpaSpecificationExecutor<Student> {
}
