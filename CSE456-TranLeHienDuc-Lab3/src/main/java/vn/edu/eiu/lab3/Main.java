package vn.edu.eiu.lab3;

import vn.edu.eiu.lab3.entity.Student;
import vn.edu.eiu.lab3.service.MajorService;
import vn.edu.eiu.lab3.service.SchoolService;
import vn.edu.eiu.lab3.service.StudentService;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== University Management System ===");

        // Initialize services
        SchoolService schoolService = new SchoolService();
        MajorService majorService = new MajorService();
        StudentService studentService = new StudentService();

        try {
            // Test School CRUD operations
            testSchoolOperations(schoolService);

            // Test Major CRUD operations
            testMajorOperations(majorService);

            // Test Student CRUD operations
            testStudentOperations(studentService);

            System.out.println("\n=== All CRUD operations completed successfully! ===");

        } catch (Exception e) {
            System.err.println("Error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void testSchoolOperations(SchoolService schoolService) {
        System.out.println("\n=== Testing School CRUD Operations ===");

        // Create schools
        schoolService.createSchool("CS", "Computer Science", "Building A");
        schoolService.createSchool("ENG", "Engineering", "Building B");
        schoolService.createSchool("BUS", "Business", "Building C");

        // Read all schools
        schoolService.displayAllSchools();

        // Update a school
        schoolService.updateSchool("CS", "Computer Science & IT", "Building A - Floor 2");

        // Read updated school
        System.out.println("\nAfter update:");
        schoolService.displayAllSchools();
    }

    private static void testMajorOperations(MajorService majorService) {
        System.out.println("\n=== Testing Major CRUD Operations ===");

        // Create majors
        majorService.createMajor("CSCI", "Computer Science", "CS");
        majorService.createMajor("SOFT", "Software Engineering", "CS");
        majorService.createMajor("MECH", "Mechanical Engineering", "ENG");
        majorService.createMajor("BUSI", "Business Administration", "BUS");

        // Read all majors
        majorService.displayAllMajors();

        // Update a major
        majorService.updateMajor("SOFT", "Software Engineering & AI", "CS");

        // Read updated majors
        System.out.println("\nAfter update:");
        majorService.displayAllMajors();
    }

    private static void testStudentOperations(StudentService studentService) {
        System.out.println("\n=== Testing Student CRUD Operations ===");

        // Create students
        studentService.createStudent(
                "Nguyeb Khanh Duy", Student.Gender.Male,
                LocalDate.of(2000, 5, 15), 3.75,
                "CSCI", "CS", 2022);

        studentService.createStudent(
                "Tran Hoa Binh", Student.Gender.Female,
                LocalDate.of(1999, 8, 22), 3.90,
                "SOFT", "CS", 2021);

        studentService.createStudent(
                "Nguyen Phuc", Student.Gender.Male,
                LocalDate.of(2001, 3, 10), 3.50,
                "MECH", "ENG", 2023);

        studentService.createStudent(
                "Tran Khanh Linh", Student.Gender.Female,
                LocalDate.of(2000, 12, 5), 3.85,
                "BUSI", "BUS", 2022);

        // Read all students
        studentService.displayAllStudents();

        // Update a student (assuming first student has ID 1)
        studentService.updateStudent(
                1L, "Tran Hoa Binh", Student.Gender.Male,
                LocalDate.of(2000, 5, 15), 3.80,
                "CSCI", "CS", 2022);

        // Read updated students
        System.out.println("\nAfter update:");
        studentService.displayAllStudents();

        // Test additional queries
        System.out.println("\n=== Additional Query Tests ===");

        // Find students by GPA range
        System.out.println("\nStudents with GPA between 3.7 and 4.0:");
        studentService.getStudentsByGpaRange(3.7, 4.0)
                .forEach(student -> System.out.printf("- %s (GPA: %.2f)%n", student.getFullName(), student.getGpa()));

        // Find students by enrollment year
        System.out.println("\nStudents enrolled in 2022:");
        studentService.getStudentsByEnrollmentYear(2022)
                .forEach(student -> System.out.printf("- %s%n", student.getFullName()));

        // Find students by school
        System.out.println("\nStudents in Computer Science school:");
        studentService.getStudentsBySchoolId("CS")
                .forEach(student -> System.out.printf("- %s%n", student.getFullName()));
    }
}