package com.gerasimchuk.controllers;

import com.gerasimchuk.converters.OrderToDTOConverter;
import com.gerasimchuk.dto.OrderDTO;
import com.gerasimchuk.entities.*;
import com.gerasimchuk.repositories.*;
import com.gerasimchuk.services.interfaces.CargoService;
import com.gerasimchuk.services.interfaces.OrderService;
import com.gerasimchuk.services.interfaces.TruckService;
import com.gerasimchuk.services.interfaces.UserService;
import com.gerasimchuk.utils.OrderWithRoute;
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

    @Autowired
    public AdminController(OrderRepository orderRepository, TruckRepository truckRepository, UserRepository userRepository, CargoRepository cargoRepository, CityRepository cityRepository, RouteRepository routeRepository, UserService userService, OrderService orderService, CargoService cargoService, TruckService truckService) {
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
    }

    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(AdminController.class);


    @RequestMapping(value = "/adminmainpage", method = RequestMethod.GET)
    String adminMainPage(Model ui){
        log.info("Controller: AdminController, metod = adminMainPage,  action = \"/adminmainpage\", request = GET");

//
//        Collection<Order> orders = orderRepository.getAll();
//        List<OrderWithRoute> ordersWithRoutes = new ArrayList<OrderWithRoute>();
//        for(Order o: orders){
//            List<City> cities = (List<City>) orderService.getOrderRoute(OrderToDTOConverter.convert(o));
//            ordersWithRoutes.add(new OrderWithRoute(o, cities));
//        }
//        Collection<Truck> trucks = truckRepository.getAll();
//        Collection<User> usersList = userRepository.getAll();
//        Collection<City> citiesList = cityRepository.getAll();
//        Collection<Route> routesList = routeRepository.getAll();
//
//        Collection<Cargo> cargos = cargoRepository.getAll();
//        ui.addAttribute("cargoList", cargos);
//        ui.addAttribute("usersList", usersList);
//        ui.addAttribute("ordersList", ordersWithRoutes);
//        ui.addAttribute("trucksList", trucks);
//        ui.addAttribute("citiesList", citiesList);
//        ui.addAttribute("routesList", routesList);

        ////////////////////////
        Collection<Cargo> cargos = cargoRepository.getAll();
        Collection<User> users = userRepository.getAll();
        Collection<Truck> trucks = truckRepository.getAll();
        Collection<Order> orders = orderRepository.getAll();
        //Map<Order, Collection<City>> routes = orderService.getRoutes(orders);
        //Collection<City> routePoints = orderService.getO
        Collection<Route> routesList = routeRepository.getAll();

        Collection<City> citiesList = cityRepository.getAll();
        List<OrderWithRoute> ordersWithRoutes = new ArrayList<OrderWithRoute>();
        for(Order o: orders){
            List<City> cities = (List<City>) orderService.getOrderRoute(OrderToDTOConverter.convert(o));
            ordersWithRoutes.add(new OrderWithRoute(o, cities));
        }
        ui.addAttribute("cargoList", cargos);
        ui.addAttribute("usersList", users);
        ui.addAttribute("trucksList", trucks);
        ui.addAttribute("ordersList", ordersWithRoutes);
        ui.addAttribute("citiesList", citiesList);
        ui.addAttribute("routesList", routesList);
        return "/admin/adminmainpage";
    }


    // todo: replace OrderDTO with IdDTO !
    @RequestMapping(value = "/adminmainpage/{id}", method = RequestMethod.POST)
    public String adminMainPagePost(@PathVariable("id") int action, OrderDTO orderDTO, BindingResult bindingResult, Model ui){
        log.info("Controller: AdminController, metod = adminMainPage,  action = \"/adminmainpage\", request = POST");
        if (orderDTO == null){
            log.error("Error: order Data Access object is empty!");
            ui.addAttribute("actionFailed", "Error: order Data Access object is empty");
            return "failure";
        }
        if (action == 1){
            // edit order
            int id = 0;
            try{
                id = Integer.parseInt(orderDTO.getId());
            }
            catch (Exception e){
                e.printStackTrace();
                log.error("Error: order id value is not valid!");
                ui.addAttribute("actionFailed", "Error: order id value is not valid!");
                return "failure";
            }
            Order updated = null;
            if (id != 0) updated = orderRepository.getById(id);
            if (updated != null){
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
            log.error("Error: no such order in database");
            ui.addAttribute("actionFailed", "Error: no such order in database");
            return "failure";
        }
        if (action == 2){
            // delete order
            Order deleted = null;
            int id = 0;
            try{
                id = Integer.parseInt(orderDTO.getId());
            }
            catch (Exception e){
                e.printStackTrace();
                log.error("Error: order id value is not valid!");
                ui.addAttribute("actionFailed", "Error: order id value is not valid!");
                return "failure";
            }
            if (id != 0) deleted = orderRepository.getById(id);
            if (deleted != null){
                boolean result = orderService.deleteOrder(orderDTO);
                if (result){
                    log.info("Order deleted successfully!");
                    ui.addAttribute("actionSuccess", "Order deleted successfully!");
                    return "success";
                }
                else {
                    log.error("Error: deleteOrder method in OrderService returned false.");
                    ui.addAttribute("actionFailed", "Error while trying to delete order.");
                    return "failure";
                }
            }

        }
        if (action == 3) {
            // edit truck
            int id = 0;
            try {
                id = Integer.parseInt(orderDTO.getId());
            } catch (Exception e) {
                log.error("Error: truck id value is not valid!");
                ui.addAttribute("actionFailed", "Error: truck id value is not valid!");
                return "failure";
            }
            if (id == 0) {
                log.error("Error: truck id value is zero!");
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
        if (action == 4){
            // delete truck
            int id = 0;
            try {
                id = Integer.parseInt(orderDTO.getId());
            } catch (Exception e) {
                log.error("Error: truck id value is not valid!");
                ui.addAttribute("actionFailed", "Error: truck id value is not valid!");
                return "failure";
            }
            if (id == 0) {
                log.error("Error: truck id value is zero!");
                ui.addAttribute("actionFailed", "Error: truck id value is zero!");
                return "failure";
            }
            boolean result = truckService.deleteTruck(id);
            if (result){
                log.info("Truck deleted successfully!");
                ui.addAttribute("actionSuccess","Truck deleted successfully!" );
                return "success";
            }
            else {
                log.error("Error: method deleteTruck in TruckService returned false.");
                ui.addAttribute("actionFailed", "Error: method deleteTruck in TruckService returned false.");
                return "failure";
            }
        }
        log.error("Error: no such action!");
        ui.addAttribute("actionFailed", "Error no such action!");
        return "failure";
    }


    @RequestMapping(value = "/orderchangepage", method = RequestMethod.GET)
    String orderChangePage(Model ui){
        log.info("Controller: AdminController, metod = orderChangePage,  action = \"/orderchangepage\", request = GET");
        return "/admin/orderchangepage";
    }

    //todo: refactor!!
    @RequestMapping(value = "/orderchangepage", method = RequestMethod.POST)
    String orderChangePagePost(OrderDTO orderDTO, BindingResult bindingResult, Model ui){
        log.info("Controller: AdminController, metod = orderChangePage,  action = \"/orderchangepage\", request = POST");
        if (orderDTO == null){
            log.error("Error: order Data Access object is empty!");
            ui.addAttribute("actionFailed", "Error: order Data Access object is empty");
            return "failure";
        }
        ui.addAttribute("updatedOrderDTO", orderDTO);
        Order order = null;
        // fill by real
        if (orderDTO.getCargosInOrder() == null || orderDTO.getCargosInOrder().length == 0 || orderDTO.getCargosInOrder()[0].equals("No cargos available")){
            int id = 0;
            try{
                id = Integer.parseInt(orderDTO.getId());
            }
            catch (Exception e){
                e.printStackTrace();
                log.error("Error: order id value is not valid!");
                ui.addAttribute("actionFailed", "Error: order id value is not valid!");
                return "failure";
            }
            OrderDTO newOrderDTO = null;
            if (id != 0){
                order = orderRepository.getById(id);
                if (order != null){
                    List<Cargo> currentCargosInOrder =new ArrayList<Cargo>(order.getCargosInOrder());
                    String[] newCargosInOrder = new String[currentCargosInOrder.size()];
                    for(int i = 0; i < newCargosInOrder.length; i++){
                        newCargosInOrder[i] = Integer.toString(currentCargosInOrder.get(i).getId());
                    }
                    newOrderDTO = new OrderDTO(
                            orderDTO.getId(),
                            orderDTO.getPersonalNumber(),
                            orderDTO.getDescription(),
                            orderDTO.getStatus(),
                            orderDTO.getAssignedTruckId(),
                            newCargosInOrder);
                    Collection<Truck> availableTrucks = orderService.getAvailableTrucks(newOrderDTO);
                    availableTrucks.add(order.getAssignedTruck());
                    Collection<Cargo> chosenCargos = orderService.getChosenCargos(newOrderDTO);
                    ui.addAttribute("availableTrucks", availableTrucks);
                    ui.addAttribute("chosenCargos", chosenCargos);
                }
                else {
                    log.error("Error: no such order!");
                    ui.addAttribute("actionFailed", "Error: no such order!");
                    return "failure";
                }
            }
            else {
                log.error("Error: order id value is not valid!");
                ui.addAttribute("actionFailed", "Error: order id value is not valid!");
                return "failure";
            }

        }
        else {
            Collection<Truck> availableTrucks = orderService.getAvailableTrucks(orderDTO);
            if (order!= null)
                if (order.getAssignedTruck() != null) availableTrucks.add(order.getAssignedTruck());
            Collection<Cargo> chosenCargos = orderService.getChosenCargos(orderDTO);
            ui.addAttribute("availableTrucks", availableTrucks);
            ui.addAttribute("chosenCargos", chosenCargos);
        }
        return "/admin/reassigntrucktoorderpage";
    }


    @RequestMapping(value = "/reassigntrucktoorderpage", method = RequestMethod.GET)
    String reassignTruckToOrderPage(Model ui){
        log.info("Controller: AdminController, metod = reassignTruckToOrderPage,  action = \"/reassigntrucktoorderpage\", request = GET");

        return "/admin/reassigntrucktoorderpage";
    }

    @RequestMapping(value = "/reassigntrucktoorderpage", method = RequestMethod.POST)
    String reassignTruckToOrderPagePost(OrderDTO orderDTO, BindingResult bindingResult, Model ui){
        log.info("Controller: AdminController, metod = reassignTruckToOrderPagePost,  action = \"/reassigntrucktoorderpage\", request = POST");
        if (orderDTO == null){
            log.error("Error: order Data Access object is empty!");
            ui.addAttribute("actionFailed", "Error: order Data Access object is empty");
            return "failure";
        }
        boolean result = orderService.updateOrder(orderDTO);
        if (result){
            log.info("Order successfully edited!");
            ui.addAttribute("actionSuccess", "Order successfully edited!");
            return "success";
        }
        else {
            log.error("Error: updateOrder method in OrderService returned false.");
            ui.addAttribute("actionFailed", "Error while trying to update order.");
            return "failure";
        }
    }


}
