package com.gerasimchuk.entities;


import com.gerasimchuk.enums.OrderStatus;
import com.gerasimchuk.utils.JSONconvertable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;



@Entity(name = "Orders")
@Table(name = "orders", schema = "mywwldatabase", catalog = "")
public class Order implements JSONconvertable {
    private int id;
    private String personalNumber;
    private String description;
    private String date;
    private OrderStatus status;
    private Truck assignedTruck;
    private Set<Cargo> cargosInOrder;

    public Order() {
    }

    public Order(String personalNumber,
                 String description,
                 String date,
                 OrderStatus status,
                 Truck assignedTruck) {
        this.personalNumber = personalNumber;
        this.description = description;
        this.date = date;
        this.status = status;
        this.assignedTruck = assignedTruck;
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

    @Column(name = "personal_number", nullable = false, unique = true)
    public String getPersonalNumber() {
        return personalNumber;
    }

    public void setPersonalNumber(String personalNumber) {
        this.personalNumber = personalNumber;
    }

    @Column(name = "description", nullable = false)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "date", nullable = false)
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    @OneToOne
    @JoinColumn(name = "assigned_truck_id")
    public Truck getAssignedTruck() {
        return assignedTruck;
    }

    public void setAssignedTruck(Truck assignedTruck) {
        this.assignedTruck = assignedTruck;
    }

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
    public Set<Cargo> getCargosInOrder() {
        return cargosInOrder;
    }

    public void setCargosInOrder(Set<Cargo> cargosInOrder) {
        this.cargosInOrder = cargosInOrder;
    }


    @Override
    public String convertToJSONString() {
        // todo: add fields (assignedTruck, cargosInOrders) to result!
        StringBuilder result = new StringBuilder();
        result.append("{");
        result.append("\"id\":").append("\"").append(id).append("\"").append(",");
        result.append("\"personalNumber\":").append("\"").append(personalNumber).append("\"").append(",");
        result.append("\"description\":").append("\"").append(description).append("\"").append(",");
        result.append("\"date\":").append("\"").append(date).append("\"").append(",");
        result.append("\"status\":").append("\"").append(status).append("\"").append("}");
        return result.toString();
    }
}
