package com.archana.StudentManagement.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.archana.StudentManagement.Entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{
	// Supports "Search Name" box
    List<Student> findByFirstNameContainingIgnoreCase(String name);
    
    // Supports "Class" dropdown
    List<Student> findByStandard(String standard);
    
    // Supports "Gender" dropdown
    List<Student> findByGender(String gender);
    
    // Supports "Status" dropdown
    List<Student> findByActive(boolean active);
}