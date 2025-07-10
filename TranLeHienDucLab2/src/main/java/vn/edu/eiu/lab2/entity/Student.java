package vn.edu.eiu.lab2.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name= "Students") // neu muon ten bang khac ten class
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    @Column(name ="Id",columnDefinition = "CHAR(10)")
    private String id;

    @Column(name = "Name",columnDefinition = "NVARCHAR(50)",nullable = false)
    private String name;

    @Column(name = "Year Of Birth",nullable = false)
    private int yob;

    @Column(name = "Gpa",nullable = false)
    private double gpa;

}
