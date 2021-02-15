package com.anisimovdenis.hw5;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Dao<T, E extends Serializable> {

    private final SessionFactory sessionFactory;
    private final Class<T> entityClass;

    private final String DELETE_ALL_HQL;
    private final String DELETE_BY_ID_HQL;
    private final String GET_ALL_HQL;

    public Dao(final SessionFactory sessionFactory, final Class<T> entityClass) {
        Objects.requireNonNull(sessionFactory);
        Objects.requireNonNull(entityClass);
        this.sessionFactory = sessionFactory;
        this.entityClass = entityClass;
        final String entityClassName = entityClass.getSimpleName();
        this.DELETE_ALL_HQL = "DELETE FROM " + entityClassName;
        this.DELETE_BY_ID_HQL = "DELETE FROM " + entityClassName + " o WHERE o.id=";
        this.GET_ALL_HQL = "FROM " + entityClassName;
    }

    public void deleteById(final E id) {
        try (final Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            session.createQuery(DELETE_BY_ID_HQL + id).executeUpdate();
            session.getTransaction().commit();
        }
    }

    public void delete(final T object) {
        try (final Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            session.delete(object);
            session.getTransaction().commit();
        }
    }

    public void deleteAll() {
        try (final Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            session.createQuery(DELETE_ALL_HQL).executeUpdate();
            session.getTransaction().commit();
        }
    }

    public Optional<T> getById(final E id) {
        try (final Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            final T object = session.get(entityClass, id);
            session.getTransaction().commit();
            return object == null ? Optional.empty() : Optional.of(object);
        }
    }

    public List<T> getAll() {
        try (final Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            final List<T> list = session.createQuery(GET_ALL_HQL).getResultList();
            session.getTransaction().commit();
            return list;
        }
    }

    public void save(final T object) {
        try (final Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            session.save(object);
            session.getTransaction().commit();
        }
    }

    public void saveAll(final List<T> objects) {
        try (final Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            for (final T object : objects) {
                session.save(object);
            }
            session.getTransaction().commit();
        }
    }

    public void update(final T object) {
        try (final Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            session.update(object);
            session.getTransaction().commit();
        }
    }
}
