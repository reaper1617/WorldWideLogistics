package com.gerasimchuk.services.interfaces;

import com.gerasimchuk.dto.CityDTO;
import com.gerasimchuk.enums.UpdateMessageType;


public interface CityService {
    boolean createCity(CityDTO cityDTO);
    boolean updateCity(CityDTO cityDTO);
    boolean deleteCity(int cityId) throws Exception;
    UpdateMessageType deleteCity(int cityId, int val);
}
