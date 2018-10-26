package com.gerasimchuk.services.interfaces;

import com.gerasimchuk.entities.Driver;
import com.gerasimchuk.enums.DriverStatus;
import com.gerasimchuk.enums.UpdateMessageType;
import com.gerasimchuk.utils.ReturnValuesContainer;

/** Driver Service
 * @author Reaper
 * @version 1.0
 */

public interface DriverService {

    DriverStatus getDriverStatusValFromString(String status);
    void updateDriverHoursWorked();

    UpdateMessageType updateDriverStatus(int driverId, DriverStatus newStatus);
//    ReturnValuesContainer<DriverStatus> updateDriverStatus(int driverId, DriverStatus newStatus, int val);
//    void testShedule();
}
