package com.codegym.springboot_modul_6.service.FE_SF_Service;

import com.codegym.springboot_modul_6.model.FE_SF_Model.Entity.CartSF;

public interface ICartService extends IGeneralService<CartSF>{
    void removeCartItem(String serialNumber, String accountName);
}
