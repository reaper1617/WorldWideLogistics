package com.gerasimchuk.repositories;

import com.gerasimchuk.entities.Admin;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Repository
@Transactional
public class AdminRepositoryImpl implements AdminRepository {

    private SessionFactory sessionFactory;

    @Autowired
    public AdminRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public Admin create() {
        Admin admin = new Admin();
        sessionFactory.getCurrentSession().persist(admin);
        return admin;
    }

    @Transactional
    public Admin update(int id) {
        Admin updated = sessionFactory.getCurrentSession().get(Admin.class,id);
        // update actions
        sessionFactory.getCurrentSession().update(updated);
        return updated;
    }

    @Transactional
    public Admin getById(int id) {
        return sessionFactory.getCurrentSession().get(Admin.class,id);
    }

    @Transactional
    public Collection<Admin> getAll() {
        return sessionFactory.getCurrentSession().createQuery("from Admins", Admin.class).getResultList();
    }

    @Transactional
    public void remove(int id) {
        Admin removed = sessionFactory.getCurrentSession().get(Admin.class,id);
        sessionFactory.getCurrentSession().remove(removed);
    }
}
