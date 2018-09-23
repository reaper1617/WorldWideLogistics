package com.gerasimchuk.dto;

/** Cargo Data Transfer Object
 * @author Reaper
 * @version 1.0
 */

public class UserDTO {
    private String id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String personalNumber;
    private String password;
    private String role;
    private String hoursWorked;
    private String driverStatus;
    private String currentCityName;
    private String currentTruckRegistrationNumber;
    private String orderId;



    public UserDTO() {
    }

    public UserDTO(String id, String firstName, String middleName, String lastName, String personalNumber, String password, String role, String hoursWorked, String driverStatus, String currentCityName, String currentTruckRegistrationNumber, String orderId) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.personalNumber = personalNumber;
        this.password = password;
        this.role = role;
        this.hoursWorked = hoursWorked;
        this.driverStatus = driverStatus;
        this.currentCityName = currentCityName;
        this.currentTruckRegistrationNumber = currentTruckRegistrationNumber;
        this.orderId = orderId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPersonalNumber() {
        return personalNumber;
    }

    public void setPersonalNumber(String personalNumber) {
        this.personalNumber = personalNumber;
    }

    public String getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(String hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    public String getDriverStatus() {
        return driverStatus;
    }

    public void setDriverStatus(String driverStatus) {
        this.driverStatus = driverStatus;
    }

    public String getCurrentCityName() {
        return currentCityName;
    }

    public void setCurrentCityName(String currentCityName) {
        this.currentCityName = currentCityName;
    }

    public String getCurrentTruckRegistrationNumber() {
        return currentTruckRegistrationNumber;
    }

    public void setCurrentTruckRegistrationNumber(String currentTruckRegistrationNumber) {
        this.currentTruckRegistrationNumber = currentTruckRegistrationNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserDTO)) return false;

        UserDTO driverDTO = (UserDTO) o;

        if (!firstName.equals(driverDTO.firstName)) return false;
        if (!middleName.equals(driverDTO.middleName)) return false;
        if (!lastName.equals(driverDTO.lastName)) return false;
        if (personalNumber != null ? !personalNumber.equals(driverDTO.personalNumber) : driverDTO.personalNumber != null)
            return false;
        if (!hoursWorked.equals(driverDTO.hoursWorked)) return false;
        if (!driverStatus.equals(driverDTO.driverStatus)) return false;
        if (!currentCityName.equals(driverDTO.currentCityName)) return false;
        return currentTruckRegistrationNumber != null ? currentTruckRegistrationNumber.equals(driverDTO.currentTruckRegistrationNumber) : driverDTO.currentTruckRegistrationNumber == null;
    }

    @Override
    public int hashCode() {
        int result = firstName.hashCode();
        result = 31 * result + middleName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + (personalNumber != null ? personalNumber.hashCode() : 0);
        result = 31 * result + hoursWorked.hashCode();
        result = 31 * result + driverStatus.hashCode();
        result = 31 * result + currentCityName.hashCode();
        result = 31 * result + (currentTruckRegistrationNumber != null ? currentTruckRegistrationNumber.hashCode() : 0);
        return result;
    }
}
