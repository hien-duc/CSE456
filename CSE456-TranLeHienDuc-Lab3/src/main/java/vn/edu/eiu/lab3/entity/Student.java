package vn.edu.eiu.lab3.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "student")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Long studentId;

    @Column(name = "full_name", length = 100, nullable = false)
    private String fullName;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    private Gender gender;

    @Column(name = "dob", nullable = false)
    private LocalDate dob;

    @Column(name = "gpa")
    private Double gpa;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "major_id", referencedColumnName = "major_id")
    private Major major;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "school_id", referencedColumnName = "school_id", nullable = false)
    private School school;

    @Column(name = "enrollment_year", nullable = false)
    private Integer enrollmentYear;

    public enum Gender {
        Male, Female, Other
    }

    public Student(String fullName, Gender gender, LocalDate dob, Double gpa,
            Major major, School school, Integer enrollmentYear) {
        this.fullName = fullName;
        this.gender = gender;
        this.dob = dob;
        this.gpa = gpa;
        this.major = major;
        this.school = school;
        this.enrollmentYear = enrollmentYear;
    }
}