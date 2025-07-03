package vn.edu.eiu.Lab1.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Student {

    private String id;
    private String firstName;
    private String lastName;
    private int yearOfBirth;
    private double gpa;

}
