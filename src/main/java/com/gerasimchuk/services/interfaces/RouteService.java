package com.gerasimchuk.services.interfaces;

import com.gerasimchuk.dto.RouteDTO;
import com.gerasimchuk.entities.Route;
import com.gerasimchuk.enums.UpdateMessageType;
import com.gerasimchuk.utils.ReturnValuesContainer;

public interface RouteService {
    boolean createRoute(RouteDTO routeDTO);
    ReturnValuesContainer<Route> createRoute(RouteDTO routeDTO, int val);
    boolean updateRoute(RouteDTO routeDTO);
    ReturnValuesContainer<Route>  updateRoute(RouteDTO routeDTO, int val);
    boolean deleteRoute(int routeId) throws Exception;

    UpdateMessageType deleteRoute(int routeId, int val);
}
