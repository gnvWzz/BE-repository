package com.codegym.springboot_modul_6.repository.FE_SF_Repository;

import com.codegym.springboot_modul_6.model.FE_SF_Model.Entity.ProductSF;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductRepositorySF extends JpaRepository<ProductSF, Long> {

    @Query("select u from ProductSF u ")
    List<ProductSF> findAllProduct(String serialNumber);

    @Query("select u from ProductSF u  where u.category = ?1 ")
    Page<ProductSF> getAllProductByCategory(String category, PageRequest of);

}
