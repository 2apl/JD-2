package com.example;

import com.example.entity.Employee;
import com.example.entity.Gender;
import com.example.entity.Language;
import com.example.entity.Manager;
import com.example.entity.Programmer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class TestApp {

    public static final SessionFactory FACTORY = new Configuration().configure().buildSessionFactory();

    @Test
    public void test1() {
        try (Session session = FACTORY.openSession()) {
            session.beginTransaction();
            Manager manager = new Manager("Sveta", Gender.FEMALE, "WF");
            session.save(manager);

            Programmer programmer = new Programmer("Ivan", Gender.MALE, Language.JAVA);
            session.save(programmer);
            session.getTransaction().commit();
        }

        try (Session session = FACTORY.openSession()) {
            session.createQuery("select e from Employee e", Employee.class).list();
        }
    }
}
