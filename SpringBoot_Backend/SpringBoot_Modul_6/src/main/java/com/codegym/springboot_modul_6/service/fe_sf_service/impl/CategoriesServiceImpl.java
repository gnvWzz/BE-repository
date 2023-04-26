package com.codegym.springboot_modul_6.service.fe_sf_service.impl;

import com.codegym.springboot_modul_6.model.fe_sf_model.entity.Categories;
import com.codegym.springboot_modul_6.repository.fe_sf_repository.ICategoriesRepository;
import com.codegym.springboot_modul_6.service.fe_sf_service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriesServiceImpl implements CategoryService {

    @Autowired
    private ICategoriesRepository iCategoriesRepository;

    @Override
    public Iterable<Categories> findAll() {
        return iCategoriesRepository.findAll();
    }

    @Override
    public Optional<Categories> findById(Long id) {
        return Optional.empty();
    }



    @Override
    public void save(Categories categories) {

    }

    @Override
    public boolean remove(Long id) {
        return false;
    }
}

