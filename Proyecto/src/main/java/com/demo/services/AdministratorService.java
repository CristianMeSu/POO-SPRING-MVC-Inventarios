package com.demo.services;

import com.demo.models.Administrator;

import java.util.List;
import java.util.Optional;

public interface AdministratorService {
    void createAdministrator(Administrator administrator);

    Optional<Administrator> findById(int id);

    void deleteAdministrator(int id);

    List<Administrator> findAll();
}