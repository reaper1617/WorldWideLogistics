package com.gerasimchuk.converters;

import com.gerasimchuk.dto.TruckDTO;
import com.gerasimchuk.entities.Truck;

import java.util.List;

public interface TruckToDTOConverter {
    TruckDTO convert(Truck truck);
    List<TruckDTO> convert(List<Truck> trucks);
}
