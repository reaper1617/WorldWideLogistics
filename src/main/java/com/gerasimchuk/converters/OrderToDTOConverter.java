package com.gerasimchuk.converters;

import com.gerasimchuk.dto.OrderWithRouteDTO;
import com.gerasimchuk.entities.Order;
import com.gerasimchuk.exceptions.routeexceptions.RouteException;

public interface OrderToDTOConverter {
    OrderWithRouteDTO convertToDTOWithRoute(Order order) throws RouteException;

}
