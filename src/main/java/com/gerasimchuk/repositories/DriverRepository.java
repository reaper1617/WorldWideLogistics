package com.gerasimchuk.repositories;

import com.gerasimchuk.entities.City;
import com.gerasimchuk.entities.Driver;
import com.gerasimchuk.entities.Truck;
import com.gerasimchuk.enums.DriverStatus;

import java.util.Collection;

public interface DriverRepository {
    Driver create(double hoursWorked, DriverStatus status, City currentCity, Truck currentTruck);
    Driver update(int id,double hoursWorked, DriverStatus status, City currentCity, Truck currentTruck);
    Driver getById(int id);
    Collection<Driver> getAll();
    void remove(int id);
}
