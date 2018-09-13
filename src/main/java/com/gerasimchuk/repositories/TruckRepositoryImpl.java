package com.gerasimchuk.repositories;

import com.gerasimchuk.entities.City;
import com.gerasimchuk.entities.Driver;
import com.gerasimchuk.entities.Order;
import com.gerasimchuk.entities.Truck;
import com.gerasimchuk.enums.TruckState;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Set;


@Repository
@Transactional
public class TruckRepositoryImpl implements TruckRepository {

    private SessionFactory sessionFactory;

    @Autowired
    public TruckRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public Truck create(String registrationNumber, int numOfDrivers, double capacity, TruckState state, City currentCity) {
        Truck truck = new Truck(registrationNumber,numOfDrivers,capacity,state,currentCity);
        sessionFactory.getCurrentSession().persist(truck);
        return truck;
    }

//    @Transactional
//    public Truck create(String registrationNumber, int numOfDrivers, double capacity, TruckState state, City currentCity, Set<Driver> driverInTruck, Order assignedOrder) {
//        Truck truck = new Truck(registrationNumber,numOfDrivers,capacity,state,currentCity,driverInTruck,assignedOrder);
//        sessionFactory.getCurrentSession().persist(truck);
//        return truck;
//    }


    @Transactional
    public Truck update(int id, String registrationNumber, int numOfDrivers, double capacity, TruckState state, City currentCity) {
        Truck updated = sessionFactory.getCurrentSession().get(Truck.class,id);
        updated.setRegistrationNumber(registrationNumber);
        updated.setNumOfDrivers(numOfDrivers);
        updated.setCapacity(capacity);
        updated.setState(state);
        updated.setCurrentCity(currentCity);
        sessionFactory.getCurrentSession().update(updated);
        return updated;
    }

    @Transactional
    public Truck getById(int id) {
        return sessionFactory.getCurrentSession().get(Truck.class,id);
    }

    @Transactional
    public Truck getByRegistrationNumber(String registrationNumber) {
        if (registrationNumber==null) return null;
        Collection<Truck> trucks = sessionFactory.getCurrentSession().createQuery("from Trucks", Truck.class).getResultList();
        for(Truck t: trucks){
            if (t.getRegistrationNumber().equals(registrationNumber)) return t;
        }
        return null;
    }

    @Transactional
    public Collection<Truck> getAll() {
        return sessionFactory.getCurrentSession().createQuery("from Trucks", Truck.class).getResultList();
    }

    @Transactional
    public void remove(int id) {
        Truck removed = sessionFactory.getCurrentSession().get(Truck.class,id);
        sessionFactory.getCurrentSession().remove(removed);
    }
}
