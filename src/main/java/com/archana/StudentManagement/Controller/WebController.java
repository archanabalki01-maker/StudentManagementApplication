package com.archana.StudentManagement.Controller;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
        return "index";
    }
    
    @Autowired
    private StudentExportService exportService;

    @GetMapping("/export/csv")
    public void exportToCSV(HttpServletResponse response) throws IOException {
        List<Student> students = studentService.findAll();
        exportService.exportToCSV(response, students);
    }
}