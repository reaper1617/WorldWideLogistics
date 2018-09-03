package com.gerasimchuk.controllers;

import com.gerasimchuk.entities.Cargo;
import com.gerasimchuk.entities.City;
import com.gerasimchuk.entities.Manager;
import com.gerasimchuk.entities.Route;
import com.gerasimchuk.enums.CargoStatus;
import com.gerasimchuk.enums.DriverStatus;
import com.gerasimchuk.enums.OrderStatus;
import com.gerasimchuk.enums.TruckState;
import com.gerasimchuk.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Collection;
import java.util.Random;

@Controller
public class MainController {


    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(MainController.class);


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        log.info("In method index of main controller");

        return "/login/login";
    }
}
