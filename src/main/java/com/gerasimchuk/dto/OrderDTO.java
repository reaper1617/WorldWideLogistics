package com.gerasimchuk.dto;

/** Order Data Transfer Object
 * @author Reaper
 * @version 1.0
 */

public class OrderDTO {
    private String id;
    private String personalNumber;
    private String description;
  //  private String dateAndTime;
    private String status;
    private String assignedTruckId;
  //  private String[] assignedDrivers;
  //  private String[] route;
    private String[] cargosInOrder;

    public OrderDTO() {
    }

    public OrderDTO(String id, String personalNumber, String description, String status, String assignedTruckId, String[] cargosInOrder) {
        this.id = id;
        this.personalNumber = personalNumber;
        this.description = description;
        this.status = status;
        this.assignedTruckId = assignedTruckId;
        this.cargosInOrder = cargosInOrder;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String[] getCargosInOrder() {
        return cargosInOrder;
    }

    public void setCargosInOrder(String[] cargosInOrder) {
        this.cargosInOrder = cargosInOrder;
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




}
