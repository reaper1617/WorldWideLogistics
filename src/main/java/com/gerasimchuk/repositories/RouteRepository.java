package com.gerasimchuk.repositories;

import com.gerasimchuk.entities.City;
import com.gerasimchuk.entities.Route;

import java.util.Collection;

public interface RouteRepository {
    Route create(City cityFrom, City cityTo, double distance);
    Route update(int id,City cityFrom, City cityTo, double distance);
    Route getById(int id);
    Route getByCities(City cityFrom, City cityTo);
    Collection<Route> getAll();
    void remove(int id);

    //
    Collection<Route> getRoutesForOnePage(int size, int pageNumber);
}
