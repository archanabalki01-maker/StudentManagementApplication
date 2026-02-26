package com.archana.StudentManagement.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.archana.StudentManagement.Entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>, JpaSpecificationExecutor<Student>{
	// Supports "Search Name" box
	List<Student> findByFirstNameContainingIgnoreCase(String name);
    List<Student> findByStandard(String standard);
    List<Student> findByGender(String gender);
    List<Student> findByActive(boolean active);
}