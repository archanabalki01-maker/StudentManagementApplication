package com.archana.StudentManagement.Controller;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.archana.StudentManagement.Entity.Student;
import com.archana.StudentManagement.Service.StudentExportService;
import com.archana.StudentManagement.Service.StudentService;

import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/students") // This handles the UI routes
public class WebController {

    @Autowired
    private StudentService studentService;

    // Main Dashboard
    @GetMapping("/")
    public String viewDashboard(Model model) {
        List<Student> listStudents = studentService.findAll();
        model.addAttribute("listStudents", listStudents);
        return "index"; // returns index.html
    }

    // Handle Filter Form (No JS required)
    @GetMapping("/filter")
    public String filterStudents(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String standard,
            @RequestParam(required = false) String gender,
            @RequestParam(required = false) Boolean active,
            Model model) {
        
        List<Student> filteredResults = studentService.getFilteredStudents(name, standard, gender, active);
        model.addAttribute("listStudents", filteredResults);
     // Pass the search values back to the view
        model.addAttribute("savedName", name);
        model.addAttribute("savedStandard", standard);
        model.addAttribute("savedGender", gender);
        model.addAttribute("savedActive", active);
        return "index";
    }
    
    @GetMapping("/new")
    public String showNewStudentForm(Model model) {
        // This creates the empty student object for the form to 'bind' to
        model.addAttribute("student", new Student());
        
        // This tells your navbar to highlight the 'Add Student' tab in orange
        model.addAttribute("currentPage", "add");
        
        // This MUST match your file name 'new_student.html' (without the .html)
        return "new_student"; 
    }

    @PostMapping("/saveStudent")
    public String saveStudent(@ModelAttribute("student") Student student) {
        // Your service call to save the data to the database
        studentService.saveStudent(student);
        
        // After saving, send them back to the main dashboard
        return "redirect:/students/";
    }
    
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        // 1. Get student from service
        Student student = studentService.getStudentById(id);
        
        // 2. Add student object to model (This "populates" the form)
        model.addAttribute("student", student);
        
        // 3. Set currentPage for the active nav tab
        model.addAttribute("currentPage", "update"); 
        
        // 4. Return your new update student html file
        return "update_student"; 
    }
   
    
    
    
    @Autowired
    private StudentExportService exportService;

    @GetMapping("/export/csv")
    public void exportToCSV(HttpServletResponse response) throws IOException {
        List<Student> students = studentService.findAll();
        exportService.exportToCSV(response, students);
    }
}