package com.gerasimchuk.converters;

import com.gerasimchuk.dto.GoogleMarkerDTO;
import com.gerasimchuk.dto.OrderWithRouteDTO;
import com.gerasimchuk.entities.Order;
import com.gerasimchuk.exceptions.routeexceptions.RouteException;

import java.util.Collection;

public interface OrderToDTOConverter {
    OrderWithRouteDTO convertToDTOWithRoute(Order order) throws RouteException;
    GoogleMarkerDTO convertToGoogleMarkerDto(Order order) throws RouteException;
    Collection<OrderWithRouteDTO> convertToDTOWithRouteCollection(Collection<Order> orders) throws RouteException;
}
