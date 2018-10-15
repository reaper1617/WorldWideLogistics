package com.gerasimchuk.services.interfaces;

import com.gerasimchuk.dto.TruckDTO;
import com.gerasimchuk.entities.Truck;
import com.gerasimchuk.enums.UpdateMessageType;

import java.util.Collection;

/** Truck Service
 * @author Reaper
 * @version 1.0
 */

public interface TruckService {

    UpdateMessageType createTruck(TruckDTO truckDTO);
    UpdateMessageType updateTruck(TruckDTO truckDTO);
    UpdateMessageType deleteTruck(int id);

    Collection<Truck> getFreeTrucks();
}
