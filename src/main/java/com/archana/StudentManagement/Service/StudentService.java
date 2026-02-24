package com.archana.StudentManagement.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.archana.StudentManagement.Entity.Student;
import com.archana.StudentManagement.Repository.StudentRepository;

@Service
public class StudentService{
	
	@Autowired
	private  StudentRepository studentRepository ;
	
	// 1. Dashboard Load
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    // 2. Add/Update logic
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }
 // THE MISSING PIECE: Actual Database Deletion
    public void deleteStudent(Long id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
        } else {
            throw new RuntimeException("Student not found with id: " + id);
        }
    }
 
    //search -> dasboard
	public List<Student> getFilteredStudents(String name, String standard, String gender, Boolean active) {
	    if (name != null && !name.isEmpty()) {
	        return studentRepository.findByFirstNameContainingIgnoreCase(name);
	    } else if (standard != null && !standard.isEmpty()) {
	        return studentRepository.findByStandard(standard);
	    } else if (gender != null && !gender.isEmpty()) {
	        return studentRepository.findByGender(gender);
	    } else if (active != null) {
	        return studentRepository.findByActive(active);
	    }
	    return studentRepository.findAll();
	}
	
	
	}
	


