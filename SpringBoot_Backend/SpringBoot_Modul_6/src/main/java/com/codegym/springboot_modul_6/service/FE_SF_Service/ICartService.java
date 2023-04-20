package com.codegym.springboot_modul_6.service.FE_SF_Service;

import com.codegym.springboot_modul_6.model.FE_SF_Model.Entity.CartSF;
import com.codegym.springboot_modul_6.model.FE_SF_Model.model.CartModel;

import java.util.Optional;

public interface ICartService extends IGeneralService<CartSF>{
    void saveCart(CartSF cartSF);

    void removeCartItem(String serialNumber, String accountName);

    Optional<CartModel> getCart(String accountName);

    void deleteCartItem(Long id);

    Optional<CartSF> findCartSFByAccountName(String name);

    Optional<CartSF> findCartSFByAccountId(Long id);

    void updateCart(CartSF cartSF);
}
