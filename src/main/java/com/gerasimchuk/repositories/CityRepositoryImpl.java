package com.gerasimchuk.repositories;

import com.gerasimchuk.entities.City;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Repository
@Transactional
public class CityRepositoryImpl implements CityRepository {

    private SessionFactory sessionFactory;

    @Autowired
    public CityRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public City create(String name, boolean hasAgency) {
        City city = new City(name,hasAgency);
        sessionFactory.getCurrentSession().persist(city);
        return city;
    }

    @Transactional
    public City update(int id, String name, boolean hasAgency) {
        City updated = sessionFactory.getCurrentSession().get(City.class,id);
        updated.setName(name);
        updated.setHasAgency(hasAgency);
        sessionFactory.getCurrentSession().update(updated);
        return updated;
    }

    @Transactional
    public City getById(int id) {
        return sessionFactory.getCurrentSession().get(City.class,id);
    }

    @Transactional
    public Collection<City> getAll() {
        return sessionFactory.getCurrentSession().createQuery("from Cities", City.class).getResultList();
    }

    @Transactional
    public void remove(int id) {
        City removed = sessionFactory.getCurrentSession().get(City.class, id);
        sessionFactory.getCurrentSession().remove(removed);
    }
}
