package com.gerasimchuk.converters;

import com.gerasimchuk.dto.CityDTO;
import com.gerasimchuk.entities.City;

import java.util.List;

public interface CityToDTOConverter {
    CityDTO convert(City city);
    List<CityDTO> convert(List<City> cities);

}
