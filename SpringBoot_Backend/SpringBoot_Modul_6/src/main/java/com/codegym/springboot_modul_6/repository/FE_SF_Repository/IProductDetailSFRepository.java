package com.codegym.springboot_modul_6.repository.FE_SF_Repository;

import com.codegym.springboot_modul_6.model.FE_SF_Model.Entity.ProductSFDetail;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;


@Repository
@Transactional
public interface IProductDetailSFRepository extends JpaRepository<ProductSFDetail, Long> {

    @Query(value = "select u from ProductSFDetail u where u.serialNumber = ?1")
    Optional<ProductSFDetail> getProductSFDetail(String serialNumber);


    @Modifying
    @Query(value = "UPDATE ProductSFDetail pd SET pd.status = 'false' WHERE pd.serialNumber = ?1")
//    @Query(nativeQuery = true, value = "UPDATE product_detail SET `status` = 'false' WHERE product_detail.serial_number = ?1")
    void removeBySerialNumber(String serialNumber);

    @Query("select u from ProductSFDetail u where u.serialNumber = ?1 ")

    Optional<ProductSFDetail> findBySerialNumber(String serialNumber);

    @Query(value = "select * from product_detail where serial_number in :serials", nativeQuery = true)
    List<ProductSFDetail> getProductSFDetails(@Param(value = "serials") List<String> serials);
}
