package com.rafsan.inventory.model;

import com.rafsan.inventory.HibernateUtil;
import com.rafsan.inventory.dao.PurchaseDao;
import com.rafsan.inventory.entity.Purchase;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;

public class PurchaseModel implements PurchaseDao {

    private static Session session;
    
    public ObservableList<Purchase> getPurchases() {
        ObservableList<Purchase> list = FXCollections.observableArrayList();
    
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        
        // Ensure no active transaction before starting a new one
        if (!session.getTransaction().isActive()) {
            session.beginTransaction();
        }
    
        try {
            List<Purchase> purchases = session.createQuery("from Purchase").list();
            purchases.forEach(list::add);
            session.getTransaction().commit(); // Commit the transaction
        } catch (Exception e) {
            session.getTransaction().rollback(); // Rollback in case of error
            throw e; // Re-throw the exception to handle it elsewhere
        } finally {
            if (session != null && session.isOpen()) {
                session.close(); // Always close the session to prevent leaks
            }
        }
    
        return list;
    }

    @Override
    public Purchase getPurchase(long id) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Purchase purchase = session.get(Purchase.class, id);
        session.getTransaction().commit();

        return purchase;
    }

    @Override
    public void savePurchase(Purchase purchase) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(purchase);
        session.getTransaction().commit();
    }

    @Override
    public void updatePurchase(Purchase purchase) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Purchase p = session.get(Purchase.class, purchase.getId());
        p.setProduct(purchase.getProduct());
        p.setSupplier(purchase.getSupplier());
        p.setQuantity(purchase.getQuantity());
        p.setDate(purchase.getDate());
        session.getTransaction().commit();
    }

    @Override
    public void deletePurchase(Purchase purchase) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Purchase p = session.get(Purchase.class, purchase.getId());
        session.delete(p);
        session.getTransaction().commit();
    }
    
}
