package com.gerasimchuk.services.interfaces;

import com.gerasimchuk.entities.Driver;
import com.gerasimchuk.enums.DriverStatus;

/** Driver Service
 * @author Reaper
 * @version 1.0
 */

public interface DriverService {

    DriverStatus getDriverStatusValFromString(String status);
    void updateDriverHoursWorked();

//    void testShedule();
}
