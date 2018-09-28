package com.gerasimchuk.repositories;

import com.gerasimchuk.entities.Order;
import com.gerasimchuk.entities.Truck;
import com.gerasimchuk.enums.OrderStatus;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/** Implementation of {@link OrderRepository} interface
 * @author Reaper
 * @version 1.0
 */

@Repository
@Transactional
public class OrderRepositoryImpl implements OrderRepository {

    private SessionFactory sessionFactory;
    private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(OrderRepositoryImpl.class);

    @Autowired
    public OrderRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public Order create(String personalNumber, String description, String date, OrderStatus status, Truck assignedTruck) {
        LOGGER.info("Class: " + this.getClass().getName() + " method: create");
        Order order = new Order(personalNumber, description, date, status, assignedTruck);
        sessionFactory.getCurrentSession().persist(order);
        LOGGER.info("Persisted order: " + order.getDescription());
        return order;
    }

    @Transactional
    public Order update(int id, String personalNumber, String description, String date, OrderStatus status, Truck assignedTruck) {
        LOGGER.info("Class: " + this.getClass().getName() + " method: update");
        Order updated = sessionFactory.getCurrentSession().get(Order.class,id);
        updated.setPersonalNumber(personalNumber);
        updated.setDescription(description);
        updated.setDate(date);
        updated.setStatus(status);
        updated.setAssignedTruck(assignedTruck);
        sessionFactory.getCurrentSession().update(updated);
        LOGGER.info("Updated order: " + updated.getDescription());
        return updated;
    }

    @Transactional
    public Order getById(int id) {
        LOGGER.info("Class: " + this.getClass().getName() + " method: getById");
        Order res = sessionFactory.getCurrentSession().get(Order.class, id);
        LOGGER.info("Found order: " + res.getDescription());
        return res;
    }

    @Transactional
    public Collection<Order> getAll() {
        LOGGER.info("Class: " + this.getClass().getName() + " method: getAll");
        Collection<Order> res = sessionFactory.getCurrentSession().createQuery("from Orders", Order.class).getResultList();
        LOGGER.info("Found collection: " + res + ", size = " + res.size());
        return res;
    }

    @Transactional
    public void remove(int id) {
        LOGGER.info("Class: " + this.getClass().getName() + " method: remove");
        Order removed = sessionFactory.getCurrentSession().get(Order.class, id);
        sessionFactory.getCurrentSession().remove(removed);
        LOGGER.info("Removed order: " + removed.getDescription());
    }
}
