package com.gerasimchuk.repositories;

import com.gerasimchuk.entities.Admin;
import com.gerasimchuk.entities.Driver;
import com.gerasimchuk.entities.Manager;
import com.gerasimchuk.entities.User;

import java.util.Collection;

public interface UserRepository {

    User create(String name,
                String middleName,
                String lastName,
                String personalNumber,
                String password,
                Driver driver,
                Manager manager,
                Admin admin);

    User update(int id,
                String name,
                String middleName,
                String lastName,
                String personalNumber,
                String password,
                Driver driver,
                Manager manager,
                Admin admin);

    User getById(int id);

    User getByDriverId(int driverId);
    User getByManagerId(int managerId);
    User getByAdminId(int adminId);

    User getByPersonalNumber(String personalNumber);

    Collection<User> getAll();

    void remove(int id);
}
