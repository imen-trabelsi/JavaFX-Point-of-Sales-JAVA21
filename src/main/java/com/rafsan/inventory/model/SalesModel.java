package com.rafsan.inventory.model;

import com.rafsan.inventory.HibernateUtil;
import com.rafsan.inventory.dao.SaleDao;
import com.rafsan.inventory.entity.Sale;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;

import java.util.List;

public class SalesModel implements SaleDao {

    private static Session session;

    @Override
    public ObservableList<Sale> getSales() {
        ObservableList<Sale> list = FXCollections.observableArrayList();

        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Sale> sales = session.createQuery("from Sale", Sale.class).getResultList();
        session.getTransaction().commit();
        list.addAll(sales);

        return list;
    }

    @Override
    public ObservableList<Sale> getSaleByProductId(long id) {
        ObservableList<Sale> list = FXCollections.observableArrayList();

        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        // Use JPA Criteria API to filter by product ID
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Sale> query = builder.createQuery(Sale.class);
        Root<Sale> root = query.from(Sale.class);
        Predicate predicate = builder.equal(root.get("product").get("id"), id);
        query.select(root).where(predicate);

        List<Sale> sales = session.createQuery(query).getResultList();
        session.getTransaction().commit();
        list.addAll(sales);

        return list;
    }

    @Override
    public Sale getSale(long id) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Sale sale = session.get(Sale.class, id);
        session.getTransaction().commit();

        return sale;
    }

    @Override
    public void saveSale(Sale sale) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.persist(sale);
        session.getTransaction().commit();
    }

    @Override
    public void updateSale(Sale sale) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Sale s = session.get(Sale.class, sale.getId());
        if (s != null) {
            s.setProduct(sale.getProduct());
            s.setQuantity(sale.getQuantity());
            s.setPrice(sale.getPrice());
            s.setTotal(sale.getTotal());
            s.setDate(sale.getDate());
        }
        session.getTransaction().commit();
    }

    @Override
    public void deleteSale(Sale sale) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Sale s = session.get(Sale.class, sale.getId());
        if (s != null) {
            session.remove(s);
        }
        session.getTransaction().commit();
    }
}
