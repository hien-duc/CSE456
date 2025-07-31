package vn.edu.eiu.lab5.repository;

import vn.edu.eiu.lab5.entity.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepository {

    @Autowired
    private SessionFactory sessionFactory;

    public Product save(Product product) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.persist(product);
            transaction.commit();
            return product;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        } finally {
            session.close();
        }
    }

    public List<Product> findAll() {
        Session session = sessionFactory.openSession();
        try {
            return session.createQuery("FROM Product", Product.class).getResultList();
        } finally {
            session.close();
        }
    }
}
