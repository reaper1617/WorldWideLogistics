package com.gerasimchuk.entities;


import com.gerasimchuk.enums.TruckState;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "Trucks")
@Table(name = "trucks", schema = "mywwldatabase", catalog = "")
public class Truck {

    private int id;
    private String registrationNumber;
    private int numOfDrivers;
    private double capacity;
    private TruckState state;
    private City currentCity;
    private Set<Driver> driverInTruck;
    private Order assignedOrder;

    public Truck() {
    }

    public Truck(String registrationNumber, int numOfDrivers, double capacity, TruckState state, City currentCity) {
        this.registrationNumber = registrationNumber;
        this.numOfDrivers = numOfDrivers;
        this.capacity = capacity;
        this.state = state;
        this.currentCity = currentCity;
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

    @Column(name = "registration_number", nullable = false, unique = true)
    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    @Column(name = "num_of_drivers", nullable = false)
    public int getNumOfDrivers() {
        return numOfDrivers;
    }

    public void setNumOfDrivers(int numOfDrivers) {
        this.numOfDrivers = numOfDrivers;
    }

    @Column(name = "capacity", nullable = false)
    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    @Column(name = "state", nullable = false)
    @Enumerated(value = EnumType.STRING)
    public TruckState getState() {
        return state;
    }

    public void setState(TruckState state) {
        this.state = state;
    }

    @ManyToOne
    @JoinColumn(name = "current_city_id", nullable = false)
    public City getCurrentCity() {
        return currentCity;
    }

    public void setCurrentCity(City currentCity) {
        this.currentCity = currentCity;
    }

    @OneToMany(mappedBy = "currentTruck")
    public Set<Driver> getDriverInTruck() {
        return driverInTruck;
    }

    public void setDriverInTruck(Set<Driver> driverInTruck) {
        this.driverInTruck = driverInTruck;
    }

    @OneToOne(mappedBy = "assignedTruck")
    public Order getAssignedOrder() {
        return assignedOrder;
    }

    public void setAssignedOrder(Order assignedOrder) {
        this.assignedOrder = assignedOrder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Truck)) return false;

        Truck truck = (Truck) o;

        if (id != truck.id) return false;
        if (numOfDrivers != truck.numOfDrivers) return false;
        if (Double.compare(truck.capacity, capacity) != 0) return false;
        if (!registrationNumber.equals(truck.registrationNumber)) return false;
        if (state != truck.state) return false;
        return currentCity.equals(truck.currentCity);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + registrationNumber.hashCode();
        result = 31 * result + numOfDrivers;
        temp = Double.doubleToLongBits(capacity);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + state.hashCode();
        result = 31 * result + currentCity.hashCode();
        return result;
    }

    //todo: onetomany with drivers
}
