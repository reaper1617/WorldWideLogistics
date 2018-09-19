package com.gerasimchuk.services.impls;

import com.gerasimchuk.entities.Driver;
import com.gerasimchuk.enums.DriverStatus;
import com.gerasimchuk.services.interfaces.DriverService;
import org.springframework.stereotype.Service;

@Service
public class DriverServiceImpl implements DriverService {


    public DriverStatus getDriverStatusValFromString(String status) {
        if (status == null || status.length()==0) return null;
        if (status.equals("FREE")) return DriverStatus.FREE;
        if (status.equals("DRIVING")) return DriverStatus.DRIVING;
        if (status.equals("RESTING")) return DriverStatus.RESTING;
        if (status.equals("SECOND_DRIVER")) return DriverStatus.SECOND_DRIVER;
        if (status.equals("LOAD_UNLOAD_WORKS")) return DriverStatus.LOAD_UNLOAD_WORKS;
        return null;
    }
}
