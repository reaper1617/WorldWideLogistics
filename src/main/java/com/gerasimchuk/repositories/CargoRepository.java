package com.gerasimchuk.repositories;

import com.gerasimchuk.entities.Cargo;
import com.gerasimchuk.entities.Order;
import com.gerasimchuk.entities.Route;
import com.gerasimchuk.enums.CargoStatus;

import java.util.Collection;

public interface CargoRepository {
    Cargo create(String personalNumber, String name, double weight, CargoStatus status, Route route);
    Cargo update(int id,String personalNumber, String name, double weight, CargoStatus status, Route route);
    Cargo update(int id,String personalNumber, String name, double weight, CargoStatus status, Route route, Order order);
    Cargo getById(int id);
    Collection<Cargo> getAll();
    void remove(int id);
}
