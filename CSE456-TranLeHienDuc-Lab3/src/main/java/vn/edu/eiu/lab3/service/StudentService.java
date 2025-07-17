package vn.edu.eiu.lab3.service;

import vn.edu.eiu.lab3.dao.MajorDAO;
import vn.edu.eiu.lab3.dao.SchoolDAO;
import vn.edu.eiu.lab3.dao.StudentDAO;
import vn.edu.eiu.lab3.dao.impl.MajorDAOImpl;
import vn.edu.eiu.lab3.dao.impl.SchoolDAOImpl;
import vn.edu.eiu.lab3.dao.impl.StudentDAOImpl;
import vn.edu.eiu.lab3.entity.Major;
import vn.edu.eiu.lab3.entity.School;
import vn.edu.eiu.lab3.entity.Student;

import java.time.LocalDate;
import java.util.List;

public class StudentService {

    private final StudentDAO studentDAO;
    private final MajorDAO majorDAO;
    private final SchoolDAO schoolDAO;

    public StudentService() {
        this.studentDAO = new StudentDAOImpl();
        this.majorDAO = new MajorDAOImpl();
        this.schoolDAO = new SchoolDAOImpl();
    }

    public void createStudent(String fullName, Student.Gender gender, LocalDate dob,
            Double gpa, String majorId, String schoolId, Integer enrollmentYear) {
        Major major = majorDAO.findById(majorId);
        School school = schoolDAO.findById(schoolId);

        if (major != null && school != null) {
            Student student = new Student(fullName, gender, dob, gpa, major, school, enrollmentYear);
            studentDAO.save(student);
            System.out.println("Student created successfully: " + student.getFullName());
        } else {
            if (major == null)
                System.out.println("Major not found with ID: " + majorId);
            if (school == null)
                System.out.println("School not found with ID: " + schoolId);
        }
    }

    public void updateStudent(Long studentId, String fullName, Student.Gender gender,
            LocalDate dob, Double gpa, String majorId, String schoolId, Integer enrollmentYear) {
        Student student = studentDAO.findById(studentId);
        if (student != null) {
            Major major = majorDAO.findById(majorId);
            School school = schoolDAO.findById(schoolId);

            if (major != null && school != null) {
                student.setFullName(fullName);
                student.setGender(gender);
                student.setDob(dob);
                student.setGpa(gpa);
                student.setMajor(major);
                student.setSchool(school);
                student.setEnrollmentYear(enrollmentYear);
                studentDAO.update(student);
                System.out.println("Student updated successfully: " + student.getFullName());
            } else {
                if (major == null)
                    System.out.println("Major not found with ID: " + majorId);
                if (school == null)
                    System.out.println("School not found with ID: " + schoolId);
            }
        } else {
            System.out.println("Student not found with ID: " + studentId);
        }
    }

    public void deleteStudent(Long studentId) {
        Student student = studentDAO.findById(studentId);
        if (student != null) {
            studentDAO.delete(studentId);
            System.out.println("Student deleted successfully: " + student.getFullName());
        } else {
            System.out.println("Student not found with ID: " + studentId);
        }
    }

    public Student getStudentById(Long studentId) {
        return studentDAO.findById(studentId);
    }

    public List<Student> getAllStudents() {
        return studentDAO.findAll();
    }

    public List<Student> getStudentsByMajorId(String majorId) {
        return studentDAO.findByMajorId(majorId);
    }

    public List<Student> getStudentsBySchoolId(String schoolId) {
        return studentDAO.findBySchoolId(schoolId);
    }

    public List<Student> getStudentsByEnrollmentYear(Integer year) {
        return studentDAO.findByEnrollmentYear(year);
    }

    public List<Student> getStudentsByGpaRange(Double minGpa, Double maxGpa) {
        return studentDAO.findByGpaRange(minGpa, maxGpa);
    }

    public void displayAllStudents() {
        List<Student> students = getAllStudents();
        System.out.println("\n=== All Students ===");
        if (students.isEmpty()) {
            System.out.println("No students found.");
        } else {
            for (Student student : students) {
                System.out.printf("ID: %d, Name: %s, Gender: %s, DOB: %s, GPA: %.2f, Major: %s, School: %s, Year: %d%n",
                        student.getStudentId(), student.getFullName(), student.getGender(),
                        student.getDob(), student.getGpa(),
                        student.getMajor() != null ? student.getMajor().getMajorName() : "N/A",
                        student.getSchool() != null ? student.getSchool().getSchoolName() : "N/A",
                        student.getEnrollmentYear());
            }
        }
    }
}