package com.gerasimchuk.entities;

import com.gerasimchuk.enums.DriverStatus;

import javax.persistence.*;

@Entity(name = "Drivers")
@Table(name = "drivers", schema = "mywwldatabase", catalog = "")
public class Driver {

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Driver)) return false;

        Driver driver = (Driver) o;

        if (id != driver.id) return false;
        if (Double.compare(driver.hoursWorked, hoursWorked) != 0) return false;
        if (status != driver.status) return false;
        if (!currentCity.equals(driver.currentCity)) return false;
        if (currentTruck != null ? !currentTruck.equals(driver.currentTruck) : driver.currentTruck != null)
            return false;
        return user != null ? user.equals(driver.user) : driver.user == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        temp = Double.doubleToLongBits(hoursWorked);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + status.hashCode();
        result = 31 * result + currentCity.hashCode();
        result = 31 * result + (currentTruck != null ? currentTruck.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }
}
