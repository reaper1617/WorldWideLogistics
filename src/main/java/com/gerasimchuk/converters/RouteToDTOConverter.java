package com.gerasimchuk.converters;

import com.gerasimchuk.dto.RouteDTO;
import com.gerasimchuk.entities.Route;

import java.util.List;

public interface RouteToDTOConverter {
    RouteDTO convert(Route route);
    List<RouteDTO> convert(List<Route> routes);
}
