package com.gerasimchuk.services.interfaces;

import com.gerasimchuk.dto.OrderDTO;
import com.gerasimchuk.entities.Cargo;
import com.gerasimchuk.entities.City;
import com.gerasimchuk.entities.Order;
import com.gerasimchuk.entities.Truck;
import com.gerasimchuk.enums.OrderStatus;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/** Order Service
 * @author Reaper
 * @version 1.0
 */

public interface OrderService {

    Collection<Cargo> getChosenCargos(OrderDTO orderDTO);
    Collection<Truck> getAvailableTrucks(OrderDTO orderDTO);
    Collection<City> getOrderRoute(OrderDTO orderDTO);
//    Map<Order, Collection<City>> getRoutes(Collection<Order> orders);
//    Collection<Truck> getAvailableTrucks(List<Cargo> cargosInOrder);
    boolean createOrder(OrderDTO orderDTO);
    boolean updateOrder(OrderDTO orderDTO);
    OrderStatus getOrderStatusFromString(String status);
    boolean areAllCargosDelivered(Order order);
    boolean deleteOrder(OrderDTO orderDTO);
}
