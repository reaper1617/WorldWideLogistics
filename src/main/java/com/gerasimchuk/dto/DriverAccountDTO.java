package com.gerasimchuk.dto;


/** Driver Account Data Transfer Object
 * @author Reaper
 * @version 1.0
 */

public class DriverAccountDTO {
    private String driverId; // its user id !!
    private String driverStatus;
    private String orderId;
    private String orderStatus;
    private String cargoId;
    private String cargoStatus;

    public DriverAccountDTO() {
    }

    public DriverAccountDTO(String driverId, String driverStatus, String orderId, String orderStatus, String cargoId, String cargoStatus) {
        this.driverId = driverId;
        this.driverStatus = driverStatus;
        this.orderId = orderId;
        this.orderStatus = orderStatus;
        this.cargoId = cargoId;
        this.cargoStatus = cargoStatus;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public String getDriverStatus() {
        return driverStatus;
    }

    public void setDriverStatus(String driverStatus) {
        this.driverStatus = driverStatus;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getCargoId() {
        return cargoId;
    }

    public void setCargoId(String cargoId) {
        this.cargoId = cargoId;
    }

    public String getCargoStatus() {
        return cargoStatus;
    }

    public void setCargoStatus(String cargoStatus) {
        this.cargoStatus = cargoStatus;
    }
}
