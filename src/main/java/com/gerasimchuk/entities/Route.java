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
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
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

    @OneToMany(mappedBy = "route")
    public Set<Cargo> getCargosOnRoute() {
        return cargosOnRoute;
    }

    public void setCargosOnRoute(Set<Cargo> cargosOnRoute) {
        this.cargosOnRoute = cargosOnRoute;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Route)) return false;

        Route route = (Route) o;

        if (id != route.id) return false;
        if (Double.compare(route.distance, distance) != 0) return false;
        if (!cityFrom.equals(route.cityFrom)) return false;
        return cityTo.equals(route.cityTo);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + cityFrom.hashCode();
        result = 31 * result + cityTo.hashCode();
        temp = Double.doubleToLongBits(distance);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
