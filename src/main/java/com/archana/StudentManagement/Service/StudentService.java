package com.archana.StudentManagement.Service;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
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
    
    //update 
    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Student not found for id :: " + id));
    }

    /**
     * Logic for saving or updating a student.
     * Spring Data JPA handles the update automatically if the 'id' field is present.
     */
   
    //search -> dasboard
    public List<Student> getFilteredStudents(String name, String standard, String gender, Boolean active) {
        // We use a Specification here because it's the only way to make 
        // 'Name', 'Class', and 'Gender' work together in one database hit.
        return studentRepository.findAll((Specification<Student>) (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (name != null && !name.isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("firstName")), "%" + name.toLowerCase() + "%"));
            }
            if (standard != null && !standard.isEmpty()) {
                predicates.add(cb.equal(root.get("standard"), standard));
            }
            if (gender != null && !gender.isEmpty()) {
                predicates.add(cb.equal(root.get("gender"), gender));
            }
            if (active != null) {
                predicates.add(cb.equal(root.get("active"), active));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        });
    }
	
	}
	


