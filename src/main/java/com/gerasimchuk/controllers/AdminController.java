package com.gerasimchuk.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/** Admin Controller
 * @author Reaper
 * @version 1.0
 */

@Controller
public class AdminController {

    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(AdminController.class);


    @RequestMapping(value = "/adminmainpage", method = RequestMethod.GET)
    String adminMainPage(){
        log.info("Controller: AdminController, metod = adminMainPage,  action = \"/adminmainpage\", request = GET");
        return "/admin/adminmainpage";
    }

}
