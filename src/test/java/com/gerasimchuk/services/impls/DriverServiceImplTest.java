package com.gerasimchuk.services.impls;

import com.gerasimchuk.entities.Driver;
import com.gerasimchuk.enums.DriverStatus;
import com.gerasimchuk.services.interfaces.DriverService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:**/testConf.xml"})
@WebAppConfiguration
public class DriverServiceImplTest {


    @Autowired
    private DriverService driverService;

    @Test
    public void getDriverStatusValFromStringFree() {
        String status = "FREE";
        DriverStatus result = driverService.getDriverStatusValFromString(status);
        assertEquals(result,DriverStatus.FREE);
    }

    @Test
    public void getDriverStatusValFromStringDriving() {
        String status = "DRIVING";
        DriverStatus result = driverService.getDriverStatusValFromString(status);
        assertEquals(result,DriverStatus.DRIVING);
    }

    @Test
    public void getDriverStatusValFromStringResting() {
        String status = "RESTING";
        DriverStatus result = driverService.getDriverStatusValFromString(status);
        assertEquals(result,DriverStatus.RESTING);
    }

    @Test
    public void getDriverStatusValFromStringSecondDriver() {
        String status = "SECOND_DRIVER";
        DriverStatus result = driverService.getDriverStatusValFromString(status);
        assertEquals(result,DriverStatus.SECOND_DRIVER);
    }

    @Test
    public void getDriverStatusValFromStringLoadUnloadWorks() {
        String status = "LOAD_UNLOAD_WORKS";
        DriverStatus result = driverService.getDriverStatusValFromString(status);
        assertEquals(result,DriverStatus.LOAD_UNLOAD_WORKS);
    }

    @Test
    public void getDriverStatusValFromNullString() {
        String status = null;
        DriverStatus result = driverService.getDriverStatusValFromString(status);
        assertNull(result);
    }
}