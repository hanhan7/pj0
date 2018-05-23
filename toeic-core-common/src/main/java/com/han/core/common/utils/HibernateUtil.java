package com.han.core.common.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by VO VAN NHAN on 5/20/2018.
 */
public class HibernateUtil {
    private static final SessionFactory SESSION_FACTORY = buildSessionFactory();

    //build session factory
    private static SessionFactory buildSessionFactory() {
        try {
            return new Configuration().configure().buildSessionFactory();
        } catch (Throwable e) {
            System.out.println("Initial session factory failed");
            throw new ExceptionInInitializerError(e);
        }
        //create sessionfactory from hibernate.cfg.xml
    }

    //get session factory
    public static SessionFactory getSessionFactory() {
        return SESSION_FACTORY;
    }
}
