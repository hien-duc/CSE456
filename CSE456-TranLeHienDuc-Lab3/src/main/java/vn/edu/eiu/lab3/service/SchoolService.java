package vn.edu.eiu.lab3.service;

import vn.edu.eiu.lab3.dao.SchoolDAO;
import vn.edu.eiu.lab3.dao.impl.SchoolDAOImpl;
import vn.edu.eiu.lab3.entity.School;

import java.util.List;

public class SchoolService {

    private final SchoolDAO schoolDAO;

    public SchoolService() {
        this.schoolDAO = new SchoolDAOImpl();
    }

    public void createSchool(String schoolId, String schoolName, String location) {
        School school = new School(schoolId, schoolName, location);
        schoolDAO.save(school);
        System.out.println("School created successfully: " + school.getSchoolName());
    }

    public void updateSchool(String schoolId, String schoolName, String location) {
        School school = schoolDAO.findById(schoolId);
        if (school != null) {
            school.setSchoolName(schoolName);
            school.setLocation(location);
            schoolDAO.update(school);
            System.out.println("School updated successfully: " + school.getSchoolName());
        } else {
            System.out.println("School not found with ID: " + schoolId);
        }
    }

    public void deleteSchool(String schoolId) {
        School school = schoolDAO.findById(schoolId);
        if (school != null) {
            schoolDAO.delete(schoolId);
            System.out.println("School deleted successfully: " + school.getSchoolName());
        } else {
            System.out.println("School not found with ID: " + schoolId);
        }
    }

    public School getSchoolById(String schoolId) {
        return schoolDAO.findById(schoolId);
    }

    public List<School> getAllSchools() {
        return schoolDAO.findAll();
    }

    public School getSchoolByName(String schoolName) {
        return schoolDAO.findByName(schoolName);
    }

    public void displayAllSchools() {
        List<School> schools = getAllSchools();
        System.out.println("\n=== All Schools ===");
        if (schools.isEmpty()) {
            System.out.println("No schools found.");
        } else {
            for (School school : schools) {
                System.out.printf("ID: %s, Name: %s, Location: %s%n",
                        school.getSchoolId(), school.getSchoolName(), school.getLocation());
            }
        }
    }
}