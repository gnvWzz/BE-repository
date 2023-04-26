package com.codegym.springboot_modul_6.service.fe_sf_service.impl;

import com.codegym.springboot_modul_6.model.fe_sf_model.entity.*;
import com.codegym.springboot_modul_6.model.fe_sf_model.dto.OrderDetailDto;
import com.codegym.springboot_modul_6.model.fe_sf_model.dto.OrderDto;
import com.codegym.springboot_modul_6.model.fe_sf_model.dto.PromoOrderDto;
import com.codegym.springboot_modul_6.repository.fe_sf_repository.IOrderRepository;
import com.codegym.springboot_modul_6.repository.fe_sf_repository.IProductDetailSFRepository;
import com.codegym.springboot_modul_6.service.fe_sf_service.AccountService;
import com.codegym.springboot_modul_6.service.fe_sf_service.CartService;
import com.codegym.springboot_modul_6.service.fe_sf_service.OrderService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private IOrderRepository iOrderRepository;

//    public Optional<OrderSF> checkOutOrder(String username){
//
//    }

    @Autowired
    private AccountService accountService;

    @Autowired
    private CartService cartService;

    @Autowired
    private IProductDetailSFRepository iProductDetailSFRepository;


    @Override
    @Transactional
    public void saveOrder(OrderDto orderDto, String username){
        Account account = accountService.findAccountByUsername(username).orElseThrow();
        CartSF cartSF = cartService.findCartSFByAccountId(account.getId())
                .orElseThrow(() -> new RuntimeException("Cart Not Found"));
        List<String> serialNumbers = new ArrayList<>();
        for (OrderDetailDto o: orderDto.getOrderDetailDtoList()
             ) {
            serialNumbers.add(o.getSerialNumber());
        }
        List<ProductSFDetail> productSFDetails = iProductDetailSFRepository.getProductSFDetails(serialNumbers);
        List<String> productHaveQuantityEqual0 = new ArrayList<>();
        for (ProductSFDetail p: productSFDetails
             ) {
            JSONParser parser = new JSONParser();
            try {
                JSONObject sizeColorImgQuantity = (JSONObject) parser.parse(p.getSize_color_img_quantity());
                Gson gson = new GsonBuilder().create();
                SizeColorImgQuantity sizeColorImgQuantity1 = gson.fromJson(sizeColorImgQuantity.toString(), SizeColorImgQuantity.class);
                for (OrderDetailDto o : orderDto.getOrderDetailDtoList()
                ) {
                    if (o.getSerialNumber().equals(p.getSerialNumber())) {
                        if (sizeColorImgQuantity1.getQuantity() - o.getQuantity() >= 0) {
                            sizeColorImgQuantity1.setQuantity(sizeColorImgQuantity1.getQuantity() - o.getQuantity());
                            String temp = gson.toJson(sizeColorImgQuantity1);
                            p.setSize_color_img_quantity(temp);
                            iProductDetailSFRepository.save(p);
                        } else {
                            productHaveQuantityEqual0.add(o.getName());
                        }
                    }
                }
            } catch (ParseException e) {
                throw new RuntimeException();
            }
        }

        if (productHaveQuantityEqual0.size() > 0){
            throw new RuntimeException(productHaveQuantityEqual0.toString());
        }

        saveOrder(orderDto, account);
        replaceTotalPriceCart(cartSF);
        cleanCartItems(cartSF);
    }

    @Override
    public List<OrderSF> getAllOrderByAccountId(Long accountId) {
        return iOrderRepository.getAllByAccount_Id(accountId);
    }

    @Override
    public Optional<OrderSF> findOrderByOrderCode(String orderCode) {
        return iOrderRepository.findByOrderCode(orderCode);
    }


    private void cleanCartItems(CartSF cartSF) {
        cartService.deleteCartItem(cartSF.getId());
    }



    private void replaceTotalPriceCart(CartSF cartSF){
        double beginTotalPrice = 0;
        cartSF.setTotalPrice(beginTotalPrice);
        cartService.save(cartSF);
    }

    private void saveOrder(OrderDto orderDto, Account account) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        OrderSF order = new OrderSF();
        order.setAccount(account);
        BeanUtils.copyProperties(orderDto, order);
        order.setOrderDetailSFS(orderDetailSFList(orderDto.getOrderDetailDtoList(), order));
//        order.setTotalPrice(getTotal(order.getOrderDetailSFS()));
        UUID uuid = UUID.randomUUID();
        order.setOrderCode(String.valueOf(uuid));
        order.setDateOrder(simpleDateFormat.format(new Date()));
        iOrderRepository.save(order);
    }

    private double getTotal(List<OrderDetailSF> orderDetailSFS){
        double total = 0f;
        for (OrderDetailSF o:
             orderDetailSFS) {
            total += (o.getQuantity() * o.getPrice());
        }
        return total;
    }


    private List<OrderDetailSF> orderDetailSFList(List<OrderDetailDto> orderDetailDtoList, OrderSF orderSF){
        List<OrderDetailSF> orderDetailSFS = new ArrayList<>();
        for (OrderDetailDto o :
                orderDetailDtoList) {
            OrderDetailSF orderDetailSF = new OrderDetailSF();
            BeanUtils.copyProperties(o, orderDetailSF);
            orderDetailSF.setOrderSF(orderSF);
            orderDetailSF.setSubTotal(orderDetailSF.getPrice() * orderDetailSF.getQuantity());
            orderDetailSFS.add(orderDetailSF);
        }
        return orderDetailSFS;
    }


//    Long them chuc nang discount order
    @Override
    public Optional<PromoOrderDto> orderSerive_promoOrderDto(PromoOrderDto promoOrderDto, List<Promos> promos){
        boolean have_promo = false;
        for (Promos p: promos
             ) {
            if (Objects.equals(p.getName(), promoOrderDto.getPromoCode())){
                Long moneyPhantram = (long) (promoOrderDto.getTotalPrice() * p.getDiscount()/ 100);
                promoOrderDto.setTotalPrice(promoOrderDto.getTotalPrice() - moneyPhantram);
                have_promo = true;
            }
        }
        if (!have_promo){
            throw new RuntimeException();
        }
        return Optional.ofNullable(promoOrderDto);
    }
}
