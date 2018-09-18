package com.gerasimchuk.utils;

import com.gerasimchuk.entities.Cargo;
import com.gerasimchuk.entities.City;
import com.gerasimchuk.entities.Order;
import com.gerasimchuk.entities.Truck;
import com.gerasimchuk.enums.OrderStatus;

import java.util.List;
import java.util.Set;

public class OrderWithRoute {
    private int id;
    private String personalNumber;
    private String description;
    private String date;
    private OrderStatus status;
    private Truck assignedTruck;
    private Set<Cargo> cargosInOrder;
    private List<City> route;

    public OrderWithRoute(int id, String personalNumber, String description, String date, OrderStatus status, Truck assignedTruck, Set<Cargo> cargosInOrder, List<City> route) {
        this.id = id;
        this.personalNumber = personalNumber;
        this.description = description;
        this.date = date;
        this.status = status;
        this.assignedTruck = assignedTruck;
        this.cargosInOrder = cargosInOrder;
        this.route = route;
    }

    public OrderWithRoute(Order order, List<City> route){
        this.id = order.getId();
        this.personalNumber = order.getPersonalNumber();
        this.description = order.getDescription();
        this.date = order.getDate();
        this.status = order.getStatus();
        this.assignedTruck = order.getAssignedTruck();
        this.cargosInOrder = order.getCargosInOrder();
        this.route = route;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPersonalNumber() {
        return personalNumber;
    }

    public void setPersonalNumber(String personalNumber) {
        this.personalNumber = personalNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Truck getAssignedTruck() {
        return assignedTruck;
    }

    public void setAssignedTruck(Truck assignedTruck) {
        this.assignedTruck = assignedTruck;
    }

    public Set<Cargo> getCargosInOrder() {
        return cargosInOrder;
    }

    public void setCargosInOrder(Set<Cargo> cargosInOrder) {
        this.cargosInOrder = cargosInOrder;
    }

    public List<City> getRoute() {
        return route;
    }

    public void setRoute(List<City> route) {
        this.route = route;
    }
}
