package com.gerasimchuk.services.impls;

import com.gerasimchuk.dto.TruckDTO;
import com.gerasimchuk.entities.City;
import com.gerasimchuk.entities.Driver;
import com.gerasimchuk.entities.Order;
import com.gerasimchuk.entities.Truck;
import com.gerasimchuk.enums.OrderStatus;
import com.gerasimchuk.enums.TruckState;
import com.gerasimchuk.repositories.*;
import com.gerasimchuk.services.interfaces.TruckService;
import com.gerasimchuk.services.interfaces.UserService;
import com.gerasimchuk.validators.DTOValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/** Implementation for {@link TruckService} interface
 * @author Reaper
 * @version 1.0
 */

@Service
public class TruckServiceImpl implements TruckService {

    private TruckRepository truckRepository;
    private CityRepository cityRepository;
    private DriverRepository driverRepository;
    private UserRepository userRepository;
    private OrderRepository orderRepository;

    private UserService userService;
    private DTOValidator dtoValidator;
    private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(TruckServiceImpl.class);

    @Autowired
    public TruckServiceImpl(TruckRepository truckRepository, CityRepository cityRepository, DriverRepository driverRepository, UserRepository userRepository, OrderRepository orderRepository, UserService userService, DTOValidator dtoValidator) {
        this.truckRepository = truckRepository;
        this.cityRepository = cityRepository;
        this.driverRepository = driverRepository;
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.userService = userService;
        this.dtoValidator = dtoValidator;
    }


    @Transactional
    public boolean createTruck(TruckDTO truckDTO) {
        LOGGER.info("Class: " + this.getClass().getName() + " method: createTruck");
        if (!dtoValidator.validate(truckDTO)) {
            LOGGER.error("Error: truckDTO is not valid.");
            return false;
        }
        int numOfDrivers =0;
        double capacity = 0;
        TruckState state = getTruckStateFromTruckDTO(truckDTO);
        City city = cityRepository.getByName(truckDTO.getCurrentCity());
        try{
            numOfDrivers = Integer.parseInt(truckDTO.getNumberOfDrivers());
            capacity = Double.parseDouble(truckDTO.getCapacity());
        }
        catch (Exception e){
            e.printStackTrace();
            LOGGER.error("Error: cannot parse number of drivers and capacity values.");
            return false;
        }
        Truck created = truckRepository.create(truckDTO.getRegistrationNumber(),numOfDrivers,capacity,state,city);
        LOGGER.info("Assigning drivers...");
        // if !null - assign drivers
        if (truckDTO.getAssignedDrivers()!=null){
            if (truckDTO.getAssignedDrivers().length > numOfDrivers) {
                LOGGER.error("Error: number of drivers to assign ("
                        + truckDTO.getAssignedDrivers().length
                        + ") is more than truck maximal number of drivers (" + numOfDrivers + ")");
                return false;
            }
            // assign drivers
            //assignedDrivers = new HashSet<Driver>();
            for(int i = 0; i < truckDTO.getAssignedDrivers().length; i++){
                int id = 0;
                try {
                    id = Integer.parseInt(truckDTO.getAssignedDrivers()[i]);
                }
                catch (Exception e){
                    e.printStackTrace();
                    LOGGER.warn("Error: cannot parse driver id.");
                }
                if (id != 0) {
                    Driver driver = userRepository.getById(id).getDriver();
                    LOGGER.info("Driver: " + driver.getUser().getPersonalNumber());
                    if (driver.getCurrentCity().getName().equals(created.getCurrentCity().getName())) {
                        driverRepository.update(driver.getId(), driver.getHoursWorked(), driver.getStatus(), driver.getCurrentCity(), created);
                    }
                    else {
                        LOGGER.warn("Error: driver " + driver.getUser().getPersonalNumber() + " is in " + driver.getCurrentCity().getName()
                        + " but truck " + created.getRegistrationNumber() + " is in " + created.getCurrentCity().getName()
                        + ". Driver doesn't assigned.");
                    }
                }
                else{
                    LOGGER.warn("Driver id value is not valid (id = 0).");
                }
            }
        }
        else{
            LOGGER.info("There are no drivers to assign.");
        }
        LOGGER.info("Truck " + created.getRegistrationNumber() + " created successfully");
        return true;
    }

    public boolean updateTruck(TruckDTO truckDTO) {
        LOGGER.info("Class: " + this.getClass().getName() + " method: updateTruck");
        if (!dtoValidator.validate(truckDTO)) {
            LOGGER.error("Error: truckDTO is not valid");
            return false;
        }
        int id = 0;
        try{
            id = Integer.parseInt(truckDTO.getId());
        }
        catch (Exception e){
            e.printStackTrace();
            LOGGER.info("Error: can not parse truck id.");
            return false;
        }
        Truck updated = truckRepository.getById(id);
        if (updated.getAssignedOrder() != null) {
            LOGGER.error("Error: can not update truck which has an assigned order.");
            return false;
        }
        boolean result = updateTruckWithFieldsFromDTO(updated,truckDTO);
        if (result){
            LOGGER.info("Truck " + updated.getRegistrationNumber() + " updated successfully.");
        }
        else {
            LOGGER.error("Error: failed to update truck.");
        }
        return result;
    }

    public boolean deleteTruck(int id) {
        LOGGER.info("Class: " + this.getClass().getName() + " method: deleteTruck");
        if (id <= 0) {
            LOGGER.error("Error: truck id value is not valid (id = " + id + ").");
            return false;
        }
        Truck deleted = truckRepository.getById(id);
        if (deleted == null) {
            LOGGER.error("Error: there is no truck with id = " + id + " in database.");
            return false;
        }
        Order order = deleted.getAssignedOrder();
        if (order != null) {
            if (order.getStatus().equals(OrderStatus.EXECUTING)
                    || order.getStatus().equals(OrderStatus.EXECUTED)) {
                LOGGER.error("Error: can not delete truck which has an assigned order with state = " + order.getStatus());
                return false;
            }
            orderRepository.update(order.getId(),
                    order.getPersonalNumber(),
                    order.getDescription(),
                    order.getDate(),
                    order.getStatus(),
                    null);
            LOGGER.info("Truck " + deleted.getRegistrationNumber() + " successfully unassigned from order " + order.getDescription());
        }
        LOGGER.info("Unassignin drivers ...");
        Collection<Driver> drivers = deleted.getDriversInTruck();
        if (drivers != null){
            for(Driver d: drivers){
                driverRepository.update(d.getId(),d.getHoursWorked(),d.getStatus(),d.getCurrentCity(),null);
                LOGGER.info("Driver:" + d.getUser().getPersonalNumber() + " successfully unassigned");
            }
        }
        LOGGER.info("All drivers unassigned.");
        truckRepository.remove(id);
        LOGGER.info("Truck " + deleted.getRegistrationNumber() + " successfully deleted.");
        return true;
    }


    public Collection<Truck> getFreeTrucks() {
        LOGGER.info("Class: " + this.getClass().getName() + " method: deleteTruck");
        Collection<Truck> trucks = truckRepository.getAll();
        Collection<Truck> freeTrucks = new ArrayList<Truck>();
        for(Truck t: trucks){
            LOGGER.info("Truck: " + t.getRegistrationNumber());
            if (!t.getState().equals(TruckState.READY)){
                LOGGER.warn("Truck " + t.getRegistrationNumber() + " has state " + t.getState() + ". Truck not added to freeTrucksCollection.");
            }
            if (t.getAssignedOrder() != null){
                LOGGER.warn("Truck " + t.getRegistrationNumber() + " has assigned order " + t.getAssignedOrder() + ". Truck not added to freeTrucksCollection.");
            }
            if (t.getDriversInTruck().size() == t.getNumOfDrivers()){
                LOGGER.warn("Truck " + t.getRegistrationNumber() + " has full set of drivers. Truck not added to freeTrucksCollection.");
            }
            if (t.getState().equals(TruckState.READY)
                    && t.getAssignedOrder() == null
                    && t.getDriversInTruck().size() < t.getNumOfDrivers()){
                freeTrucks.add(t);
            }
        }
        LOGGER.info("FreeTruckCollection: " + freeTrucks + ", size = " + freeTrucks.size());
        return freeTrucks;
    }

    // utils

    TruckState getTruckStateFromTruckDTO(TruckDTO truckDTO){
        LOGGER.info("Class: " + this.getClass().getName() + " method: getTruckStateFromTruckDTO");
        TruckState result = null;
        if (truckDTO == null) {
            LOGGER.error("Error: truckDTO is null.");
            return null;
        }
        if (truckDTO.getState().equals("Ready")) result = TruckState.READY;
        if (truckDTO.getState().equals("Not ready")) result = TruckState.NOT_READY;
        if (result!= null){
            LOGGER.info("Truck state is " + result);
        }
        else {
            LOGGER.error("Error: truck state is null");
        }
        return result;
    }

    // todo: mfk refactor!!!!
    private boolean updateTruckWithFieldsFromDTO(Truck updated, TruckDTO truckDTO){
        LOGGER.info("Class: " + this.getClass().getName() + " method: updateTruckWithFieldsFromDTO");
        String newRegistrationNumber = null;
        int newNumberOfDrivers = 0;
        double newCapacity = 0;
        TruckState newState = null;
        City newCurrentCity = null;
        if (truckDTO.getRegistrationNumber()!=null && truckDTO.getRegistrationNumber().length()!=0) newRegistrationNumber = truckDTO.getRegistrationNumber();
        else newRegistrationNumber = updated.getRegistrationNumber();
        LOGGER.info("New registration number: " + newRegistrationNumber);
        if (truckDTO.getNumberOfDrivers()!=null && truckDTO.getNumberOfDrivers().length()!=0){
            try {
                newNumberOfDrivers = Integer.parseInt(truckDTO.getNumberOfDrivers());
            }
            catch (Exception e){
                e.printStackTrace();
                LOGGER.error("Error: can not parse number of drivers.");
                return false;
            }
        }
        else newNumberOfDrivers = updated.getNumOfDrivers();
        LOGGER.info("New number of drivers: " + newNumberOfDrivers);
        if (truckDTO.getCapacity()!=null && truckDTO.getCapacity().length()!=0){
            try{
                newCapacity = Double.parseDouble(truckDTO.getCapacity());
            }
            catch (Exception e){
                e.printStackTrace();
                LOGGER.error("Error: can not parse capacity.");
                return false;
            }
        }
        else newCapacity = updated.getCapacity();
        LOGGER.info("New capacity: " + newCapacity);
        if (truckDTO.getState()!=null && truckDTO.getState().length()!=0) newState = getTruckStateFromTruckDTO(truckDTO);
        else newState = updated.getState();
        LOGGER.info("New state: " + newState);
        if (truckDTO.getCurrentCity()!=null && truckDTO.getCurrentCity().length()!=0 && !truckDTO.getCurrentCity().equals("Not selected") && !truckDTO.getCurrentCity().equals("No cities available")) newCurrentCity = cityRepository.getByName(truckDTO.getCurrentCity());
        else newCurrentCity = updated.getCurrentCity();
        LOGGER.info("New current city: " + newCurrentCity.getName());
        if (truckDTO.getAssignedDrivers()!=null
                && !truckDTO.getAssignedDrivers()[0].equals("Do nothing")
                && !truckDTO.getAssignedDrivers()[0].equals("No drivers available")){
            if (newNumberOfDrivers < truckDTO.getAssignedDrivers().length) {
                LOGGER.error("Error: new number of drivers is less than number of assigned drivers");
                return false;
            }

            String[] drivers = truckDTO.getAssignedDrivers();
            //  delete this truck from all drivers
            LOGGER.info("Unassigning drivers from truck...");
            Collection<Driver> oldAssignedDrivers = updated.getDriversInTruck();
            for(Driver d: oldAssignedDrivers){
                driverRepository.update(d.getId(),d.getHoursWorked(),d.getStatus(),d.getCurrentCity(),null);
                LOGGER.info("Driver " + d.getUser().getPersonalNumber() + " successfully unassigned from truck " + updated.getRegistrationNumber());
            }
            LOGGER.info("All drivers unassigned successfully.");
            if (!truckDTO.getAssignedDrivers()[0].equals("Unassign current drivers")) {
                for (int i = 0; i < drivers.length; i++) {
                    if (!drivers[i].equals("Do nothing")
                            && !drivers[i].equals("No drivers available")
                            && !drivers[i].equals("Unassign current drivers")) {
                        int id = 0;
                        try {
                            id = Integer.parseInt(drivers[i]);
                        } catch (Exception e) {
                            e.printStackTrace();
                            LOGGER.error("Error: can not parse driver id.");
                            return false;
                        }
                        Driver driver = userRepository.getById(id).getDriver();
                        if (driver.getCurrentCity().getName().equals(newCurrentCity.getName())) {
                            driverRepository.update(driver.getId(), driver.getHoursWorked(), driver.getStatus(), driver.getCurrentCity(), updated);
                            LOGGER.info("Driver " + driver.getUser().getPersonalNumber() + " successfully assigned to truck " + newRegistrationNumber);
                        }
                        else {
                            LOGGER.warn("Driver " + driver.getUser().getPersonalNumber() + " is in " + driver.getCurrentCity().getName()
                            + " but new current city is " + newCurrentCity.getName());
                        }
                    }
                }
            }
        }
        else {
            if (newNumberOfDrivers < updated.getDriversInTruck().size()) {
                LOGGER.error("Error: new number of drivers less than current number of drivers in truck.");
                return false;
            }
        }
        truckRepository.update(updated.getId(), newRegistrationNumber,newNumberOfDrivers, newCapacity, newState, newCurrentCity);
        LOGGER.info("Truck " + updated.getRegistrationNumber() + " updated successfully.");
        return true;
    }
}
