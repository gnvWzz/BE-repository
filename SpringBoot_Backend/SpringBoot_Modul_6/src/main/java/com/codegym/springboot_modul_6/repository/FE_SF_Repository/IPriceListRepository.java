package com.codegym.springboot_modul_6.repository.FE_SF_Repository;

import com.codegym.springboot_modul_6.model.FE_SF_Model.Entity.PriceList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPriceListRepository extends JpaRepository<PriceList, Long> {

    @Query(value = "select u from PriceList u where u.productSF.id = ?1")
    List<PriceList> getPriceListByProductId(Long productId);

}
