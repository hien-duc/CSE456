package vn.edu.eiu.lab3.dao;

import vn.edu.eiu.lab3.entity.School;

public interface SchoolDAO extends BaseDAO<School, String> {
    School findByName(String schoolName);
}