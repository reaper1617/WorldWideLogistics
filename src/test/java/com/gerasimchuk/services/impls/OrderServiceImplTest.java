package com.gerasimchuk.services.impls;

import com.gerasimchuk.dto.OrderDTO;
import com.gerasimchuk.entities.*;
import com.gerasimchuk.enums.CargoStatus;
import com.gerasimchuk.enums.TruckState;
import com.gerasimchuk.enums.UpdateMessageType;
import com.gerasimchuk.exceptions.driverexceptions.TooManyHoursWorkedForOrderException;
import com.gerasimchuk.exceptions.routeexceptions.RouteException;
import com.gerasimchuk.repositories.CargoRepository;
import com.gerasimchuk.repositories.CityRepository;
import com.gerasimchuk.repositories.RouteRepository;
import com.gerasimchuk.repositories.TruckRepository;
import com.gerasimchuk.services.interfaces.CargoService;
import com.gerasimchuk.services.interfaces.OrderService;
import com.gerasimchuk.utils.ReturnValuesContainer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:**/testConf.xml"})
@WebAppConfiguration
public class OrderServiceImplTest {


    @Autowired
    private OrderService orderService;

    @Autowired
    private CargoRepository cargoRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private TruckRepository truckRepository;

    @Autowired
    RouteRepository routeRepository;

    @Test
    public void getChosenCargos() {
        Collection<Cargo> cargos = cargoRepository.getAll();
        String[] cargosInOrder = new String[1];
        for(Cargo c: cargos){
            if (c.getStatus().equals(CargoStatus.PREPARED)) {
                cargosInOrder[0] = Integer.toString(c.getId());
                break;
            }
        }
        OrderDTO orderDTO = new OrderDTO(null,null,null,null,null,cargosInOrder);
        Collection<Cargo> cargos1 = orderService.getChosenCargos(orderDTO);
        assertEquals(1, cargos1.size());
    }

    @Test
    public void getAvailableTrucks() {
        OrderDTO orderDTO = null;
        Collection<Truck>  res = null;
        try {
            res = orderService.getAvailableTrucks(orderDTO);
        } catch (RouteException e) {
            e.printStackTrace();
            fail();
        }
        assertNull(res);
    }

    @Test
    public void getOrderRoute() {
        OrderDTO orderDTO = null;
        Truck truck = null;
        List<City> route = null;
        try {
            route = orderService.getOrderRoute(orderDTO,truck);
        } catch (RouteException e) {
            e.printStackTrace();
            fail();
        }
        assertNull(route);
    }

    @Test
    public void createOrder() {
        OrderDTO orderDTO = new OrderDTO();
        UpdateMessageType res = null;
        try {
            res = orderService.createOrder(orderDTO);
        } catch (RouteException e) {
            e.printStackTrace();
            fail();
        }
        assertEquals(UpdateMessageType.ERROR_CAN_NOT_PARSE_ORDER_ID, res);
    }

    @Test
    public void createRealOrder() {
        City currentCity = cityRepository.getByName("Moscow");
        City cityTo = cityRepository.getByName("Petrozavodsk");
        Truck newTruck = new Truck("ln37465",3,3,TruckState.READY,currentCity);
        Truck createdTruck = truckRepository.create(newTruck.getRegistrationNumber(),newTruck.getNumOfDrivers(),newTruck.getCapacity(),newTruck.getState(),newTruck.getCurrentCity());
        Route createdRoute = routeRepository.create(currentCity, cityTo,500);
        Cargo c1 = cargoRepository.create("7738526452","unitTestCargo",5,CargoStatus.PREPARED,createdRoute);
        String[] cargosInOrder = {Integer.toString(c1.getId())};
        OrderDTO orderDTO = new OrderDTO("",null,"unitTestOrder",null,Integer.toString(createdTruck.getId()),cargosInOrder);
        ReturnValuesContainer<Order> res = null;
        try {
            res = orderService.createOrder(orderDTO,0);
        } catch (RouteException e) {
            e.printStackTrace();
            fail();
        }
        // delete part:
        orderService.deleteOrder(res.getReturnedValue().getId());
        cargoRepository.remove(c1.getId());
        routeRepository.remove(createdRoute.getId());
        truckRepository.remove(createdTruck.getId());
        assertEquals(UpdateMessageType.ORDER_CREATED, res.getUpdateMessageType());
    }

    @Test
    public void createOrder1() {
        OrderDTO orderDTO = new OrderDTO();
        Truck truck = new Truck();
        ReturnValuesContainer<Order> res = null;
        try {
            res = orderService.createOrder(orderDTO,0);
        } catch (RouteException e) {
            e.printStackTrace();
            fail();
        }
        assertEquals(res.getUpdateMessageType(),UpdateMessageType.ERROR_CAN_NOT_PARSE_ORDER_ID);
    }

    @Test
    public void updateOrder() {
        City currentCity = cityRepository.getByName("Moscow");
        City cityTo = cityRepository.getByName("Petrozavodsk");
        Truck newTruck = new Truck("ln37465",3,3,TruckState.READY,currentCity);
        Truck createdTruck = truckRepository.create(newTruck.getRegistrationNumber(),newTruck.getNumOfDrivers(),newTruck.getCapacity(),newTruck.getState(),newTruck.getCurrentCity());
        Route createdRoute = routeRepository.create(currentCity, cityTo,500);
        Cargo c1 = cargoRepository.create("7738526452","unitTestCargo",5,CargoStatus.PREPARED,createdRoute);
        String[] cargosInOrder = {Integer.toString(c1.getId())};
        OrderDTO orderDTO = new OrderDTO("",null,"unitTestOrder",null,Integer.toString(createdTruck.getId()),cargosInOrder);
        ReturnValuesContainer<Order> res = null;
        try {
            res = orderService.createOrder(orderDTO,0);
        } catch (RouteException e) {
            e.printStackTrace();
            fail();
        }
        Order createdOrder = res.getReturnedValue();
        OrderDTO updOrderDTO = new OrderDTO(Integer.toString(createdOrder.getId()),null,"unitTestOrderUpd",null,null,null);
        UpdateMessageType result = null;
        try {
            result = orderService.updateOrder(updOrderDTO);
        } catch (RouteException e) {
            e.printStackTrace();
            fail();
        } catch (TooManyHoursWorkedForOrderException e) {
            e.printStackTrace();
            fail();
        }

        // delete part:
        orderService.deleteOrder(res.getReturnedValue().getId());
        cargoRepository.remove(c1.getId());
        routeRepository.remove(createdRoute.getId());
        truckRepository.remove(createdTruck.getId());
        assertEquals(UpdateMessageType.ORDER_EDITED, result);
    }

    @Test
    public void getOrderStatusFromString() {
    }

    @Test
    public void areAllCargosDelivered() {
    }

    @Test
    public void deleteOrder() {
    }

    @Test
    public void deleteOrder1() {
    }

    @Test
    public void getExecutingTime() {
    }

    @Test
    public void getRoutes() {
    }
}