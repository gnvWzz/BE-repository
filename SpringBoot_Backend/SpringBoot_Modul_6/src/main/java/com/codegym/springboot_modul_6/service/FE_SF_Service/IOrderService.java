package com.codegym.springboot_modul_6.service.FE_SF_Service;

import com.codegym.springboot_modul_6.model.FE_SF_Model.Entity.OrderSF;
import com.codegym.springboot_modul_6.model.FE_SF_Model.Entity.Promos;
import com.codegym.springboot_modul_6.model.FE_SF_Model.dto.OrderDto;
import com.codegym.springboot_modul_6.model.FE_SF_Model.dto.PromoOrderDto;

import java.util.List;
import java.util.Optional;

public interface IOrderService {
    void saveOrder(OrderDto orderDto, String username);

    List<OrderSF> getAllOrderByAccountId(Long accountId);

    Optional<OrderSF> findOrderByOrderCode(String orderCode);

    //    Long them chuc nang discount order
    Optional<PromoOrderDto> orderSerive_promoOrderDto(PromoOrderDto promoOrderDto, List<Promos> promos);
}
