package com.gerasimchuk.services.impls;

import com.gerasimchuk.entities.Driver;
import com.gerasimchuk.entities.User;
import com.gerasimchuk.enums.DriverStatus;
import com.gerasimchuk.repositories.DriverRepository;
import com.gerasimchuk.repositories.UserRepository;
import com.gerasimchuk.services.interfaces.DriverService;
import com.gerasimchuk.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

/** Implementation for {@link DriverService} interface
 * @author Reaper
 * @version 1.0
 */


@Service
public class DriverServiceImpl implements DriverService {
    @Autowired
    private UserService userService;

    @Autowired
    private DriverRepository driverRepository;

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

    @Override
    public void updateDriverHoursWorked() {
        LOGGER.info("Class: " + this.getClass().getName() + " method: updateDriverHoursWorked");
        Collection<User> driversWithoutOrders = userService.getFreeDrivers();
        for(User user: driversWithoutOrders){
            Driver d = user.getDriver();
            driverRepository.update(d.getId(),0,d.getStatus(),d.getCurrentCity(),d.getCurrentTruck());
        }
        // todo: rabbitMQSender.sendMessage(messageConstructor.createMessage(UpdateMessageType.TRUCK_DELETED, statisticService));
        LOGGER.info("Class: " + this.getClass().getName() + " out from updateDriverHoursWorked method: all free drivers");
    }

//    @Override
//    public void testShedule() {
//        LOGGER.info("Sheduled! just for visibility of working sheduled method");
//    }


}
