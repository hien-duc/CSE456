package vn.edu.eiu.practice.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import vn.edu.eiu.practice.entity.School;

import java.util.List;

public class SchoolDAO {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("lab3-pu");

    public void save(School school) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(school);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public School findById(String schoolId) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(School.class, schoolId);
        } finally {
            em.close();
        }
    }

    public List<School> findAll() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT s FROM School s", School.class).getResultList();
        } finally {
            em.close();
        }
    }

    public void update(School school) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(school);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public void delete(String schoolId) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            School school = em.find(School.class, schoolId);
            if (school != null) {
                em.remove(school);
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
