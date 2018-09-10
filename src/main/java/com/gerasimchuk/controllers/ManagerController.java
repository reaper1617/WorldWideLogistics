package com.gerasimchuk.controllers;


import com.gerasimchuk.dto.CargoDTO;
import com.gerasimchuk.entities.Cargo;
import com.gerasimchuk.entities.City;
import com.gerasimchuk.repositories.CargoRepository;
import com.gerasimchuk.repositories.CityRepository;
import com.gerasimchuk.services.interfaces.CargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/** Manager Controller
 * @author Reaper
 * @version 1.0
 */

@Controller
public class ManagerController {

    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(ManagerController.class);

    private CargoRepository cargoRepository;
    private CityRepository cityRepository;

    private CargoService cargoService;

    @Autowired
    public ManagerController(CargoRepository cargoRepository, CityRepository cityRepository, CargoService cargoService) {
        this.cargoRepository = cargoRepository;
        this.cityRepository = cityRepository;
        this.cargoService = cargoService;
    }

    @RequestMapping(value = "/managermainpage", method = RequestMethod.GET)
    String managerMainPage(Model ui){
        log.info("Controller: ManagerController, metod = managerMainPage,  action = \"/managermainpage\", request = GET");
        Collection<Cargo> cargos = cargoRepository.getAll();
        // todo: driverList
        // todo: truckList
        // todo: ??? driversInTruckList??
        // todo: routePoints !!
        ui.addAttribute("cargoList", cargos);
        return "/manager/managermainpage";
    }

    @RequestMapping(value = "/addnewcargopage", method = RequestMethod.GET)
    String addNewCargoPage(Model ui){
        log.info("Controller: ManagerController, metod = addNewCargoPage,  action = \"/addnewcargopage\", request = GET");
        Collection<City> citiesList = cityRepository.getAll();
        ui.addAttribute("citiesList",citiesList);
        return "/manager/addnewcargopage";
    }

    @RequestMapping(value = "/addnewcargopage", method = RequestMethod.POST)
    String addNewCargoPagePost(CargoDTO cargoDTO, BindingResult bindingResult, Model ui){
        log.info("Controller: ManagerController, metod = addNewCargoPage,  action = \"/addnewcargopage\", request = POST");

        boolean result = cargoService.createCargo(cargoDTO);
        if (result){
            ui.addAttribute("actionSuccess","New cargo successfully added!");
            return "success";
        }
        else {
            ui.addAttribute("actionFailed","Error while trying to add cargo!");
            return "failure";
        }
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

    @RequestMapping(value = "/success", method = RequestMethod.GET)
    String success(Model ui){
        return "success";
    }

    @RequestMapping(value = "/failure", method = RequestMethod.GET)
    String failure(Model ui){
        return "failure";
    }

}
