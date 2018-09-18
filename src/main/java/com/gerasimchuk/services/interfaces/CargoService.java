package com.gerasimchuk.services.interfaces;

import com.gerasimchuk.dto.CargoDTO;
import com.gerasimchuk.entities.Cargo;

import java.util.Collection;

/** Cargo Service
 * @author Reaper
 * @version 1.0
 */

public interface CargoService {

    boolean createCargo(CargoDTO cargoDTO);
    boolean updateCargo(CargoDTO cargoDTO);
    boolean deleteCargo(int cargoId);

    Collection<Cargo> getAvailableCargos();

}
