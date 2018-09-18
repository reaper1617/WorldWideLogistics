package com.gerasimchuk.utils;

import com.gerasimchuk.entities.Cargo;
import com.gerasimchuk.entities.City;
import com.gerasimchuk.enums.CargoActionType;

import java.util.Map;

public class RoutePoint {
    private City city;
    private Map<Cargo,CargoActionType> cargoActionTypeMap;

    public RoutePoint() {
    }

    public RoutePoint(City city, Map<Cargo, CargoActionType> cargoActionTypeMap) {
        this.city = city;
        this.cargoActionTypeMap = cargoActionTypeMap;
    }

    public void addCargoToRoutePoint(Cargo cargo, CargoActionType cargoActionType){
        cargoActionTypeMap.put(cargo,cargoActionType);
    }


    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Map<Cargo, CargoActionType> getCargoActionTypeMap() {
        return cargoActionTypeMap;
    }

    public void setCargoActionTypeMap(Map<Cargo, CargoActionType> cargoActionTypeMap) {
        this.cargoActionTypeMap = cargoActionTypeMap;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoutePoint that = (RoutePoint) o;

        if (!city.equals(that.city)) return false;
        return cargoActionTypeMap.equals(that.cargoActionTypeMap);
    }

    @Override
    public int hashCode() {
        int result = city.hashCode();
        result = 31 * result + cargoActionTypeMap.hashCode();
        return result;
    }
}
