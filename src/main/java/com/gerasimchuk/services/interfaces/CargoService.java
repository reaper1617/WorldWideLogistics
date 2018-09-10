package com.gerasimchuk.services.interfaces;

import com.gerasimchuk.dto.CargoDTO;

/** Cargo Service
 * @author Reaper
 * @version 1.0
 */

public interface CargoService {

    boolean createCargo(CargoDTO cargoDTO);
    boolean updateCargo(CargoDTO cargoDTO);
    boolean deleteCargo(int cargoId);

}
