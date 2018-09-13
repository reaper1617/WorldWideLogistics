package com.gerasimchuk.dto;

import java.util.Arrays;

/** Truck Data Transfer Object
 * @author Reaper
 * @version 1.0
 */

public class TruckDTO {
    private String id;
    private String registrationNumber;
    private String numberOfDrivers;
    private String capacity;
    private String state;
    private String currentCity;
    private String[] assignedDrivers;
    private String assignedOrderId;

    public TruckDTO() {
    }

    public TruckDTO(String id, String registrationNumber, String numberOfDrivers, String capacity, String state, String currentCity, String[] assignedDrivers, String assignedOrderId) {
        this.id = id;
        this.registrationNumber = registrationNumber;
        this.numberOfDrivers = numberOfDrivers;
        this.capacity = capacity;
        this.state = state;
        this.currentCity = currentCity;
        this.assignedDrivers = assignedDrivers;
        this.assignedOrderId = assignedOrderId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getNumberOfDrivers() {
        return numberOfDrivers;
    }

    public void setNumberOfDrivers(String numberOfDrivers) {
        this.numberOfDrivers = numberOfDrivers;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCurrentCity() {
        return currentCity;
    }

    public void setCurrentCity(String currentCity) {
        this.currentCity = currentCity;
    }

    public String[] getAssignedDrivers() {
        return assignedDrivers;
    }

    public void setAssignedDrivers(String[] assignedDrivers) {
        this.assignedDrivers = assignedDrivers;
    }

    public String getAssignedOrderId() {
        return assignedOrderId;
    }

    public void setAssignedOrderId(String assignedOrderId) {
        this.assignedOrderId = assignedOrderId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TruckDTO)) return false;

        TruckDTO truckDTO = (TruckDTO) o;

        if (!registrationNumber.equals(truckDTO.registrationNumber)) return false;
        if (!numberOfDrivers.equals(truckDTO.numberOfDrivers)) return false;
        if (!capacity.equals(truckDTO.capacity)) return false;
        if (!state.equals(truckDTO.state)) return false;
        if (!currentCity.equals(truckDTO.currentCity)) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        if (!Arrays.equals(assignedDrivers, truckDTO.assignedDrivers)) return false;
        return assignedOrderId != null ? assignedOrderId.equals(truckDTO.assignedOrderId) : truckDTO.assignedOrderId == null;
    }

    @Override
    public int hashCode() {
        int result = registrationNumber.hashCode();
        result = 31 * result + numberOfDrivers.hashCode();
        result = 31 * result + capacity.hashCode();
        result = 31 * result + state.hashCode();
        result = 31 * result + currentCity.hashCode();
        result = 31 * result + (assignedDrivers != null ? assignedDrivers.hashCode() : 0);
        result = 31 * result + (assignedOrderId != null ? assignedOrderId.hashCode() : 0);
        return result;
    }
}
