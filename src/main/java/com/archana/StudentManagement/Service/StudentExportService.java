package com.archana.StudentManagement.Service;

import com.archana.StudentManagement.Entity.Student;
import com.opencsv.CSVWriter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.List;

@Service
public class StudentExportService {

    public void exportToCSV(HttpServletResponse response, List<Student> students) throws IOException {
        // Set the file name and content type
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=students_list.csv");

        CSVWriter writer = new CSVWriter(response.getWriter());

        // 1. Add Header Row
        String[] header = {"ID", "First Name", "Last Name", "Class", "Gender", "Contact", "Status"};
        writer.writeNext(header);

        // 2. Add Data Rows
        for (Student student : students) {
            String[] data = {
                String.valueOf(student.getId()),
                student.getFirstName(),
                student.getLastName(),
                student.getStandard(),
                student.getGender(),
                student.getContactNumber(),
                student.isActive() ? "Active" : "Inactive"
            };
            writer.writeNext(data);
        }

        writer.close();
    }
}