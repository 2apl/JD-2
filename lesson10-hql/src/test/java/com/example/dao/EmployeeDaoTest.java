package com.example.dao;

import java.util.List;

import com.example.entity.Employee;
import com.example.entity.Gender;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.junit.Before;
import org.junit.Test;

public class EmployeeDaoTest extends BaseDaoTest {

    @Before
    public void initBefore() {
        init();
    }

    @Test
    public void hqlTest() {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            Query<Object[]> query = getWithSubquery(session);
            List resultList = query.list();
            resultList.forEach(System.out::println);
            session.getTransaction().commit();
        }
    }

    private Query getWithSubquery(Session session) {
        return session.createQuery("select new com.example.dto.EmployeeDto(e, count(c)) from Employee e " +
                "join e.contact c where lower( e.name) = (select em.name from Employee em where id = :emId)")
                .setParameter("emId", 2);
    }

    private Query getWithNewObjectDtoQuery(Session session) {
        /**
         * creates DTO inside query, doesn't work when package starts from by
         * */
        return session.createQuery("select new com.example.dto.EmployeeDto(e, count(c)) from Employee e join e.contact c");
    }

    private Query<Object[]> getWithGroupByQuery(Session session) {
        return session.createQuery("select e, count(e.contact) from Employee e group by e", Object[].class);
    }

    private Query<Long> getCountFunctionQuery(Session session) {
        return session.createQuery("select count(e) from Employee e", Long.class);
    }

    private Query<Employee> getEmployeeWithFetchForLazyQuery(Session session) {
        /**
         * fetch - get all employees even if it eager type
         * */
        return session.createQuery("select e from Employee e join fetch e.contact", Employee.class);
    }

    private Query<Employee> getEmployeeFromContactQuery(Session session) {
        return session.createQuery("select e from Contact c join c.employee e where c.phone = :phone", Employee.class)
                        .setParameter("phone", "44")
                        /**
                         * for pagination
                         * */
                        .setFirstResult(1)
                        .setMaxResults(2);
    }

    private Query<Employee> getEmployeeByNameQuery(Session session) {
        return session.createQuery("select e from Employee e where e.name = :employeeName", Employee.class)
                        .setParameter("employeeName", "Ivan");
    }

    private void init() {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            Employee ivan = new Employee("Ivan2", Gender.MALE);
            session.save(ivan);

            Employee petr = new Employee("Petr", Gender.MALE);
            session.save(petr);
            session.getTransaction().commit();
        }
    }

}
