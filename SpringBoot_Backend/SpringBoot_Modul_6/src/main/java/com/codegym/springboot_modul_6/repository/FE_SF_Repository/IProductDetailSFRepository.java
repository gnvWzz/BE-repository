package com.codegym.springboot_modul_6.repository.FE_SF_Repository;

import com.codegym.springboot_modul_6.model.FE_SF_Model.Entity.ProductSFDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IProductDetailSFRepository extends JpaRepository<ProductSFDetail, Long> {

    @Query(value = "select u from ProductSFDetail u where u.serialNumber = ?1")
    Optional<ProductSFDetail> getProductSFDetail(String serialNumber);

}
