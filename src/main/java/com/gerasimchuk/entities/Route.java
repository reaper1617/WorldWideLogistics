package com.gerasimchuk.entities;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "Routes")
@Table(name = "routes", schema = "mywwldatabase", catalog = "")
public class Route {
    private int id;
    private City cityFrom;
    private City cityTo;
    private double distance;
    private Set<Cargo> cargosOnRoute;

    public Route() {
    }

    public Route(City cityFrom, City cityTo, double distance) {
        this.cityFrom = cityFrom;
        this.cityTo = cityTo;
        this.distance = distance;
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

    @ManyToOne
    @JoinColumn(name = "city_from_id", nullable = false)
    public City getCityFrom() {
        return cityFrom;
    }

    public void setCityFrom(City cityFrom) {
        this.cityFrom = cityFrom;
    }

    @ManyToOne
    @JoinColumn(name = "city_to_id", nullable = false)
    public City getCityTo() {
        return cityTo;
    }

    public void setCityTo(City cityTo) {
        this.cityTo = cityTo;
    }

    @Column(name = "distance", nullable = false)
    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    @OneToMany(mappedBy = "route", fetch = FetchType.EAGER)
    public Set<Cargo> getCargosOnRoute() {
        return cargosOnRoute;
    }

    public void setCargosOnRoute(Set<Cargo> cargosOnRoute) {
        this.cargosOnRoute = cargosOnRoute;
    }

}
