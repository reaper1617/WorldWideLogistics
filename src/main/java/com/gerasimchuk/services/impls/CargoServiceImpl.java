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

/** Implementation for {@link CargoService} interface
 * @author Reaper
 * @version 1.0
 */


@Service
public class CargoServiceImpl implements CargoService {

    CargoRepository cargoRepository;
    RouteRepository routeRepository;
    CityRepository cityRepository;
    DTOValidator dtoValidator;

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
            if (updated!=null){
                updateCargoWithFieldsFromDTO(updated, cargoDTO);
                return true;
            }
        }
        return false;
    }

    public boolean deleteCargo(int cargoId) {
        return false;
    }

    // ** util methods

    private CargoStatus getCargoStatusFromCargoDTO(CargoDTO cargoDTO){
        if (cargoDTO.getStatus() != null) {
            if (cargoDTO.getStatus().equals(CargoStatus.DELIVERED)) return CargoStatus.DELIVERED;
            if (cargoDTO.getStatus().equals(CargoStatus.LOADED)) return CargoStatus.LOADED;
            if (cargoDTO.getStatus().equals(CargoStatus.PREPARED)) return CargoStatus.PREPARED;
            if (cargoDTO.getStatus().equals(CargoStatus.SHIPPING)) return CargoStatus.SHIPPING;
        }
        return CargoStatus.PREPARED;
    }

    private void updateCargoWithFieldsFromDTO(Cargo updated, CargoDTO cargoDTO){
        String personalNumber = null;
        String name= null;
        double weight = 0;
        City cityFrom = null;
        City cityTo = null;
        CargoStatus status = null;
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
        if (cargoDTO.getStatus()!=null && cargoDTO.getStatus().length()!=0) status = getCargoStatusFromCargoDTO(cargoDTO);
        else status = updated.getStatus();
        Route route = routeRepository.getByCities(cityFrom, cityTo);
        if (route!=null){
            cargoRepository.update(updated.getId(),personalNumber,name,weight,status,route);
        }
    }
}
