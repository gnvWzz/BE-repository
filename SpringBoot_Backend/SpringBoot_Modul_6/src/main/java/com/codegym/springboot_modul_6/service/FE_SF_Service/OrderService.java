package com.codegym.springboot_modul_6.service.FE_SF_Service;

import com.codegym.springboot_modul_6.model.FE_SF_Model.Entity.Account;
import com.codegym.springboot_modul_6.model.FE_SF_Model.Entity.CartSF;
import com.codegym.springboot_modul_6.model.FE_SF_Model.Entity.OrderDetailSF;
import com.codegym.springboot_modul_6.model.FE_SF_Model.Entity.OrderSF;
import com.codegym.springboot_modul_6.model.FE_SF_Model.dto.OrderDetailDto;
import com.codegym.springboot_modul_6.model.FE_SF_Model.dto.OrderDto;
import com.codegym.springboot_modul_6.repository.FE_SF_Repository.IOrderRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderService implements IOrderService{

    @Autowired
    private IOrderRepository iOrderRepository;

//    public Optional<OrderSF> checkOutOrder(String username){
//
//    }

    @Autowired
    private IAccountService iAccountService;

    @Autowired
    private ICartService iCartService;


    @Override
    @Transactional
    public void saveOrder(OrderDto orderDto, String username){
        Account account = iAccountService.findAccountByUsername(username).orElseThrow();
        CartSF cartSF = iCartService.findCartSFByAccountId(account.getId())
                .orElseThrow(() -> new RuntimeException("Cart Not Found"));
        saveOrder(orderDto, account);
        replaceTotalPriceCart(cartSF);
        cleanCartItems(cartSF);
    }

    private void cleanCartItems(CartSF cartSF) {
        iCartService.deleteCartItem(cartSF.getId());
    }

    private void replaceTotalPriceCart(CartSF cartSF){
        double beginTotalPrice = 0;
        cartSF.setTotalPrice(beginTotalPrice);
        iCartService.save(cartSF);
    }

    private void saveOrder(OrderDto orderDto, Account account) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        OrderSF order = new OrderSF();
        order.setAccount(account);
        BeanUtils.copyProperties(orderDto, order);
        order.setOrderDetailSFS(orderDetailSFList(orderDto.getOrderDetailDtoList(), order));
        order.setTotalPrice(getTotal(order.getOrderDetailSFS()));
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

}
