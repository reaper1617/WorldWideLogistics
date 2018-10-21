package com.gerasimchuk.converters;

import com.gerasimchuk.dto.CargoDTO;
import com.gerasimchuk.entities.Cargo;

import java.util.List;

public interface CargoToDTOConverter {
    CargoDTO convert(Cargo cargo);
    List<CargoDTO> convert(List<Cargo> cargoList);
}
