package com.codegym.springboot_modul_6.repository.fe_sf_repository;

import com.codegym.springboot_modul_6.model.fe_sf_model.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPriceListRepository extends JpaRepository<Price, Long> {

    @Query(value = "select u from Price u where u.productSF.id = ?1")
    List<Price> getPriceListByProductId(Long productId);

}
