package vn.edu.eiu.temp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name="student")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;

    @Column(name = "full_name",length = 100,nullable = false)
    private String fullName;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    public enum Gender{
        Male,Female,Other
    }

    @Column(name = "dob",nullable = false)
    private LocalDate dob;

    @Column(name = "gpa")
    private Double gpa;

    @ManyToOne
    @JoinColumn(name = "school_id")
    private School school;

    @ManyToOne
    @JoinColumn(name = "major_id")
    private Major major;

    @Column(name = "enrollment_year")
    private int enrollmentYear;

    public Student(String fullName, Gender gender, LocalDate dob, Double gpa, School school, Major major, int enrollmentYear) {
        this.fullName = fullName;
        this.gender = gender;
        this.dob = dob;
        this.gpa = gpa;
        this.school = school;
        this.major = major;
        this.enrollmentYear = enrollmentYear;
    }
}
