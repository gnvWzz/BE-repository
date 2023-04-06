package com.codegym.springboot_modul_6.repository.FE_SF_Repository;

import com.codegym.springboot_modul_6.model.FE_SF_Model.Entity.ProductSF;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductRepositorySF extends JpaRepository<ProductSF, Long> {

    @Query("select u from ProductSF u ")
    List<ProductSF> findAllProduct(String serialNumber);


    @Query("select u from ProductSF u  where u.category = ?1 ")
    Page<ProductSF> getAllProductByCategory(String category, PageRequest of);

//    @Query("select u from ProductSF u where u.category = ?1")
//    List<ProductSF> findProducts(String category);
//
//    @Query("select u from ProductSF u where u.serial_number = ?1")
//    ProductSF findBySerialNumber(String serialNumber);
//
//    Page<ProductSF> findAllByCategory(String category, PageRequest of);
//
//    @Query("select u from ProductSF u where u.category = ?1 order by u.price asc ")
//    Page<ProductSF> findAllByPriceOrderByPriceASC(String category ,PageRequest of);
//
//    Page<ProductSF> findAllByCategoryOrderByPriceDesc(String category, PageRequest of);
//
//    @Query("select u from ProductSF u where u.category = ?1 and u.size like concat('%', ?2, '%') order by u.price asc ")
//    Page<ProductSF> findAllByCategoryAndSizeOrderByPriceAsc(String category, String size, PageRequest of);
//
//    @Query("select u from ProductSF u where u.category = ?1 and u.name like concat('%', ?2, '%')")
//    Page<ProductSF> findByNameProduct(String category, String name, PageRequest of);
//
//    @Query("select u from ProductSF u where u.category = ?1 and u.size like concat('%', ?2, '%') order by u.price desc ")
//    Page<ProductSF> findAllByCategoryAndSizeOrderByPriceDesc(String category, String size, PageRequest of);
//
//    @Query("select u from ProductSF u where u.name = ?1 and u.manufacturer = ?2 and u.color = ?3 and u.size = ?4")
//    ProductSF findByNameAndManufacturerAndColorAndSize(String name, String manufacturer, String color, String size);
//
//    @Query("select u from ProductSF u where u.name = ?1 and u.manufacturer = ?2")
//    List<ProductSF> findByNameAnAndManufacturer(String name, String manufacturer);
}
