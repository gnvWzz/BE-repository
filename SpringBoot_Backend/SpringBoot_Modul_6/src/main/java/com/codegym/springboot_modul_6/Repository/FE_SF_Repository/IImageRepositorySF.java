package com.codegym.springboot_modul_6.Repository.FE_SF_Repository;

import com.codegym.springboot_modul_6.model.FE_SF_Model.Entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IImageRepositorySF extends JpaRepository<Image, Long> {
//    @Query(value = "select * from image where product_id = :productId", nativeQuery = true)
//    List<Image> findByProductId(@Param("productId") Long productId);
}
