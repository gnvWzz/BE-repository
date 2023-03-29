package com.codegym.springboot_modul_6.Service.FE_SF_Service;

import com.codegym.springboot_modul_6.Model.FE_SF_Model.Entity.Categories;

import java.util.Optional;

public class CategoriesService implements ICategoriesService {
    @Override
    public Iterable<Categories> findAll() {
        return null;
    }

    @Override
    public Optional<Categories> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void save(Categories categories) {

    }

    @Override
    public void remove(Long id) {

    }
}
