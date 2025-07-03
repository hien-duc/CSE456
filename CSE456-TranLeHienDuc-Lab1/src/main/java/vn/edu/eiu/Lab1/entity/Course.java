package vn.edu.eiu.Lab1.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Course {

    private String idCourse;
    private String name;
    private int credit;
    private double hours;


}
