package com.codegym.springboot_modul_6.controller.fe_sf_controller;

import com.codegym.springboot_modul_6.model.fe_sf_model.dto.OrderDto;
import com.codegym.springboot_modul_6.model.fe_sf_model.dto.PromoOrderDto;
import com.codegym.springboot_modul_6.model.fe_sf_model.entity.Promos;
import com.codegym.springboot_modul_6.model.fe_sf_model.model.OrderDetailsSFModel;
import com.codegym.springboot_modul_6.model.fe_sf_model.model.OrderSFModel;
import com.codegym.springboot_modul_6.service.fe_sf_service.OrderService;
import com.codegym.springboot_modul_6.service.thirdpartyservice.CategoryCache;
import com.codegym.springboot_modul_6.service.thirdpartyservice.ThirdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
@CrossOrigin(origins = "${app.cors.allowedOrigins}")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ThirdService thirdService;

    private final CategoryCache categoryCache = CategoryCache.getCategoryCache();

    @GetMapping(value = "/")
    public ResponseEntity<?> getOrderByName() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/")
    public ResponseEntity<?> saveOrder(@RequestBody OrderDto orderDto,
                                       @RequestParam(value = "username") String username) {
        try {
            orderService.saveOrder(orderDto, username);
            return new ResponseEntity<>("Add successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/list/{username}")
    public ResponseEntity<?> getListOrder(@PathVariable("username") String username) {
        try {
            List<OrderSFModel> orderSFModelList = thirdService.getListOrder(username);
            return new ResponseEntity<>(orderSFModelList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("FAIL", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/orderDetails/{orderCode}")
    public ResponseEntity<?> getOrderDetails(@PathVariable("orderCode") String orderCode) {
        try {
            List<OrderDetailsSFModel> orderDetailsSFModelList = thirdService.getListOrderDetails(orderCode);
            return new ResponseEntity<>(orderDetailsSFModelList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("FAIL", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    Long them vao chuc nang promos
    @PostMapping("/promo")
    @SuppressWarnings("unchecked")
    public ResponseEntity<?> getPromosOrder(@RequestBody PromoOrderDto promoOrderDto) {
        try {
            List<Promos> promos = (List<Promos>) categoryCache.getCacheCategories().get(CategoryCache.PROMOS);
            PromoOrderDto afterDiscount = orderService.orderSerive_promoOrderDto(promoOrderDto, promos).orElseThrow(() -> new RuntimeException("Illegal order"));
            return new ResponseEntity<>(afterDiscount, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Promo is invalid", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
