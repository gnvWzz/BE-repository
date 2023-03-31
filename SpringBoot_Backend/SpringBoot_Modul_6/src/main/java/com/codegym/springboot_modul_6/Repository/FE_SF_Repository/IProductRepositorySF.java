package com.codegym.springboot_modul_6.Repository.FE_SF_Repository;

import com.codegym.springboot_modul_6.Model.FE_SF_Model.Entity.ProductSF;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductRepositorySF extends JpaRepository<ProductSF, Long> {

    @Query("select u from ProductSF u where u.category = ?1")
    List<ProductSF> findCategory(String category);

    @Query("select u from ProductSF u where u.serial_number = ?1")
    ProductSF findBySerialNumber(String serialNumber);
}
