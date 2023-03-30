package com.codegym.springboot_modul_6.Repository.FE_SF_Repository;

import com.codegym.springboot_modul_6.Model.FE_SF_Model.Entity.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoriesRepository extends JpaRepository<Categories, Long> {
}
