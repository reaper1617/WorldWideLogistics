package com.gerasimchuk.services.impls;


import com.gerasimchuk.dto.CityDTO;
import com.gerasimchuk.entities.City;
import com.gerasimchuk.repositories.CityRepository;
import com.gerasimchuk.services.interfaces.CityService;
import com.gerasimchuk.validators.DTOValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;

@Service
public class CityServiceImpl implements CityService {

    private CityRepository cityRepository;
    private DTOValidator dtoValidator;

    @Autowired
    public CityServiceImpl(CityRepository cityRepository, DTOValidator dtoValidator) {
        this.cityRepository = cityRepository;
        this.dtoValidator = dtoValidator;
    }

    public boolean createCity(CityDTO cityDTO) {
        if (!dtoValidator.validate(cityDTO)) return false;
        boolean hasAgency = cityDTO.getHasAgency().equals("HAS");
        City city = cityRepository.create(cityDTO.getName(),hasAgency);
        if (city == null) return false;
        return true;
    }

    public boolean updateCity(CityDTO cityDTO) {
        if (!dtoValidator.validate(cityDTO)) return false;
        int id = 0;
        try{
            id = Integer.parseInt(cityDTO.getId());
        }
        catch (Exception e){
            e.printStackTrace();
           return false;
        }
        if (id == 0) return false;
        City city = cityRepository.getById(id);
        if (city == null) return false;
        String newName = null;
        boolean newHasAgency = false;
        if (cityDTO.getName()!= null && cityDTO.getName().length()!=0) newName = cityDTO.getName();
        else newName = city.getName();
        if (cityDTO.getHasAgency()!= null
                && cityDTO.getHasAgency().length()!=0
                && !cityDTO.getHasAgency().equals("Not selected")) newHasAgency = cityDTO.getHasAgency().equals("HAS");
        else newHasAgency = city.isHasAgency();
        cityRepository.update(city.getId(),newName, newHasAgency);
        return true;
    }

    public boolean deleteCity(int cityId) throws Exception {
        if (cityId <= 0) return false;
        City city = cityRepository.getById(cityId);
        if (city == null) return false;
        cityRepository.remove(cityId);
        return true;
    }
}
