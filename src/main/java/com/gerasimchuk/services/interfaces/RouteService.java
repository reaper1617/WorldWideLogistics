package com.gerasimchuk.services.interfaces;

import com.gerasimchuk.dto.RouteDTO;

public interface RouteService {
    boolean createRoute(RouteDTO routeDTO);
    boolean updateRoute(RouteDTO routeDTO);
    boolean deleteRoute(int routeId) throws Exception;
}
