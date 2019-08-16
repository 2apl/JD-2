package com.example.dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.AfterClass;
import org.junit.BeforeClass;

public class BaseDaoTest {

    protected static SessionFactory factory;

    @BeforeClass
    public static void before() {
        factory = new Configuration().configure().buildSessionFactory();
    }

    @AfterClass
    public static void after() {
        factory.close();
    }
}
