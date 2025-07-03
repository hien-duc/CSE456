package vn.edu.eiu.Lab1;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import vn.edu.eiu.Lab1.entity.*;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    @SneakyThrows
    public static void main(String[] args) {
        Student student = new Student("1", "Le", "Tran", 1999, 3.8);
        Student student2 = new Student("2", "Do", "Tran", 2000, 3.9);
        Student student3 = new Student("3", "Duc", "Tran", 2000, 4.0);

        Course course = new Course("1", "Java", 3, 6.0);
        Course course2 = new Course("2", "C++", 3, 6.0);
        Course course3 = new Course("3", "Python", 3, 6.0);

        System.out.println("Student: " + student.toString() +  "\n");
        System.out.println("Student: " + student2.toString() +  "\n");
        System.out.println("Student: " + student3.toString() +  "\n");

        System.out.println("Course: " + course.toString() +  "\n");
        System.out.println("Course: " + course2.toString() +  "\n");
        System.out.println("Course: " + course3.toString() +  "\n");

        //Object to JSON
        ObjectMapper mapper = new ObjectMapper();
//        try {
            String jstd1 = mapper.writeValueAsString(student);
            String jstd2 = mapper.writeValueAsString(student2);
            String jstd3 = mapper.writeValueAsString(student3);
            System.out.println("Student in JSON: " + jstd1);
            System.out.println("Student in JSON: " + jstd2);
            System.out.println("Student in JSON: " + jstd3);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        //JSON to Object

        String objToJson = """
                {"id":"4","firstName":"Anh","lastName":"Tran","yearOfBirth":2000,"gpa":4.0}""";
        Student std4FromJson = mapper.readValue(objToJson, Student.class);
        System.out.println("Student in JSON to Object: " + std4FromJson.toString());
    }
}