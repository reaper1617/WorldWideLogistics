package com.gerasimchuk.services.impls;

import com.gerasimchuk.services.interfaces.CargoService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.junit.jupiter.api.Assertions.*;



class CargoServiceImplTest {




    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(CargoServiceImplTest.class);

    @Test
    void createCargo() {
        log.info("in CreateCargo method test");
        assertTrue(true);
    }

    @Test
    void updateCargo() {
    }

    @Test
    void deleteCargo() {
    }

    @Test
    void getAvailableCargos() {
    }

    @Test
    void getCargoStatusFromString() {
    }
}