package com.gerasimchuk.services.interfaces;

import com.gerasimchuk.dto.OrderDTO;
import com.gerasimchuk.entities.Cargo;
import com.gerasimchuk.entities.City;
import com.gerasimchuk.entities.Order;
import com.gerasimchuk.entities.Truck;
import com.gerasimchuk.enums.OrderStatus;
import com.gerasimchuk.enums.UpdateMessageType;
import com.gerasimchuk.exceptions.driverexceptions.TooManyHoursWorkedForOrderException;
import com.gerasimchuk.exceptions.routeexceptions.RouteException;
import com.gerasimchuk.utils.ReturnValuesContainer;

import java.util.Collection;

/** Order Service
 * @author Reaper
 * @version 1.0
 */

public interface OrderService {

    Collection<Cargo> getChosenCargos(OrderDTO orderDTO);
    Collection<Truck> getAvailableTrucks(OrderDTO orderDTO) throws RouteException;
    Collection<City> getOrderRoute(OrderDTO orderDTO, Truck truck) throws RouteException;
//    Map<Order, Collection<City>> getRoutes(Collection<Order> orders);
//    Collection<Truck> getAvailableTrucks(List<Cargo> cargosInOrder);


    UpdateMessageType createOrder(OrderDTO orderDTO) throws RouteException;
    ReturnValuesContainer<Order> createOrder(OrderDTO orderDTO, int val) throws RouteException;
    UpdateMessageType updateOrder(OrderDTO orderDTO) throws RouteException, TooManyHoursWorkedForOrderException;
    OrderStatus getOrderStatusFromString(String status);
    boolean areAllCargosDelivered(Order order);
    UpdateMessageType deleteOrder(OrderDTO orderDTO);


    double getExecutingTime(OrderDTO orderDTO) throws RouteException;
}
