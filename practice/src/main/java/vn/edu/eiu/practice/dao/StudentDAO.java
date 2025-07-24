package vn.edu.eiu.practice.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import vn.edu.eiu.practice.entity.Student;

import java.util.List;

public class StudentDAO {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("lab3-pu");

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

    public Student findById(Long studentId) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Student.class, studentId);
        } finally {
            em.close();
        }
    }

    public List<Student> findAll() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT s FROM Student s", Student.class).getResultList();
        } finally {
            em.close();
        }
    }

    public List<Student> findByMajorId(String majorId) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT s FROM Student s WHERE s.major.majorId = :majorId", Student.class)
                    .setParameter("majorId", majorId)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public List<Student> findBySchoolId(String schoolId) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT s FROM Student s WHERE s.school.schoolId = :schoolId", Student.class)
                    .setParameter("schoolId", schoolId)
                    .getResultList();
        } finally {
            em.close();
        }
    }

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

    public static void closeEntityManagerFactory() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}
