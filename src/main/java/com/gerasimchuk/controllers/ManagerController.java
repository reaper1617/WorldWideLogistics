package com.gerasimchuk.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/** Manager Controller
 * @author Reaper
 * @version 1.0
 */

@Controller
public class ManagerController {

    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(ManagerController.class);

    @RequestMapping(value = "/managermainpage", method = RequestMethod.GET)
    String managerMainPage(){
        log.info("Controller: ManagerController, metod = managerMainPage,  action = \"/managermainpage\", request = GET");
        return "/manager/managermainpage";
    }

    @RequestMapping(value = "/addnewcargopage", method = RequestMethod.GET)
    String addNewCargoPage(){
        log.info("Controller: ManagerController, metod = addNewCargoPage,  action = \"/addnewcargopage\", request = GET");
        return "/manager/addnewcargopage";
    }

    @RequestMapping(value = "/addnewdriverpage", method = RequestMethod.GET)
    String addNewDriverPage(){
        log.info("Controller: ManagerController, metod = addNewDriverPage,  action = \"/addnewdriverpage\", request = GET");
        return "/manager/addnewdriverpage";
    }

    @RequestMapping(value = "/addneworderpage", method = RequestMethod.GET)
    String addNewOrderPage(){
        log.info("Controller: ManagerController, metod = addNewOrderPage,  action = \"/addneworderpage\", request = GET");
        return "/manager/addneworderpage";
    }

    @RequestMapping(value = "/addnewtruckpage", method = RequestMethod.GET)
    String addNewTruckPage(){
        log.info("Controller: ManagerController, metod = addNewTruckPage,  action = \"/addnewtruckpage\", request = GET");
        return "/manager/addnewtruckpage";
    }

    @RequestMapping(value = "/cargochangepage", method = RequestMethod.GET)
    String cargoChangePage(){
        log.info("Controller: ManagerController, metod = cargoChangePage,  action = \"/cargochangepage\", request = GET");
        return "/manager/cargochangepage";
    }

    @RequestMapping(value = "/driverchangepage", method = RequestMethod.GET)
    String driverChangePage(){
        log.info("Controller: ManagerController, metod = driverChangePage,  action = \"/driverchangepage\", request = GET");
        return "/manager/driverchangepage";
    }

    @RequestMapping(value = "/truckchangepage", method = RequestMethod.GET)
    String truckChangePage(){
        log.info("Controller: ManagerController, metod = truckChangePage,  action = \"/truckchangepage\", request = GET");
        return "/manager/truckchangepage";
    }

}
