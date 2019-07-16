package com.example;

import com.example.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ApplicationDemo {

    public static void main(String[] args) {

        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory()) {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            Employee employee = new Employee(1L, "Ivan");
            session.save(employee);
            session.getTransaction().commit();
            session.close();
        }
    }
}
