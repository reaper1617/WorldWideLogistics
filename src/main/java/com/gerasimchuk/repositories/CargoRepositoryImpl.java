package com.gerasimchuk.repositories;

import com.gerasimchuk.entities.Cargo;
import com.gerasimchuk.entities.Order;
import com.gerasimchuk.entities.Route;
import com.gerasimchuk.enums.CargoStatus;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Repository
@Transactional
public class CargoRepositoryImpl implements CargoRepository {

    private SessionFactory sessionFactory;

    @Autowired
    public CargoRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public Cargo create(String personalNumber, String name, double weight, CargoStatus status, Route route) {
        Cargo cargo = new Cargo(personalNumber,name,weight,status,route);
        sessionFactory.getCurrentSession().persist(cargo);
        return cargo;
    }

    @Transactional
    public Cargo update(int id, String personalNumber, String name, double weight, CargoStatus status, Route route) {
        Cargo updated = sessionFactory.getCurrentSession().get(Cargo.class, id);
        updated.setPersonalNumber(personalNumber);
        updated.setName(name);
        updated.setWeight(weight);
        updated.setStatus(status);
        updated.setRoute(route);
        sessionFactory.getCurrentSession().update(updated);
        return updated;
    }

    @Transactional
    public Cargo update(int id, String personalNumber, String name, double weight, CargoStatus status, Route route, Order order) {
        Cargo updated = sessionFactory.getCurrentSession().get(Cargo.class, id);
        updated.setPersonalNumber(personalNumber);
        updated.setName(name);
        updated.setWeight(weight);
        updated.setStatus(status);
        updated.setRoute(route);
        updated.setOrder(order);
        sessionFactory.getCurrentSession().update(updated);
        return updated;
    }

    @Transactional
    public Cargo getById(int id) {
        return sessionFactory.getCurrentSession().get(Cargo.class, id);
    }

    @Transactional
    public Collection<Cargo> getAll() {
        return sessionFactory.getCurrentSession().createQuery("from Cargos", Cargo.class).getResultList();
    }

    @Transactional
    public void remove(int id) {
        Cargo removed = sessionFactory.getCurrentSession().get(Cargo.class,id);
        sessionFactory.getCurrentSession().remove(removed);
    }
}
