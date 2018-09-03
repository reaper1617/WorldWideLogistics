package com.gerasimchuk.repositories;

import com.gerasimchuk.entities.Manager;

import java.util.Collection;

public interface ManagerRepository {

    Manager create();
    Manager update(int id);
    Manager getById(int id);
    Collection<Manager> getAll();
    void remove(int id);
}
