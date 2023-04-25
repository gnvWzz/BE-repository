package com.codegym.springboot_modul_6.service.fe_sf_service;

import java.util.Optional;

public interface GeneralService<T>{

    Iterable<T> findAll();

    Optional<T> findById(Long id);

    void save(T t);

    boolean remove(Long id);
}
