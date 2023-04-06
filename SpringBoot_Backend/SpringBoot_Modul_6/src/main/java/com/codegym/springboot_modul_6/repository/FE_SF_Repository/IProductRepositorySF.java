package com.codegym.springboot_modul_6.repository.FE_SF_Repository;

import com.codegym.springboot_modul_6.model.FE_SF_Model.Entity.ProductSF;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepositorySF extends JpaRepository<ProductSF, Long> {
}
