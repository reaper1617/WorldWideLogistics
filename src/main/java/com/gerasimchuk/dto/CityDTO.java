package com.gerasimchuk.dto;

/** City Data Transfer Object
 * @author Reaper
 * @version 1.0
 */

public class CityDTO {
    private String id;
    private String name;
    private String hasAgency;
    private String[] driversInCity;
    private String[] trucksInCity;

    public CityDTO() {
    }

    public CityDTO(String id, String name, String hasAgency) {
        this.id = id;
        this.name = name;
        this.hasAgency = hasAgency;
    }

    public CityDTO(String id, String name, String hasAgency, String[] driversInCity, String[] trucksInCity) {
        this.id = id;
        this.name = name;
        this.hasAgency = hasAgency;
        this.driversInCity = driversInCity;
        this.trucksInCity = trucksInCity;
    }

    public String[] getDriversInCity() {
        return driversInCity;
    }

    public void setDriversInCity(String[] driversInCity) {
        this.driversInCity = driversInCity;
    }

    public String[] getTrucksInCity() {
        return trucksInCity;
    }

    public void setTrucksInCity(String[] trucksInCity) {
        this.trucksInCity = trucksInCity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHasAgency() {
        return hasAgency;
    }

    public void setHasAgency(String hasAgency) {
        this.hasAgency = hasAgency;
    }


}
