package com.codegym.springboot_modul_6.service.FE_SF_Service;

import java.util.Optional;

public interface IGeneralService <T>{

    Iterable<T> findAll();

    Optional<T> findById(Long id);

    void save(T t);

    boolean remove(Long id);
}
