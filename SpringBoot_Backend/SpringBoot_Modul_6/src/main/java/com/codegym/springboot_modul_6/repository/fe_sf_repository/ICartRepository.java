package com.codegym.springboot_modul_6.repository.fe_sf_repository;

import com.codegym.springboot_modul_6.model.fe_sf_model.entity.CartSF;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICartRepository extends JpaRepository<CartSF, Long> {

    @Query(value = "select u from CartSF u where u.accountName = ?1")
    Optional<CartSF> findAccountName(String accountUser);



    Optional<CartSF> findCartByAccountName(String name);

    Optional<CartSF> findByAccountId(Long accountId);

    @Modifying
    @Query(value = "delete from btob_database.cart_detail where cart_id = ?1", nativeQuery = true)
    void removeCartItemById(Long cartId);


}
