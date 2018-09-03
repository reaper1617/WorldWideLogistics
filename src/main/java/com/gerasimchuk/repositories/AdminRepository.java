package com.gerasimchuk.repositories;

import com.gerasimchuk.entities.Admin;

import java.util.Collection;

public interface AdminRepository {
    Admin create();
    Admin update(int id);
    Admin getById(int id);
    Collection<Admin> getAll();
    void remove(int id);
}
