package com.rafsan.inventory.model;

import com.rafsan.inventory.HibernateUtil;
import com.rafsan.inventory.dao.CategoryDao;
import com.rafsan.inventory.entity.Category;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;

public class CategoryModel implements CategoryDao {

    private static Session session;

    @Override
    public ObservableList<Category> getCategories() {
        ObservableList<Category> list = FXCollections.observableArrayList();

        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Category> categories = session.createQuery("from Category", Category.class).getResultList();
        session.getTransaction().commit();
        list.addAll(categories);

        return list;
    }

    @Override
    public Category getCategory(long id) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Category category = session.get(Category.class, id);
        session.getTransaction().commit();

        return category;
    }

    @Override
    public void saveCategory(Category category) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.persist(category);
        session.getTransaction().commit();
    }

    @Override
    public void updateCategory(Category category) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Category c = session.get(Category.class, category.getId());
        c.setType(category.getType());
        c.setDescription(category.getDescription());
        session.getTransaction().commit();
    }

    @Override
    public void deleteCategory(Category category) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Category c = session.get(Category.class, category.getId());
        session.remove(c);
        session.getTransaction().commit();
    }

    @Override
    public ObservableList<String> getTypes() {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        // Use JPA Criteria API to retrieve types
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<String> query = builder.createQuery(String.class);
        Root<Category> root = query.from(Category.class);
        query.select(root.get("type"));

        List<String> types = session.createQuery(query).getResultList();
        session.getTransaction().commit();

        return FXCollections.observableArrayList(types);
    }
}
