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

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class TruckServiceImpl implements TruckService {

    private TruckRepository truckRepository;
    private CityRepository cityRepository;
    private DriverRepository driverRepository;
    private UserRepository userRepository;
    private OrderRepository orderRepository;

    private UserService userService;
    private DTOValidator dtoValidator;

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

    public boolean createTruck(TruckDTO truckDTO) {
        if (!dtoValidator.validate(truckDTO)) return false;
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
            return false;
        }
        Truck created = truckRepository.create(truckDTO.getRegistrationNumber(),numOfDrivers,capacity,state,city);

        // if !null - assign drivers
        if (truckDTO.getAssignedDrivers()!=null){
            if (truckDTO.getAssignedDrivers().length > numOfDrivers) return false;
            // assign drivers
            //assignedDrivers = new HashSet<Driver>();
            for(int i = 0; i < truckDTO.getAssignedDrivers().length; i++){
                int id = 0;
                try {
                    id = Integer.parseInt(truckDTO.getAssignedDrivers()[i]);
                }
                catch (Exception e){
                    e.printStackTrace();
                    return false;
                }
                if (id == 0) return false;
                Driver driver = userRepository.getById(id).getDriver();
                driverRepository.update(driver.getId(),driver.getHoursWorked(),driver.getStatus(),driver.getCurrentCity(),created);
            }
        }
//        if (truckDTO.getAssignedOrderId()!=null){
//            int id = 0;
//            try{
//                id = Integer.parseInt(truckDTO.getAssignedOrderId());
//            }
//            catch (Exception e){
//                e.printStackTrace();
//                return false;
//            }
//            if (id == 0) return false;
//            orderRepository.getById(id).setAssignedTruck(created);
//        }
        return true;
    }

    public boolean updateTruck(TruckDTO truckDTO) {
        if (!dtoValidator.validate(truckDTO)) return false;
        int id = 0;
        try{
            id = Integer.parseInt(truckDTO.getId());
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
        Truck updated = truckRepository.getById(id);
        return updateTruckWithFieldsFromDTO(updated,truckDTO);
    }

    public boolean deleteTruck(int id) {
        if (id <= 0) return false;
        Truck deleted = truckRepository.getById(id);
        if (deleted == null) return false;
        Order order = deleted.getAssignedOrder();
        if (order != null) {
            if (order.getStatus().equals(OrderStatus.EXECUTING)
                    || order.getStatus().equals(OrderStatus.EXECUTED)) return false;
            orderRepository.update(order.getId(),
                    order.getPersonalNumber(),
                    order.getDescription(),
                    order.getDate(),
                    order.getStatus(),
                    null);
        }
        Collection<Driver> drivers = deleted.getDriversInTruck();
        if (drivers != null){
            for(Driver d: drivers){
                driverRepository.update(d.getId(),d.getHoursWorked(),d.getStatus(),d.getCurrentCity(),null);
            }
        }
        truckRepository.remove(id);
        return true;
    }

    // utils

    TruckState getTruckStateFromTruckDTO(TruckDTO truckDTO){
        if (truckDTO == null) return TruckState.NOT_READY;
        if (truckDTO.getState().equals("Ready")) return TruckState.READY;
        if (truckDTO.getState().equals("Not ready")) return TruckState.NOT_READY;
        return TruckState.NOT_READY;
    }

    // todo: mfk refactor!!!!
    private boolean updateTruckWithFieldsFromDTO(Truck updated, TruckDTO truckDTO){
        String newRegistrationNumber = null;
        int newNumberOfDrivers = 0;
        double newCapacity = 0;
        TruckState newState = null;
        City newCurrentCity = null;
        if (truckDTO.getRegistrationNumber()!=null && truckDTO.getRegistrationNumber().length()!=0) newRegistrationNumber = truckDTO.getRegistrationNumber();
        else newRegistrationNumber = updated.getRegistrationNumber();
        if (truckDTO.getNumberOfDrivers()!=null && truckDTO.getNumberOfDrivers().length()!=0){
            try {
                newNumberOfDrivers = Integer.parseInt(truckDTO.getNumberOfDrivers());
            }
            catch (Exception e){
                e.printStackTrace();
                return false;
            }
        }
        else newNumberOfDrivers = updated.getNumOfDrivers();
        if (truckDTO.getCapacity()!=null && truckDTO.getCapacity().length()!=0){
            try{
                newCapacity = Double.parseDouble(truckDTO.getCapacity());
            }
            catch (Exception e){
                e.printStackTrace();
                return false;
            }
        }
        else newCapacity = updated.getCapacity();
        if (truckDTO.getState()!=null && truckDTO.getState().length()!=0) newState = getTruckStateFromTruckDTO(truckDTO);
        else newState = updated.getState();
        if (truckDTO.getCurrentCity()!=null && truckDTO.getCurrentCity().length()!=0 && !truckDTO.getCurrentCity().equals("Not selected") && !truckDTO.getCurrentCity().equals("No cities available")) newCurrentCity = cityRepository.getByName(truckDTO.getCurrentCity());
        else newCurrentCity = updated.getCurrentCity();
        if (truckDTO.getAssignedDrivers()!=null
                && !truckDTO.getAssignedDrivers()[0].equals("Do nothing")
                && !truckDTO.getAssignedDrivers()[0].equals("No drivers available")){
            if (newNumberOfDrivers < truckDTO.getAssignedDrivers().length) return false;

            String[] drivers = truckDTO.getAssignedDrivers();
            //  delete this truck from all drivers
            Collection<Driver> oldAssignedDrivers = updated.getDriversInTruck();
            for(Driver d: oldAssignedDrivers){
                driverRepository.update(d.getId(),d.getHoursWorked(),d.getStatus(),d.getCurrentCity(),null);
            }
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
                            return false;
                        }
                        Driver driver = userRepository.getById(id).getDriver();
                        driverRepository.update(driver.getId(), driver.getHoursWorked(), driver.getStatus(), driver.getCurrentCity(), updated);
                    }
                }
            }
        }
        else {
            if (newNumberOfDrivers < updated.getDriversInTruck().size()) return false;
        }
        truckRepository.update(updated.getId(), newRegistrationNumber,newNumberOfDrivers, newCapacity, newState, newCurrentCity);
        return true;
    }
}
