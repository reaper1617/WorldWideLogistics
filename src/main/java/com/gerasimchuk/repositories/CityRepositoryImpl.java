package com.gerasimchuk.repositories;

import com.gerasimchuk.entities.City;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/** Implementation of {@link CityRepository} interface
 * @author Reaper
 * @version 1.0
 */

@Repository
@Transactional
public class CityRepositoryImpl implements CityRepository {

    private SessionFactory sessionFactory;
    private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(CityRepositoryImpl.class);

    @Autowired
    public CityRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public City create(String name, boolean hasAgency) {
        LOGGER.info("Class: " + this.getClass().getName() + " method: create");
        City city = new City(name,hasAgency);
        sessionFactory.getCurrentSession().persist(city);
        LOGGER.info("Persisted city: " + city.getName());
        return city;
    }

    @Transactional
    public City update(int id, String name, boolean hasAgency) {
        LOGGER.info("Class: " + this.getClass().getName() + " method: update");
        City updated = sessionFactory.getCurrentSession().get(City.class,id);
        updated.setName(name);
        updated.setHasAgency(hasAgency);
        sessionFactory.getCurrentSession().update(updated);
        LOGGER.info("Updated city: " + updated.getName());
        return updated;
    }

    @Transactional
    public City getById(int id) {
        LOGGER.info("Class: " + this.getClass().getName() + " method: getById");
        City res = sessionFactory.getCurrentSession().get(City.class,id);
        LOGGER.info("Found city: " + res.getName());
        return res;
    }

    @Transactional
    public City getByName(String name) {
        LOGGER.info("Class: " + this.getClass().getName() + " method: getByName");
        Collection<City> cities = sessionFactory.getCurrentSession().createQuery("from Cities", City.class).getResultList();
        for(City c: cities){
            if (c.getName().equals(name)){
                LOGGER.info("Found city: " + c.getName());
                return c;
            }
        }
        LOGGER.info("City not found");
        return null;
    }

    @Transactional
    public Collection<City> getAll() {
        LOGGER.info("Class: " + this.getClass().getName() + " method: getAll");
        Collection<City> res = sessionFactory.getCurrentSession().createQuery("from Cities", City.class).getResultList();
        LOGGER.info("Found collection: " + res + ", size = " + res.size());
        return res;
    }

    @Transactional
    public void remove(int id) {
        LOGGER.info("Class: " + this.getClass().getName() + " method: remove");
        City removed = sessionFactory.getCurrentSession().get(City.class, id);
        sessionFactory.getCurrentSession().remove(removed);
        LOGGER.info("Removed city: " + removed.getName());
    }
}
