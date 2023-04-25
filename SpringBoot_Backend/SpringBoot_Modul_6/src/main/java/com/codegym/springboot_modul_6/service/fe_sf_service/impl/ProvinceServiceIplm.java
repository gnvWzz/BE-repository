package com.codegym.springboot_modul_6.service.fe_sf_service.impl;

import com.codegym.springboot_modul_6.model.fe_sf_model.entity.Province;
import com.codegym.springboot_modul_6.repository.fe_sf_repository.IProvinceRepository;
import com.codegym.springboot_modul_6.service.fe_sf_service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class ProvinceServiceIplm implements ProvinceService {

    @Autowired
    private IProvinceRepository provinceRepository;

    @Override
    public Iterable<Province> findAll() {
        return provinceRepository.findAll();
    }

    @Override
    public Optional<Province> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void save(Province province) {

    }

    @Override
    public boolean remove(Long id) {
        return false;
    }
}
