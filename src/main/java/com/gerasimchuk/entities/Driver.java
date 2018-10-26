package com.gerasimchuk.entities;

import com.gerasimchuk.enums.DriverStatus;
import com.gerasimchuk.utils.JSONconvertable;
import com.google.gson.Gson;

import javax.persistence.*;

@Entity(name = "Drivers")
@Table(name = "drivers", schema = "mywwldatabase", catalog = "")
public class Driver implements JSONconvertable {

    private int id;
    private double hoursWorked;
    private DriverStatus status;
    private City currentCity;
    private Truck currentTruck;
    private User user;


    public Driver() {
    }

    public Driver(double hoursWorked, DriverStatus status, City currentCity, Truck currentTruck) {
        this.hoursWorked = hoursWorked;
        this.status = status;
        this.currentCity = currentCity;
        this.currentTruck = currentTruck;
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


    @Column(name = "hours_worked", nullable = false)
    public double getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(double hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    public DriverStatus getStatus() {
        return status;
    }

    public void setStatus(DriverStatus status) {
        this.status = status;
    }

    @ManyToOne
    @JoinColumn(name = "current_city_id", nullable = false)
    public City getCurrentCity() {
        return currentCity;
    }

    public void setCurrentCity(City currentCity) {
        this.currentCity = currentCity;
    }


    @ManyToOne
    @JoinColumn(name = "current_truck_id")
    public Truck getCurrentTruck() {
        return currentTruck;
    }

    public void setCurrentTruck(Truck currentTruck) {
        this.currentTruck = currentTruck;
    }

    @OneToOne(mappedBy = "driver")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    @Override
    public String convertToJSONString() {
        StringBuilder result = new StringBuilder("{");
        result.append("\"id\":\"").append(id).append("\",");
        result.append("\"hoursWorked\":\"").append(hoursWorked).append("\",");
        result.append("\"status\":\"").append(status).append("\",");
        result.append("\"currentCity\":\"").append(currentCity.getName()).append("\"");
        if (currentTruck != null) result.append(",\"currentTruck\":\"").append(currentTruck.getRegistrationNumber()).append("\"");
        result.append("}");
        return result.toString();
    }
}
