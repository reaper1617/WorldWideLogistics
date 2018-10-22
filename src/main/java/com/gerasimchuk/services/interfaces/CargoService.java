package com.gerasimchuk.services.interfaces;

import com.gerasimchuk.dto.CargoDTO;
import com.gerasimchuk.entities.Cargo;
import com.gerasimchuk.enums.CargoStatus;
import com.gerasimchuk.enums.UpdateMessageType;
import com.gerasimchuk.utils.ReturnValuesContainer;

import java.util.Collection;

/** Cargo Service
 * @author Reaper
 * @version 1.0
 */

public interface CargoService {

    boolean createCargo(CargoDTO cargoDTO);
    //ReturnValuesContainer<Cargo> createCargo(CargoDTO cargoDTO, int val);
    boolean updateCargo(CargoDTO cargoDTO);
    boolean deleteCargo(int cargoId);
    UpdateMessageType deleteCargo(int cargoId, int val);

    Collection<Cargo> getAvailableCargos();
    CargoStatus getCargoStatusFromString(String status);
}
