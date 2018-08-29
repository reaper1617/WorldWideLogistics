package com.gerasimchuk.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(MainController.class);

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        log.info("In method index of main controller");
        return "/login/login";
    }
}
