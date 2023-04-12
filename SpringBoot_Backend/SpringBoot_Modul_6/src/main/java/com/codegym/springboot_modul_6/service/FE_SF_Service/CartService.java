package com.codegym.springboot_modul_6.service.FE_SF_Service;

import com.codegym.springboot_modul_6.model.FE_SF_Model.Entity.CartDetailSF;
import com.codegym.springboot_modul_6.model.FE_SF_Model.Entity.CartSF;
import com.codegym.springboot_modul_6.model.FE_SF_Model.Entity.ProductSFDetail;
import com.codegym.springboot_modul_6.repository.FE_SF_Repository.ICartRepository;
import com.codegym.springboot_modul_6.repository.FE_SF_Repository.IProductDetailSFRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CartService implements ICartService {

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
        if (cart.isPresent()) {
            addCartExistOrNewItem(cartSF, cart.get());
        } else {
            addNewCart(cartSF);
        }
    }

    public void addCartExistOrNewItem(CartSF cartNew, CartSF cartOld) {
        List<CartDetailSF> cartDetailSFS = new ArrayList<>();
        CartSF cartSF = new CartSF();
        cartSF.setId(cartOld.getId());
        cartSF.setAccountName(cartOld.getAccountName());
        int count = 0;
        if (cartNew.getCartDetailSFS().size() < cartOld.getCartDetailSFS().size()){
            for (int i = 0; i < cartOld.getCartDetailSFS().size(); i++) {
                CartDetailSF cartDetailSF = new CartDetailSF();
                if (Objects.equals(cartOld.getCartDetailSFS().get(i).getSerialNumber(), cartNew.getCartDetailSFS().get(i).getSerialNumber())) {
                    BeanUtils.copyProperties(cartNew.getCartDetailSFS().get(i), cartDetailSF);
                    cartDetailSF.setId(cartOld.getCartDetailSFS().get(i).getId());
                    cartDetailSF.setCartSF(cartOld);
                    cartDetailSF.setSubTotal(cartDetailSF.getPrice() * cartDetailSF.getQuantity());
                    cartDetailSFS.add(cartDetailSF);
                    count++;
                }
            }
            for (int i = count; i < cartNew.getCartDetailSFS().size(); i++){
                CartDetailSF cartDetailSF = new CartDetailSF();
                BeanUtils.copyProperties(cartNew.getCartDetailSFS().get(i), cartDetailSF);
                cartDetailSF.setCartSF(cartOld);
                cartDetailSF.setSubTotal(cartDetailSF.getPrice() * cartDetailSF.getQuantity());
                cartDetailSFS.add(cartDetailSF);
            }
            cartSF.setCartDetailSFS(cartDetailSFS);
            cartSF.setTotalPrice(getTotalMoney(cartSF.getCartDetailSFS()) + cartOld.getTotalPrice());
            iCartRepository.save(cartSF);
        }else {
            for (int i = 0; i < cartOld.getCartDetailSFS().size(); i++) {
                CartDetailSF cartDetailSF = new CartDetailSF();
                if (Objects.equals(cartOld.getCartDetailSFS().get(i).getSerialNumber(), cartNew.getCartDetailSFS().get(i).getSerialNumber())) {
                    BeanUtils.copyProperties(cartNew.getCartDetailSFS().get(i), cartDetailSF);
                    cartDetailSF.setId(cartOld.getCartDetailSFS().get(i).getId());
                    cartDetailSF.setCartSF(cartOld);
                    cartDetailSF.setSubTotal(cartDetailSF.getPrice() * cartDetailSF.getQuantity());
                    cartDetailSFS.add(cartDetailSF);
                    count++;
                }
            }
            for (int i = count; i < cartNew.getCartDetailSFS().size(); i++){
                CartDetailSF cartDetailSF = new CartDetailSF();
                BeanUtils.copyProperties(cartNew.getCartDetailSFS().get(i), cartDetailSF);
                cartDetailSF.setCartSF(cartOld);
                cartDetailSF.setSubTotal(cartDetailSF.getPrice() * cartDetailSF.getQuantity());
                cartDetailSFS.add(cartDetailSF);
            }
            cartSF.setCartDetailSFS(cartDetailSFS);
            cartSF.setTotalPrice(getTotalMoney(cartSF.getCartDetailSFS()));
            iCartRepository.save(cartSF);
        }

    }

//    private void addNewItemCart(CartSF cartSF, Iterable<CartSF> cart, Optional<ProductSFDetail> productSFDetail) {
//        cartSF.setId(cart.get().getId());
//        cartSF.getCartDetailSFS().get(0).setCartSF(cartSF);
//        cartSF.getCartDetailSFS().get(0).setPrice(productSFDetail.get().getPrice() * cartSF.getCartDetailSFS().get(0).getQuantity());
//        List<CartDetailSF> temp = cart.get().getCartDetailSFS();
//        temp.addAll(cartSF.getCartDetailSFS());
//        cartSF.setCartDetailSFS(temp);
//        cartSF.setTotalPrice(getTotalMoney(cartSF.getCartDetailSFS()));
//        iCartRepository.save(cartSF);
//    }

//    private void changeItemCartExist(CartSF cartSF, Iterable<CartSF> cart, Optional<ProductSFDetail> productSFDetail) {
//        CartSF cart_temp = new CartSF();
//        cart_temp.setId(cartSF.getId());
//        for (CartDetailSF c: cart.get().getCartDetailSFS()
//             ) {
//            if (c.getSerialNumber().equals(cartSF.getCartDetailSFS().get(0).getSerialNumber())){
//                cart.get().getCartDetailSFS().get(0).setQuantity(cartSF.getCartDetailSFS().get(0).getQuantity());
//                cart.get().getCartDetailSFS().get(0).setPrice(productSFDetail.get().getPrice() * cart.get().getCartDetailSFS().get(0).getQuantity());
//                cart.get().setTotalPrice(getTotalMoney(cart.get().getCartDetailSFS()));
//            }
//        }
//    }

    private void addNewCart(CartSF cartSF) {
        List<CartDetailSF> cartDetailSFS = new ArrayList<>();
        for (CartDetailSF c : cartSF.getCartDetailSFS()
        ) {
            CartDetailSF cartTemp = new CartDetailSF();
            BeanUtils.copyProperties(c, cartTemp);
            cartTemp.setCartSF(cartSF);
            cartTemp.setSubTotal(cartTemp.getPrice() * cartTemp.getQuantity());
            cartDetailSFS.add(cartTemp);
        }
        cartSF.setCartDetailSFS(cartDetailSFS);
        cartSF.setTotalPrice(getTotalMoney(cartSF.getCartDetailSFS()));
        iCartRepository.save(cartSF);
    }

    private Double getTotalMoney(Iterable<CartDetailSF> cartDetailSFS) {
        double tempMoney = 0.0;
        for (CartDetailSF c :
                cartDetailSFS) {
            tempMoney += c.getSubTotal();
        }
        return tempMoney;
    }

    private Optional<ProductSFDetail> findProductSFDetailBySerialNumber(String serialNumber) {
        return iProductDetailSFRepository.getProductSFDetail(serialNumber);
    }

    private Iterable<ProductSFDetail> getListProductSFDetail(List<CartDetailSF> cartDetailSFS) {
        List<ProductSFDetail> tempCartDetail = new ArrayList<>();
        for (CartDetailSF c : cartDetailSFS
        ) {
            tempCartDetail.add(findProductSFDetailBySerialNumber(c.getSerialNumber()).get());
        }
        return tempCartDetail;
    }

    private Optional<CartSF> findAccountName(String name) {
        return iCartRepository.findAccountName(name);
    }


    @Override
    public void remove(Long id) {

    }
}
