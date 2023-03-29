package com.codegym.springboot_modul_6.Service.FE_SF_Service;

import java.util.Optional;

public interface IGeneralService <T>{

    Iterable<T> findAll();

    Optional<T> findById(Long id);

    void save(T t);

    void remove(Long id);
}
