package vn.edu.eiu.practice;

import vn.edu.eiu.practice.dao.MajorDAO;
import vn.edu.eiu.practice.dao.SchoolDAO;
import vn.edu.eiu.practice.dao.StudentDAO;
import vn.edu.eiu.practice.entity.Major;
import vn.edu.eiu.practice.entity.School;
import vn.edu.eiu.practice.entity.Student;

import java.time.LocalDate;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        SchoolDAO schoolDAO = new SchoolDAO();
        MajorDAO majorDAO = new MajorDAO();
        StudentDAO studentDAO = new StudentDAO();

        try {
            System.out.println("=== Testing CRUD Operations ===\n");

            // Test School CRUD
            testSchoolCRUD(schoolDAO);

            // Test Major CRUD
            testMajorCRUD(majorDAO, schoolDAO);

            // Test Student CRUD
            testStudentCRUD(studentDAO, majorDAO, schoolDAO);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close EntityManagerFactory
            SchoolDAO.closeEntityManagerFactory();
            MajorDAO.closeEntityManagerFactory();
            StudentDAO.closeEntityManagerFactory();
        }
    }

    private static void testSchoolCRUD(SchoolDAO schoolDAO) {
        System.out.println("--- Testing School CRUD ---");

        // Create Schools
        School school1 = new School("CS", "Computer Science", "Building A");
        School school2 = new School("ENG", "Engineering", "Building B");

        schoolDAO.save(school1);
        schoolDAO.save(school2);
        System.out.println("✓ Schools created successfully");

        // Read Schools
        List<School> schools = schoolDAO.findAll();
        System.out.println("✓ Found " + schools.size() + " schools:");
        schools.forEach(s -> System.out.println("  - " + s.getSchoolName() + " (" + s.getSchoolId() + ")"));

        // Update School
        school1.setLocation("Building A - Floor 3");
        schoolDAO.update(school1);
        System.out.println("✓ School updated successfully");

        System.out.println();
    }

    private static void testMajorCRUD(MajorDAO majorDAO, SchoolDAO schoolDAO) {
        System.out.println("--- Testing Major CRUD ---");

        // Get existing schools
        School csSchool = schoolDAO.findById("CS");
        School engSchool = schoolDAO.findById("ENG");

        // Create Majors
        Major major1 = new Major("SOFT", "Software Engineering", csSchool);
        Major major2 = new Major("CYBR", "Cybersecurity", csSchool);
        Major major3 = new Major("MECH", "Mechanical Engineering", engSchool);

        majorDAO.save(major1);
        majorDAO.save(major2);
        majorDAO.save(major3);
        System.out.println("✓ Majors created successfully");

        // Read Majors
        List<Major> majors = majorDAO.findAll();
        System.out.println("✓ Found " + majors.size() + " majors:");
        majors.forEach(m -> System.out.println("  - " + m.getMajorName() + " (" + m.getMajorId() + ")"));

        // Find majors by school
        List<Major> csMajors = majorDAO.findBySchoolId("CS");
        System.out.println("✓ Found " + csMajors.size() + " majors in CS school");

        // Update Major
        major1.setMajorName("Software Development");
        majorDAO.update(major1);
        System.out.println("✓ Major updated successfully");

        System.out.println();
    }

    private static void testStudentCRUD(StudentDAO studentDAO, MajorDAO majorDAO, SchoolDAO schoolDAO) {
        System.out.println("--- Testing Student CRUD ---");

        // Get existing entities
        School csSchool = schoolDAO.findById("CS");
        Major softMajor = majorDAO.findById("SOFT");
        Major cybrMajor = majorDAO.findById("CYBR");

        // Create Students
        Student student1 = new Student(
            "John Doe",
            Student.Gender.Male,
            LocalDate.of(2000, 5, 15),
            3.75,
            softMajor,
            csSchool,
            2023
        );

        Student student2 = new Student(
            "Jane Smith",
            Student.Gender.Female,
            LocalDate.of(1999, 8, 22),
            3.90,
            cybrMajor,
            csSchool,
            2022
        );

        Student student3 = new Student(
            "Alex Johnson",
            Student.Gender.Other,
            LocalDate.of(2001, 3, 10),
            3.60,
            softMajor,
            csSchool,
            2024
        );

        studentDAO.save(student1);
        studentDAO.save(student2);
        studentDAO.save(student3);
        System.out.println("✓ Students created successfully");

        // Read Students
        List<Student> students = studentDAO.findAll();
        System.out.println("✓ Found " + students.size() + " students:");
        students.forEach(s -> System.out.println("  - " + s.getFullName() + " (ID: " + s.getStudentId() +
                                                ", GPA: " + s.getGpa() + ")"));

        // Find students by major
        List<Student> softwareStudents = studentDAO.findByMajorId("SOFT");
        System.out.println("✓ Found " + softwareStudents.size() + " students in Software Engineering");

        // Find students by school
        List<Student> csStudents = studentDAO.findBySchoolId("CS");
        System.out.println("✓ Found " + csStudents.size() + " students in CS school");

        // Update Student
        if (!students.isEmpty()) {
            Student firstStudent = students.get(0);
            firstStudent.setGpa(3.85);
            studentDAO.update(firstStudent);
            System.out.println("✓ Student GPA updated successfully");
        }

        // Find by ID
        if (!students.isEmpty()) {
            Long studentId = students.get(0).getStudentId();
            Student foundStudent = studentDAO.findById(studentId);
            System.out.println("✓ Found student by ID: " + foundStudent.getFullName());
        }

        System.out.println();
        System.out.println("=== All CRUD operations completed successfully! ===");
    }
}
