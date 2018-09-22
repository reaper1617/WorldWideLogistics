package com.gerasimchuk.services.impls;


import com.gerasimchuk.dto.CargoDTO;
import com.gerasimchuk.entities.Cargo;
import com.gerasimchuk.entities.City;
import com.gerasimchuk.entities.Route;
import com.gerasimchuk.enums.CargoStatus;
import com.gerasimchuk.repositories.CargoRepository;
import com.gerasimchuk.repositories.CityRepository;
import com.gerasimchuk.repositories.RouteRepository;
import com.gerasimchuk.services.interfaces.CargoService;
import com.gerasimchuk.utils.PersonalNumberGenerator;
import com.gerasimchuk.validators.DTOValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

/** Implementation for {@link CargoService} interface
 * @author Reaper
 * @version 1.0
 */


@Service
public class CargoServiceImpl implements CargoService {

    private CargoRepository cargoRepository;
    private RouteRepository routeRepository;
    private CityRepository cityRepository;
    private DTOValidator dtoValidator;

    @Autowired
    public CargoServiceImpl(CargoRepository cargoRepository, RouteRepository routeRepository, CityRepository cityRepository, DTOValidator dtoValidator) {
        this.cargoRepository = cargoRepository;
        this.routeRepository = routeRepository;
        this.cityRepository = cityRepository;
        this.dtoValidator = dtoValidator;
    }

    public boolean createCargo(CargoDTO cargoDTO) {
        if (!dtoValidator.validate(cargoDTO)) return false;
        String personalNumber = PersonalNumberGenerator.generate(10);
        double weight = Double.parseDouble(cargoDTO.getWeight());
        CargoStatus status = getCargoStatusFromCargoDTO(cargoDTO);
        City cityFrom = cityRepository.getByName(cargoDTO.getCityFrom());
        City cityTo = cityRepository.getByName(cargoDTO.getCityTo());
        if (cityFrom!=null && cityTo!=null && !cityFrom.getName().equals(cityTo.getName())){
            Route route = routeRepository.getByCities(cityFrom,cityTo);
            if (route!=null){
                cargoRepository.create(personalNumber,cargoDTO.getName(),weight,status,route);
                return true;
            }
            else return false;
        }
        return false;
    }

    public boolean updateCargo(CargoDTO cargoDTO) {
        if (!dtoValidator.validate(cargoDTO)) return false;
        if (cargoDTO.getId()!=null && cargoDTO.getId().length()!=0){
            int id = Integer.parseInt(cargoDTO.getId());
            Cargo updated = cargoRepository.getById(id);
            if (updated.getOrder()!=null) return false;
            if (updated!=null){
                updateCargoWithFieldsFromDTO(updated, cargoDTO);
                return true;
            }
        }
        return false;
    }

    public boolean deleteCargo(int cargoId) {
        // TODO: implement logics!
        return false;
    }

    public Collection<Cargo> getAvailableCargos() {
        Collection<Cargo> cargos = cargoRepository.getAll();
        Collection<Cargo> availableCargos = new ArrayList<Cargo>();
        for (Cargo c: cargos){
            if (c.getOrder() == null && !c.getStatus().equals(CargoStatus.DELIVERED)) availableCargos.add(c);
        }
        return availableCargos;
    }

    public CargoStatus getCargoStatusFromString(String status) {
        if (status == null || status.length()==0)  return null;
        if (status.equals("PREPARED") ) return CargoStatus.PREPARED;
        if (status.equals("SHIPPING") ) return CargoStatus.SHIPPING;
        if (status.equals("LOADED") ) return CargoStatus.LOADED;
        if (status.equals("DELIVERED") ) return CargoStatus.DELIVERED;
        return null;
    }

    // ** util methods

    private CargoStatus getCargoStatusFromCargoDTO(CargoDTO cargoDTO){
        if (cargoDTO.getStatus() != null) {
            if (cargoDTO.getStatus().equals("Delivered")) return CargoStatus.DELIVERED;
            if (cargoDTO.getStatus().equals("Loaded")) return CargoStatus.LOADED;
            if (cargoDTO.getStatus().equals("Prepared")) return CargoStatus.PREPARED;
            if (cargoDTO.getStatus().equals("Shipping")) return CargoStatus.SHIPPING;
        }
        return CargoStatus.PREPARED;
    }

    private void updateCargoWithFieldsFromDTO(Cargo updated, CargoDTO cargoDTO){
        if (updated == null || cargoDTO == null) return;
        String personalNumber = null;
        String name= null;
        double weight = 0;
        City cityFrom = null;
        City cityTo = null;
        CargoStatus status = null;
        Route route = null;
        if (cargoDTO.getPersonalNumber()!=null && cargoDTO.getPersonalNumber().length()!=0) personalNumber = cargoDTO.getPersonalNumber();
        else personalNumber = updated.getPersonalNumber();
        if (cargoDTO.getName()!=null && cargoDTO.getName().length()!=0) name = cargoDTO.getName();
        else name = updated.getName();
        if (cargoDTO.getWeight()!=null && cargoDTO.getWeight().length()!=0) weight = Double.parseDouble(cargoDTO.getWeight());
        else weight = updated.getWeight();
        if (cargoDTO.getCityFrom()!=null && cargoDTO.getCityFrom().length()!=0) cityFrom = cityRepository.getByName(cargoDTO.getCityFrom());
        else cityFrom = updated.getRoute().getCityFrom();
        if (cargoDTO.getCityTo()!=null && cargoDTO.getCityTo().length()!=0) cityTo = cityRepository.getByName(cargoDTO.getCityTo());
        else cityTo = updated.getRoute().getCityTo();
        if (cargoDTO.getStatus()!=null && cargoDTO.getStatus().length()!=0 && !cargoDTO.getStatus().equals("Not selected")) status = getCargoStatusFromCargoDTO(cargoDTO);
        else status = updated.getStatus();
        if (cargoDTO.getCityFrom()!=null && cargoDTO.getCityFrom().length()!=0 && !cargoDTO.getCityFrom().equals("Not selected") && cargoDTO.getCityTo()!=null && cargoDTO.getCityTo().length()!=0 && !cargoDTO.getCityTo().equals("Not selected")) {
            cityFrom = cityRepository.getByName(cargoDTO.getCityFrom());
            cityTo = cityRepository.getByName(cargoDTO.getCityTo());
            route = routeRepository.getByCities(cityFrom, cityTo);
        }
        else route = updated.getRoute();
        cargoRepository.update(updated.getId(),personalNumber,name,weight,status,route);
    }
}
