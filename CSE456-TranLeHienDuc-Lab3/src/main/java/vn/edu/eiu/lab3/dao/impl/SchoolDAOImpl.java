package vn.edu.eiu.lab3.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import vn.edu.eiu.lab3.dao.SchoolDAO;
import vn.edu.eiu.lab3.entity.School;

import java.util.List;

public class SchoolDAOImpl implements SchoolDAO {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("lab3-pu");

    @Override
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

    @Override
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

    @Override
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

    @Override
    public School findById(String schoolId) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(School.class, schoolId);
        } finally {
            em.close();
        }
    }

    @Override
    public List<School> findAll() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<School> query = em.createQuery("SELECT s FROM School s", School.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public School findByName(String schoolName) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<School> query = em.createQuery(
                    "SELECT s FROM School s WHERE s.schoolName = :name", School.class);
            query.setParameter("name", schoolName);
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }
}