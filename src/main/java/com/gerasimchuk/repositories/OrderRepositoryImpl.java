package com.gerasimchuk.repositories;

import com.gerasimchuk.entities.Order;
import com.gerasimchuk.entities.Truck;
import com.gerasimchuk.enums.OrderStatus;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Repository
@Transactional
public class OrderRepositoryImpl implements OrderRepository {

    private SessionFactory sessionFactory;

    @Autowired
    public OrderRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public Order create(String personalNumber, String description, String date, OrderStatus status, Truck assignedTruck) {
        Order order = new Order(personalNumber, description, date, status, assignedTruck);
        sessionFactory.getCurrentSession().persist(order);
        return order;
    }

    @Transactional
    public Order update(int id, String personalNumber, String description, String date, OrderStatus status, Truck assignedTruck) {
        Order updated = sessionFactory.getCurrentSession().get(Order.class,id);
        updated.setPersonalNumber(personalNumber);
        updated.setDescription(description);
        updated.setDate(date);
        updated.setStatus(status);
        updated.setAssignedTruck(assignedTruck);
        sessionFactory.getCurrentSession().update(updated);
        return updated;
    }

    @Transactional
    public Order getById(int id) {
        return sessionFactory.getCurrentSession().get(Order.class, id);
    }

    @Transactional
    public Collection<Order> getAll() {
        return sessionFactory.getCurrentSession().createQuery("from Orders", Order.class).getResultList();
    }

    @Transactional
    public void remove(int id) {
        Order removed = sessionFactory.getCurrentSession().get(Order.class, id);
        sessionFactory.getCurrentSession().remove(removed);
    }
}
