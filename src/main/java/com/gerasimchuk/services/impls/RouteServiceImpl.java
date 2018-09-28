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

/** Implementation for {@link RouteService} interface
 * @author Reaper
 * @version 1.0
 */


@Service
public class RouteServiceImpl implements RouteService {

    private RouteRepository routeRepository;
    private CityRepository cityRepository;
    private DTOValidator dtoValidator;
    private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(RouteServiceImpl.class);

    @Autowired
    public RouteServiceImpl(RouteRepository routeRepository, CityRepository cityRepository, DTOValidator dtoValidator) {
        this.routeRepository = routeRepository;
        this.cityRepository = cityRepository;
        this.dtoValidator = dtoValidator;
    }

    @Override
    public boolean createRoute(RouteDTO routeDTO) {
        LOGGER.info("Class: " + this.getClass().getName() + " method: createRoute");
        if (!dtoValidator.validate(routeDTO)) {
            LOGGER.error("Error: routeDTO is not valid.");
            return false;
        }
        String cityFrom = routeDTO.getCityFrom();
        String cityTo = routeDTO.getCityTo();
        if (cityFrom == null){
            LOGGER.error("Error: cityFrom is null.");
            return false;
        }
        if (cityTo == null){
            LOGGER.error("Error: cityTo is null.");
            return false;
        }
        if (cityFrom.equals(cityTo)){
            LOGGER.error("Error: cityFrom equals cityTo.");
            return false;
        }
        City cFrom = cityRepository.getByName(cityFrom);
        City cTo = cityRepository.getByName(cityTo);
        double distance = 0;
        try {
            distance = Double.parseDouble(routeDTO.getDistance());
        }
        catch (Exception e){
            e.printStackTrace();
            LOGGER.error("Error: cannot parse distance value.");
            return false;
        }
        if (distance == 0) {
            LOGGER.error("Error: distance value is not valid (distance =0).");
            return false;
        }
        Route r = routeRepository.create(cFrom,cTo,distance);
        LOGGER.info("Route with cityFrom = " + r.getCityFrom() + ", cityTo" + r.getCityTo() + " and distance = " + r.getDistance() + " created successfully.");
        return true;
    }

    @Override
    public boolean updateRoute(RouteDTO routeDTO) {
        LOGGER.info("Class: " + this.getClass().getName() + " method: updateRoute");
        if (!dtoValidator.validate(routeDTO)) {
            LOGGER.error("Error: routeDTO is not valid.");
            return false;
        }
        if (routeDTO.getId() == null || routeDTO.getId().length()==0){
            LOGGER.error("Error: id is null or empty.");
            return false;
        }
        int id = 0;
        try {
            id = Integer.parseInt(routeDTO.getId());
        }
        catch (Exception e){
            e.printStackTrace();
            LOGGER.error("Error: cannot parse id value.");
            return false;
        }
        if (id == 0) {
            LOGGER.error("Error: id value is not valid (id = 0).");
            return false;
        }
        Route updated = routeRepository.getById(id);
        if (updated == null) {
            LOGGER.error("Error: there is no route with id = " + id + " in database.");
            return false;
        }

        City cityFrom = null;
        City cityTo = null;
        double distance = 0;
        if (routeDTO.getCityFrom()!=null
                && routeDTO.getCityFrom().length()!=0
                && !routeDTO.getCityFrom().equals("No cities available")) cityFrom = cityRepository.getByName(routeDTO.getCityFrom());
        else cityFrom = updated.getCityFrom();
        LOGGER.info("New cityFrom = " + cityFrom);
        if (routeDTO.getCityTo()!=null
                && routeDTO.getCityTo().length()!=0
                && !routeDTO.getCityTo().equals("No cities available")) cityTo = cityRepository.getByName(routeDTO.getCityTo());
        else cityTo = updated.getCityTo();
        LOGGER.info("New cityTo = " + cityTo);
        if (cityFrom.getName().equals(cityTo.getName())){
            LOGGER.error("Error: cityFrom and cityTo are equal.");
            return false;
        }
        if (routeDTO.getDistance()!= null && routeDTO.getDistance().length()!=0) {
            try {
                distance = Double.parseDouble(routeDTO.getDistance());
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        else distance = updated.getDistance();
        LOGGER.info("New distance = " + distance);
        routeRepository.update(updated.getId(),cityFrom,cityTo,distance);
        LOGGER.info("Route id = " + updated.getId()
                + " cityFrom = "
                + updated.getCityFrom().getName()
                + " cityTo = " + updated.getCityTo().getName()
                + " distance = " + updated.getDistance()
                + " updated successfully.");
        return true;
    }

    @Override
    public boolean deleteRoute(int routeId) throws  Exception{
        LOGGER.info("Class: " + this.getClass().getName() + " method: deleteRoute");
        if (routeId <= 0) {
            LOGGER.error("Error: routeId value is not valid (id = " + routeId + ").");
            return false;
        }
        Route deleted = routeRepository.getById(routeId);
        if (deleted == null){
            LOGGER.error("Error: there is no route with id = " + routeId + " in database.");
            return false;
        }
        routeRepository.remove(routeId);
        LOGGER.info("Route id = " + routeId + "deleted successfully.");
        return true;
    }
}
