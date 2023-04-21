package com.codegym.springboot_modul_6.repository.FE_SF_Repository;

import com.codegym.springboot_modul_6.model.FE_SF_Model.Entity.ProductSF;
import com.codegym.springboot_modul_6.model.FE_SF_Model.Entity.ProductSFDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface IProductRepositorySF extends JpaRepository<ProductSF, Long> {

    @Query("select u from ProductSF u where u.category = ?1 ")
    Page<ProductSF> getAllProductByCategory(String category, PageRequest of);

    @Query(value = "select * from product join price_list on product.id = price_list.product_id where price_list.price_id = 0 and product.category = ?1 order by price_list.price asc", nativeQuery = true)
    Page<ProductSF> getAllProductPriceAsc(String category, PageRequest of);

    @Query(value = "select * from product join price_list on product.id = price_list.product_id where price_list.price_id = 0 and product.category = ?1 order by price_list.price desc", nativeQuery = true)
    Page<ProductSF> getAllProductPriceDesc(String category, PageRequest of);

    @Query(value = "select u from ProductSF u where u.category = :category and u.name like concat('%', :name, '%') ")
    Page<ProductSF> getAllProductByName(@Param(value = "category") String category,@Param(value = "name") String name, PageRequest of);
    @Query(value = "select u from ProductSF u ")
    Page<ProductSF> getAll(PageRequest of);

    ProductSF findByPackageId(String packageId);


    @Query(value = "select u from ProductSF u order by rand()")
    Page<ProductSF> productRepository_getRanDomProduct(PageRequest of);

    @Query(value = "select * from product join price_list on product.id = price_list.product_id where price_list.price >= ?1 and price_list.price <= ?2 and product.category = ?3 order by price_list.price asc", nativeQuery = true)
    Page<ProductSF> findProductsByMinPriceToMaxPrice(Double minPrice, Double maxPrice, String category,PageRequest of);
}
