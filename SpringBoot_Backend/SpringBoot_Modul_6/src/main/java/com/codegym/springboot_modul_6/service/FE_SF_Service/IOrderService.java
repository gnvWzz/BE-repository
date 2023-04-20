package com.codegym.springboot_modul_6.service.FE_SF_Service;

import com.codegym.springboot_modul_6.model.FE_SF_Model.Entity.OrderSF;
import com.codegym.springboot_modul_6.model.FE_SF_Model.dto.OrderDto;

import java.util.List;

public interface IOrderService {
    void saveOrder(OrderDto orderDto, String username);

    List<OrderSF> getAllOrderByAccountId(Long accountId);
}
