package vn.edu.eiu.lab5.repository;

import vn.edu.eiu.lab5.entity.Invoice;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class InvoiceRepository {

    @Autowired
    private SessionFactory sessionFactory;

    public Invoice save(Invoice invoice) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.persist(invoice);
            transaction.commit();
            return invoice;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        } finally {
            session.close();
        }
    }
}
