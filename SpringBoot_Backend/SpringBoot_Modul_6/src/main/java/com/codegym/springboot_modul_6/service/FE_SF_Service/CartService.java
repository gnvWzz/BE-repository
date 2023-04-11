package com.codegym.springboot_modul_6.service.FE_SF_Service;

import com.codegym.springboot_modul_6.model.FE_SF_Model.Entity.CartSF;

import java.util.Optional;

public class CartService implements ICartService{



    @Override
    public Iterable<CartSF> findAll() {
        return null;
    }

    @Override
    public Optional<CartSF> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void save(CartSF cartSF) {

    }

    @Override
    public void remove(Long id) {

    }
}
