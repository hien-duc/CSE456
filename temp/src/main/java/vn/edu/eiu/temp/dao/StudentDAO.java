package vn.edu.eiu.temp.dao;

import jakarta.persistence.*;
import vn.edu.eiu.temp.entity.Student;

import java.util.List;

public class StudentDAO {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("lab3-pu");

    public void save(Student student){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(student);
        em.getTransaction().commit();
        em.close();
    }

    public void delete(Student student){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.remove(student);
        em.getTransaction().commit();
        em.close();
    }

    public void update(Student student){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(student);
        em.getTransaction().commit();
        em.close();
    }

    public Student findById(Long studentId){
        EntityManager em = emf.createEntityManager();
        return  em.find(Student.class,studentId);
    }


    public Student findByFullName(String fullName){
        EntityManager em = emf.createEntityManager();
        try {
            return  em.find(Student.class,fullName);
        }finally {
            em.close();
        }
    }

    public List<Student> fillAllStudent(){
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT s from Student s", Student.class).getResultList();
        }finally {
            em.close();
        }
    }

    public static void closeEmf(){
        emf.close();
    }

}
