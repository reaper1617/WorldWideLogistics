package com.gerasimchuk.services.interfaces;

import com.gerasimchuk.entities.Admin;
import com.gerasimchuk.entities.Manager;
import com.gerasimchuk.entities.User;

public interface AdminService {

    void addNewAdmin(User admin);
    void changeAdmin(User admin);
    void deleteAdmin(User admin);


    void addNewManager(User manager);
    void changeManager(User manager);
    void deleteManager(User manager);

}
