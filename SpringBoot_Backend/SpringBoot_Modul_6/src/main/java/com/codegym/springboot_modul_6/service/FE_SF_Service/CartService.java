package com.codegym.springboot_modul_6.service.FE_SF_Service;

import com.codegym.springboot_modul_6.model.FE_SF_Model.Entity.*;
import com.codegym.springboot_modul_6.model.FE_SF_Model.model.CartModel;
import com.codegym.springboot_modul_6.repository.FE_SF_Repository.IAccountRepository;
import com.codegym.springboot_modul_6.repository.FE_SF_Repository.ICartRepository;
import com.codegym.springboot_modul_6.repository.FE_SF_Repository.IProductDetailSFRepository;
import com.codegym.springboot_modul_6.util.FE_SF_Util.Mapper.LongMapper;
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
    private IAccountRepository iAccountRepository;

    @Autowired
    private IProductDetailSFRepository iProductDetailSFRepository;

    @Autowired
    private LongMapper mapper;

    @Autowired
    private IProductService iProductService;

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

    @Override
    public void removeCartItem(String serialNumber, String accountName) {
        Optional<CartSF> cartOld = iCartRepository.findAccountName(accountName);
        iCartRepository.removeCartItem(cartOld.orElseThrow(), serialNumber);
        Optional<CartSF> cartNew = iCartRepository.findAccountName(accountName);
        Account account = iAccountRepository.findByUsername(accountName).orElseThrow();
        cartNew.orElseThrow().setAccount(account);
        cartNew.orElseThrow().setTotalPrice(getTotalMoney(cartNew.orElseThrow().getCartDetailSFS()));
        iCartRepository.save(cartNew.get());
    }

    @Override
    public Optional<CartModel> getCart(String accountName) {
        Optional<CartSF> cartSF = iCartRepository.findAccountName(accountName);
        Optional<CartModel> cartModel = mapper.cartModel(cartSF.orElseThrow());
        return cartModel;
    }

    public void addCartExistOrNewItem(CartSF cartNew, CartSF cartOld) {
        Account account = iAccountRepository.findByUsername(cartNew.getAccountName()).orElseThrow();
        CartSF cartSF = new CartSF();
        cartSF.setId(cartOld.getId());
        cartSF.setAccountName(cartOld.getAccountName());
        ProductSFDetail getBySerialNumber = iProductDetailSFRepository.getProductSFDetail(cartNew.getCartDetailSFS().get(0).getSerialNumber()).orElseThrow();
        ProductSF productSF = iProductService.findById(getBySerialNumber.getProductSF().getId()).orElseThrow();
        if (cartOld.getCartDetailSFS().size() == 0) {
            List<CartDetailSF> cartDetailSFSNew = new ArrayList<>();
            CartDetailSF cartDetailSF = new CartDetailSF();
            cartDetailSF.setCartSF(cartOld);
            cartDetailSF.setName(productSF.getName());
            cartDetailSF.setSerialNumber(cartNew.getCartDetailSFS().get(0).getSerialNumber());
            cartDetailSF.setPrice(cartNew.getCartDetailSFS().get(0).getPrice());
            cartDetailSF.setQuantity(cartNew.getCartDetailSFS().get(0).getQuantity());
            cartDetailSF.setSubTotal(cartDetailSF.getPrice() * cartDetailSF.getQuantity());
            cartDetailSFSNew.add(cartDetailSF);
            cartSF.setAccount(account);
            cartSF.setCartDetailSFS(cartDetailSFSNew);
            cartSF.setTotalPrice(getTotalMoney(cartSF.getCartDetailSFS()));
            iCartRepository.save(cartSF);
        } else {
            for (CartDetailSF c : cartOld.getCartDetailSFS()
            ) {
                CartDetailSF cartDetailSF = new CartDetailSF();
                if (Objects.equals(c.getSerialNumber(), cartNew.getCartDetailSFS().get(0).getSerialNumber())) {
                    BeanUtils.copyProperties(c, cartDetailSF);
                    cartDetailSF.setId(c.getId());
                    cartDetailSF.setCartSF(cartOld);
                    cartDetailSF.setName(productSF.getName());
                    cartDetailSF.setPrice(cartNew.getCartDetailSFS().get(0).getPrice());
                    cartDetailSF.setQuantity(cartNew.getCartDetailSFS().get(0).getQuantity());
                    cartDetailSF.setSubTotal(cartDetailSF.getPrice() * cartDetailSF.getQuantity());
                    cartOld.getCartDetailSFS().remove(c);
                    cartOld.getCartDetailSFS().add(cartDetailSF);
                    break;
                } else {
                    cartDetailSF.setCartSF(cartOld);
                    cartDetailSF.setSerialNumber(cartNew.getCartDetailSFS().get(0).getSerialNumber());
                    cartDetailSF.setName(productSF.getName());
                    cartDetailSF.setPrice(cartNew.getCartDetailSFS().get(0).getPrice());
                    cartDetailSF.setQuantity(cartNew.getCartDetailSFS().get(0).getQuantity());
                    cartDetailSF.setSubTotal(cartDetailSF.getPrice() * cartDetailSF.getQuantity());
                    cartOld.getCartDetailSFS().add(cartDetailSF);
                    break;
                }
            }
            cartSF.setAccount(account);
            cartSF.setCartDetailSFS(cartOld.getCartDetailSFS());
            cartSF.setTotalPrice(getTotalMoney(cartSF.getCartDetailSFS()));
            iCartRepository.save(cartSF);
        }


    }

    private void updateCart() {
        //===========================================================
//        int count = 0;
//        if (cartNew.getCartDetailSFS().size() < cartOld.getCartDetailSFS().size()) {
//            for (int i = 0; i < cartNew.getCartDetailSFS().size(); i++) {
//                for (int j = 0; j < cartOld.getCartDetailSFS().size(); j++) {
//                    CartDetailSF cartDetailSF = new CartDetailSF();
//                    if (Objects.equals(cartOld.getCartDetailSFS().get(j).getSerialNumber(), cartNew.getCartDetailSFS().get(i).getSerialNumber())) {
//                        ProductSFDetail productSFDetail = findProductSFDetailBySerialNumber(cartNew.getCartDetailSFS().get(i).getSerialNumber()).orElseThrow();
//                        ProductSF productSF = iProductService.findById(productSFDetail.getProductSF().getId()).orElseThrow();
//                        BeanUtils.copyProperties(cartNew.getCartDetailSFS().get(i), cartDetailSF);
//                        cartDetailSF.setName(productSF.getName());
//                        cartDetailSF.setId(cartOld.getCartDetailSFS().get(j).getId());
//                        cartDetailSF.setCartSF(cartOld);
//                        cartDetailSF.setSubTotal(cartDetailSF.getPrice() * cartDetailSF.getQuantity());
//                        cartDetailSFS.add(cartDetailSF);
//                        count++;
//                    }
//                }
//            }
//            for (int i = count; i < cartNew.getCartDetailSFS().size(); i++) {
//                CartDetailSF cartDetailSF = new CartDetailSF();
//                ProductSFDetail productSFDetail = findProductSFDetailBySerialNumber(cartNew.getCartDetailSFS().get(i).getSerialNumber()).orElseThrow();
//                ProductSF productSF = iProductService.findById(productSFDetail.getProductSF().getId()).orElseThrow();
//                BeanUtils.copyProperties(cartNew.getCartDetailSFS().get(i), cartDetailSF);
//                cartDetailSF.setName(productSF.getName());
//                cartDetailSF.setCartSF(cartOld);
//                cartDetailSF.setSubTotal(cartDetailSF.getPrice() * cartDetailSF.getQuantity());
//                cartDetailSFS.add(cartDetailSF);
//            }
//            cartSF.setCartDetailSFS(cartDetailSFS);
//            cartSF.setTotalPrice(getTotalMoney(cartSF.getCartDetailSFS()) + cartOld.getTotalPrice());
//            iCartRepository.save(cartSF);
//        } else {
//            for (int i = 0; i < cartNew.getCartDetailSFS().size(); i++) {
//                for (int j = 0; j < cartOld.getCartDetailSFS().size(); j++) {
//                    CartDetailSF cartDetailSF = new CartDetailSF();
//                    if (Objects.equals(cartOld.getCartDetailSFS().get(j).getSerialNumber(), cartNew.getCartDetailSFS().get(i).getSerialNumber())) {
//                        ProductSFDetail productSFDetail = findProductSFDetailBySerialNumber(cartNew.getCartDetailSFS().get(i).getSerialNumber()).orElseThrow();
//                        ProductSF productSF = iProductService.findById(productSFDetail.getProductSF().getId()).orElseThrow();
//                        BeanUtils.copyProperties(cartNew.getCartDetailSFS().get(i), cartDetailSF);
//                        cartDetailSF.setName(productSF.getName());
//                        cartDetailSF.setId(cartOld.getCartDetailSFS().get(j).getId());
//                        cartDetailSF.setCartSF(cartOld);
//                        cartDetailSF.setSubTotal(cartDetailSF.getPrice() * cartDetailSF.getQuantity());
//                        cartDetailSFS.add(cartDetailSF);
//                        count++;
//                    }
//                }
//            }
//            for (int i = count; i < cartNew.getCartDetailSFS().size(); i++) {
//                CartDetailSF cartDetailSF = new CartDetailSF();
//                ProductSFDetail productSFDetail = findProductSFDetailBySerialNumber(cartNew.getCartDetailSFS().get(i).getSerialNumber()).orElseThrow();
//                ProductSF productSF = iProductService.findById(productSFDetail.getProductSF().getId()).orElseThrow();
//                BeanUtils.copyProperties(cartNew.getCartDetailSFS().get(i), cartDetailSF);
//                cartDetailSF.setName(productSF.getName());
//                cartDetailSF.setCartSF(cartOld);
//                cartDetailSF.setSubTotal(cartDetailSF.getPrice() * cartDetailSF.getQuantity());
//                cartDetailSFS.add(cartDetailSF);
//            }
//            cartSF.setAccount(account);
//            cartSF.setCartDetailSFS(cartDetailSFS);
//            cartSF.setTotalPrice(getTotalMoney(cartSF.getCartDetailSFS()));
//            iCartRepository.save(cartSF);
//        }
//=================================================================
    }

    private void addNewCart(CartSF cartSF) {
        List<CartDetailSF> cartDetailSFS = new ArrayList<>();
        ProductSFDetail productSFDetail = findProductSFDetailBySerialNumber(cartSF.getCartDetailSFS().get(0).getSerialNumber()).orElseThrow();
        ProductSF productSF = iProductService.findById(productSFDetail.getProductSF().getId()).orElseThrow();
        for (CartDetailSF c : cartSF.getCartDetailSFS()
        ) {
            CartDetailSF cartTemp = new CartDetailSF();
            BeanUtils.copyProperties(c, cartTemp);
            cartTemp.setName(productSF.getName());
            cartTemp.setCartSF(cartSF);
            cartTemp.setSubTotal(cartTemp.getPrice() * cartTemp.getQuantity());
            cartDetailSFS.add(cartTemp);
        }
        Account account = iAccountRepository.findByUsername(cartSF.getAccountName()).orElseThrow();
        cartSF.setAccount(account);
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
    public boolean remove(Long id) {
        return false;
    }
}