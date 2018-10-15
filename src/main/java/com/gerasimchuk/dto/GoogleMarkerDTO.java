package com.gerasimchuk.dto;

public class GoogleMarkerDTO {
    private String id;
    private String personalNumber;
    private String description;
    private String date;
    private String status;
    private String assignedTruckId;
    private String assignedTruckRegistrationNumber;
    private String[] cargosInOrder;
    private String[] assignedDrivers;
    private String[] route;
    private String currentCity;

    public GoogleMarkerDTO() {
    }

    public GoogleMarkerDTO(String id,
                           String personalNumber,
                           String description,
                           String date,
                           String status,
                           String assignedTruckId,
                           String assignedTruckRegistrationNumber,
                           String[] cargosInOrder,
                           String[] assignedDrivers,
                           String[] route,
                           String currentCity) {
        this.id = id;
        this.personalNumber = personalNumber;
        this.description = description;
        this.date = date;
        this.status = status;
        this.assignedTruckId = assignedTruckId;
        this.assignedTruckRegistrationNumber = assignedTruckRegistrationNumber;
        this.cargosInOrder = cargosInOrder;
        this.assignedDrivers = assignedDrivers;
        this.route = route;
        this.currentCity = currentCity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPersonalNumber() {
        return personalNumber;
    }

    public void setPersonalNumber(String personalNumber) {
        this.personalNumber = personalNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAssignedTruckId() {
        return assignedTruckId;
    }

    public void setAssignedTruckId(String assignedTruckId) {
        this.assignedTruckId = assignedTruckId;
    }

    public String getAssignedTruckRegistrationNumber() {
        return assignedTruckRegistrationNumber;
    }

    public void setAssignedTruckRegistrationNumber(String assignedTruckRegistrationNumber) {
        this.assignedTruckRegistrationNumber = assignedTruckRegistrationNumber;
    }

    public String[] getCargosInOrder() {
        return cargosInOrder;
    }

    public void setCargosInOrder(String[] cargosInOrder) {
        this.cargosInOrder = cargosInOrder;
    }

    public String[] getAssignedDrivers() {
        return assignedDrivers;
    }

    public void setAssignedDrivers(String[] assignedDrivers) {
        this.assignedDrivers = assignedDrivers;
    }

    public String[] getRoute() {
        return route;
    }

    public void setRoute(String[] route) {
        this.route = route;
    }

    public String getCurrentCity() {
        return currentCity;
    }

    public void setCurrentCity(String currentCity) {
        this.currentCity = currentCity;
    }
}
