package com.codegym.springboot_modul_6.repository.FE_BO_Repository;

import com.codegym.springboot_modul_6.model.FE_BO_Model.entity.Store;
import com.codegym.springboot_modul_6.model.FE_SF_Model.Entity.CartSF;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
//    Store phai la unique thi moi dung duoc cau query
//    @Query(value = "select u from Store u where u.name = ?1")
//    @Query("select u from Store u where u.name = :name")
    @Query(value = "select * from store as s where s.name = :name", nativeQuery = true)
    Optional<Store> findStoreByName(@Param("name") String name);

    Optional<Store> findByAccount_Id(Long id);

    Optional<Store> findByName(String name);
}
