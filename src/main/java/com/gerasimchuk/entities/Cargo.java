package com.gerasimchuk.entities;

import com.gerasimchuk.enums.CargoStatus;

import javax.persistence.*;

@Entity(name = "Cargos")
@Table(name = "cargos",schema = "mywwldatabase", catalog = "")
public class Cargo implements Comparable<Cargo> {
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


    public int compareTo(Cargo o) {
        if (this.id == o.getId()) return 0;
        else if (this.id > o.getId()) return 1;
        else return -1;
    }
}
