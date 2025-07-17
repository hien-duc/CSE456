package vn.edu.eiu.lab3.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import vn.edu.eiu.lab3.dao.StudentDAO;
import vn.edu.eiu.lab3.entity.Student;

import java.util.List;

public class StudentDAOImpl implements StudentDAO {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("lab3-pu");

    @Override
    public void save(Student student) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(student);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public void update(Student student) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(student);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public void delete(Long studentId) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Student student = em.find(Student.class, studentId);
            if (student != null) {
                em.remove(student);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public Student findById(Long studentId) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Student.class, studentId);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Student> findAll() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Student> query = em.createQuery("SELECT s FROM Student s", Student.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Student> findByMajorId(String majorId) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Student> query = em.createQuery(
                    "SELECT s FROM Student s WHERE s.major.majorId = :majorId", Student.class);
            query.setParameter("majorId", majorId);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Student> findBySchoolId(String schoolId) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Student> query = em.createQuery(
                    "SELECT s FROM Student s WHERE s.school.schoolId = :schoolId", Student.class);
            query.setParameter("schoolId", schoolId);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Student> findByEnrollmentYear(Integer year) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Student> query = em.createQuery(
                    "SELECT s FROM Student s WHERE s.enrollmentYear = :year", Student.class);
            query.setParameter("year", year);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Student> findByGpaRange(Double minGpa, Double maxGpa) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Student> query = em.createQuery(
                    "SELECT s FROM Student s WHERE s.gpa BETWEEN :minGpa AND :maxGpa", Student.class);
            query.setParameter("minGpa", minGpa);
            query.setParameter("maxGpa", maxGpa);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}