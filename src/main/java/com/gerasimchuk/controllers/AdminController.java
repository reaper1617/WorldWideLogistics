package com.gerasimchuk.controllers;

import com.gerasimchuk.converters.OrderToDTOConverter;
import com.gerasimchuk.dto.CityDTO;
import com.gerasimchuk.dto.OrderDTO;
import com.gerasimchuk.dto.RouteDTO;
import com.gerasimchuk.dto.UserDTO;
import com.gerasimchuk.entities.*;
import com.gerasimchuk.enums.UserRole;
import com.gerasimchuk.repositories.*;
import com.gerasimchuk.services.interfaces.*;
import com.gerasimchuk.utils.OrderWithRoute;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/** Admin Controller
 * @author Reaper
 * @version 1.0
 */

@Controller
public class AdminController {


    private OrderRepository orderRepository;
    private TruckRepository truckRepository;
    private UserRepository userRepository;
    private CargoRepository cargoRepository;
    private CityRepository cityRepository;
    private RouteRepository routeRepository;

    private UserService userService;
    private OrderService orderService;
    private CargoService cargoService;
    private TruckService truckService;
    private CityService cityService;
    private RouteService routeService;
    private AmqpTemplate amqpTemplate;

    @Autowired
    public AdminController(OrderRepository orderRepository, TruckRepository truckRepository, UserRepository userRepository, CargoRepository cargoRepository, CityRepository cityRepository, RouteRepository routeRepository, UserService userService, OrderService orderService, CargoService cargoService, TruckService truckService, CityService cityService, RouteService routeService, AmqpTemplate amqpTemplate) {
        this.orderRepository = orderRepository;
        this.truckRepository = truckRepository;
        this.userRepository = userRepository;
        this.cargoRepository = cargoRepository;
        this.cityRepository = cityRepository;
        this.routeRepository = routeRepository;
        this.userService = userService;
        this.orderService = orderService;
        this.cargoService = cargoService;
        this.truckService = truckService;
        this.cityService = cityService;
        this.routeService = routeService;
        this.amqpTemplate = amqpTemplate;
    }

    private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(AdminController.class);



    @RequestMapping(value = "/adminmainpage/{id}", method = RequestMethod.GET)
    public String adminMainPage(@PathVariable("id") int id, Model ui) {

        LOGGER.info("Controller: AdminController, metod = adminMainPage,  action = \"/adminmainpage\", request = GET");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String personalNumber = authentication.getName();
        LOGGER.info("Authenticated user personal number:" + personalNumber);
        amqpTemplate.convertAndSend("myQueue", "Authenticated user personal number:" + personalNumber);
        User loggedUser = userRepository.getByPersonalNumber(personalNumber);
        if (loggedUser == null){
            LOGGER.error("Error: logged user not found!");
            ui.addAttribute("actionFailed", "Error: logged user not found!");
            return "failure";
        }
        if (loggedUser.getAdmin() == null) {
            LOGGER.error("Error: access violation - user is not an admin");
            ui.addAttribute("actionFailed","Error: access violation - user is not an admin");
            return "failure";
        }
        Collection<Cargo> cargos = cargoRepository.getAll();
        Collection<User> users = userRepository.getAll();
        Collection<Truck> trucks = truckRepository.getAll();
        Collection<Order> orders = orderRepository.getAll();
        //Map<Order, Collection<City>> routes = orderService.getRoutes(orders);
        //Collection<City> routePoints = orderService.getO
        Collection<Route> routesList = routeRepository.getAll();
        Collection<City> citiesList = cityRepository.getAll();
        List<OrderWithRoute> ordersWithRoutes = new ArrayList<OrderWithRoute>();
        for (Order o : orders) {
            try {
                List<City> cities = (List<City>) orderService.getOrderRoute(OrderToDTOConverter.convert(o), null);
                if (o.getAssignedTruck() != null) cities.add(0,o.getAssignedTruck().getCurrentCity());
                ordersWithRoutes.add(new OrderWithRoute(o, cities));
            }
            catch (Exception e){
                e.printStackTrace();
                LOGGER.error("Error: cannot create route for order " + o.getDescription());
            }
        }
        ui.addAttribute("cargoList", cargos);
        ui.addAttribute("usersList", users);
        ui.addAttribute("trucksList", trucks);
        ui.addAttribute("ordersList", ordersWithRoutes);
        ui.addAttribute("citiesList", citiesList);
        ui.addAttribute("routesList", routesList);
        return "/admin/adminmainpage";
    }

    private int parseId(OrderDTO orderDTO){
        int id = 0;
        try {
            id = Integer.parseInt(orderDTO.getId());
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return id;
    }

    // todo: replace OrderDTO with IdDTO !
    @RequestMapping(value = "/adminmainpage/{id}", method = RequestMethod.POST)
    public String adminMainPagePost(@PathVariable("id") int action, OrderDTO orderDTO, BindingResult bindingResult, Model ui) {
        LOGGER.info("Controller: AdminController, metod = adminMainPage,  action = \"/adminmainpage\", request = POST");
        if (orderDTO == null) {
            LOGGER.error("Error: order Data Access object is empty!");
            ui.addAttribute("actionFailed", "Error: order Data Access object is empty");
            return "failure";
        }
        if (action == 1) {
            LOGGER.info("Trying to edit order");
            // edit order
            int id = parseId(orderDTO);
            Order updated = null;
            if (id != 0) updated = orderRepository.getById(id);
            else {
                LOGGER.error("Error: order id value is zero!");
                ui.addAttribute("actionFailed", "Error: order id value is zero!");
                return "failure";
            }
            if (updated != null) {
                // cargos available + cargos in current order
                Collection<Cargo> availableCargos = cargoService.getAvailableCargos();
//                Collection<Cargo> chosenCargos = orderService.getChosenCargos(orderDTO);
                Collection<Cargo> cargos = new ArrayList<Cargo>();
                cargos.addAll(availableCargos);
                cargos.addAll(updated.getCargosInOrder());
                ui.addAttribute("availableCargos", cargos);
                ui.addAttribute("updatedOrder", updated);
                return "/admin/orderchangepage";
            }
            LOGGER.error("Error: no such order in database");
            ui.addAttribute("actionFailed", "Error: no such order in database");
            return "failure";
        }
        if (action == 2) {
            // delete order
            Order deleted = null;
            int id = parseId(orderDTO);
            if (id != 0) deleted = orderRepository.getById(id);
            else {
                LOGGER.error("Error: order id value is zero!");
                ui.addAttribute("actionFailed", "Error: order id value is zero!");
                return "failure";
            }
            if (deleted != null) {
                boolean result = orderService.deleteOrder(orderDTO);
                if (result) {
                    LOGGER.info("Order deleted successfully!");
                    ui.addAttribute("actionSuccess", "Order deleted successfully!");
                    return "success";
                } else {
                    LOGGER.error("Error: deleteOrder method in OrderService returned false.");
                    ui.addAttribute("actionFailed", "Error while trying to delete order.");
                    return "failure";
                }
            }

        }
        if (action == 3) {
            // edit truck
            int id = parseId(orderDTO);
            if (id == 0) {
                LOGGER.error("Error: truck id value is zero!");
                ui.addAttribute("actionFailed", "Error: truck id value is zero!");
                return "failure";
            }
            Truck truck = truckRepository.getById(id);
            Collection<City> cities = cityRepository.getAll();
            Collection<User> drivers = userService.getAllDrivers();
            ui.addAttribute("updatedTruckId", id);
            ui.addAttribute("updatedTruck", truck);
            ui.addAttribute("citiesList", cities);
            ui.addAttribute("driversList", drivers);
            return "/manager/truckchangepage";
        }
        if (action == 4) {
            // delete truck
            int id = parseId(orderDTO);
            if (id == 0) {
                LOGGER.error("Error: truck id value is zero!");
                ui.addAttribute("actionFailed", "Error: truck id value is zero!");
                return "failure";
            }
            boolean result = truckService.deleteTruck(id);
            if (result) {
                LOGGER.info("Truck deleted successfully!");
                ui.addAttribute("actionSuccess", "Truck deleted successfully!");
                return "success";
            } else {
                LOGGER.error("Error: method deleteTruck in TruckService returned false.");
                ui.addAttribute("actionFailed", "Error: method deleteTruck in TruckService returned false.");
                return "failure";
            }
        }
        if (action == 5){
            // edit user
            int id = parseId(orderDTO);
            if (id == 0){
                LOGGER.error("Error: user id value is zero!");
                ui.addAttribute("actionFailed", "Error: user id value is zero!");
                return "failure";
            }
            User updated = userRepository.getById(id);
            if (updated == null){
                LOGGER.error("Error: no such user in database!");
                ui.addAttribute("actionFailed", "Error: no such user in database!");
                return "failure";
            }
            ui.addAttribute("updatedUser", updated);

            Collection<City> cities = cityRepository.getAll();
            ui.addAttribute("citiesList", cities);
            Collection<Truck> availableTrucks = truckService.getFreeTrucks();
            if (updated.getDriver()!=null){
                if (updated.getDriver().getCurrentTruck()!=null){
                    availableTrucks.add(updated.getDriver().getCurrentTruck());
                }
            }
            ui.addAttribute("trucksList", availableTrucks);
            return "/admin/userchangepage";
        }
        if (action == 6){
            // delete user
            int id = parseId(orderDTO);
            if (id == 0){
                LOGGER.error("Error: user id value is zero!");
                ui.addAttribute("actionFailed", "Error: user id value is zero!");
                return "failure";
            }
            boolean result = userService.deleteUser(id);
            if (result){
                LOGGER.info("User deleted successfully!");
                ui.addAttribute("actionSuccess","User deleted successfully!");
                return "success";
            }
            else {
                LOGGER.error("Error: deleteUser method in UserService returned false!");
                ui.addAttribute("actionFailed","Error: deleteUser method in UserService returned false!");
                return "failure";
            }
        }
        if (action == 7){
            // change cargo
            int id = parseId(orderDTO);
            if (id == 0){
                LOGGER.error("Error: cargo id value is zero!");
                ui.addAttribute("actionFailed", "Error: cargo id value is zero!");
                return "failure";
            }
            ui.addAttribute("updatedCargoId", id);
            Collection<City> citiesList = cityRepository.getAll();
            ui.addAttribute("citiesList", citiesList);
            return "/manager/cargochangepage";
        }
        if (action == 8){
            // delete cargo
            int id = parseId(orderDTO);
            if (id == 0){
                LOGGER.error("Error: cargo id value is zero!");
                ui.addAttribute("actionFailed", "Error: cargo id value is zero!");
                return "failure";
            }
            boolean result = cargoService.deleteCargo(id);
            if (result){
                LOGGER.info("Cargo deleted successfully!");
                ui.addAttribute("actionSuccess", "Cargo deleted successfully!");
                return "success";
            }
            else {
                LOGGER.error("Error: deleteCargo method in CargoService returned false!");
                ui.addAttribute("actionFailed", "Error: deleteCargo method in CargoService returned false!");
                return "failure";
            }
        }
        if (action == 9){
            // edit city
            int id = parseId(orderDTO);
            if (id == 0) {
                LOGGER.error("Error: city id value is zero!");
                ui.addAttribute("actionFailed", "Error: city id value is zero!");
                return "failure";
            }
            ui.addAttribute("changedCityId", id);
            return "/admin/changecitypage";
        }
        if (action == 10){
            int id = parseId(orderDTO);
            if (id == 0) {
                LOGGER.error("Error: city id value is zero!");
                ui.addAttribute("actionFailed", "Error: city id value is zero!");
                return "failure";
            }
            boolean result = false;
            try {
                result = cityService.deleteCity(id);
            }
            catch (Exception e){
                e.printStackTrace();
                LOGGER.error("Error: there are some drivers or trucks in this city. They need to be unassigned from city for successful remove.");
                ui.addAttribute("actionFailed", "Error: there are some drivers or trucks in this city. They need to be unassigned from city for successful remove.");
                return "failure";
            }
            if (result){
                LOGGER.info("City deleted successfully!");
                ui.addAttribute("actionSuccess", "City deleted successfully!");
                return "success";
            }
            else {
                LOGGER.error("Error: deleteCity method in CityService returned false!");
                ui.addAttribute("actionFailed", "Error: deleteCity method in CityService returned false!");
                return "failure";
            }
        }
        if (action == 11){
            // edit route
            int id = parseId(orderDTO);
            if (id == 0) {
                LOGGER.error("Error: route id value is zero!");
                ui.addAttribute("actionFailed", "Error: route id value is zero!");
                return "failure";
            }
            ui.addAttribute("updatedRouteId", id);
            Collection<City> cities = cityRepository.getAll();
            ui.addAttribute("citiesList", cities);
            return "/admin/changeroutepage";

        }
        if (action == 12){
            // delete route
            int id = parseId(orderDTO);
            if (id == 0) {
                LOGGER.error("Error: route id value is zero!");
                ui.addAttribute("actionFailed", "Error: route id value is zero!");
                return "failure";
            }
            boolean result = false;
            try {
                result = routeService.deleteRoute(id);
            }
            catch (Exception e){
                e.printStackTrace();
                LOGGER.error("Error: there are some cargos with this route. They need to be unassigned from route for successful remove.");
                ui.addAttribute("actionFailed", "Error: there are some cargos with this route. They need to be unassigned from route for successful remove.");
                return "failure";
            }
            if (result){
                LOGGER.info("Route deleted successfully!");
                ui.addAttribute("actionSuccess", "Route deleted successfully!");
                return "success";
            }
            else {
                LOGGER.error("Error: deleteRoute method in RouteService returned false!");
                ui.addAttribute("actionFailed", "Error: deleteRoute method in RouteService returned false!");
                return "failure";
            }
        }

        LOGGER.error("Error: no such action!");
        ui.addAttribute("actionFailed", "Error no such action!");
        return "failure";
    }

    @RequestMapping(value = "/changeroutepage", method = RequestMethod.GET)
    public String changeRoute(Model ui){
        LOGGER.info("Controller: AdminController, metod = changeRoute,  action = \"/changeroutepage\", request = GET");
        Collection<City> cities = cityRepository.getAll();
        ui.addAttribute("citiesList", cities);
        return "/admin/changeroutepage";
    }

    @RequestMapping(value = "/changeroutepage", method = RequestMethod.POST)
    public String changeRoutePost(RouteDTO routeDTO, BindingResult bindingResult,Model ui){
        LOGGER.info("Controller: AdminController, metod = changeRoute,  action = \"/changeroutepage\", request = POST");
        if (routeDTO == null){
            LOGGER.error("Error: route Data Transfer Object is null!");
            ui.addAttribute("actionFailed", "Error: route Data Transfer Object is null!");
            return "failure";
        }
        boolean result = routeService.updateRoute(routeDTO);
        if (result){
            LOGGER.info("Route successfully updated!");
            ui.addAttribute("actionSuccess", "Route successfully updated!");
            return "success";
        }
        else {
            LOGGER.error("Error: updateRoute method in RouteService returned false!");
            ui.addAttribute("actionFailed","Error:  updateRoute method in RouteService returned false!" );
            return "failure";
        }
    }


    @RequestMapping(value = "/userchangepage", method = RequestMethod.GET)
    public String userChangePage(Model ui){
        LOGGER.info("Controller: AdminController, metod = userChangePage,  action = \"/userchangepage\", request = GET");
        Collection<City> cities = cityRepository.getAll();
        ui.addAttribute("citiesList", cities);
        Collection<Truck> availableTrucks = truckService.getFreeTrucks();
        ui.addAttribute("updatedUser", null);
        return "/admin/userchangepage";
    }

    @RequestMapping(value = "/userchangepage", method = RequestMethod.POST)
    public String userChangePagePost(UserDTO userDTO, BindingResult bindingResult,Model ui){
        LOGGER.info("Controller: AdminController, metod = userChangePagePost,  action = \"/userchangepage\", request = POST");
        if (userDTO == null) {
            LOGGER.error("Error: user Data Transfer Object is null!");
            ui.addAttribute("actionFailed", "Error: user Data Transfer Object is null!");
            return "failure";
        }
        boolean result = userService.updateUser(userDTO);
        if (result){
            LOGGER.info("User successfully updated!");
            ui.addAttribute("actionSuccess", "User successfully updated!");
            return "success";
        }
        else {
            LOGGER.error("Error: updateUser method in UserService returned false!");
            ui.addAttribute("actionFailed","Error: updateUser method in UserService returned false!" );
            return "failure";
        }
    }


    @RequestMapping(value = "/orderchangepage", method = RequestMethod.GET)
    public String orderChangePage(Model ui) {
        LOGGER.info("Controller: AdminController, metod = orderChangePage,  action = \"/orderchangepage\", request = GET");
        return "/admin/orderchangepage";
    }

    //todo: refactor!!
    @RequestMapping(value = "/orderchangepage", method = RequestMethod.POST)
    public String orderChangePagePost(OrderDTO orderDTO, BindingResult bindingResult, Model ui) {
        LOGGER.info("Controller: AdminController, metod = orderChangePage,  action = \"/orderchangepage\", request = POST");
        if (orderDTO == null) {
            LOGGER.error("Error: order Data Access object is empty!");
            ui.addAttribute("actionFailed", "Error: order Data Access object is empty");
            return "failure";
        }
        ui.addAttribute("updatedOrderDTO", orderDTO);
        Order order = null;
        // fill by real
        if (orderDTO.getCargosInOrder() == null || orderDTO.getCargosInOrder().length == 0 || orderDTO.getCargosInOrder()[0].equals("No cargos available")) {
            int id = 0;
            try {
                id = Integer.parseInt(orderDTO.getId());
            } catch (Exception e) {
                e.printStackTrace();
                LOGGER.error("Error: order id value is not valid!");
                ui.addAttribute("actionFailed", "Error: order id value is not valid!");
                return "failure";
            }
            OrderDTO newOrderDTO = null;
            if (id != 0) {
                order = orderRepository.getById(id);
                if (order != null) {
                    List<Cargo> currentCargosInOrder = new ArrayList<Cargo>(order.getCargosInOrder());
                    String[] newCargosInOrder = new String[currentCargosInOrder.size()];
                    for (int i = 0; i < newCargosInOrder.length; i++) {
                        newCargosInOrder[i] = Integer.toString(currentCargosInOrder.get(i).getId());
                    }
                    newOrderDTO = new OrderDTO(
                            orderDTO.getId(),
                            orderDTO.getPersonalNumber(),
                            orderDTO.getDescription(),
                            orderDTO.getStatus(),
                            orderDTO.getAssignedTruckId(),
                            newCargosInOrder);
                    Collection<Truck> availableTrucks = null;
                    try {
                        availableTrucks = orderService.getAvailableTrucks(newOrderDTO);
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                    if (availableTrucks == null) availableTrucks = new ArrayList<Truck>();
                    availableTrucks.add(order.getAssignedTruck());
                    Collection<Cargo> chosenCargos = orderService.getChosenCargos(newOrderDTO);
                    ui.addAttribute("availableTrucks", availableTrucks);
                    ui.addAttribute("chosenCargos", chosenCargos);
                } else {
                    LOGGER.error("Error: no such order!");
                    ui.addAttribute("actionFailed", "Error: no such order!");
                    return "failure";
                }
            } else {
                LOGGER.error("Error: order id value is not valid!");
                ui.addAttribute("actionFailed", "Error: order id value is not valid!");
                return "failure";
            }

        } else {
            Collection<Truck> availableTrucks = null;
            try {
                availableTrucks = orderService.getAvailableTrucks(orderDTO);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Collection<Cargo> chosenCargos = orderService.getChosenCargos(orderDTO);
            ui.addAttribute("availableTrucks", availableTrucks);
            ui.addAttribute("chosenCargos", chosenCargos);
        }
        return "/admin/reassigntrucktoorderpage";
    }


    @RequestMapping(value = "/reassigntrucktoorderpage", method = RequestMethod.GET)
    public String reassignTruckToOrderPage(Model ui) {
        LOGGER.info("Controller: AdminController, metod = reassignTruckToOrderPage,  action = \"/reassigntrucktoorderpage\", request = GET");

        return "/admin/reassigntrucktoorderpage";
    }

    @RequestMapping(value = "/reassigntrucktoorderpage", method = RequestMethod.POST)
    public String reassignTruckToOrderPagePost(OrderDTO orderDTO, BindingResult bindingResult, Model ui) {
        LOGGER.info("Controller: AdminController, metod = reassignTruckToOrderPagePost,  action = \"/reassigntrucktoorderpage\", request = POST");
        if (orderDTO == null) {
            LOGGER.error("Error: order Data Access object is empty!");
            ui.addAttribute("actionFailed", "Error: order Data Access object is empty");
            return "failure";
        }
        boolean result = false;
        try {
            result = orderService.updateOrder(orderDTO);
        }
        catch (Exception e){
            LOGGER.error("Error: " + e.getMessage());
            ui.addAttribute("actionFailed", "Error: " + e.getMessage());
            return "failure";
        }
        if (result) {
            LOGGER.info("Order successfully edited!");
            ui.addAttribute("actionSuccess", "Order successfully edited!");
            return "success";
        } else {
            LOGGER.error("Error: updateOrder method in OrderService returned false.");
            ui.addAttribute("actionFailed", "Error while trying to update order.");
            return "failure";
        }
    }


    @RequestMapping(value = "/addnewuserpage", method = RequestMethod.GET)
    String addNewUserPage(Model ui) {
        LOGGER.info("Controller: AdminController, metod = addNewUserPage,  action = \"/addnewuserpage\", request = GET");
        Collection<UserRole> userRoles = userService.getRoles();
        ui.addAttribute("userRoles", userRoles);
        Collection<City> cities = cityRepository.getAll();
        ui.addAttribute("citiesList", cities);
        Collection<Truck> availableTrucks = truckService.getFreeTrucks();
        ui.addAttribute("trucksList", availableTrucks);
        return "/admin/addnewuserpage";
    }

    @RequestMapping(value = "/addnewuserpage", method = RequestMethod.POST)
    String addNewUserPagePost(UserDTO userDTO, BindingResult bindingResult, Model ui) {
        LOGGER.info("Controller: AdminController, metod = addNewUserPage,  action = \"/addnewuserpage\", request = POST");
        if (userDTO == null) {
            LOGGER.error("Error: user Data Transfer Object is null!");
            ui.addAttribute("actionFailed", "Error: user Data Transfer Object is null!");
            return "failure";
        }
        boolean result = userService.createUser(userDTO);
        if (result) {
            LOGGER.info("New " + userDTO.getRole().toLowerCase() + " created successfully");
            ui.addAttribute("actionSuccess", "New " + userDTO.getRole().toLowerCase() + " created successfully");
            return "success";
        } else {
            LOGGER.error("Error: createUser method in UserService returned false!");
            ui.addAttribute("actionFailed", "Error: createUser method in UserService returned false!");
            return "failure";
        }
    }

    @RequestMapping(value = "/addnewcitypage", method = RequestMethod.GET)
    public String addNewCity(){
        LOGGER.info("Controller: AdminController, metod = addNewCityPage,  action = \"/addnewcitypage\", request = GET");
        return "/admin/addnewcitypage";
    }

    @RequestMapping(value = "/addnewcitypage", method = RequestMethod.POST)
    public String addNewCityPost(CityDTO cityDTO, BindingResult bindingResult, Model ui){
        LOGGER.info("Controller: AdminController, metod = addNewCityPage,  action = \"/addnewcitypage\", request = POST");
        if (cityDTO == null){
            LOGGER.error("Error: city Data Transfer Object is null!");
            ui.addAttribute("actionFailed", "Error: city Data Transfer Object is null!");
            return "failure";
        }
        boolean result = cityService.createCity(cityDTO);
        if (result){
            LOGGER.info("City created successfully!");
            ui.addAttribute("actionSuccess", "City created successfully!");
            return "success";
        }
        else {
            LOGGER.error("Error: createCity method in CityService returned false!");
            ui.addAttribute("actionFailed", "Error: createCity method in CityService returned false!");
            return "failure";
        }
    }

    @RequestMapping(value = "/changecitypage", method = RequestMethod.GET)
    public String changeCity(){
        LOGGER.info("Controller: AdminController, metod = changeCityPage,  action = \"/addnewcitypage\", request = GET");
        return "/admin/changecitypage";
    }

    @RequestMapping(value = "/changecitypage", method = RequestMethod.POST)
    public String changeCityPost(CityDTO cityDTO, BindingResult bindingResult, Model ui){
        LOGGER.info("Controller: AdminController, metod = changeCityPage,  action = \"/changecitypage\", request = POST");
        if (cityDTO == null){
            LOGGER.error("Error: city Data Transfer Object is null!");
            ui.addAttribute("actionFailed", "Error: city Data Transfer Object is null!");
            return "failure";
        }
        boolean result = cityService.updateCity(cityDTO);
        if (result){
            LOGGER.info("City updated successfully!");
            ui.addAttribute("actionSuccess", "City updated successfully!");
            return "success";
        }
        else {
            LOGGER.error("Error: updateCity method in CityService returned false!");
            ui.addAttribute("actionFailed", "Error: updateCity method in CityService returned false!");
            return "failure";
        }
    }

    @RequestMapping(value = "/addnewroutepage", method = RequestMethod.GET)
    public String addNewRoute(Model ui){
        LOGGER.info("Controller: AdminController, metod = addNewRoute,  action = \"/addnewroutepage\", request = GET");
        Collection<City> citiesList = cityRepository.getAll();
        ui.addAttribute("citiesList", citiesList);
        return "/admin/addnewroutepage";
    }

    @RequestMapping(value = "/addnewroutepage", method = RequestMethod.POST)
    public String addNewRoutePost(RouteDTO routeDTO, BindingResult bindingResult, Model ui){
        LOGGER.info("Controller: AdminController, metod = addNewRoute,  action = \"/addnewroutepage\", request = POST");
        if (routeDTO == null){
            LOGGER.error("Error: route Data Transfer Object is null!");
            ui.addAttribute("actionFailed", "Error: route Data Transfer Object is null!");
            return "failure";
        }
        boolean result = routeService.createRoute(routeDTO);
        if (result){
            LOGGER.info("Route created successfully!");
            ui.addAttribute("actionSuccess", "Route created successfully!");
            return "success";
        }
        else {
            LOGGER.error("Error: createRoute method in RouteService returned false!");
            ui.addAttribute("actionFailed", "Error: createRoute method in RouteService returned false!");
            return "failure";
        }
    }

}