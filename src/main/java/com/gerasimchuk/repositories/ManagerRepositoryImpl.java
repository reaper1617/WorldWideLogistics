package com.gerasimchuk.repositories;

import com.gerasimchuk.entities.Manager;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;


@Repository
@Transactional
public class ManagerRepositoryImpl implements ManagerRepository {


    private SessionFactory sessionFactory;

    @Autowired
    public ManagerRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public Manager create() {
        Manager manager = new Manager();
        sessionFactory.getCurrentSession().persist(manager);
        return manager;
    }

    @Transactional
    public Manager update(int id) {
        Manager updated = sessionFactory.getCurrentSession().get(Manager.class,id);
        // update actions
        sessionFactory.getCurrentSession().update(updated);
        return updated;
    }

    @Transactional
    public Manager getById(int id) {
        return sessionFactory.getCurrentSession().get(Manager.class, id);
    }

    @Transactional
    public Collection<Manager> getAll() {
        return sessionFactory.getCurrentSession().createQuery("from Managers",Manager.class).getResultList();
    }

    public void remove(int id) {
        Manager removed = sessionFactory.getCurrentSession().get(Manager.class, id);
        sessionFactory.getCurrentSession().remove(removed);
    }
}
