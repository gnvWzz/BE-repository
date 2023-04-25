package com.codegym.springboot_modul_6.service.fe_sf_service;

import com.codegym.springboot_modul_6.model.fe_sf_model.entity.OrderSF;
import com.codegym.springboot_modul_6.model.fe_sf_model.entity.Promos;
import com.codegym.springboot_modul_6.model.fe_sf_model.dto.OrderDto;
import com.codegym.springboot_modul_6.model.fe_sf_model.dto.PromoOrderDto;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    void saveOrder(OrderDto orderDto, String username);

    //    Long them chuc nang discount order
    Optional<PromoOrderDto> orderSerive_promoOrderDto(PromoOrderDto promoOrderDto, List<Promos> promos);
}
