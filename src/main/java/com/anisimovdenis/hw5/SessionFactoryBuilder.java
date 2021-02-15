package com.anisimovdenis.hw5;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionFactoryBuilder {

    private static volatile SessionFactory sessionFactory;

    public static SessionFactory build() {
        return build("hibernate.cfg.xml");
    }

    public static SessionFactory build(String configurationResource) {
        if (sessionFactory == null) {
            synchronized (SessionFactoryBuilder.class) {
                if (sessionFactory == null) {
                    sessionFactory = new Configuration().configure(configurationResource).buildSessionFactory();
                }
            }
        }
        return sessionFactory;
    }

    public static void closeSessionFactory() throws HibernateException {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}
