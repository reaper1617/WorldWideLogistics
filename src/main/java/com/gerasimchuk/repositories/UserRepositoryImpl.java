package com.gerasimchuk.repositories;

import com.gerasimchuk.entities.Admin;
import com.gerasimchuk.entities.Driver;
import com.gerasimchuk.entities.Manager;
import com.gerasimchuk.entities.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;


@Repository
@Transactional
public class UserRepositoryImpl implements UserRepository {

    private SessionFactory sessionFactory;

    @Autowired
    public UserRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public User create(String name, String middleName, String lastName, String personalNumber, String password, Driver driver, Manager manager, Admin admin) {
        User user = new User(name,middleName,lastName,personalNumber,password,driver,manager,admin);
        sessionFactory.getCurrentSession().persist(user);
        return user;
    }

    @Transactional
    public User update(int id, String name, String middleName, String lastName, String personalNumber, String password, Driver driver, Manager manager, Admin admin) {
        User updated = sessionFactory.getCurrentSession().get(User.class,id);
        updated.setName(name);
        updated.setMiddleName(middleName);
        updated.setLastName(lastName);
        updated.setPersonalNumber(personalNumber);
        updated.setPassword(password);
        updated.setDriver(driver);
        updated.setManager(manager);
        updated.setAdmin(admin);
        sessionFactory.getCurrentSession().update(updated);
        return updated;
    }

    @Transactional
    public User getById(int id) {
        return sessionFactory.getCurrentSession().get(User.class,id);
    }

    @Transactional
    public User getByDriverId(int driverId) {
        Collection<User> users = sessionFactory.getCurrentSession().createQuery("from Users", User.class).getResultList();
        for(User u: users){
            if (u.getDriver()!=null){
                if (u.getDriver().getId() == driverId) return u;
            }
        }
        return null;
    }

    public User getByManagerId(int managerId) {
        Collection<User> users = sessionFactory.getCurrentSession().createQuery("from Users", User.class).getResultList();
        for(User u: users){
            if (u.getManager()!=null){
                if (u.getManager().getId()==managerId) return u;
            }
        }
        return null;
    }

    public User getByAdminId(int adminId) {
        Collection<User> users = sessionFactory.getCurrentSession().createQuery("from Users", User.class).getResultList();
        for(User u: users){
            if (u.getAdmin()!=null){
                if (u.getAdmin().getId()==adminId) return u;
            }
        }
        return null;
    }

    @Transactional
    public User getByPersonalNumber(String personalNumber) {
        Collection<User> users = sessionFactory.getCurrentSession().createQuery("from Users", User.class).getResultList();
        for(User u: users){
            if (u.getPersonalNumber().equals(personalNumber)) return u;
        }
        return null;
    }

    @Transactional
    public Collection<User> getAll() {
        return sessionFactory.getCurrentSession().createQuery("from Users", User.class).getResultList();
    }

    @Transactional
    public void remove(int id) {
        User removed = sessionFactory.getCurrentSession().get(User.class,id);
        sessionFactory.getCurrentSession().remove(removed);
    }
}
