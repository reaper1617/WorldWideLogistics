package com.gerasimchuk.converters;

import com.gerasimchuk.dto.OrderDTO;
import com.gerasimchuk.entities.Cargo;
import com.gerasimchuk.entities.Order;
import com.gerasimchuk.enums.OrderStatus;

/** Order to Data Transfer Object converter
 * @author Reaper
 * @version 1.0
 */

public class OrderToDTOConverter {

    private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(OrderToDTOConverter.class);

    public static OrderDTO convert(Order order){
        LOGGER.info("Class: OrderToDTOConvert, method: convert");
        LOGGER.info("Trying to convert order " + order.getDescription() + " to OrderDTO");
        String id = Integer.toString(order.getId());
        LOGGER.info("Id field is: " + id);
        String personalNumber = order.getPersonalNumber();
        LOGGER.info("personalNumber  field is: " + personalNumber);
        String description = order.getDescription();
        LOGGER.info("description field is: " + description);
        String status = getOrderStatus(order);
        LOGGER.info("dtatus  field is: " + status);
        String assignedTruckId = null;
        if (order.getAssignedTruck() != null) {
            assignedTruckId = Integer.toString(order.getAssignedTruck().getId());
        }
        LOGGER.info("assignedTruckId field is: " + assignedTruckId);
        String[] cargosInOrder = null;
        if (order.getCargosInOrder() != null && !order.getCargosInOrder().isEmpty()) {
            cargosInOrder = new String[order.getCargosInOrder().size()];
            Object[] cargosInOrderArray = order.getCargosInOrder().toArray();
            if (cargosInOrderArray == null || cargosInOrderArray.length == 0) return null;
            for (int i = 0; i < cargosInOrder.length; i++) {
                cargosInOrder[i] = Integer.toString(((Cargo) cargosInOrderArray[i]).getId());
            }
        }
        LOGGER.info("cargosInOrder field is: " + cargosInOrder);
        OrderDTO orderDTO = new OrderDTO(id,personalNumber,description,status,assignedTruckId,cargosInOrder);
        LOGGER.info("Created orderDTO: " + orderDTO);
        return orderDTO;
    }


    private static String getOrderStatus(Order order){
        LOGGER.info("Class: OrderToDTOConvert, method: getOrderStatus");
        OrderStatus status = order.getStatus();
        if (status.equals(OrderStatus.NOT_PREPARED)){
            LOGGER.info("Defined status: Not prepared");
            return "Not prepared";}
        if (status.equals(OrderStatus.PREPARED)){
            LOGGER.info("Defined status: Prepared");
            return "Prepared";
        }
        if (status.equals(OrderStatus.EXECUTING)){
            LOGGER.info("Defined status: Executing");
            return "Executing";
        }
        if (status.equals(OrderStatus.EXECUTED)) {
            LOGGER.info("Defined status: Executed");
            return "Executed";
        }
        LOGGER.info("Status undefined: returned \"Not prepared\" value");
        return "Not prepared";
    }
}
