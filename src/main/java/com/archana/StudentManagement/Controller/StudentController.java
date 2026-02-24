package com.archana.StudentManagement.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.archana.StudentManagement.Entity.Student;
import com.archana.StudentManagement.Service.StudentService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor // Automatically injects the StudentService
public class StudentController{
	
	@Autowired
	private  StudentService studentService;
	
	  // Add new student
    @PostMapping("/add")
    public Student addStudent(@RequestBody Student student) {
        return studentService.saveStudent(student);
    }
    
	// Fetch all students
    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.findAll();
    }
    
 // Delete student
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id) {
        try {
            studentService.deleteStudent(id); // Ensure this matches the Service method name!
            return ResponseEntity.ok().body("Student with ID " + id + " deleted successfully.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while deleting.");
        }
    }

    // Search and Filter endpoint
    @GetMapping("/search")
    public ResponseEntity<List<Student>> searchStudents(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String standard,
            @RequestParam(required = false) String gender,
            @RequestParam(required = false) Boolean active) {
        
        List<Student> results = studentService.getFilteredStudents(name, standard, gender, active);
        return ResponseEntity.ok(results);
    }
    
 

 
	
}