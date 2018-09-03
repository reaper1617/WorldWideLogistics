package com.gerasimchuk.repositories;

import com.gerasimchuk.entities.City;
import com.gerasimchuk.entities.Route;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;


@Repository
@Transactional
public class RouteRepositoryImpl implements RouteRepository {

    private SessionFactory sessionFactory;

    @Autowired
    public RouteRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public Route create(City cityFrom, City cityTo, double distance) {
        Route route = new Route(cityFrom,cityTo,distance);
        sessionFactory.getCurrentSession().persist(route);
        return route;
    }

    @Transactional
    public Route update(int id, City cityFrom, City cityTo, double distance) {
        Route updated = sessionFactory.getCurrentSession().get(Route.class,id);
        updated.setCityFrom(cityFrom);
        updated.setCityTo(cityTo);
        updated.setDistance(distance);
        sessionFactory.getCurrentSession().update(updated);
        return updated;
    }

    @Transactional
    public Route getById(int id) {
        return sessionFactory.getCurrentSession().get(Route.class, id);
    }

    public Collection<Route> getAll() {
        return sessionFactory.getCurrentSession().createQuery("from Routes", Route.class).getResultList();
    }

    @Transactional
    public void remove(int id) {
        Route removed = sessionFactory.getCurrentSession().get(Route.class,id);
        sessionFactory.getCurrentSession().remove(removed);
    }
}
