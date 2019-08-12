package com.example;

import java.time.LocalDateTime;

import com.example.entity.Company;
import com.example.entity.Employee;
import com.example.entity.EmployeeMeeting;
import com.example.entity.Gender;
import com.example.entity.Meeting;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class AppDemo {

    public static void main(String[] args) {
        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            checkManyToManyWithIntermediateEntity(session);
            session.getTransaction().commit();
        }
    }

    private static void checkManyToManyWithIntermediateEntity(Session session) {
        Employee employee = session.find(Employee.class, 3L);
        Meeting meeting = session.find(Meeting.class, 1L);
        /**
         * if we need some data from intermediate many to many table
         * */
        EmployeeMeeting employeeMeeting = new EmployeeMeeting(employee, meeting, LocalDateTime.now());
        session.save(employeeMeeting);
    }

    private static void createMeeting(Session session) {
        Meeting test = new Meeting("test");
        session.save(test);
    }

    private static void deleteCompanyAndEmployeeWithCascadeTypeRemove(Session session) {
        Company company = session.find(Company.class, 1L);
        session.delete(company);
    }

    private static void getEmployee(Session session) {
        /**
         * by default in Employee class field company is EAGER fetch type
         * */
        Employee employee = session.find(Employee.class, 2L);
    }

    private static void getLazyInitializationException(Session session) {
        Company company = getCompany(session);
        /**
         * Producing Lazy initialization exception, because object company is detached from session and employees field is Lazy fetch type
         * */
        session.evict(company);
        System.out.println(company.getEmployees());
    }

    private static Company getCompany(Session session) {
        /**
         * One company for many employees, default fetch type for set of employees is LAZY,
         * because of it left join to employee table is not producing by default
         * */
        return session.find(Company.class, 1L);
    }

    private static void saveCompanyAndEmployee(Session session) {
        Company itRex = new Company("ITRex");
        session.save(itRex);
        Employee employee = new Employee("Ivan", Gender.MALE, itRex);
        session.save(employee);
    }

}
