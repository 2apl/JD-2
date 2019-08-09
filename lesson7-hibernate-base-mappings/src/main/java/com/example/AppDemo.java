package com.example;

import com.example.entity.Address;
import com.example.entity.Employee;
import com.example.entity.Gender;
import com.example.entity.ManyIds;
import com.example.entity.Multiple;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class AppDemo {

    public static void main(String[] args) {
        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            saveEntityWithEmbeddedId(session);
            session.getTransaction().commit();
        }
    }

    private static void saveEntityWithEmbeddedId(Session session) {
        Multiple first = new Multiple(new ManyIds(1L, 2L), "first");
        session.save(first);
    }

    private static void findEmployee(Session session) {
        Employee employee = session.find(Employee.class, 2L);
    }

    private static void saveEmployeeWithoutAddress(Session session) {
        session.save(new Employee("Petr", Gender.MALE));
    }

    private static void saveEmployeeWithEmbeddedEntity(Session session) {
        session.save(new Employee("Ivan", Gender.MALE, new Address("Minsk", "Street1"), new Address("Grodno", "Street2")));
    }

}
