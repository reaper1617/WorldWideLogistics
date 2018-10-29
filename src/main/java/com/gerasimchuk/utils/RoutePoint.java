package com.gerasimchuk.utils;

import com.gerasimchuk.entities.Cargo;
import com.gerasimchuk.entities.City;
import com.gerasimchuk.enums.CargoActionType;

import java.util.Map;

/**
 * The type Route point.
 */
public class RoutePoint {
    private City city;
    private Map<Cargo,CargoActionType> cargoActionTypeMap;

    /**
     * Instantiates a new Route point.
     */
    public RoutePoint() {
    }

    /**
     * Instantiates a new Route point.
     *
     * @param city               the city
     * @param cargoActionTypeMap the cargo action type map
     */
    public RoutePoint(City city, Map<Cargo, CargoActionType> cargoActionTypeMap) {
        this.city = city;
        this.cargoActionTypeMap = cargoActionTypeMap;
    }

    /**
     * Add cargo to route point.
     *
     * @param cargo           the cargo
     * @param cargoActionType the cargo action type
     */
    public void addCargoToRoutePoint(Cargo cargo, CargoActionType cargoActionType){
        cargoActionTypeMap.put(cargo,cargoActionType);
    }


    /**
     * Gets city.
     *
     * @return the city
     */
    public City getCity() {
        return city;
    }

    /**
     * Sets city.
     *
     * @param city the city
     */
    public void setCity(City city) {
        this.city = city;
    }

    /**
     * Gets cargo action type map.
     *
     * @return the cargo action type map
     */
    public Map<Cargo, CargoActionType> getCargoActionTypeMap() {
        return cargoActionTypeMap;
    }

    /**
     * Sets cargo action type map.
     *
     * @param cargoActionTypeMap the cargo action type map
     */
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
