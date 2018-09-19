package com.gerasimchuk.controllers;

import com.gerasimchuk.converters.OrderToDTOConverter;
import com.gerasimchuk.dto.DriverAccountDTO;
import com.gerasimchuk.dto.OrderDTO;
import com.gerasimchuk.entities.City;
import com.gerasimchuk.entities.Driver;
import com.gerasimchuk.entities.Order;
import com.gerasimchuk.entities.User;
import com.gerasimchuk.enums.CargoStatus;
import com.gerasimchuk.enums.DriverStatus;
import com.gerasimchuk.enums.OrderStatus;
import com.gerasimchuk.repositories.DriverRepository;
import com.gerasimchuk.repositories.OrderRepository;
import com.gerasimchuk.repositories.TruckRepository;
import com.gerasimchuk.repositories.UserRepository;
import com.gerasimchuk.services.interfaces.DriverService;
import com.gerasimchuk.services.interfaces.OrderService;
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


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

    private DriverService driverService;
    private OrderService orderService;

    @Autowired
    public DriverController(UserRepository userRepository, TruckRepository truckRepository, DriverRepository driverRepository, OrderRepository orderRepository, DriverService driverService, OrderService orderService) {
        this.userRepository = userRepository;
        this.truckRepository = truckRepository;
        this.driverRepository = driverRepository;
        this.orderRepository = orderRepository;
        this.driverService = driverService;
        this.orderService = orderService;
    }

    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(DriverController.class);

    private String setDriverMainPageAttributes(Model ui){
        log.info("Controller: DriverController, metod = setDriverMainPageAttributes");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String personalNumber = authentication.getName();
        log.info("Authenticated user personal number:" + personalNumber);
        User loggedUser = userRepository.getByPersonalNumber(personalNumber);
        if (loggedUser == null){
            log.error("Error: logged user not found!");
            ui.addAttribute("actionFailed", "Error: logged user not found!");
            return "failure";
        }
        ui.addAttribute("loggedDriver" , loggedUser);
        if (loggedUser.getDriver() == null) {
            log.error("Error: access violation - user is not a driver");
            ui.addAttribute("Error: access violation - user is not a driver");
            return "failure";
        }
        if (loggedUser.getDriver().getCurrentTruck()!=null) {
            Collection<Driver> driversInTruck = loggedUser.getDriver().getCurrentTruck().getDriversInTruck();
            ui.addAttribute("driversInTruck", driversInTruck);
            Order order = loggedUser.getDriver().getCurrentTruck().getAssignedOrder();
            OrderDTO orderDTO = OrderToDTOConverter.convert(order);
            OrderWithRoute orderWithRoute = new OrderWithRoute(order, (List<City>) orderService.getOrderRoute(orderDTO));
            ui.addAttribute("orderWithRoute", orderWithRoute);
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

    @RequestMapping(value = "/drivermainpage", method = RequestMethod.GET)
    String driverMainPage(Model ui){
        log.info("Controller: DriverController, metod = driverMainPage,  action = \"/drivermainpage\", request = GET");
        String resultUrl = setDriverMainPageAttributes(ui);
        return resultUrl;
    }

    @RequestMapping(value = "/drivermainpage/{id}", method = RequestMethod.POST)
    public String driverMainPagePost(@PathVariable("id") int action, DriverAccountDTO driverAccountDTO, BindingResult bindingResult, Model ui){
        log.info("Controller: DriverController, metod = driverMainPagePost,  action = \"/drivermainpage\", request = POST");
        if (driverAccountDTO == null){
            log.error("Error: DriverAccount Data Transger Object is empty!");
            ui.addAttribute("actionFailed", "Error: DriverAccount Data Transger Object is empty!" );
            return "failure";
        }
        if (action == 0){
            log.info("Trying to update driver status");
            // update driver status
            int id = 0;
            try{
                id = Integer.parseInt(driverAccountDTO.getDriverId());
            }
            catch (Exception e){
                e.printStackTrace();
                log.error("Error: driver id is not valid");
                ui.addAttribute("actionFailed", "Error: driver id is not valid" );
                return "failure";
            }
            User user = userRepository.getById(id);
            log.info("Trying to update status for driver id = " + driverAccountDTO.getDriverId());
            if (user.getDriver() != null){
                Driver driver = user.getDriver();
                // get status!!
                DriverStatus status = driverService.getDriverStatusValFromString(driverAccountDTO.getDriverStatus());
                if (status == null) {
                    log.error("Error: driver status value is not valid!");
                    ui.addAttribute("actionFailed", "Error: driver status value is not valid!");
                    return "failure";
                }
                driverRepository.update(driver.getId(),driver.getHoursWorked(),status, driver.getCurrentCity(), driver.getCurrentTruck());
                ui.addAttribute("driverStatusUpdatedSuccessfully", "Updated successfully!");
                return setDriverMainPageAttributes(ui);
            }
            ui.addAttribute("actionFailed", "Error: driver object is not valid");
            return "failure";
        }
        if (action == 1){
            // update current order info
            log.info("Trying to update order status");
            int id = 0;
            try{
                id = Integer.parseInt(driverAccountDTO.getOrderId());
            }
            catch (Exception e){
                e.printStackTrace();
                log.error("Error: order id is not valid");
                ui.addAttribute("Error: order id is not valid");
                return "failure";
            }
            Order order = orderRepository.getById(id);
            if (order != null){
                OrderStatus orderStatus = orderService.getOrderStatusFromString(driverAccountDTO.getOrderStatus());
                if (orderStatus != null) {
                    orderRepository.update(order.getId(),
                            order.getPersonalNumber(),
                            order.getDescription(),
                            order.getDate(),
                            orderStatus,
                            order.getAssignedTruck());
                    ui.addAttribute("orderStatusUpdatedSuccessfully", "Updated successfully!");
                    return setDriverMainPageAttributes(ui);
                }
                else {
                    log.error("Error: order status value is not valid!");
                    ui.addAttribute("actionFailed", "Error: order status value is not valid!");
                    return "failure";
                }
            }
            ui.addAttribute("actionFailed", "Error: order object is not valid");
            return "failure";
        }
        if (action == 2){
            // update current order cargos state


        }

        ui.addAttribute("actionFailed", "Error: unknown action!");
        return "failure";
    }
}
