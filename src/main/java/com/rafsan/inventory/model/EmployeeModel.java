package com.rafsan.inventory.model;

import com.rafsan.inventory.HibernateUtil;
import com.rafsan.inventory.dao.EmployeeDao;
import com.rafsan.inventory.entity.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class EmployeeModel implements EmployeeDao {

    private static Session session;

    @Override
    public ObservableList<Employee> getEmployees() {
        ObservableList<Employee> list = FXCollections.observableArrayList();

        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Employee> employees = session.createQuery("from Employee", Employee.class).getResultList();
        session.getTransaction().commit();
        list.addAll(employees);

        return list;
    }

    @Override
    public Employee getEmployee(long id) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Employee employee = session.get(Employee.class, id);
        session.getTransaction().commit();

        return employee;
    }

    @Override
    public String getEmployeeType(String username) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query<Employee> query = session.createQuery("from Employee where userName = :username", Employee.class);
        query.setParameter("username", username);
        Employee employee = query.uniqueResult();
        session.getTransaction().commit();

        return employee != null ? employee.getType() : null;
    }

    @Override
    public void saveEmployee(Employee employee) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.persist(employee);
        session.getTransaction().commit();
    }

    @Override
    public void updateEmployee(Employee employee) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Employee e = session.get(Employee.class, employee.getId());
        if (e != null) {
            e.setFirstName(employee.getFirstName());
            e.setLastName(employee.getLastName());
            e.setUserName(employee.getUserName());
            e.setPassword(employee.getPassword());
            e.setPhone(employee.getPhone());
            e.setAddress(employee.getAddress());
        }
        session.getTransaction().commit();
    }

    @Override
    public void deleteEmployee(Employee employee) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Employee e = session.get(Employee.class, employee.getId());
        if (e != null) {
            session.remove(e);
        }
        session.getTransaction().commit();
    }

    @Override
    public boolean checkUser(String username) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query<Employee> query = session.createQuery("from Employee where userName = :username", Employee.class);
        query.setParameter("username", username);
        Employee employee = query.uniqueResult();
        session.getTransaction().commit();

        return employee != null;
    }

    @Override
    public boolean checkPassword(String username, String password) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query<Employee> query = session.createQuery("from Employee where userName = :username", Employee.class);
        query.setParameter("username", username);
        Employee employee = query.uniqueResult();
        session.getTransaction().commit();

        return employee != null && employee.getPassword().equals(password);
    }
}
