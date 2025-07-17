package vn.edu.eiu.lab3.dao;

import vn.edu.eiu.lab3.entity.Student;
import java.util.List;

public interface StudentDAO extends BaseDAO<Student, Long> {
    List<Student> findByMajorId(String majorId);

    List<Student> findBySchoolId(String schoolId);

    List<Student> findByEnrollmentYear(Integer year);

    List<Student> findByGpaRange(Double minGpa, Double maxGpa);
}