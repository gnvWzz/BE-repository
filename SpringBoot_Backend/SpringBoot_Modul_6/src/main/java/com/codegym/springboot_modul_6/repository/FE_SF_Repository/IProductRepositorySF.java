package com.codegym.springboot_modul_6.repository.FE_SF_Repository;

import com.codegym.springboot_modul_6.model.FE_SF_Model.Entity.ProductSF;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductRepositorySF extends JpaRepository<ProductSF, Long> {

    @Query("select u from ProductSF u where u.category = ?1 ")
    Page<ProductSF> getAllProductByCategory(String category, PageRequest of);

    @Query(value = "select * from btob_database.product right join product_detail on product.id = product_detail.product_id where product.category = :category group by product_detail.package_id order by product_detail.price asc" , nativeQuery = true)
    Page<ProductSF> getAllProductPriceAsc(@Param(value = "category") String category, PageRequest of);

    @Query(value = "select * from btob_database.product right join product_detail on product.id = product_detail.product_id where product.category = ?1 group by product_detail.package_id order by product_detail.price desc" , nativeQuery = true)
    Page<ProductSF> getAllProductPriceDesc(String category, PageRequest of);

    @Query(value = "select u from ProductSF u right join ProductSFDetail p on u.id = p.productSF.id where u.category = :category and u.name like concat('%', :name, '%') ")
    Page<ProductSF> getAllProductByName(@Param(value = "category") String category,@Param(value = "name") String name, PageRequest of);

    @Query(value = "select u from ProductSF u ")
    Page<ProductSF> getAll(PageRequest of);

}
