package com.rafsan.inventory.model;

import com.rafsan.inventory.HibernateUtil;
import com.rafsan.inventory.dao.SupplierDao;
import com.rafsan.inventory.entity.Product;
import com.rafsan.inventory.entity.Supplier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;

import java.util.List;

public class SupplierModel implements SupplierDao {

    private static Session session;

    @Override
    public ObservableList<Supplier> getSuppliers() {
        ObservableList<Supplier> list = FXCollections.observableArrayList();
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Supplier> suppliers = session.createQuery("from Supplier", Supplier.class).getResultList();
        session.getTransaction().commit();
        list.addAll(suppliers);

        return list;
    }

    @Override
    public Supplier getSupplier(long id) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Supplier supplier = session.get(Supplier.class, id);
        session.getTransaction().commit();

        return supplier;
    }

    @Override
    public void saveSuplier(Supplier supplier) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.persist(supplier);
        session.getTransaction().commit();
    }

    @Override
    public void updateSuplier(Supplier supplier) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Supplier s = session.get(Supplier.class, supplier.getId());
        if (s != null) {
            s.setName(supplier.getName());
            s.setPhone(supplier.getPhone());
            s.setAddress(supplier.getAddress());
        }
        session.getTransaction().commit();
    }

    @Override
    public void deleteSuplier(Supplier supplier) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
    
            // Check if the supplier has associated products
            Long productCount = session.createQuery("select count(p) from Product p where supplierId = :supplierId", Long.class)
                                       .setParameter("supplierId", supplier.getId())
                                       .uniqueResult();
    
            if (productCount > 0) {
                throw new IllegalStateException("Cannot delete supplier with associated products.");
            }
    
            // Delete the supplier
            session.delete(supplier);
    
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e; // Re-throw to handle it elsewhere
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
    


    @Override
    public ObservableList<String> getNames() {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        // Use JPA Criteria API to retrieve supplier names
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<String> query = builder.createQuery(String.class);
        Root<Supplier> root = query.from(Supplier.class);
        query.select(root.get("name"));

        List<String> names = session.createQuery(query).getResultList();
        session.getTransaction().commit();

        return FXCollections.observableArrayList(names);
    }
}
