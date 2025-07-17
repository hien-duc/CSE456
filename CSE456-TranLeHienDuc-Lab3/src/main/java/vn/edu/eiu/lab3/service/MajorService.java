package vn.edu.eiu.lab3.service;

import vn.edu.eiu.lab3.dao.MajorDAO;
import vn.edu.eiu.lab3.dao.SchoolDAO;
import vn.edu.eiu.lab3.dao.impl.MajorDAOImpl;
import vn.edu.eiu.lab3.dao.impl.SchoolDAOImpl;
import vn.edu.eiu.lab3.entity.Major;
import vn.edu.eiu.lab3.entity.School;

import java.util.List;

public class MajorService {

    private final MajorDAO majorDAO;
    private final SchoolDAO schoolDAO;

    public MajorService() {
        this.majorDAO = new MajorDAOImpl();
        this.schoolDAO = new SchoolDAOImpl();
    }

    public void createMajor(String majorId, String majorName, String schoolId) {
        School school = schoolDAO.findById(schoolId);
        if (school != null) {
            Major major = new Major(majorId, majorName, school);
            majorDAO.save(major);
            System.out.println("Major created successfully: " + major.getMajorName());
        } else {
            System.out.println("School not found with ID: " + schoolId);
        }
    }

    public void updateMajor(String majorId, String majorName, String schoolId) {
        Major major = majorDAO.findById(majorId);
        if (major != null) {
            School school = schoolDAO.findById(schoolId);
            if (school != null) {
                major.setMajorName(majorName);
                major.setSchool(school);
                majorDAO.update(major);
                System.out.println("Major updated successfully: " + major.getMajorName());
            } else {
                System.out.println("School not found with ID: " + schoolId);
            }
        } else {
            System.out.println("Major not found with ID: " + majorId);
        }
    }

    public void deleteMajor(String majorId) {
        Major major = majorDAO.findById(majorId);
        if (major != null) {
            majorDAO.delete(majorId);
            System.out.println("Major deleted successfully: " + major.getMajorName());
        } else {
            System.out.println("Major not found with ID: " + majorId);
        }
    }

    public Major getMajorById(String majorId) {
        return majorDAO.findById(majorId);
    }

    public List<Major> getAllMajors() {
        return majorDAO.findAll();
    }

    public List<Major> getMajorsBySchoolId(String schoolId) {
        return majorDAO.findBySchoolId(schoolId);
    }

    public void displayAllMajors() {
        List<Major> majors = getAllMajors();
        System.out.println("\n=== All Majors ===");
        if (majors.isEmpty()) {
            System.out.println("No majors found.");
        } else {
            for (Major major : majors) {
                System.out.printf("ID: %s, Name: %s, School: %s%n",
                        major.getMajorId(), major.getMajorName(),
                        major.getSchool() != null ? major.getSchool().getSchoolName() : "N/A");
            }
        }
    }
}