package vn.edu.eiu.lab3.dao;

import vn.edu.eiu.lab3.entity.Major;
import java.util.List;

public interface MajorDAO extends BaseDAO<Major, String> {
    List<Major> findBySchoolId(String schoolId);

    Major findByName(String majorName);
}