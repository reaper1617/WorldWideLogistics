package com.gerasimchuk.services.impls;

import com.gerasimchuk.dto.AdminDTO;
import com.gerasimchuk.dto.DriverDTO;
import com.gerasimchuk.dto.ManagerDTO;
import com.gerasimchuk.entities.Admin;
import com.gerasimchuk.entities.Driver;
import com.gerasimchuk.entities.Manager;
import com.gerasimchuk.entities.User;
import com.gerasimchuk.enums.DriverStatus;
import com.gerasimchuk.enums.UpdateMessageType;
import com.gerasimchuk.repositories.AdminRepository;
import com.gerasimchuk.repositories.DriverRepository;
import com.gerasimchuk.repositories.ManagerRepository;
import com.gerasimchuk.repositories.UserRepository;
import com.gerasimchuk.services.interfaces.UserService;
import com.gerasimchuk.utils.ReturnValuesContainer;
import org.junit.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:**/testConf.xml"})
@WebAppConfiguration
public class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private ManagerRepository managerRepository;

    @Autowired
    private AdminRepository adminRepository;

//    private static List<User> createdUsers = new ArrayList<User>();

    public UserServiceImplTest() {
    }

    @BeforeClass
    public static void setup(){
        System.out.println("In setup!");
//        createdUsers = new ArrayList<User>();
    }

    @AfterClass
    public static void finish(){
        System.out.println("In finish!");
//        for(User u: createdUsers){
//            if (u != null) userRepository.remove(u.getId());
//        }
    }


    @Test
    public void createDriver() {
        DriverDTO driverDTO = new DriverDTO("","DriverFirstName", "DriverMiddleName", "DriverLastName","9999999999","password","1","FREE","Moscow","ff55555",null);
        ReturnValuesContainer<User> result = userService.createDriver(driverDTO,0);
//        createdUsers.add(result.getReturnedValue());
        userRepository.remove(result.getReturnedValue().getId());
        driverRepository.remove(result.getReturnedValue().getDriver().getId());
        assertEquals(UpdateMessageType.DRIVER_CREATED,result.getUpdateMessageType());
    }


    @Test
    public void createManager() {
        ManagerDTO managerDTO = new ManagerDTO("", "ManagerFirstName", "ManagerMiddleName", "ManagerLastName","8888888888","password");
        ReturnValuesContainer<User> result = userService.createManager(managerDTO,0);
//        createdUsers.add(result.getReturnedValue());
        userRepository.remove(result.getReturnedValue().getId());
        managerRepository.remove(result.getReturnedValue().getManager().getId());
        assertEquals(UpdateMessageType.MANAGER_CREATED,result.getUpdateMessageType());
    }

    @Test
    public void createAdmin() {
        AdminDTO adminDTO = new AdminDTO("", "AdminFirstName", "AdminMiddleName", "AdminLastName","password","7777777777");
        ReturnValuesContainer<User> result = userService.createAdmin(adminDTO,0);
//        createdUsers.add(result.getReturnedValue());
        userRepository.remove(result.getReturnedValue().getId());
        adminRepository.remove(result.getReturnedValue().getAdmin().getId());
        assertEquals(UpdateMessageType.ADMIN_CREATED,result.getUpdateMessageType());
    }

    @Test
    public void updateDriver() {
        DriverDTO driverDTO = new DriverDTO("","DriverUpdatedFirstName", "DriverUpdatedMiddleName", "DriverUpdatedLastName","6666666666","password","1","FREE","Moscow","ff55555",null);
        ReturnValuesContainer<User> result = userService.createDriver(driverDTO,0);
        User d = result.getReturnedValue();
//        createdUsers.add(d);
        DriverDTO driverUpdatedDTO = new DriverDTO(Integer.toString(d.getId()),"DriverUpdatedTwiceFirstName", null, "DriverUpdatedLastName",null,null,null,null,null,"ff55555",null);
        UpdateMessageType updated = userService.updateDriver(driverUpdatedDTO);
        userRepository.remove(result.getReturnedValue().getId());
        driverRepository.remove(result.getReturnedValue().getDriver().getId());
        assertEquals(UpdateMessageType.DRIVER_EDITED, updated);
    }

    @Test
    public void updateManager() {
        ManagerDTO managerDTO = new ManagerDTO("", "ManagerUpdatedFirstName", "ManagerUpdatedMiddleName", "ManagerUpdatedLastName","5555555555","password");
        ReturnValuesContainer<User> result = userService.createManager(managerDTO,0);
        User m = result.getReturnedValue();
//        createdUsers.add(m);
        ManagerDTO managerUpdatedDTO = new ManagerDTO(Integer.toString(m.getId()), "ManagerUpdatedTwiceFirstName", "ManagerUpdatedMiddleName", null,null,"passworddd");
        UpdateMessageType updated = userService.updateManager(managerUpdatedDTO);
        userRepository.remove(result.getReturnedValue().getId());
        managerRepository.remove(result.getReturnedValue().getManager().getId());
        assertEquals(UpdateMessageType.MANAGER_EDITED, updated);
    }

    @Test
    public void updateAdmin() {
        AdminDTO adminDTO = new AdminDTO("", "AdminUpdatedFirstName", "AdminUpdatedMiddleName", "AdminUpdatedLastName","password","4444444444");
        ReturnValuesContainer<User> result = userService.createAdmin(adminDTO,0);
        User a = result.getReturnedValue();
//        createdUsers.add(a);
        AdminDTO adminUpdatedDTO = new AdminDTO(Integer.toString(a.getId()), null, "AdminUpdatedTwiceMiddleName", "AdminUpdatedTwiceLastName","passsword",null);
        UpdateMessageType updated = userService.updateAdmin(adminUpdatedDTO);
        userRepository.remove(result.getReturnedValue().getId());
        adminRepository.remove(result.getReturnedValue().getAdmin().getId());
        assertEquals(UpdateMessageType.ADMIN_EDITED, updated);
    }

    @Test
    public void deleteDriver() {
        DriverDTO driverDTO = new DriverDTO("","DriverDeletedFirstName", "DriverDeletedMiddleName", "DriverDeletedLastName","3333333333","password","1","FREE","Moscow",null,null);
        ReturnValuesContainer<User> result = userService.createDriver(driverDTO,0);
        User d = result.getReturnedValue();
        UpdateMessageType res = userService.deleteDriver(d.getId());
        assertEquals(UpdateMessageType.DRIVER_DELETED, res);
    }

    @Test
    public void deleteManager() {
        ManagerDTO managerDTO = new ManagerDTO("", "ManagerDeletedFirstName", "ManagerDeletedMiddleName", "ManagerDeletedLastName","2222222222","password");
        ReturnValuesContainer<User> result = userService.createManager(managerDTO,0);
        User m = result.getReturnedValue();
        UpdateMessageType res = userService.deleteManager(m.getId());
        assertEquals(UpdateMessageType.MANAGER_DELETED, res);
    }

    @Test
    public void deleteAdmin() {
        AdminDTO adminDTO = new AdminDTO("", "AdminDeletedFirstName", "AdminDeletedMiddleName", "AdminDeletedLastName","password","1111111111");
        ReturnValuesContainer<User> result = userService.createAdmin(adminDTO,0);
        User a = result.getReturnedValue();
        UpdateMessageType res = userService.deleteAdmin(a.getId());
        assertEquals(UpdateMessageType.ADMIN_DELETED, res);
    }

    @Test
    public void getAllDrivers() {
        Collection<User> driversByUserService = userService.getAllDrivers();
        Collection<Driver> driversByDriverRepository = driverRepository.getAll();
        boolean isAllDrivers = true;
        for(User u: driversByUserService){
            if (u.getDriver() == null){
                isAllDrivers = false;
                break;
            }
        }
        boolean res = (driversByUserService.size() == driversByDriverRepository.size())&& isAllDrivers;
        assertTrue(res);
    }

    @Test
    public void getFreeDrivers() {
    }

    @Test
    public void getRoles() {
    }

    @Test
    public void createUser() {
    }

    @Test
    public void updateUser() {
    }

    @Test
    public void deleteUser() {
    }
}

