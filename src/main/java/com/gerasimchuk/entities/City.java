package com.gerasimchuk.entities;


import javax.persistence.*;
import java.util.Set;

@Entity(name = "Cities")
@Table(name = "cities", schema = "mywwldatabase", catalog = "")
public class City {

    private int id;
    private String name;
    private boolean hasAgency;
    private Set<Driver> driversInCity;
    private Set<City> citiesFrom;
    private Set<City> citiesTo;
    private Set<Truck> trucksInCity;

    public City() {
    }

    public City(String name, boolean hasAgency) {
        this.name = name;
        this.hasAgency = hasAgency;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "name", nullable = false, unique = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "has_agency", nullable = false)
    public boolean isHasAgency() {
        return hasAgency;
    }

    public void setHasAgency(boolean hasAgency) {
        this.hasAgency = hasAgency;
    }

    @OneToMany(mappedBy = "currentCity")
    public Set<Driver> getDriversInCity() {
        return driversInCity;
    }

    public void setDriversInCity(Set<Driver> driversInCity) {
        this.driversInCity = driversInCity;
    }

    @OneToMany(targetEntity = Route.class,mappedBy = "cityFrom")
    public Set<City> getCitiesFrom() {
        return citiesFrom;
    }

    public void setCitiesFrom(Set<City> citiesFrom) {
        this.citiesFrom = citiesFrom;
    }

    @OneToMany(targetEntity = Route.class, mappedBy = "cityTo")
    public Set<City> getCitiesTo() {
        return citiesTo;
    }

    public void setCitiesTo(Set<City> citiesTo) {
        this.citiesTo = citiesTo;
    }

    @OneToMany(mappedBy = "currentCity")
    public Set<Truck> getTrucksInCity() {
        return trucksInCity;
    }

    public void setTrucksInCity(Set<Truck> trucksInCity) {
        this.trucksInCity = trucksInCity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof City)) return false;

        City city = (City) o;

        if (id != city.id) return false;
        if (hasAgency != city.hasAgency) return false;
        return name != null ? name.equals(city.name) : city.name == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (hasAgency ? 1 : 0);
        return result;
    }
}
