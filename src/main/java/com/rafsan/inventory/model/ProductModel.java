package com.rafsan.inventory.model;

import com.rafsan.inventory.HibernateUtil;
import com.rafsan.inventory.dao.ProductDao;
import com.rafsan.inventory.entity.Product;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class ProductModel implements ProductDao {

    private static Session session;

    @Override
    public ObservableList<Product> getProducts() {
        ObservableList<Product> list = FXCollections.observableArrayList();
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Product> products = session.createQuery("from Product", Product.class).getResultList();
        session.getTransaction().commit();
        list.addAll(products);

        return list;
    }

    @Override
    public Product getProduct(long id) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Product product = session.get(Product.class, id);
        session.getTransaction().commit();

        return product;
    }

    @Override
    public Product getProductByName(String productName) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query<Product> query = session.createQuery("from Product where productName = :name", Product.class);
        query.setParameter("name", productName);
        Product product = query.uniqueResult();
        session.getTransaction().commit();

        return product;
    }

    @Override
    public void saveProduct(Product product) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.persist(product);
        session.getTransaction().commit();
    }

    @Override
    public void updateProduct(Product product) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Product p = session.get(Product.class, product.getId());
        if (p != null) {
            p.setProductName(product.getProductName());
            p.setCategory(product.getCategory());
            p.setQuantity(product.getQuantity());
            p.setPrice(product.getPrice());
            p.setDescription(product.getDescription());
        }
        session.getTransaction().commit();
    }

    @Override
    public void increaseProduct(Product product) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Product p = session.get(Product.class, product.getId());
        if (p != null) {
            p.setQuantity(product.getQuantity());
        }
        session.getTransaction().commit();
    }

    @Override
    public void decreaseProduct(Product product) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Product p = session.get(Product.class, product.getId());
        if (p != null) {
            p.setQuantity(product.getQuantity());
        }
        session.getTransaction().commit();
    }

    @Override
    public void deleteProduct(Product product) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Product p = session.get(Product.class, product.getId());
        if (p != null) {
            session.remove(p);
        }
        session.getTransaction().commit();
    }

    @Override
    public ObservableList<String> getProductNames() {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        // Use JPA Criteria API to retrieve product names
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<String> query = builder.createQuery(String.class);
        Root<Product> root = query.from(Product.class);
        query.select(root.get("productName"));

        List<String> productNames = session.createQuery(query).getResultList();
        session.getTransaction().commit();

        return FXCollections.observableArrayList(productNames);
    }
}
