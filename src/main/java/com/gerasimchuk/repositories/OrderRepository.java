package com.gerasimchuk.repositories;

import com.gerasimchuk.entities.Order;
import com.gerasimchuk.entities.Truck;
import com.gerasimchuk.enums.OrderStatus;

import java.util.Collection;

public interface OrderRepository {
    Order create(String personalNumber,
                 String description,
                 String date,
                 OrderStatus status,
                 Truck assignedTruck);
    Order update(int id,
                 String personalNumber,
                 String description,
                 String date,
                 OrderStatus status,
                 Truck assignedTruck);
    Order getById(int id);
    Order getByPersonalNumber(String personalNumber);
    Collection<Order> getAll();

    Collection<Order> getOrdersForOnePage(int size, int pageNumber);
    void remove(int id);
}
