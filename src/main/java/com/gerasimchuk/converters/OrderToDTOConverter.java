package com.gerasimchuk.converters;

import com.gerasimchuk.dto.OrderDTO;
import com.gerasimchuk.entities.Cargo;
import com.gerasimchuk.entities.Order;
import com.gerasimchuk.enums.OrderStatus;

public class OrderToDTOConverter {

    public static OrderDTO convert(Order order){
        String id = Integer.toString(order.getId());
        String personalNumber = order.getPersonalNumber();
        String description = order.getDescription();
        String status = getOrderStatus(order);
        String assignedTruckId = null;
        if (order.getAssignedTruck() != null) {
            assignedTruckId = Integer.toString(order.getAssignedTruck().getId());
        }
        String[] cargosInOrder = null;
        if (order.getCargosInOrder() != null && !order.getCargosInOrder().isEmpty()) {
            cargosInOrder = new String[order.getCargosInOrder().size()];
            Object[] cargosInOrderArray = order.getCargosInOrder().toArray();
            if (cargosInOrderArray == null || cargosInOrderArray.length == 0) return null;
            for (int i = 0; i < cargosInOrder.length; i++) {
                cargosInOrder[i] = Integer.toString(((Cargo) cargosInOrderArray[i]).getId());
            }
        }
        OrderDTO orderDTO = new OrderDTO(id,personalNumber,description,status,assignedTruckId,cargosInOrder);
        return orderDTO;
    }


    private static String getOrderStatus(Order order){
        OrderStatus status = order.getStatus();
        if (status.equals(OrderStatus.NOT_PREPARED)) return "Not prepared";
        if (status.equals(OrderStatus.PREPARED)) return "Prepared";
        if (status.equals(OrderStatus.EXECUTING)) return "Executing";
        if (status.equals(OrderStatus.EXECUTED)) return "Executed";
        return "Not prepared";
    }
}
