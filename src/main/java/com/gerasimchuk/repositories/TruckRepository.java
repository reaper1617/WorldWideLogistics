package com.gerasimchuk.repositories;

import com.gerasimchuk.entities.City;
import com.gerasimchuk.entities.Driver;
import com.gerasimchuk.entities.Order;
import com.gerasimchuk.entities.Truck;
import com.gerasimchuk.enums.TruckState;

import java.util.Collection;
import java.util.Set;

public interface TruckRepository {
    Truck create(String registrationNumber,
                 int numOfDrivers,
                 double capacity,
                 TruckState state,
                 City currentCity);

//    Truck create(String registrationNumber,
//                 int numOfDrivers,
//                 double capacity,
//                 TruckState state,
//                 City currentCity,
//                 Set<Driver> driverInTruck,
//                 Order assignedOrder);

    Truck update(int id,String registrationNumber, int numOfDrivers, double capacity, TruckState state, City currentCity);
    Truck getById(int id);
    Truck getByRegistrationNumber(String registrationNumber);
    Collection<Truck> getAll();
    void remove(int id);


    // for statistics
    int getNumberOfTrucksTotal();
    int getNumberOfTrucksFree();
    int getNumberOfTrucksNotReady();
    int getNumberOfTrucksExecutingOrder();

}
