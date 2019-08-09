package com.example;

import java.io.Serializable;

import com.example.entity.Employee;
import com.example.entity.EmployeeSequence;
import com.example.entity.EmployeeTable;
import com.example.entity.Gender;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ApplicationDemo {

    /**
     * fatal -> error -> warn -> info -> debug -> trace
     * */
    private static final Logger LOGGER = Logger.getLogger(ApplicationDemo.class);

    public static void main(String[] args) {
        LOGGER.info("Checking table sequence");
        checkTableSequenceGenerationStrategy();
    }

    private static void checkTableSequenceGenerationStrategy() {
        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(new EmployeeTable("Ivan"));
            session.save(new EmployeeTable("Sveta"));
            session.save(new EmployeeTable("Klara"));
        }
    }

    private static void saveBySequenceGenerationStrategy() {
        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            /**
             * first sequence for all object for getting id
             * than insert all object to DB
             * */
            session.save(new EmployeeSequence("Ivan"));
            session.save(new EmployeeSequence("Petr"));
            session.save(new EmployeeSequence("Carl"));
            session.getTransaction().commit();
        }
    }

    private static void checkEnumMapping() {
        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Employee ivan = new Employee("Ivan", Gender.MALE);
            session.save(ivan);
            session.getTransaction().commit();
        }
    }

    private static void getObjectAfterDelete() {
        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Employee employee = session.get(Employee.class, 10L);
            session.delete(employee);
            /**
             * dirty session, when object changed in session, but not commited to DB
             * method evict - deletes object from session, not from DB
             * method clear - cleans session
             * method merge - adds changes from current object to gotten object from DB
             * */
            Employee employee1 = session.get(Employee.class, 10L);
            session.getTransaction().commit();
        }
    }

    private static void getObjectFromDataBaseSelect() {
        Employee employee;
        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Employee sveta2 = new Employee("Sveta2");
            Serializable id = session.save(sveta2);
            employee = session.get(Employee.class, id);
            session.getTransaction().commit();
            System.out.println(sveta2);
        }
        System.out.println(employee);
    }

    private static void getObjectFromSessionCache() {
        try (SessionFactory sessionFactory = new Configuration().configure()
                .buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            Employee sveta = new Employee("Sveta");
            /**
             * method persist - like save
             * */
            Serializable id = session.save(sveta);
            /**
             * methods get, find returns object from DB
             * method load returns proxy of this Object from DB
             * */
            Employee employee = session.get(Employee.class, id);
            System.out.println(sveta);
        }
    }

}
