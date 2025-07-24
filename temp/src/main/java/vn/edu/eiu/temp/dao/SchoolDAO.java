package vn.edu.eiu.temp.dao;
import  jakarta.persistence.*;
import vn.edu.eiu.temp.entity.School;

import java.util.*;
public class SchoolDAO {
    public static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("lab3-pu");

    public void save(School School){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(School);
        em.getTransaction().commit();
        em.close();
    }

    public void delete(School School){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.remove(School);
        em.getTransaction().commit();
        em.close();
    }

    public void update(School School){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(School);
        em.getTransaction().commit();
        em.close();
    }

    public School findById(String schoolId){
        EntityManager em = emf.createEntityManager();
        try {
            return  em.find(School.class,schoolId);
        }finally {
            em.close();
        }
    }

    public List<School> findAll(){
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT s from School s",School.class).getResultList();
        }finally {
            em.close();
        }
    }
}
