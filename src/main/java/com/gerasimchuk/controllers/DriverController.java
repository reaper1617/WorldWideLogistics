package com.gerasimchuk.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/** Driver Controller
 * @author Reaper
 * @version 1.0
 */

@Controller
public class DriverController {

    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(DriverController.class);


    @RequestMapping(value = "/drivermainpage", method = RequestMethod.GET)
    String driverMainPage(){
        log.info("Controller: DriverController, metod = driverMainPage,  action = \"/drivermainpage\", request = GET");
        return "/driver/drivermainpage";
    }
}
