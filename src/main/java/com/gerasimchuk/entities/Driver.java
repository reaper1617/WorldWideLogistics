package com.gerasimchuk.entities;

import com.gerasimchuk.enums.DriverStatus;

import javax.persistence.*;

@Entity(name = "Drivers")
@Table(name = "drivers", schema = "mywwldatabase", catalog = "")
public class Driver {

    private int id;
    private int hoursWorked;
    private DriverStatus status;
    private City currentCity;
    private Truck currentTruck;
    private User user;


    public Driver() {
    }

    public Driver(int hoursWorked, DriverStatus status, City currentCity, Truck currentTruck) {
        this.hoursWorked = hoursWorked;
        this.status = status;
        this.currentCity = currentCity;
        this.currentTruck = currentTruck;
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


    @Column(name = "hours_worked", nullable = false)
    public int getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(int hoursWorked) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Driver)) return false;

        Driver driver = (Driver) o;

        if (id != driver.id) return false;
        if (hoursWorked != driver.hoursWorked) return false;
        if (status != driver.status) return false;
        if (!currentCity.equals(driver.currentCity)) return false;
        return currentTruck != null ? currentTruck.equals(driver.currentTruck) : driver.currentTruck == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + hoursWorked;
        result = 31 * result + status.hashCode();
        result = 31 * result + currentCity.hashCode();
        result = 31 * result + (currentTruck != null ? currentTruck.hashCode() : 0);
        return result;
    }
}
