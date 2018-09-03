package com.gerasimchuk.repositories;

import com.gerasimchuk.entities.City;
import com.gerasimchuk.entities.Driver;
import com.gerasimchuk.entities.Truck;
import com.gerasimchuk.enums.DriverStatus;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;


@Repository
@Transactional
public class DriverRepositoryImpl implements DriverRepository {

    private SessionFactory sessionFactory;

    @Autowired
    public DriverRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public Driver create(int hoursWorked, DriverStatus status, City currentCity, Truck currentTruck) {
        Driver driver = new Driver(hoursWorked,status,currentCity,currentTruck);
        sessionFactory.getCurrentSession().persist(driver);
        return driver;
    }

    @Transactional
    public Driver update(int id, int hoursWorked, DriverStatus status, City currentCity, Truck currentTruck) {
        Driver updated = sessionFactory.getCurrentSession().get(Driver.class,id);
        updated.setHoursWorked(hoursWorked);
        updated.setStatus(status);
        updated.setCurrentCity(currentCity);
        updated.setCurrentTruck(currentTruck);
        sessionFactory.getCurrentSession().update(updated);
        return updated;
    }

    @Transactional
    public Driver getById(int id) {
        return sessionFactory.getCurrentSession().get(Driver.class, id);
    }

    @Transactional
    public Collection<Driver> getAll() {
        return sessionFactory.getCurrentSession().createQuery("from Drivers", Driver.class).getResultList();
    }

    public void remove(int id) {
        Driver removed = sessionFactory.getCurrentSession().get(Driver.class,id);
        sessionFactory.getCurrentSession().remove(removed);
    }
}
