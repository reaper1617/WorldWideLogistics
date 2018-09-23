package com.gerasimchuk.services.interfaces;

import com.gerasimchuk.dto.AdminDTO;
import com.gerasimchuk.dto.DriverDTO;
import com.gerasimchuk.dto.ManagerDTO;
import com.gerasimchuk.dto.UserDTO;
import com.gerasimchuk.entities.Admin;
import com.gerasimchuk.entities.Driver;
import com.gerasimchuk.entities.Manager;
import com.gerasimchuk.entities.User;
import com.gerasimchuk.enums.UserRole;

import java.util.Collection;

/** User Service
 * @author Reaper
 * @version 1.0
 */

public interface UserService {

    boolean createDriver(DriverDTO driverDTO);
    boolean createManager(ManagerDTO managerDTO);
    boolean createAdmin(AdminDTO adminDTO);


    boolean updateDriver(DriverDTO driverDTO);
    boolean updateManager(ManagerDTO managerDTO);
    boolean updateAdmin(AdminDTO adminDTO);

    boolean deleteDriver(int userId);
    boolean deleteManager(int userId);
    boolean deleteAdmin(int userId);


    Collection<User> getAllDrivers();

    Collection<User> getFreeDrivers();

    Collection<UserRole> getRoles();

    boolean createUser(UserDTO userDTO);
//    Collection<Manager> getAllManagers();
//    Collection<Admin> getAllAdmins();

}
