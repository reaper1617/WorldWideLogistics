package com.gerasimchuk.controllers;

import com.gerasimchuk.constants.WWLConstants;
import com.gerasimchuk.converters.OrderToDTOConverterImpl;
import com.gerasimchuk.dto.DriverAccountDTO;
import com.gerasimchuk.dto.OrderDTO;
import com.gerasimchuk.entities.*;
import com.gerasimchuk.enums.CargoStatus;
import com.gerasimchuk.enums.DriverStatus;
import com.gerasimchuk.enums.OrderStatus;
import com.gerasimchuk.enums.UpdateMessageType;
import com.gerasimchuk.exceptions.routeexceptions.RouteException;
import com.gerasimchuk.rabbit.RabbitMQSender;
import com.gerasimchuk.repositories.*;
import com.gerasimchuk.services.interfaces.CargoService;
import com.gerasimchuk.services.interfaces.DriverService;
import com.gerasimchuk.services.interfaces.OrderService;
import com.gerasimchuk.services.interfaces.StatisticService;
import com.gerasimchuk.utils.MessageConstructor;
import com.gerasimchuk.utils.OrderWithRoute;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;


/** Driver Controller
 * @author Reaper
 * @version 1.0
 */

@Controller
public class DriverController {


    private UserRepository userRepository;
    private TruckRepository truckRepository;
    private DriverRepository driverRepository;
    private OrderRepository orderRepository;
    private CargoRepository cargoRepository;

    private DriverService driverService;
    private OrderService orderService;
    private CargoService cargoService;
    private RabbitMQSender rabbitMQSender;
    private StatisticService statisticService;
    private MessageConstructor messageConstructor;

    @Autowired
    public DriverController(UserRepository userRepository, TruckRepository truckRepository, DriverRepository driverRepository, OrderRepository orderRepository, CargoRepository cargoRepository, DriverService driverService, OrderService orderService, CargoService cargoService, RabbitMQSender rabbitMQSender, StatisticService statisticService, MessageConstructor messageConstructor) {
        this.userRepository = userRepository;
        this.truckRepository = truckRepository;
        this.driverRepository = driverRepository;
        this.orderRepository = orderRepository;
        this.cargoRepository = cargoRepository;
        this.driverService = driverService;
        this.orderService = orderService;
        this.cargoService = cargoService;
        this.rabbitMQSender = rabbitMQSender;
        this.statisticService = statisticService;
        this.messageConstructor = messageConstructor;
    }

    private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(DriverController.class);

    private void setUpDriverPageAttributes(Model ui) {


    }

    public String setDriverMainPageAttributes(Model ui){
        LOGGER.info("Controller: DriverController, metod = setDriverMainPageAttributes");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String personalNumber = authentication.getName();
        LOGGER.info("Authenticated user personal number:" + personalNumber);
        User loggedUser = userRepository.getByPersonalNumber(personalNumber);
        if (loggedUser == null){
            LOGGER.error("Error: logged user not found!");
            ui.addAttribute("actionFailed", "Error: logged user not found!");
            return "failure";
        }
        ui.addAttribute("loggedDriver" , loggedUser);
        if (loggedUser.getDriver() == null) {
            LOGGER.error("Error: access violation - user is not a driver");
            ui.addAttribute("actionFailed","Error: access violation - user is not a driver");
            return "failure";
        }
        if (loggedUser.getDriver().getCurrentTruck()!=null) {
            Collection<Driver> driversInTruck = loggedUser.getDriver().getCurrentTruck().getDriversInTruck();
            ui.addAttribute("driversInTruck", driversInTruck);
            Order order = loggedUser.getDriver().getCurrentTruck().getAssignedOrder();
            if (order != null) {
                OrderDTO orderDTO = OrderToDTOConverterImpl.convert(order);
                try {
                    List<City> cities =  (List<City>)orderService.getOrderRoute(orderDTO, null);
                    if (order.getAssignedTruck() != null) cities.add(0, order.getAssignedTruck().getCurrentCity());
                    OrderWithRoute orderWithRoute = new OrderWithRoute(order, cities);
                    ui.addAttribute("orderWithRoute", orderWithRoute);
                }
                catch (Exception e){
                    LOGGER.error("Error: " + e.getMessage());
                    ui.addAttribute("Error: " + e.getMessage());
                    return "failure";
                }
            }
        }
        // todo: refactor
        List<DriverStatus> driverStatusList = new ArrayList<DriverStatus>();
        driverStatusList.add(DriverStatus.FREE);
        driverStatusList.add(DriverStatus.DRIVING);
        driverStatusList.add(DriverStatus.LOAD_UNLOAD_WORKS);
        driverStatusList.add(DriverStatus.RESTING);
        driverStatusList.add(DriverStatus.SECOND_DRIVER);
        ui.addAttribute("driverStatusList", driverStatusList);

        List<OrderStatus> orderStatusList = new ArrayList<OrderStatus>();
        orderStatusList.add(OrderStatus.NOT_PREPARED);
        orderStatusList.add(OrderStatus.PREPARED);
        orderStatusList.add(OrderStatus.EXECUTING);
        orderStatusList.add(OrderStatus.EXECUTED);
        ui.addAttribute("orderStatusList", orderStatusList);

        // todo: refactor!!
        List<CargoStatus> cargoStatusList = new ArrayList<CargoStatus>();
        cargoStatusList.add(CargoStatus.PREPARED);
        cargoStatusList.add(CargoStatus.LOADED);
        cargoStatusList.add(CargoStatus.SHIPPING);
        cargoStatusList.add(CargoStatus.DELIVERED);
        ui.addAttribute("cargoStatusList", cargoStatusList);
        return "/driver/drivermainpage";
    }

    @RequestMapping(value = "/drivermainpage/{id}", method = RequestMethod.GET)
    String driverMainPage(@PathVariable("id") int id, Model ui){
        LOGGER.info("Controller: DriverController, metod = driverMainPage,  action = \"/drivermainpage\", request = GET");
        String resultUrl = setDriverMainPageAttributes(ui);
        return resultUrl;
    }

//    @RequestMapping(value = "/drivermainpage2/{id}", method = RequestMethod.GET)
//    String driverMainPage2(@PathVariable("id") int id, Model ui){
//        LOGGER.info("Controller: DriverController, metod = driverMainPage2,  action = \"/drivermainpage2\", request = GET");
//        String resultUrl = setDriverMainPageAttributes(ui);
//        resultUrl += "2";
//        return resultUrl;
//    }

    @RequestMapping(value = "/drivermainpage/{id}", method = RequestMethod.POST)
    public String driverMainPagePost(@PathVariable("id") int action, DriverAccountDTO driverAccountDTO, BindingResult bindingResult, Model ui){
        LOGGER.info("Controller: DriverController, metod = driverMainPagePost,  action = \"/drivermainpage\", request = POST");
        if (driverAccountDTO == null){
            LOGGER.error("Error: DriverAccount Data Transger Object is empty!");
            ui.addAttribute("actionFailed", "Error: DriverAccount Data Transger Object is empty!" );
            return "failure";
        }
        if (action == 0){
            LOGGER.info("Trying to update driver status");
            // update driver status
            int id = 0;
            try{
                id = Integer.parseInt(driverAccountDTO.getDriverId());
            }
            catch (Exception e){
                e.printStackTrace();
                LOGGER.error("Error: driver id is not valid");
                ui.addAttribute("actionFailed", "Error: driver id is not valid" );
                return "failure";
            }
            User user = userRepository.getById(id);
            LOGGER.info("Trying to update status for driver id = " + driverAccountDTO.getDriverId());
            if (user.getDriver() != null){
                Driver driver = user.getDriver();
                // get status!!
                DriverStatus status = driverService.getDriverStatusValFromString(driverAccountDTO.getDriverStatus());
                if (status == null) {
                    LOGGER.error("Error: driver status value is not valid!");
                    ui.addAttribute("actionFailed", "Error: driver status value is not valid!");
                    return "failure";
                }
                driverRepository.update(driver.getId(),driver.getHoursWorked(),status, driver.getCurrentCity(), driver.getCurrentTruck());
                ui.addAttribute("driverStatusUpdatedSuccessfully", "Updated successfully!");
                rabbitMQSender.sendMessage(messageConstructor.createMessage(UpdateMessageType.DRIVER_EDITED, statisticService));
                return setDriverMainPageAttributes(ui);
            }
            ui.addAttribute("actionFailed", "Error: driver object is not valid");
            return "failure";
        }
        if (action == 1){
            // update current order info
            LOGGER.info("Trying to update order status");
            int id = 0;
            try{
                id = Integer.parseInt(driverAccountDTO.getOrderId());
            }
            catch (Exception e){
                e.printStackTrace();
                LOGGER.error("Error: order id is not valid");
                ui.addAttribute("Error: order id is not valid");
                return "failure";
            }
            Order order = null;
            if (id != 0)  order = orderRepository.getById(id);
            if (order != null){
                OrderStatus orderStatus = orderService.getOrderStatusFromString(driverAccountDTO.getOrderStatus());
                if (orderStatus != null) {
                    Order updatedOrder = null;
                    if (!orderService.areAllCargosDelivered(order)&&orderStatus.equals(OrderStatus.EXECUTED)){
                        LOGGER.error("Error: not all cargos in order are delivered!");
                        ui.addAttribute("actionFailed", "Error: not all of cargos in order are delivered!");
                        return "failure";
                    }
                    orderRepository.update(order.getId(),
                            order.getPersonalNumber(),
                            order.getDescription(),
                            order.getDate(),
                            OrderStatus.EXECUTED,
                            order.getAssignedTruck());

                    if(orderStatus.equals(OrderStatus.EXECUTED)){
                        Truck t = order.getAssignedTruck();
                        Set<Driver> drivers = t.getDriversInTruck();
                        ArrayList<City> cities = null;
                        try {
                            cities = (ArrayList<City>) orderService.getOrderRoute(OrderToDTOConverterImpl.convert(order), null);
                        } catch (RouteException e) {
                            e.printStackTrace();
                        }
                        City newCurrentCity = null;
                        if (cities != null) newCurrentCity  = cities.get(cities.size()-1);
                        for(Driver d: drivers){
                            double orderHours = 0;
                            try {
                                orderHours = orderService.getExecutingTime(OrderToDTOConverterImpl.convert(order));
                            } catch (RouteException e) {
                                e.printStackTrace();
                                return "failure";
                            }
                            double newHoursWorked = d.getHoursWorked() + orderHours;
                            if (newHoursWorked > WWLConstants.MAX_DRIVER_HOURS_WORKED_IN_MONTH) newHoursWorked -= WWLConstants.MAX_DRIVER_HOURS_WORKED_IN_MONTH;
                            if (newCurrentCity != null) driverRepository.update(d.getId(),d.getHoursWorked() + orderHours,DriverStatus.RESTING,newCurrentCity,d.getCurrentTruck());
                            else driverRepository.update(d.getId(),d.getHoursWorked() + orderHours,DriverStatus.RESTING,d.getCurrentCity(),d.getCurrentTruck());
                        }
                        updatedOrder = orderRepository.update(order.getId(),order.getPersonalNumber(),order.getDescription(),order.getDate(),OrderStatus.EXECUTED,null);

                        if (newCurrentCity != null){
                            truckRepository.update(t.getId(),t.getRegistrationNumber(),t.getNumOfDrivers(),t.getCapacity(),t.getState(), newCurrentCity);
                        }
                    }
                    ui.addAttribute("orderStatusUpdatedSuccessfully", "Updated successfully!");
                    rabbitMQSender.sendMessage(messageConstructor.createMessage(UpdateMessageType.ORDER_EDITED, updatedOrder));
                    return setDriverMainPageAttributes(ui);
                }
                else {
                    LOGGER.error("Error: order status value is not valid!");
                    ui.addAttribute("actionFailed", "Error: order status value is not valid!");
                    return "failure";
                }
            }
            ui.addAttribute("actionFailed", "Error: order object is not valid");
            return "failure";
        }
        if (action == 2){
            // update current order cargos state
            int id = 0;
            try{
                id = Integer.parseInt(driverAccountDTO.getCargoId());
            }
            catch (Exception e){
                e.printStackTrace();
                LOGGER.error("Error: cargo id is not valid");
                ui.addAttribute("actionFailed", "Error: cargo id is not valid");
                return "failure";
            }
            CargoStatus cargoStatus = cargoService.getCargoStatusFromString(driverAccountDTO.getCargoStatus());
            if (cargoStatus == null){
                LOGGER.error("Error: cargo status value is not valid!");
                ui.addAttribute("actionFailed", "Error: cargo status value is not valid!");
                return "failure";
            }
            Cargo cargo = null;
            if (id != 0) cargo = cargoRepository.getById(id);
            if (cargo != null){
                cargoRepository.update(cargo.getId(),cargo.getPersonalNumber(),cargo.getName(),cargo.getWeight(),cargoStatus,cargo.getRoute());
                ui.addAttribute("cargoStatusUpdatedSuccessfully", "Updated successfully!");
                ui.addAttribute("updatedCargoId", id);
                return setDriverMainPageAttributes(ui);
            }
            ui.addAttribute("actionFailed", "Error: cargo object is not valid!");
            return "failure";
        }

        ui.addAttribute("actionFailed", "Error: unknown action!");
        return "failure";
    }

    @RequestMapping(value = "/updatedriverstatus", method = RequestMethod.POST)
    @ResponseBody
    public String updateDriverStatus(@RequestParam("driverId") int driverId, @RequestParam("newStatus") String newStatus){
        LOGGER.info("Class: " + this.getClass().getName() + " method: updateDriverStatus");
        if (driverId <=0){
            LOGGER.error("Class:" + this.getClass().getName() + " out from updateDriverStatus method: driver id is not valid.");
            return null;
        }
        if (newStatus == null){
            LOGGER.error("Class:" + this.getClass().getName() + " out from updateDriverStatus method: new driver status is not valid.");
            return null;
        }
        DriverStatus driverStatus = driverService.getDriverStatusValFromString(newStatus);
        UpdateMessageType result = driverService.updateDriverStatus(driverId,driverStatus);
        Driver d = driverRepository.getById(driverId);
        if (d!=null && result.equals(UpdateMessageType.DRIVER_STATUS_UPDATED)){
            rabbitMQSender.sendMessage(messageConstructor.createMessage(UpdateMessageType.DRIVER_EDITED, d));
        }
        Gson gson = new Gson();
        String res = gson.toJson(result);
        return res;
    }

    @RequestMapping(value = "/updateorderstatus", method = RequestMethod.POST)
    @ResponseBody
    public String updateOrderStatus(@RequestParam("orderId") int orderId, @RequestParam("newStatus") String newStatus){
        LOGGER.info("Class: " + this.getClass().getName() + " method: updateOrderStatus");
        if (orderId <=0){
            LOGGER.error("Class:" + this.getClass().getName() + " out from updateOrderStatus method: order id is not valid.");
            return null;
        }
        if (newStatus == null){
            LOGGER.error("Class:" + this.getClass().getName() + " out from updateOrderStatus method: new order status is not valid.");
            return null;
        }
//        DriverStatus driverStatus = driverService.getDriverStatusValFromString(newStatus);
        OrderStatus orderStatus = orderService.getOrderStatusFromString(newStatus);
        UpdateMessageType result = orderService.updateOrderStatus(orderId,orderStatus);
        Order o = orderRepository.getById(orderId);
        if (o!=null && result.equals(UpdateMessageType.ORDER_STATUS_UPDATED)){
            rabbitMQSender.sendMessage(messageConstructor.createMessage(UpdateMessageType.ORDER_EDITED, o));
        }
        Gson gson = new Gson();
        String res = gson.toJson(result);
        return res;
    }

    @RequestMapping(value = "/updatecargostatus", method = RequestMethod.POST)
    @ResponseBody
    public String updateCargoStatus(@RequestParam("cargoId") int cargoId, @RequestParam("newStatus") String newStatus){
        LOGGER.info("Class: " + this.getClass().getName() + " method: updateCargoStatus");
        if (cargoId <=0){
            LOGGER.error("Class:" + this.getClass().getName() + " out from updateCargoStatus method: order id is not valid.");
            return null;
        }
        if (newStatus == null){
            LOGGER.error("Class:" + this.getClass().getName() + " out from updateCargoStatus method: new order status is not valid.");
            return null;
        }
        CargoStatus cargoStatus = cargoService.getCargoStatusFromString(newStatus);
        UpdateMessageType result = cargoService.updateCargoStatus(cargoId,cargoStatus);
        Gson gson = new Gson();
        String res = gson.toJson(result);
        return res;
    }


}
