package com.codegym.springboot_modul_6.service.fe_sf_service.impl;

import com.codegym.springboot_modul_6.model.fe_sf_model.entity.*;
import com.codegym.springboot_modul_6.model.fe_sf_model.model.CartModel;
import com.codegym.springboot_modul_6.repository.fe_sf_repository.IAccountRepository;
import com.codegym.springboot_modul_6.repository.fe_sf_repository.ICartRepository;
import com.codegym.springboot_modul_6.repository.fe_sf_repository.IPriceListRepository;
import com.codegym.springboot_modul_6.repository.fe_sf_repository.IProductDetailSFRepository;
import com.codegym.springboot_modul_6.service.fe_sf_service.CartService;
import com.codegym.springboot_modul_6.service.fe_sf_service.ProductService;
import com.codegym.springboot_modul_6.util.LongMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private ICartRepository iCartRepository;

    @Autowired
    private IPriceListRepository iPriceListRepository;

    @Autowired
    private IAccountRepository iAccountRepository;

    @Autowired
    private IProductDetailSFRepository iProductDetailSFRepository;

    @Autowired
    private LongMapper mapper;

    @Autowired
    private ProductService iProductService;


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
        iCartRepository.save(cartSF);
    }


    @Override
    public void saveCart(CartSF cartSF) {
        Optional<CartSF> cart = findCartSFByAccountName(cartSF.getAccountName());
        if (cart.isPresent()) {
            addCartExistOrNewItem(cartSF, cart.get());
        } else {
            addNewCart(cartSF);
        }
    }

    @Override
    public void removeCartItem(String serialNumber, String accountName) {
        Optional<CartSF> cart = iCartRepository.findCartByAccountName(accountName);
        for (CartDetailSF c : cart.orElseThrow().getCartDetailSFS()
        ) {
            if (Objects.equals(c.getSerialNumber(), serialNumber)) {
                c.setQuantity(0L);
                c.setIsDeleted("true");
            }
        }
        iCartRepository.save(cart.orElseThrow());
    }

    @Override
    public Optional<CartModel> getCart(String accountName) {
        Optional<CartSF> cartSF = iCartRepository.findCartByAccountName(accountName);
        if (cartSF.isEmpty()){
            return Optional.empty();
        }
        Optional<CartModel> cartModel = mapper.cartModel(cartSF.orElseThrow());
        return cartModel;
    }

    @Override
    public void deleteCartItem(Long id) {
        iCartRepository.removeCartItemById(id);
    }


    public void addCartExistOrNewItem(CartSF cartNew, CartSF cartOld) {
        Account account = iAccountRepository.findByUsername(cartNew.getAccountName()).orElseThrow();
        CartSF cartSF = new CartSF();
        int count = 1;
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
            cartDetailSF.setQuantity(cartNew.getCartDetailSFS().get(0).getQuantity());
            cartDetailSF.setPrice(cartService_getPriceFromPriceList(cartDetailSF.getQuantity(), cartDetailSF.getSerialNumber()));
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
                    cartDetailSF.setQuantity(cartNew.getCartDetailSFS().get(0).getQuantity() + c.getQuantity());
                    cartDetailSF.setPrice(cartService_getPriceFromPriceList(cartDetailSF.getQuantity(), c.getSerialNumber()));
                    cartDetailSF.setSubTotal(cartDetailSF.getPrice() * cartDetailSF.getQuantity());
                    cartDetailSF.setIsDeleted("false");
                    cartOld.getCartDetailSFS().remove(c);
                    cartOld.getCartDetailSFS().add(cartDetailSF);
                    count--;
                    break;
                }
            }
            if (count == 1) {
                for (CartDetailSF c : cartOld.getCartDetailSFS()
                ) {
                    CartDetailSF cartDetailSF = new CartDetailSF();
                    cartDetailSF.setCartSF(cartOld);
                    cartDetailSF.setSerialNumber(cartNew.getCartDetailSFS().get(0).getSerialNumber());
                    cartDetailSF.setName(productSF.getName());
                    cartDetailSF.setQuantity(cartNew.getCartDetailSFS().get(0).getQuantity());
                    cartDetailSF.setPrice(cartService_getPriceFromPriceList(cartDetailSF.getQuantity(), cartDetailSF.getSerialNumber()));
                    cartDetailSF.setSubTotal(cartDetailSF.getPrice() * cartDetailSF.getQuantity());
                    cartDetailSF.setIsDeleted("false");
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
            tempCartDetail.add(findProductSFDetailBySerialNumber(c.getSerialNumber()).orElseThrow(() -> new RuntimeException("Product Detail find by serial number not found")));
            return tempCartDetail;
        }
        return tempCartDetail;
    }


    @Override
    public Optional<CartSF> findCartSFByAccountName(String name) {
        return iCartRepository.findCartByAccountName(name);
    }

    @Override
    public Optional<CartSF> findCartSFByAccountId(Long accountId) {
        return iCartRepository.findByAccountId(accountId);
    }

    @Override
    public void updateCart(CartSF cartSF) {
        List<CartDetailSF> cartDetailSFS = new ArrayList<>();
        CartSF cartOld = iCartRepository.findCartByAccountName(cartSF.getAccountName()).orElseThrow(() -> new RuntimeException("Cart not found"));
        int countCartItemIsNotDeleted = 0;
        for (CartDetailSF c : cartOld.getCartDetailSFS()
        ) {
            if (Objects.equals(c.getIsDeleted(), "false")) {
                CartDetailSF cartDetailSF = new CartDetailSF();
                cartDetailSF.setCartSF(c.getCartSF());
                cartDetailSF.setId(c.getId());
                cartDetailSF.setName(cartSF.getCartDetailSFS().get(countCartItemIsNotDeleted).getName());
                cartDetailSF.setQuantity(cartSF.getCartDetailSFS().get(countCartItemIsNotDeleted).getQuantity());
                cartDetailSF.setSerialNumber(cartSF.getCartDetailSFS().get(countCartItemIsNotDeleted).getSerialNumber());
                cartDetailSF.setPrice(cartService_getPriceFromPriceList(cartDetailSF.getQuantity(), cartDetailSF.getSerialNumber()));
                cartDetailSF.setIsDeleted(c.getIsDeleted());
                cartDetailSF.setSubTotal(cartDetailSF.getPrice() * cartDetailSF.getQuantity());
                cartDetailSFS.add(cartDetailSF);
                countCartItemIsNotDeleted++;
            }
        }
        cartOld.setCartDetailSFS(cartDetailSFS);
        cartOld.setTotalPrice(getTotalMoney(cartOld.getCartDetailSFS()));
        cartOld.setIsDeleted("false");
        iCartRepository.save(cartOld);
    }


    private Double cartService_getPriceFromPriceList(Long quantity, String serialNumber){
        ProductSFDetail productSF = iProductDetailSFRepository.getProductSFDetail(serialNumber).orElseThrow(() -> new RuntimeException(
                "Product detail not found"
        ));
        List<PriceList> priceLists = iPriceListRepository.getPriceListByProductId(productSF.getProductSF().getId());
        Double priceOfCartItem = 0.0;
        for (PriceList p: priceLists
             ) {
            if ((p.getToQuantity() >= quantity) && (p.getFromQuantity() <= quantity)){
                priceOfCartItem = p.getPrice();
            }
        }
        return priceOfCartItem;
    }

    @Override
    public boolean remove(Long id) {
        return false;
    }
}
