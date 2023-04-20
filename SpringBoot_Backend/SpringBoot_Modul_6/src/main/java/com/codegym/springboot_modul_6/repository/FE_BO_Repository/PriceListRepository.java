package com.codegym.springboot_modul_6.repository.FE_BO_Repository;

import com.codegym.springboot_modul_6.model.FE_SF_Model.Entity.PriceList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceListRepository extends JpaRepository<PriceList, Long> {
    @Query(nativeQuery = true, value = "select price from price_list as pl where pl.product_id = :productId and pl.price_id = 0")
    Double findStandardPrice(@Param("productId") Long productId);
}
