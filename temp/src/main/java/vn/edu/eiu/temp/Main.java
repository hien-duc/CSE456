package vn.edu.eiu.temp;
import vn.edu.eiu.temp.dao.MajorDAO;
import vn.edu.eiu.temp.dao.SchoolDAO;
import vn.edu.eiu.temp.dao.StudentDAO;
import vn.edu.eiu.temp.entity.Major;
import vn.edu.eiu.temp.entity.School;
import vn.edu.eiu.temp.entity.Student;

import java.time.LocalDate;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        StudentDAO studentDAO = new StudentDAO();
        MajorDAO majorDAO = new MajorDAO();
        SchoolDAO schoolDAO = new SchoolDAO();

//        School school1 = new School("1","School 1","Location 1");
//        School school2 = new School("2","School 2","Location 2");
//        School school3 = new School("3","School 3","Location 3");
//
//        schoolDAO.save(school1);
//        schoolDAO.save(school2);
//        schoolDAO.save(school3);

        School school1 = schoolDAO.findById("1");
        School school2 = schoolDAO.findById("2");
        School school3 = schoolDAO.findById("3");
//        Major major1 = new Major("1","Major 1",school1);
//        Major major2 = new Major("2","Major 2",school2);
//        Major major3 = new Major("3","Major 3",school3);
//
//        majorDAO.save(major1);
//        majorDAO.save(major2);
//        majorDAO.save(major3);

        Major major1 = majorDAO.findById("1");
        Major major2 = majorDAO.findById("2");
        Major major3 = majorDAO.findById("3");

        Student student1 = new Student("Student 1",Student.Gender.Male, LocalDate.now(),null,school1,major1,2021);
        Student student2 = new Student("Student 2",Student.Gender.Female,LocalDate.now(),null,school2,major2,2021);
        Student student3 = new Student("Student 3",Student.Gender.Other,LocalDate.now(),null,school3,major3,2021);
        studentDAO.save(student1);
        studentDAO.save(student2);
        studentDAO.save(student3);
    }
}