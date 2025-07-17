package vn.edu.eiu.lab3.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import vn.edu.eiu.lab3.dao.MajorDAO;
import vn.edu.eiu.lab3.entity.Major;

import java.util.List;

public class MajorDAOImpl implements MajorDAO {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("lab3-pu");

    @Override
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

    @Override
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

    @Override
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

    @Override
    public Major findById(String majorId) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Major.class, majorId);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Major> findAll() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Major> query = em.createQuery("SELECT m FROM Major m", Major.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Major> findBySchoolId(String schoolId) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Major> query = em.createQuery(
                    "SELECT m FROM Major m WHERE m.school.schoolId = :schoolId", Major.class);
            query.setParameter("schoolId", schoolId);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public Major findByName(String majorName) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Major> query = em.createQuery(
                    "SELECT m FROM Major m WHERE m.majorName = :name", Major.class);
            query.setParameter("name", majorName);
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }
}