package com.gerasimchuk.services.impls;

import com.gerasimchuk.dto.AdminDTO;
import com.gerasimchuk.dto.DriverDTO;
import com.gerasimchuk.dto.ManagerDTO;
import com.gerasimchuk.entities.*;
import com.gerasimchuk.enums.DriverStatus;
import com.gerasimchuk.repositories.*;
import com.gerasimchuk.services.interfaces.UserService;
import com.gerasimchuk.utils.PersonalNumberGenerator;
import com.gerasimchuk.validators.DTOValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collection;

/** Implementation for {@link UserService} interface
 * @author Reaper
 * @version 1.0
 */

public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private DriverRepository driverRepository;
    private ManagerRepository managerRepository;
    private AdminRepository adminRepository;
    private CityRepository cityRepository;
    private TruckRepository truckRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private DTOValidator dtoValidator;


    @Autowired
    public UserServiceImpl(UserRepository userRepository, DriverRepository driverRepository, ManagerRepository managerRepository, AdminRepository adminRepository, CityRepository cityRepository, TruckRepository truckRepository, BCryptPasswordEncoder bCryptPasswordEncoder, DTOValidator dtoValidator) {
        this.userRepository = userRepository;
        this.driverRepository = driverRepository;
        this.managerRepository = managerRepository;
        this.adminRepository = adminRepository;
        this.cityRepository = cityRepository;
        this.truckRepository = truckRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.dtoValidator = dtoValidator;
    }

    public boolean createDriver(DriverDTO driverDTO) {
        if (!dtoValidator.validate(driverDTO)) return false;
        double hoursWorkedVal = getHoursWorkedValFromDriverDTO(driverDTO);
        DriverStatus driverStatusVal = getDriverStatusValFromDriverDTO(driverDTO);
        City city = getCurrentCityFromDriverDTO(driverDTO);
        Truck truck = getCurrentTruckFromDriverDTO(driverDTO);
        Driver driver = driverRepository.create(hoursWorkedVal,driverStatusVal,city,truck);
        String personalNumber = PersonalNumberGenerator.generate(10);
        String password = bCryptPasswordEncoder.encode(driverDTO.getPassword());
        User user = userRepository
                .create(driverDTO.getFirstName(),
                driverDTO.getMiddleName(),
                driverDTO.getLastName(),
                personalNumber,
                password,
                driver,
                null,
                null);
        return  true;
    }

    public boolean createManager(ManagerDTO managerDTO) {
        if (!dtoValidator.validate(managerDTO)) return false;
        Manager manager = managerRepository.create();
        User user = userRepository
                .create(managerDTO.getFirstName(),
                        managerDTO.getMiddleName(),
                        managerDTO.getLastName(),
                        PersonalNumberGenerator.generate(10),
                        bCryptPasswordEncoder.encode(managerDTO.getPassword()),
                        null,
                        manager,
                        null);
        return true;
    }

    public boolean createAdmin(AdminDTO adminDTO) {
        if (!dtoValidator.validate(adminDTO)) return false;
        Admin admin = adminRepository.create();
        User user = userRepository
                .create(adminDTO.getFirstName(),
                        adminDTO.getMiddleName(),
                        adminDTO.getLastName(),
                        PersonalNumberGenerator.generate(10),
                        bCryptPasswordEncoder.encode(adminDTO.getPassword()),
                        null,
                        null,
                        admin);
        return true;
    }

    public boolean updateDriver(DriverDTO driverDTO) {
        if (!dtoValidator.validate(driverDTO)) return false;

        User updated = null;
        if (driverDTO.getId()!=null) {
            if (driverDTO.getId().length() != 0) {
                if (getIdValFromDTO(driverDTO.getId()) != 0) {
                    updated = userRepository.getByDriverId(getIdValFromDTO(driverDTO.getId()));
                }
            }
        }
        if (updated!=null){
            updateDriverWithFieldsFromDTO(updated, driverDTO);
            return true;
        }
        return false;
    }

    public boolean updateManager(ManagerDTO managerDTO) {
        if (!dtoValidator.validate(managerDTO)) return false;
        User updated = null;
        if (managerDTO.getId()!=null){
            if (managerDTO.getId().length()!=0){
                if (Integer.parseInt(managerDTO.getId())!=0){
                    updated = userRepository.getByManagerId(Integer.parseInt(managerDTO.getId()));
                }
            }
        }
        if (updated!=null){
            updateManagerWithFieldsFromDTO(updated, managerDTO);
            return true;
        }
        return false;
    }

    public boolean updateAdmin(AdminDTO adminDTO) {
        if (!dtoValidator.validate(adminDTO)) return false;
        User updated = null;
        if (adminDTO.getId()!=null){
            if (adminDTO.getId().length()!=0){
                if (Integer.parseInt(adminDTO.getId())!=0){
                    updated = userRepository.getByAdminId(Integer.parseInt(adminDTO.getId()));
                }
            }
        }
        if (updated!=null){
            updateAdminWithFieldsFromDTO(updated, adminDTO);
            return true;
        }
        return false;
    }

    public boolean deleteDriver(int userId) {
        return false;
    }

    public boolean deleteManager(int userId) {
        return false;
    }

    public boolean deleteAdmin(int userId) {
        return false;
    }


    // ** util methods

    private DriverStatus getDriverStatusValFromDriverDTO(DriverDTO driverDTO){
        String driverStatus = driverDTO.getDriverStatus();
            if (driverStatus.equals("FREE")) return DriverStatus.FREE;
            if (driverStatus.equals("RESTING")) return DriverStatus.RESTING;
            if (driverStatus.equals("DRIVING")) return DriverStatus.DRIVING;
            if (driverStatus.equals("SECOND_DRIVER")) return DriverStatus.SECOND_DRIVER;
            if (driverStatus.equals("LOAD_UNLOAD_WORKS")) return DriverStatus.LOAD_UNLOAD_WORKS;
            return null;
    }

    private double getHoursWorkedValFromDriverDTO(DriverDTO driverDTO){
        return Double.parseDouble(driverDTO.getHoursWorked());

    }

    private City getCurrentCityFromDriverDTO(DriverDTO driverDTO){
        return cityRepository.getByName(driverDTO.getCurrentCityName());
    }

    private Truck getCurrentTruckFromDriverDTO(DriverDTO driverDTO){
        return truckRepository.getByRegistrationNumber(driverDTO.getCurrentTruckRegistrationNumber());
    }

    private int getIdValFromDTO(Object dto){
        if (dto instanceof AdminDTO)  return Integer.parseInt(((AdminDTO) dto).getId());
        if (dto instanceof DriverDTO) return Integer.parseInt(((DriverDTO) dto).getId());
        if (dto instanceof ManagerDTO) return Integer.parseInt(((ManagerDTO) dto).getId());
        return 0;
    }

    private void updateDriverWithFieldsFromDTO(User updated, DriverDTO driverDTO){
        String newFirstName = null;
        String newMiddleName = null;
        String newLastName = null;
        String newPersonalNumber = null;
        String newPassword = null;
        Double newHoursWorked;
        DriverStatus newDriverStatus;
        City newCity = null;
        Truck newTruck = null;
        if (driverDTO.getFirstName()!=null && driverDTO.getFirstName().length()!=0) newFirstName = driverDTO.getFirstName();
        else newFirstName = updated.getName();
        if (driverDTO.getMiddleName()!=null && driverDTO.getMiddleName().length()!=0) newMiddleName = driverDTO.getMiddleName();
        else newMiddleName = updated.getMiddleName();
        if (driverDTO.getLastName()!=null && driverDTO.getLastName().length()!=0) newLastName = driverDTO.getLastName();
        else newLastName = updated.getLastName();
        if (driverDTO.getPersonalNumber()!=null && driverDTO.getPersonalNumber().length()!=0) newPersonalNumber = driverDTO.getPersonalNumber();
        else newPersonalNumber = updated.getPersonalNumber();
        if (driverDTO.getPassword()!=null && driverDTO.getPassword().length()!=0) newPassword = bCryptPasswordEncoder.encode(driverDTO.getPassword());
        else newPassword = updated.getPassword();
        if (driverDTO.getHoursWorked()!=null && driverDTO.getHoursWorked().length()!=0) newHoursWorked = Double.parseDouble(driverDTO.getHoursWorked());
        else newHoursWorked = updated.getDriver().getHoursWorked();
        if (driverDTO.getDriverStatus()!=null && driverDTO.getDriverStatus().length()!=0) newDriverStatus = getDriverStatusValFromDriverDTO(driverDTO);
        else newDriverStatus = updated.getDriver().getStatus();
        if (driverDTO.getCurrentCityName()!=null && driverDTO.getCurrentCityName().length()!=0) newCity = cityRepository.getByName(driverDTO.getCurrentCityName());
        else newCity = updated.getDriver().getCurrentCity();
        if (driverDTO.getCurrentTruckRegistrationNumber()!=null && driverDTO.getCurrentTruckRegistrationNumber().length()!=0) newTruck = truckRepository.getByRegistrationNumber(driverDTO.getCurrentTruckRegistrationNumber());
        else newTruck = updated.getDriver().getCurrentTruck();
        Driver driver = driverRepository.update(updated.getDriver().getId(),newHoursWorked,newDriverStatus,newCity,newTruck);
        userRepository.update(updated.getId(),newFirstName,newMiddleName,newLastName,newPersonalNumber,newPassword,driver,null,null);
    }

    private void updateManagerWithFieldsFromDTO(User updated, ManagerDTO managerDTO){
        String newFirstName = null;
        String newMiddleName = null;
        String newLastName = null;
        String newPersonalNumber = null;
        String newPassword = null;
        if (managerDTO.getFirstName()!=null && managerDTO.getFirstName().length()!=0) newFirstName = managerDTO.getFirstName();
        else newFirstName = updated.getName();
        if (managerDTO.getMiddleName()!=null && managerDTO.getMiddleName().length()!=0) newMiddleName = managerDTO.getMiddleName();
        else newMiddleName = updated.getMiddleName();
        if (managerDTO.getLastName()!=null && managerDTO.getLastName().length()!=0) newLastName = managerDTO.getLastName();
        else newLastName = updated.getLastName();
        if (managerDTO.getPersonalNumber()!=null && managerDTO.getPersonalNumber().length()!=0) newPersonalNumber = managerDTO.getPersonalNumber();
        else newPersonalNumber = updated.getPersonalNumber();
        if (managerDTO.getPassword()!=null && managerDTO.getPassword().length()!=0) newPassword = bCryptPasswordEncoder.encode(managerDTO.getPassword());
        else newPassword = updated.getPassword();
        Manager manager = managerRepository.update(updated.getManager().getId());
        userRepository.update(updated.getId(),newFirstName, newMiddleName, newLastName, newPersonalNumber, newPassword, null,manager, null);
    }

    private void updateAdminWithFieldsFromDTO(User updated, AdminDTO adminDTO){
        String newFirstName = null;
        String newMiddleName = null;
        String newLastName = null;
        String newPersonalNumber = null;
        String newPassword = null;
        if (adminDTO.getFirstName()!=null && adminDTO.getFirstName().length()!=0) newFirstName = adminDTO.getFirstName();
        else newFirstName = updated.getName();
        if (adminDTO.getMiddleName()!=null && adminDTO.getMiddleName().length()!=0) newMiddleName = adminDTO.getMiddleName();
        else newMiddleName = updated.getMiddleName();
        if (adminDTO.getLastName()!=null && adminDTO.getLastName().length()!=0) newLastName = adminDTO.getLastName();
        else newLastName = updated.getLastName();
        if (adminDTO.getPersonalNumber()!=null && adminDTO.getPersonalNumber().length()!=0) newPersonalNumber = adminDTO.getPersonalNumber();
        else newPersonalNumber = updated.getPersonalNumber();
        if (adminDTO.getPassword()!=null && adminDTO.getPassword().length()!=0) newPassword = bCryptPasswordEncoder.encode(adminDTO.getPassword());
        else newPassword = updated.getPassword();
        Admin admin = adminRepository.update(updated.getAdmin().getId());
        userRepository.update(updated.getId(),newFirstName, newMiddleName, newLastName, newPersonalNumber, newPassword, null,null, admin);
    }

}
