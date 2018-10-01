package com.gerasimchuk.controllers;


import com.gerasimchuk.converters.OrderToDTOConverter;
import com.gerasimchuk.dto.*;
import com.gerasimchuk.entities.*;
import com.gerasimchuk.exceptions.routeexceptions.RouteException;
import com.gerasimchuk.repositories.*;
import com.gerasimchuk.services.interfaces.CargoService;
import com.gerasimchuk.services.interfaces.OrderService;
import com.gerasimchuk.services.interfaces.TruckService;
import com.gerasimchuk.services.interfaces.UserService;
import com.gerasimchuk.utils.OrderWithRoute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.*;

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
    public OrderRepository orderRepository;

    private CargoService cargoService;
    private UserService userService;
    private TruckService truckService;
    private OrderService orderService;

    @Autowired
    public ManagerController(CargoRepository cargoRepository, CityRepository cityRepository, TruckRepository truckRepository, UserRepository userRepository, OrderRepository orderRepository, CargoService cargoService, UserService userService, TruckService truckService, OrderService orderService) {
        this.cargoRepository = cargoRepository;
        this.cityRepository = cityRepository;
        this.truckRepository = truckRepository;
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.cargoService = cargoService;
        this.userService = userService;
        this.truckService = truckService;
        this.orderService = orderService;
    }

    @RequestMapping(value = "/managermainpage/{id}", method = RequestMethod.GET)
    String managerMainPage(@PathVariable("id") int id, Model ui){
//        if (id == 0 || id == 1 || id == 2){
//            return "/manager/managermainpage";
//        }
        log.info("Controller: ManagerController, metod = managerMainPage,  action = \"/managermainpage\", request = GET");
        //
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String personalNumber = authentication.getName();
        log.info("Authenticated user personal number:" + personalNumber);
        User loggedUser = userRepository.getByPersonalNumber(personalNumber);
        if (loggedUser == null){
            log.error("Error: logged user not found!");
            ui.addAttribute("actionFailed", "Error: logged user not found!");
            return "failure";
        }
        if (loggedUser.getManager() == null) {
            log.error("Error: access violation - user is not a manager");
            ui.addAttribute("actionFailed","Error: access violation - user is not a manager");
            return "failure";
        }

        //

        Collection<Cargo> cargos = cargoRepository.getAll();
        Collection<User> drivers = userService.getAllDrivers();
        Collection<Truck> trucks = truckRepository.getAll();
        Collection<Order> orders = orderRepository.getAll();
        //Map<Order, Collection<City>> routes = orderService.getRoutes(orders);
        //Collection<City> routePoints = orderService.getO
        // todo: routePoints !!
        List<OrderWithRoute> ordersWithRoutes = new ArrayList<OrderWithRoute>();
        for(Order o: orders){
            try {
                List<City> cities = (List<City>) orderService.getOrderRoute(OrderToDTOConverter.convert(o), null);
                if (o.getAssignedTruck() != null) cities.add(0,o.getAssignedTruck().getCurrentCity());
                ordersWithRoutes.add(new OrderWithRoute(o, cities));
            }
            catch (RouteException e){
                log.error("Error: " + e.getMessage());
                ui.addAttribute("actionFailed","Error: " + e.getMessage());
                return "failure";
            }
        }
        ui.addAttribute("cargoList", cargos);
        ui.addAttribute("driversList", drivers);
        ui.addAttribute("trucksList", trucks);
        ui.addAttribute("ordersList", ordersWithRoutes);
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
        if (action == 3){
            // order change
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
    String addNewOrderPage(Model ui){
        log.info("Controller: ManagerController, metod = addNewOrderPage,  action = \"/addneworderpage\", request = GET");
        Collection<Cargo> availableCargos = cargoService.getAvailableCargos();
        ui.addAttribute("availableCargos", availableCargos);

        return "/manager/addneworderpage";
    }

    @RequestMapping(value = "/addneworderpage", method = RequestMethod.POST)
    String addNewOrderPagePost(OrderDTO orderDTO, BindingResult bindingResult, Model ui){
        log.info("Controller: ManagerController, metod = addNewOrderPage,  action = \"/addneworderpage\", request = POST");
        if(orderDTO == null){
            log.error("Error: Order Data Transfer Object is not valid!");
            ui.addAttribute("actionFailed", "Error while trying to create order!");
            return "failure";
        }
//        if (orderDTO.getDescription()!=null && orderDTO.getDescription().length()!=0){
//            ui.addAttribute("orderDescription", orderDTO.getDescription());
//        }
//        else {
//            log.error("Error: order description data is empty.");
//            ui.addAttribute("actionFailed", "Error while trying to create order.");
//            return "failure";
//        }
        if (orderDTO.getCargosInOrder()!=null){
            Collection<Cargo> chosenCargos = orderService.getChosenCargos(orderDTO);
            ui.addAttribute("chosenCargos",chosenCargos);
        }
        else {
            log.error("Error: getChosenCargos method in OrderService returned false.");
            ui.addAttribute("actionFailed", "Error while trying to create order.");
            return "failure";
        }
        Collection<Cargo> cargos = null;
        if (ui.containsAttribute("chosenCargos")){
            Map attr = ui.asMap();
            cargos = (ArrayList<Cargo>)attr.get("chosenCargos");
        }
        if (cargos!=null) {
            try {
                Collection<Truck> availableTrucks = orderService.getAvailableTrucks(orderDTO);
                ui.addAttribute("availableTrucks", availableTrucks);
            }
            catch (RouteException e){
                log.error("Error: " + e.getMessage());
                ui.addAttribute("actionFailed", "Error: " + e.getMessage());
                return "failure";
            }
        }
        ui.addAttribute("orderDTO", orderDTO);
        return "/manager/assigntrucktoorderpage";
    }

    @RequestMapping(value = "/assigntrucktoorderpage", method = RequestMethod.GET)
    public String assignTruckToOrderPage(Model ui){
        log.info("Controller: ManagerController, metod = assignTruckToOrderPage,  action = \"/assigntrucktoorderpage\", request = GET");
//        Collection<Cargo> cargos = null;
//        if (ui.containsAttribute("chosenCargos")){
//            Map attr = ui.asMap();
//            cargos = (ArrayList<Cargo>)attr.get("chosenCargos");
//        }
//        if (cargos!=null) {
          if (ui.containsAttribute("orderDTO")) {
              if (ui.asMap().get("orderDTO") instanceof OrderDTO) {
                  OrderDTO orderDTO = ((OrderDTO) ui.asMap().get("orderDTO"));
                  try {
                      Collection<Truck> availableTrucks = orderService.getAvailableTrucks(orderDTO);
                      ui.addAttribute("availableTrucks", availableTrucks);
                  }
                  catch (RouteException e){
                      log.error("Error: " + e.getMessage());
                      ui.addAttribute("actionFailed", "Error: " + e.getMessage());
                      return "failure";
                  }
              }
              else {
                  log.error("Error: attribute 'orderDTO' is not instance of OrderDTO!");
                  ui.addAttribute("actionFailed", "Error: attribute 'orderDTO' is not instance of OrderDTO!");
                  return "failure";
              }
          }
          else{
              log.error("Error: model doesn't contain attribute 'orderDTO' !");
              ui.addAttribute("actionFailed", "Error: model doesn't contain attribute 'orderDTO' !");
              return "failure";
          }
        return "/manager/assigntrucktoorderpage";
    }

    @RequestMapping(value = "/assigntrucktoorderpage", method = RequestMethod.POST)
    public String assignTruckToOrderPagePost(OrderDTO orderDTO, BindingResult bindingResult,Model ui){
        log.info("Controller: ManagerController, metod = assignTruckToOrderPage,  action = \"/assigntrucktoorderpage\", request = POST");
        if (orderDTO == null){
            log.error("Error: Order Data Transfer Object is not valid!");
            ui.addAttribute("actionFailed", "Error while trying to create order!");
            return "failure";
        }
        boolean result = false;
        try {
            result = orderService.createOrder(orderDTO);
        }
        catch (Exception e){
            log.error("Error: " + e.getMessage());
            ui.addAttribute("actionFailed", "Error: " + e.getMessage());
            return "failure";
        }
        if (result){
            log.info("New order successfully created!");
            ui.addAttribute("actionSuccess", "New order successfully created!");
            return "success";
        }
        else {
            log.error("Error: createOrder method in OrderService returned false.");
            ui.addAttribute("actionFailed", "Error while trying to create order.");
            return "failure";
        }
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
        boolean result = false;
        try {
            result = truckService.createTruck(truckDTO);
        }
        catch (Exception e){
            log.error("Error: " + e.getMessage());
            ui.addAttribute("actionFailed", "Error: " + e.getMessage());
            return "failure";
        }
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
        // todo: get free drivers!!! status =free!
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
