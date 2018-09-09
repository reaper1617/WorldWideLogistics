package com.gerasimchuk.dto;

import java.util.Arrays;

/** Order Data Transfer Object
 * @author Reaper
 * @version 1.0
 */

public class OrderDTO {
    private String personalNumber;
    private String description;
    private String dateAndTime;
    private String status;
    private String assignedTruckRegistrationNumber;
    private String[] assignedDrivers;
    private String[] route;

    public OrderDTO() {
    }

    public OrderDTO(String personalNumber,
                    String description,
                    String dateAndTime,
                    String status,
                    String assignedTruckRegistrationNumber,
                    String[] assignedDrivers,
                    String[] route) {
        this.personalNumber = personalNumber;
        this.description = description;
        this.dateAndTime = dateAndTime;
        this.status = status;
        this.assignedTruckRegistrationNumber = assignedTruckRegistrationNumber;
        this.assignedDrivers = assignedDrivers;
        this.route = route;
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

    public String getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(String dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAssignedTruckRegistrationNumber() {
        return assignedTruckRegistrationNumber;
    }

    public void setAssignedTruckRegistrationNumber(String assignedTruckRegistrationNumber) {
        this.assignedTruckRegistrationNumber = assignedTruckRegistrationNumber;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderDTO)) return false;

        OrderDTO orderDTO = (OrderDTO) o;

        if (personalNumber != null ? !personalNumber.equals(orderDTO.personalNumber) : orderDTO.personalNumber != null)
            return false;
        if (!description.equals(orderDTO.description)) return false;
        if (!dateAndTime.equals(orderDTO.dateAndTime)) return false;
        if (!status.equals(orderDTO.status)) return false;
        if (assignedTruckRegistrationNumber != null ? !assignedTruckRegistrationNumber.equals(orderDTO.assignedTruckRegistrationNumber) : orderDTO.assignedTruckRegistrationNumber != null)
            return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        if (!Arrays.equals(assignedDrivers, orderDTO.assignedDrivers)) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(route, orderDTO.route);
    }

    @Override
    public int hashCode() {
        int result = personalNumber != null ? personalNumber.hashCode() : 0;
        result = 31 * result + description.hashCode();
        result = 31 * result + dateAndTime.hashCode();
        result = 31 * result + status.hashCode();
        result = 31 * result + (assignedTruckRegistrationNumber != null ? assignedTruckRegistrationNumber.hashCode() : 0);
        result = 31 * result + (assignedDrivers != null ? assignedDrivers.hashCode() : 0);
        result = 31 * result + (route != null ? route.hashCode() : 0);
        return result;
    }
}
