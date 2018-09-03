package com.gerasimchuk.entities;

import com.gerasimchuk.enums.CargoStatus;

import javax.persistence.*;

@Entity(name = "Cargos")
@Table(name = "cargos",schema = "mywwldatabase", catalog = "")
public class Cargo {
    private int id;
    private String personalNumber;
    private String name;
    private double weight;
    private CargoStatus status;
    private Route route;
    private Order order;

    public Cargo() {
    }

    public Cargo(String personalNumber, String name, double weight, CargoStatus status, Route route) {
        this.personalNumber = personalNumber;
        this.name = name;
        this.weight = weight;
        this.status = status;
        this.route = route;
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

    @Column(name = "personal_number", nullable = false, unique = true)
    public String getPersonalNumber() {
        return personalNumber;
    }

    public void setPersonalNumber(String personalNumber) {
        this.personalNumber = personalNumber;
    }

    @Column(name = "name", nullable = false, unique = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "weight", nullable = false)
    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    public CargoStatus getStatus() {
        return status;
    }

    public void setStatus(CargoStatus status) {
        this.status = status;
    }

    @ManyToOne
    @JoinColumn(name = "route_id", nullable = false)
    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    @ManyToOne
    @JoinColumn(name = "assigned_order_id")
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cargo)) return false;

        Cargo cargo = (Cargo) o;

        if (id != cargo.id) return false;
        if (Double.compare(cargo.weight, weight) != 0) return false;
        if (!personalNumber.equals(cargo.personalNumber)) return false;
        if (!name.equals(cargo.name)) return false;
        if (status != cargo.status) return false;
        if (!route.equals(cargo.route)) return false;
        return order != null ? order.equals(cargo.order) : cargo.order == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + personalNumber.hashCode();
        result = 31 * result + name.hashCode();
        temp = Double.doubleToLongBits(weight);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + status.hashCode();
        result = 31 * result + route.hashCode();
        result = 31 * result + (order != null ? order.hashCode() : 0);
        return result;
    }
}
