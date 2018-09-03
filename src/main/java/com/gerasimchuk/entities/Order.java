package com.gerasimchuk.entities;


import com.gerasimchuk.enums.OrderStatus;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "Orders")
@Table(name = "orders", schema = "mywwldatabase", catalog = "")
public class Order {
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

    @OneToMany(mappedBy = "order")
    public Set<Cargo> getCargosInOrder() {
        return cargosInOrder;
    }

    public void setCargosInOrder(Set<Cargo> cargosInOrder) {
        this.cargosInOrder = cargosInOrder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;

        Order order = (Order) o;

        if (id != order.id) return false;
        if (!personalNumber.equals(order.personalNumber)) return false;
        if (!description.equals(order.description)) return false;
        if (!date.equals(order.date)) return false;
        if (status != order.status) return false;
        return assignedTruck != null ? assignedTruck.equals(order.assignedTruck) : order.assignedTruck == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + personalNumber.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + date.hashCode();
        result = 31 * result + status.hashCode();
        result = 31 * result + (assignedTruck != null ? assignedTruck.hashCode() : 0);
        return result;
    }
}
