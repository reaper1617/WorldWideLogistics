package com.gerasimchuk.services.interfaces;

import com.gerasimchuk.dto.CityDTO;


public interface CityService {
    boolean createCity(CityDTO cityDTO);
    boolean updateCity(CityDTO cityDTO);
    boolean deleteCity(int cityId) throws Exception;
}
