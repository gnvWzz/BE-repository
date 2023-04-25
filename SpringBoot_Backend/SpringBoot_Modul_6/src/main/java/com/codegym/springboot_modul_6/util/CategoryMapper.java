package com.codegym.springboot_modul_6.util;

import com.codegym.springboot_modul_6.model.fe_sf_model.dto.CategoriesDto;
import com.codegym.springboot_modul_6.model.fe_sf_model.entity.Categories;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryMapper {

    public List<CategoriesDto> mapperCategories(List<Categories> categories) {
        List<CategoriesDto> list = new ArrayList<>();
        for (int i = 0; i < categories.size(); i++) {
            CategoriesDto categoriesDto = new CategoriesDto();
            BeanUtils.copyProperties(categories.get(i), categoriesDto);
            list.add(categoriesDto);
        }
        return list;
    }

}
