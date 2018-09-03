package com.gerasimchuk.controllers;

import com.gerasimchuk.entities.Manager;
import com.gerasimchuk.repositories.CityRepository;
import com.gerasimchuk.repositories.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

    private CityRepository cityRepository;
    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(MainController.class);

    @Autowired
    public MainController(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {

        log.info("In method index of main controller");

        return "/login/login";
    }
}
