package com.gerasimchuk.services.interfaces;

import com.gerasimchuk.dto.TruckDTO;

/** Truck Service
 * @author Reaper
 * @version 1.0
 */

public interface TruckService {

    boolean createTruck(TruckDTO truckDTO);
    boolean updateTruck(TruckDTO truckDTO);
    boolean deleteTruck(int id);
}
