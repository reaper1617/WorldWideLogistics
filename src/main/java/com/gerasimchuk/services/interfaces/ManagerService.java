package com.gerasimchuk.services.interfaces;

import com.gerasimchuk.entities.*;

import java.util.Collection;

/** Manager Service
 * @author Reaper
 * @version 1.0
 */

public interface ManagerService {

    void addNewCargo(Cargo cargo);
    void changeCargo(Cargo cargo);
    void deleteCargo(Cargo cargo);

    void addNewDriver(User driver);
    void changeDriver(User driver);
    void deleteDriver(User driver);

    void addNewTruck(Truck truck);
    void changeTruck(Truck truck);
    void deleteTruck(Truck truck);

    void addNewOrder(Order order);
    Collection<Truck> getTrucksFitToOrder(Order order);
    void assignTruckToOrder(Order order, Truck truck);

    Collection<Driver> getDriversFitToTruckWithAssignedOrder(Order order);
    void assignDriversToTruck(Truck truck, Collection<Driver> drivers);


}
