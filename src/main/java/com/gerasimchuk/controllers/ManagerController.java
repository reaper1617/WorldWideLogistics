package com.gerasimchuk.controllers;


import com.gerasimchuk.dto.CargoDTO;
import com.gerasimchuk.dto.DriverDTO;
import com.gerasimchuk.dto.IdDTO;
import com.gerasimchuk.dto.TruckDTO;
import com.gerasimchuk.entities.Cargo;
import com.gerasimchuk.entities.City;
import com.gerasimchuk.entities.Truck;
import com.gerasimchuk.entities.User;
import com.gerasimchuk.repositories.CargoRepository;
import com.gerasimchuk.repositories.CityRepository;
import com.gerasimchuk.repositories.TruckRepository;
import com.gerasimchuk.repositories.UserRepository;
import com.gerasimchuk.services.interfaces.CargoService;
import com.gerasimchuk.services.interfaces.TruckService;
import com.gerasimchuk.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
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
    private TruckRepository truckRepository;
    private UserRepository userRepository;

    private CargoService cargoService;
    private UserService userService;
    private TruckService truckService;

    @Autowired
    public ManagerController(CargoRepository cargoRepository, CityRepository cityRepository, TruckRepository truckRepository, UserRepository userRepository, CargoService cargoService, UserService userService, TruckService truckService) {
        this.cargoRepository = cargoRepository;
        this.cityRepository = cityRepository;
        this.truckRepository = truckRepository;
        this.userRepository = userRepository;
        this.cargoService = cargoService;
        this.userService = userService;
        this.truckService = truckService;
    }

    @RequestMapping(value = "/managermainpage", method = RequestMethod.GET)
    String managerMainPage(Model ui){
//        if (id == 0 || id == 1 || id == 2){
//            return "/manager/managermainpage";
//        }
        log.info("Controller: ManagerController, metod = managerMainPage,  action = \"/managermainpage\", request = GET");
        Collection<Cargo> cargos = cargoRepository.getAll();
        Collection<User> drivers = userService.getAllDrivers();
        Collection<Truck> trucks = truckRepository.getAll();
        // todo: driverList
        // todo: truckList
        // todo: ??? driversInTruckList??
        // todo: routePoints !!
        ui.addAttribute("cargoList", cargos);
        ui.addAttribute("driversList", drivers);
        ui.addAttribute("trucksList", trucks);
        return "/manager/managermainpage";
    }

    @RequestMapping(value = "/managermainpage/{id}", method = RequestMethod.POST)
    String managerMainPagePost(@PathVariable("id") int action, IdDTO idDTO, BindingResult bindingResult, Model ui){
        log.info("Controller: ManagerController, metod = managerMainPage,  action = \"/managermainpage\", request = POST");
        if (idDTO == null){
            log.error("Error: Id Data Transfer Object is not valid");
            ui.addAttribute("actionFailed","Error while trying to make changes!");
            return "failure";
        }
        int id = Integer.parseInt(idDTO.getId());
        if (id == 0) {
            log.error("Error: Id in Data Transfer Object is zero");
            ui.addAttribute("actionFailed", "Error while trying to make changes!");
            return "failure";
        }
        if (action == 0) {
            log.info("Trying to change cargo with id = " + idDTO.getId());
            ui.addAttribute("updatedCargoId", id);
            Collection<City> citiesList = cityRepository.getAll();
            ui.addAttribute("citiesList", citiesList);
            return "/manager/cargochangepage";
        }
        if (action == 1){
            // driver change page
            log.info("Trying to change driver with id = " + idDTO.getId());
            Collection<City> citiesList = cityRepository.getAll();
            Collection<Truck> trucksList = truckRepository.getAll(); // todo: get only trucks that fit
            ui.addAttribute("citiesList", citiesList);
            ui.addAttribute("trucksList", trucksList);
            ui.addAttribute("updatedDriverId", id);
            return "/manager/driverchangepage";
        }
        if (action == 2){
            // truck change page
            Truck truck = truckRepository.getById(id);
            Collection<City> cities = cityRepository.getAll();
            Collection<User> drivers = userService.getAllDrivers();
            ui.addAttribute("updatedTruckId", id);
            ui.addAttribute("updatedTruck", truck);
            ui.addAttribute("citiesList", cities);
            ui.addAttribute("driversList", drivers);
            return "/manager/truckchangepage";
        }
        return "failure";
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
            log.info("New cargo successfully added!");
            ui.addAttribute("actionSuccess","New cargo successfully added!");
            return "success";
        }
        else {
            log.error("Error: createCargo method in CargoService returned false.");
            ui.addAttribute("actionFailed","Error while trying to add new cargo!");
            return "failure";
        }
    }



    @RequestMapping(value = "/addnewdriverpage", method = RequestMethod.GET)
    String addNewDriverPage(Model ui){
        log.info("Controller: ManagerController, metod = addNewDriverPage,  action = \"/addnewdriverpage\", request = GET");

        Collection<City> citiesList = cityRepository.getAll();
        Collection<Truck> trucksList = truckRepository.getAll(); // todo: get only trucks that fit
        ui.addAttribute("citiesList", citiesList);
        ui.addAttribute("trucksList", trucksList);
        return "/manager/addnewdriverpage";
    }

    @RequestMapping(value = "/addnewdriverpage", method = RequestMethod.POST)
    String addNewDriverPagePost(DriverDTO driverDTO, BindingResult bindingResult, Model ui){
        log.info("Controller: ManagerController, metod = addNewDriverPage,  action = \"/addnewdriverpage\", request = POST");
        if (driverDTO == null){
            log.error("Error: Driver Data Transfer Object is not valid!");
            ui.addAttribute("actionFailed", "Error while trying to create driver!");
            return "failure";
        }

        boolean result = userService.createDriver(driverDTO);
        if (result){
            log.info("New driver successfully created!");
            ui.addAttribute("actionSuccess", "New driver successfully created!");
            return "success";
        }
        else {
            log.error("Error: createDriver method in UserService returned false.");
            ui.addAttribute("actionFailed", "Error while trying to create driver.");
            return "failure";
        }

//        Collection<City> citiesList = cityRepository.getAll();
//        Collection<Truck> trucksList = truckRepository.getAll(); // todo: get only trucks that fit
//        ui.addAttribute("citiesList", citiesList);
//        ui.addAttribute("trucksList", trucksList);

    }

    @RequestMapping(value = "/addneworderpage", method = RequestMethod.GET)
    String addNewOrderPage(){
        log.info("Controller: ManagerController, metod = addNewOrderPage,  action = \"/addneworderpage\", request = GET");
        return "/manager/addneworderpage";
    }

    @RequestMapping(value = "/addnewtruckpage", method = RequestMethod.GET)
    String addNewTruckPage(Model ui){
        log.info("Controller: ManagerController, metod = addNewTruckPage,  action = \"/addnewtruckpage\", request = GET");
        Collection<City> citiesList = cityRepository.getAll(); // todo: get available !! (may not has agency)
        ui.addAttribute("citiesList",citiesList);
        Collection<User> freeDrivers = userService.getFreeDrivers();
        ui.addAttribute("freeDrivers", freeDrivers);
        return "/manager/addnewtruckpage";
    }

    @RequestMapping(value = "/addnewtruckpage", method = RequestMethod.POST)
    String addNewTruckPagePost(TruckDTO truckDTO, BindingResult bindingResult, Model ui){
        log.info("Controller: ManagerController, metod = addNewTruckPage,  action = \"/addnewtruckpage\", request = POST");
//        Collection<City> citiesList = cityRepository.getAll(); // todo: get available !! (may not has agency)
//        ui.addAttribute("citiesList",citiesList);
//        Collection<User> freeDrivers = userService.getFreeDrivers();
//        ui.addAttribute("freeDrivers", freeDrivers);

        if(truckDTO == null){
            log.error("Error: Truck Data Transfer Object is not valid!");
            ui.addAttribute("actionFailed", "Error while trying to create truck!");
            return "failure";
        }
        boolean result = truckService.createTruck(truckDTO);
        if (result){
            log.info("New truck successfully created!");
            ui.addAttribute("actionSuccess", "New truck successfully created!");
            return "success";
        }
        else {
            log.error("Error: createTruck method in TruckService returned false.");
            ui.addAttribute("actionFailed", "Error while trying to create truck.");
            return "failure";
        }
    }

    @RequestMapping(value = "/cargochangepage", method = RequestMethod.GET)
    String cargoChangePage(Model ui){
        log.info("Controller: ManagerController, metod = cargoChangePage,  action = \"/cargochangepage\", request = GET");
        Collection<City> citiesList = cityRepository.getAll();
        ui.addAttribute("citiesList", citiesList);
        return "/manager/cargochangepage";
    }

    @RequestMapping(value = "/cargochangepage", method = RequestMethod.POST)
    String cargoChangePagePost(CargoDTO cargoDTO, BindingResult bindingResult, Model ui){
        log.info("Controller: ManagerController, metod = cargoChangePage,  action = \"/cargochangepage\", request = POST");
        if (cargoDTO == null){
            log.error("Error: Id Data Transfer Object is not valid");
            ui.addAttribute("actionFailed", "Error while trying to update cargo!");
            return "failure";
        }
        boolean result = cargoService.updateCargo(cargoDTO);
        if (result){
            log.info("Cargo updated successfully");
            ui.addAttribute("actionSuccess", "Cargo updated successfully!");
            return "success";
        }
        else {
            log.error("Error: updateCargo method in CargoService returned false");
            ui.addAttribute("actionFailed", "Error while trying to update cargo!");
            return "failure";
        }
    }

    @RequestMapping(value = "/driverchangepage", method = RequestMethod.GET)
    String driverChangePage(Model ui){
        log.info("Controller: ManagerController, metod = driverChangePage,  action = \"/driverchangepage\", request = GET");
        Collection<City> citiesList = cityRepository.getAll();
        Collection<Truck> trucksList = truckRepository.getAll(); // todo: get only trucks that fit
        ui.addAttribute("citiesList", citiesList);
        ui.addAttribute("trucksList", trucksList);
        return "/manager/driverchangepage";
    }

    @RequestMapping(value = "/driverchangepage", method = RequestMethod.POST)
    String driverChangePagePost(DriverDTO driverDTO, BindingResult bindingResult, Model ui){
        log.info("Controller: ManagerController, metod = driverChangePage,  action = \"/driverchangepage\", request = POST");
//        Collection<City> citiesList = cityRepository.getAll();
//        Collection<Truck> trucksList = truckRepository.getAll(); // todo: get only trucks that fit
//        ui.addAttribute("citiesList", citiesList);
//        ui.addAttribute("trucksList", trucksList);

        if (driverDTO == null){
            log.error("Error: Driver Data Transfer Object is not valid");
            ui.addAttribute("actionFailed", "Error while trying to update driver!");
            return "failure";
        }
        boolean result = userService.updateDriver(driverDTO);
        if (result){
            log.info("Driver updated successfully");
            ui.addAttribute("actionSuccess", "Driver updated successfully!");
            return "success";
        }
        else {
            log.error("Error: updateDriver method in UserService returned false");
            ui.addAttribute("actionFailed", "Error while trying to update driver!");
            return "failure";
        }
    }



    @RequestMapping(value = "/truckchangepage", method = RequestMethod.GET)
    String truckChangePage(Model ui){
        log.info("Controller: ManagerController, metod = truckChangePage,  action = \"/truckchangepage\", request = GET");
        Collection<City> cities = cityRepository.getAll();
        Collection<User> freeDrivers = userService.getAllDrivers();
        ui.addAttribute("citiesList", cities);
        ui.addAttribute("driversList", freeDrivers);
        return "/manager/truckchangepage";
    }

    @RequestMapping(value = "/truckchangepage", method = RequestMethod.POST)
    String truckChangePagePost(TruckDTO truckDTO, BindingResult bindingResult,Model ui){
        log.info("Controller: ManagerController, metod = truckChangePage,  action = \"/truckchangepage\", request = POST");
        if (truckDTO == null){
            log.error("Error: Truck Data Transfer Object is not valid");
            ui.addAttribute("actionFailed", "Error while trying to update truck!");
        }
        boolean result = truckService.updateTruck(truckDTO);
        if (result){
            log.info("Truck updated successfully");
            ui.addAttribute("actionSuccess", "Truck updated successfully!");
            return "success";
        }
        else {
            log.error("Error: updateTruck method in TruckService returned false");
            ui.addAttribute("actionFailed", "Error while trying to update truck!");
            return "failure";
        }
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
