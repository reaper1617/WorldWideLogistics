package com.gerasimchuk.services.interfaces;

import com.gerasimchuk.dto.AdminDTO;
import com.gerasimchuk.dto.DriverDTO;
import com.gerasimchuk.dto.ManagerDTO;
import com.gerasimchuk.dto.UserDTO;
import com.gerasimchuk.entities.Admin;
import com.gerasimchuk.entities.Driver;
import com.gerasimchuk.entities.Manager;
import com.gerasimchuk.entities.User;
import com.gerasimchuk.enums.UpdateMessageType;
import com.gerasimchuk.enums.UserRole;

import java.util.Collection;

/** User Service
 * @author Reaper
 * @version 1.0
 */

public interface UserService {

    UpdateMessageType createDriver(DriverDTO driverDTO);
    UpdateMessageType createManager(ManagerDTO managerDTO);
    UpdateMessageType createAdmin(AdminDTO adminDTO);


    UpdateMessageType updateDriver(DriverDTO driverDTO);
    UpdateMessageType updateManager(ManagerDTO managerDTO);
    UpdateMessageType updateAdmin(AdminDTO adminDTO);

    UpdateMessageType deleteDriver(int userId);
    UpdateMessageType deleteManager(int userId);
    UpdateMessageType deleteAdmin(int userId);


    Collection<User> getAllDrivers();

    Collection<User> getFreeDrivers();

    Collection<UserRole> getRoles();

    UpdateMessageType createUser(UserDTO userDTO);
    UpdateMessageType updateUser(UserDTO userDTO);
    UpdateMessageType deleteUser(int id);



//    Collection<Manager> getAllManagers();
//    Collection<Admin> getAllAdmins();

}
