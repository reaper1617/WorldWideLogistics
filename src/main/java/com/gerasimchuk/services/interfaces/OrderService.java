package com.gerasimchuk.services.interfaces;

import com.gerasimchuk.dto.OrderDTO;
import com.gerasimchuk.entities.*;
import com.gerasimchuk.enums.OrderStatus;
import com.gerasimchuk.enums.UpdateMessageType;
import com.gerasimchuk.exceptions.driverexceptions.TooManyHoursWorkedForOrderException;
import com.gerasimchuk.exceptions.routeexceptions.RouteException;
import com.gerasimchuk.utils.ReturnValuesContainer;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/** Order Service
 * @author Reaper
 * @version 1.0
 */

public interface OrderService {

    Collection<Cargo> getChosenCargos(OrderDTO orderDTO);
    Collection<Truck> getAvailableTrucks(OrderDTO orderDTO) throws RouteException;
    List<City> getOrderRoute(OrderDTO orderDTO, Truck truck) throws RouteException;
//    Map<Order, Collection<City>> getRoutes(Collection<Order> orders);
//    Collection<Truck> getAvailableTrucks(List<Cargo> cargosInOrder);

    ReturnValuesContainer<List<Driver>> checkIfDriversHoursWorkedOverLimit(double orderExecutingTime, Date date, Collection<Driver> driversInTruck);
    UpdateMessageType createOrder(OrderDTO orderDTO) throws RouteException;
    ReturnValuesContainer<Order> createOrder(OrderDTO orderDTO, int val) throws RouteException;
    UpdateMessageType updateOrder(OrderDTO orderDTO) throws RouteException, TooManyHoursWorkedForOrderException;
    ReturnValuesContainer<Order> updateOrder(OrderDTO orderDTO, int val) throws RouteException, TooManyHoursWorkedForOrderException;
    OrderStatus getOrderStatusFromString(String status);
    boolean areAllCargosDelivered(Order order);
    UpdateMessageType deleteOrder(OrderDTO orderDTO);
    UpdateMessageType deleteOrder(int orderId);


    double getExecutingTime(OrderDTO orderDTO) throws RouteException;
}
