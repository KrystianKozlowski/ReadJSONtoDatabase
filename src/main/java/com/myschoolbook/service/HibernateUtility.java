package com.myschoolbook.service;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;

/**
 * Created by Krystian on 2017-06-01.
 */
public class HibernateUtility {

    public static SessionFactory factory;

    //to disallow creating objects by other classes.

    private HibernateUtility() {
    }

    //ensures that a method can be invoked by only one thread at a time
    public static synchronized SessionFactory getSessionFactory() {

        if (factory == null) {
            factory = new Configuration().configure("hibernate.cfg.xml").
                    buildSessionFactory();
        }
        return factory;
    }

}
