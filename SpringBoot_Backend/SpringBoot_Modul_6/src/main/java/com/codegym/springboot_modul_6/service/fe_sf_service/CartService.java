package com.codegym.springboot_modul_6.service.fe_sf_service;

import com.codegym.springboot_modul_6.model.fe_sf_model.entity.CartSF;
import com.codegym.springboot_modul_6.model.fe_sf_model.model.CartModel;

import java.util.Optional;

public interface CartService extends GeneralService<CartSF> {
    void saveCart(CartSF cartSF);

    void removeCartItem(String serialNumber, String accountName);

    Optional<CartModel> getCart(String accountName);

    void deleteCartItem(Long id);

    Optional<CartSF> findCartSFByAccountName(String name);

    Optional<CartSF> findCartSFByAccountId(Long id);

    void updateCart(CartSF cartSF);
}
