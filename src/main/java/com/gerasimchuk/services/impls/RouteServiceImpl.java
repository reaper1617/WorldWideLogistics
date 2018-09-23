package com.gerasimchuk.services.impls;

import com.gerasimchuk.dto.RouteDTO;
import com.gerasimchuk.entities.City;
import com.gerasimchuk.entities.Route;
import com.gerasimchuk.repositories.CityRepository;
import com.gerasimchuk.repositories.RouteRepository;
import com.gerasimchuk.services.interfaces.RouteService;
import com.gerasimchuk.validators.DTOValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RouteServiceImpl implements RouteService {

    private RouteRepository routeRepository;
    private CityRepository cityRepository;
    private DTOValidator dtoValidator;

    @Autowired
    public RouteServiceImpl(RouteRepository routeRepository, CityRepository cityRepository, DTOValidator dtoValidator) {
        this.routeRepository = routeRepository;
        this.cityRepository = cityRepository;
        this.dtoValidator = dtoValidator;
    }

    @Override
    public boolean createRoute(RouteDTO routeDTO) {
        if (!dtoValidator.validate(routeDTO)) return false;
        String cityFrom = routeDTO.getCityFrom();
        String cityTo = routeDTO.getCityTo();
        if (cityFrom == null || cityTo == null || cityFrom.equals(cityTo)) return false;
        City cFrom = cityRepository.getByName(cityFrom);
        City cTo = cityRepository.getByName(cityTo);

        double distance = 0;
        try {
            distance = Double.parseDouble(routeDTO.getDistance());
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
        if (distance == 0) return false;
        routeRepository.create(cFrom,cTo,distance);
        return true;
    }

    @Override
    public boolean updateRoute(RouteDTO routeDTO) {
        if (!dtoValidator.validate(routeDTO)) return false;
        if (routeDTO.getId() == null || routeDTO.getId().length()==0) return false;

        int id = 0;
        try {
            id = Integer.parseInt(routeDTO.getId());
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
        if (id == 0) return false;
        Route updated = routeRepository.getById(id);
        if (updated == null) return false;

        City cityFrom = null;
        City cityTo = null;
        double distance = 0;
        if (routeDTO.getCityFrom()!=null
                && routeDTO.getCityFrom().length()!=0
                && !routeDTO.getCityFrom().equals("No cities available")) cityFrom = cityRepository.getByName(routeDTO.getCityFrom());
        else cityFrom = updated.getCityFrom();
        if (routeDTO.getCityTo()!=null
                && routeDTO.getCityTo().length()!=0
                && !routeDTO.getCityTo().equals("No cities available")) cityTo = cityRepository.getByName(routeDTO.getCityTo());
        else cityTo = updated.getCityTo();
        if (cityFrom.getName().equals(cityTo.getName())) return false;
        if (routeDTO.getDistance()!= null && routeDTO.getDistance().length()!=0) {
            try {
                distance = Double.parseDouble(routeDTO.getDistance());
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        else distance = updated.getDistance();
        routeRepository.update(updated.getId(),cityFrom,cityTo,distance);
        return true;
    }

    @Override
    public boolean deleteRoute(int routeId) throws  Exception{
        if (routeId <= 0) return false;
        Route deleted = routeRepository.getById(routeId);
        if (deleted == null) return false;
        routeRepository.remove(routeId);
        return true;
    }
}
