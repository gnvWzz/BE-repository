package com.codegym.springboot_modul_6.repository.fe_bo_repository;

import com.codegym.springboot_modul_6.model.fe_sf_model.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {
    @Query(nativeQuery = true, value = "select price from price_list as pl where pl.product_id = :productId and pl.price_id = 0")
    Double findStandardPrice(@Param("productId") Long productId);

    @Modifying
    @Query(nativeQuery = true, value = "DELETE FROM price_list as pl WHERE pl.product_id = :productId")
    void deletePriceListByProductId(@Param("productId") Long productId);
}
