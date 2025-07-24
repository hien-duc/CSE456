package vn.edu.eiu.practice.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import vn.edu.eiu.practice.entity.Major;

import java.util.List;

public class MajorDAO {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("lab3-pu");

    public void save(Major major) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(major);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public Major findById(String majorId) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Major.class, majorId);
        } finally {
            em.close();
        }
    }

    public List<Major> findAll() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT m FROM Major m", Major.class).getResultList();
        } finally {
            em.close();
        }
    }

    public List<Major> findBySchoolId(String schoolId) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT m FROM Major m WHERE m.school.schoolId = :schoolId", Major.class)
                    .setParameter("schoolId", schoolId)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public void update(Major major) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(major);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public void delete(String majorId) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Major major = em.find(Major.class, majorId);
            if (major != null) {
                em.remove(major);
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
