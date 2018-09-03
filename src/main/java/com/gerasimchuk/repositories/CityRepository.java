package com.gerasimchuk.repositories;

import com.gerasimchuk.entities.City;

import java.util.Collection;

public interface CityRepository {
    City create(String name, boolean hasAgency);
    City update(int id,String name, boolean hasAgency);
    City getById(int id);
    Collection<City> getAll();
    void remove(int id);
}
