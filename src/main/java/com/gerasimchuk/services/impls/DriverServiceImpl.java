package com.gerasimchuk.services.impls;

import com.gerasimchuk.entities.Driver;
import com.gerasimchuk.enums.DriverStatus;
import com.gerasimchuk.services.interfaces.DriverService;
import org.springframework.stereotype.Service;

/** Implementation for {@link DriverService} interface
 * @author Reaper
 * @version 1.0
 */


@Service
public class DriverServiceImpl implements DriverService {
    private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(DriverServiceImpl.class);

    public DriverStatus getDriverStatusValFromString(String status) {
        LOGGER.info("Class: " + this.getClass().getName() + " method: getDriverStatusValFromString");
        if (status == null || status.length()==0) {
            LOGGER.error("Error: status string is null or empty.");
            return null;
        }
        DriverStatus result = null;
        if (status.equals("FREE")) result = DriverStatus.FREE;
        if (status.equals("DRIVING")) result = DriverStatus.DRIVING;
        if (status.equals("RESTING")) result = DriverStatus.RESTING;
        if (status.equals("SECOND_DRIVER")) result = DriverStatus.SECOND_DRIVER;
        if (status.equals("LOAD_UNLOAD_WORKS")) result = DriverStatus.LOAD_UNLOAD_WORKS;
        if (result != null){
            LOGGER.info("Driver status is " + result);
        }
        else {
            LOGGER.error("Error: driver status is null");
        }
        return result;
    }
}
