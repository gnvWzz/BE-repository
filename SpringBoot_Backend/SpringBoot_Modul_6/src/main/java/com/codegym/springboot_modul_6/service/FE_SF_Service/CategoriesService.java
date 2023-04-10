package com.codegym.springboot_modul_6.service.FE_SF_Service;

import com.codegym.springboot_modul_6.model.FE_SF_Model.Entity.Categories;
import com.codegym.springboot_modul_6.repository.FE_SF_Repository.ICategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Service
public class CategoriesService implements ICategoryService {

    private static final ICategoryService CATEGORIES_SERVICE = new CategoriesService();

    public static ICategoryService getCategoriesService(){
        return CATEGORIES_SERVICE;
    }

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
    public void remove(Long id) {

    }
}

