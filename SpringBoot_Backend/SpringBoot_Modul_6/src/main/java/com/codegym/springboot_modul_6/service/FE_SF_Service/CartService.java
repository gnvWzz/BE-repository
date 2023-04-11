package com.codegym.springboot_modul_6.service.FE_SF_Service;

import com.codegym.springboot_modul_6.model.FE_SF_Model.Entity.CartDetailSF;
import com.codegym.springboot_modul_6.model.FE_SF_Model.Entity.CartSF;
import com.codegym.springboot_modul_6.model.FE_SF_Model.Entity.ProductSF;
import com.codegym.springboot_modul_6.model.FE_SF_Model.Entity.ProductSFDetail;
import com.codegym.springboot_modul_6.repository.FE_SF_Repository.ICartRepository;
import com.codegym.springboot_modul_6.repository.FE_SF_Repository.IProductDetailSFRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CartService implements ICartService{

    @Autowired
    private ICartRepository iCartRepository;

    @Autowired
    private IProductDetailSFRepository iProductDetailSFRepository;

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
        Optional<CartSF> cart = findAccountName(cartSF.getAccountName());
        Optional<ProductSFDetail> productSFDetail = findProductSFDetailBySerialNumber(cartSF.getCartDetailSFS().get(0).getSerialNumber());
        if (cart.isPresent() && productSFDetail.isPresent()){
            if (Objects.equals(cart.get().getCartDetailSFS().get(0).getSerialNumber(), cartSF.getCartDetailSFS().get(0).getSerialNumber())){
                cart.get().getCartDetailSFS().get(0).setQuantity(cartSF.getCartDetailSFS().get(0).getQuantity());
                cart.get().getCartDetailSFS().get(0).setPrice(productSFDetail.get().getPrice() * cart.get().getCartDetailSFS().get(0).getQuantity());
                cart.get().setTotalPrice(getTotalMoney(cart.get().getCartDetailSFS()));
                iCartRepository.save(cart.get());
            }else {
                cartSF.setId(cart.get().getId());
                cartSF.getCartDetailSFS().get(0).setCartSF(cartSF);
                cartSF.setTotalPrice(getTotalMoney(cart.get().getCartDetailSFS()) + getTotalMoney(cartSF.getCartDetailSFS()));
                iCartRepository.save(cartSF);
            }
        }else {
            cartSF.getCartDetailSFS().get(0).setCartSF(cartSF);
            cartSF.setTotalPrice(getTotalMoney(cartSF.getCartDetailSFS()));
            iCartRepository.save(cartSF);
        }
    }

    private Double getTotalMoney(Iterable<CartDetailSF> cartDetailSFS){
        double tempMoney = 0.0;
        for (CartDetailSF c :
             cartDetailSFS) {
            tempMoney += c.getPrice();
        }
        return tempMoney;
    }

    private Optional<ProductSFDetail> findProductSFDetailBySerialNumber(String serialNumber){
        return iProductDetailSFRepository.getProductSFDetail(serialNumber);
    }

    private Optional<CartSF> findAccountName(String name){
        return iCartRepository.findAccountName(name);
    }



    @Override
    public void remove(Long id) {

    }
}
