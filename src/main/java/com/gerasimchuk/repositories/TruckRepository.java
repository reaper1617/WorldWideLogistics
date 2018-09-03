package com.gerasimchuk.repositories;

import com.gerasimchuk.entities.City;
import com.gerasimchuk.entities.Truck;
import com.gerasimchuk.enums.TruckState;

import java.util.Collection;

public interface TruckRepository {
    Truck create(String registrationNumber, int numOfDrivers, double capacity, TruckState state, City currentCity);
    Truck update(int id,String registrationNumber, int numOfDrivers, double capacity, TruckState state, City currentCity);
    Truck getById(int id);
    Collection<Truck> getAll();
    void remove(int id);
}
