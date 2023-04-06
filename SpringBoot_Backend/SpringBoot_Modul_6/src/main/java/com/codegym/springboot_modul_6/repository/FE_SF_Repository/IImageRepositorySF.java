package com.codegym.springboot_modul_6.repository.FE_SF_Repository;

import com.codegym.springboot_modul_6.model.FE_SF_Model.Entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IImageRepositorySF extends JpaRepository<Image, Long> {
    List<Image> findByProductSFDetail(Long id);

    @Query(value = "select * from image where product_detail_id IN :ids", nativeQuery = true)
    List<Image> findByProductDetailIds(@Param("ids")List<Long> ids);

}
