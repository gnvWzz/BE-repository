package com.codegym.springboot_modul_6.model.FE_SF_Model.model;

import com.codegym.springboot_modul_6.model.FE_SF_Model.Entity.Categories;
import com.codegym.springboot_modul_6.model.FE_SF_Model.Entity.Province;
import com.codegym.springboot_modul_6.model.FE_SF_Model.dto.CategoriesDto;
import com.codegym.springboot_modul_6.model.FE_SF_Model.dto.ProvinceDto;

import java.util.List;

public class CacheModel {
    private List<CategoriesDto> categories;
    private List<ProvinceDto> provinces;

    public List<CategoriesDto> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoriesDto> categories) {
        this.categories = categories;
    }

    public List<ProvinceDto> getProvinces() {
        return provinces;
    }

    public void setProvinces(List<ProvinceDto> provinces) {
        this.provinces = provinces;
    }

    public CacheModel(List<CategoriesDto> categories, List<ProvinceDto> provinces) {
        this.categories = categories;
        this.provinces = provinces;
    }

    public CacheModel() {
    }
}
