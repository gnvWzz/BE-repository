package com.codegym.springboot_modul_6.repository.FE_SF_Repository;

import com.codegym.springboot_modul_6.model.FE_SF_Model.Entity.CartSF;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface ICartRepository extends JpaRepository<CartSF, Long> {

    @Query(value = "select u from CartSF u where u.accountName = ?1")
    Optional<CartSF> findCartByAccountName(String accountUser);


    @Modifying
    @Query(value = "delete from btob_database.cart_detail where cart_id = ?1", nativeQuery = true)
    void removeCartItem(Long cartId);

    Optional<CartSF> findByAccountId(Long accountId);
}
