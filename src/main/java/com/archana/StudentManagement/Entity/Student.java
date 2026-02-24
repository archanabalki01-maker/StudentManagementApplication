package com.archana.StudentManagement.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "students")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student{
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	
	private Long id;
    
    private String firstName;
    private String lastName;
    private String contactNumber;
    private String standard; // e.g., Grade 10, Year 1
    private String gender;
   @Column(nullable = false)
    private boolean active = true; 
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getStandard() {
		return standard;
	}
	public void setStandard(String standard) {
		this.standard = standard;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}

	public boolean isActive() { return active; }
	public void setActive(boolean active) { this.active = active; }
}
