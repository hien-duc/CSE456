package vn.edu.eiu.temp.dao;
import jakarta.persistence.*;
import vn.edu.eiu.temp.entity.Major;

import java.util.*;
public class MajorDAO {
    public static final EntityManagerFactory emf  = Persistence.createEntityManagerFactory("lab3-pu");

    public void save (Major major){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(major);
        em.getTransaction().commit();
        em.close();
    }
    public void delete(Major major){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();;
        em.remove(major);
        em.getTransaction().commit();
        em.close();
    }

    public void update(Major major){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(major);
        em.getTransaction().commit();
        em.close();
    }

    public Major findById(String majorId){
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Major.class, majorId);
        }finally {
            em.close();
        }

    }
}
