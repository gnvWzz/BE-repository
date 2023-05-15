package com.codegym.springboot_modul_6.repository.fe_sf_repository;

import com.codegym.springboot_modul_6.model.fe_sf_model.dto.IProductSFBestSellers;

import com.codegym.springboot_modul_6.model.fe_sf_model.entity.ProductSF;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IProductRepositorySF extends JpaRepository<ProductSF, Long> {

    @Query("select u from ProductSF u where u.category = ?1 ")
    Page<ProductSF> getAllProductByCategory(String category, PageRequest of);

    @Query(value = "select * from product join price_list on product.id = price_list.product_id where price_list.price_id = 0 and product.category = ?1 order by price_list.price asc", nativeQuery = true)
    Page<ProductSF> getAllProductPriceAsc(String category, PageRequest of);

    @Query(value = "select * from product join price_list on product.id = price_list.product_id where price_list.price_id = 0 and product.category = ?1 order by price_list.price desc", nativeQuery = true)
    Page<ProductSF> getAllProductPriceDesc(String category, PageRequest of);

    @Query(value = "select u from ProductSF u where u.category = :category and u.name like concat('%', :name, '%') ")
    Page<ProductSF> getAllProductByName(@Param(value = "category") String category, @Param(value = "name") String name, PageRequest of);

    @Query(value = "select u from ProductSF u order by rand()")
    Page<ProductSF> productRepository_getRanDomProduct(PageRequest of);

    @Query(value = "select * from product join price_list on product.id = price_list.product_id where price_list.price >= ?1 and price_list.price <= ?2 and price_list.from_quantity = 1 and product.category = ?3 order by price_list.price asc", nativeQuery = true)
    Page<ProductSF> findProductsByMinPriceToMaxPrice(Double minPrice, Double maxPrice, String category, PageRequest of);

    @Query(value = "select u from ProductSF u where u.store.id = ?1 ")
    Page<ProductSF> getProductStoreById(Long storeId, PageRequest of);

    Optional<ProductSF> findProductSFByName(String name);

    @Query(value = "select  * from product where  category =?1 and not name =?2 order by rand() limit 4", nativeQuery = true)
    List<ProductSF> findRandomProductYouLikeThis(String category, String productName);

    @Query(value = "SELECT\n" +
            "    p.id AS id,\n" +
            "    p.name AS name,\n" +
            "    MAX(pd.size_color_img_quantity) AS info,\n" +
            "    SUM(od.quantity) AS totalsales\n" +
            "FROM\n" +
            "    product p\n" +
            "    JOIN product_detail pd ON p.id = pd.product_id\n" +
            "    JOIN order_detail od ON p.`name` = od.`name`\n" +
            "GROUP BY\n" +
            "    p.id\n" +
            "ORDER BY\n" +
            "    totalsales DESC\n" +
            "LIMIT 4;",
            nativeQuery = true)
    List<IProductSFBestSellers> getBestSellers();

}
