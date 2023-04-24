package com.codegym.springboot_modul_6.controller.FE_SF_Controller;

import com.codegym.springboot_modul_6.model.FE_SF_Model.Entity.Promos;
import com.codegym.springboot_modul_6.model.FE_SF_Model.dto.OrderDto;
import com.codegym.springboot_modul_6.model.FE_SF_Model.dto.PromoOrderDto;
import com.codegym.springboot_modul_6.model.FE_SF_Model.model.OrderDetailsSFModel;
import com.codegym.springboot_modul_6.model.FE_SF_Model.model.OrderSFModel;
import com.codegym.springboot_modul_6.service.FE_SF_Service.IOrderService;
import com.codegym.springboot_modul_6.service.thirdpartyservice.CategoryCache;
import com.codegym.springboot_modul_6.service.thirdpartyservice.ThirdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
@CrossOrigin(origins = "http://localhost:3000")
public class OrderController {

    @Autowired
    private IOrderService iOrderService;

    @Autowired
    private ThirdService thirdService;

    private CategoryCache categoryCache = CategoryCache.getCategoryCache();

    @GetMapping(value = "")
    public ResponseEntity<?> getOrderByName(@RequestBody String username) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<?> saveOrder(@RequestBody OrderDto orderDto,
                                           @RequestParam(value = "username") String username) {
        try {
            iOrderService.saveOrder(orderDto, username);
            return new ResponseEntity<>("Add successfully", HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/list/{username}")
    public ResponseEntity<?> getListOrder (@PathVariable("username") String username){
        try {
            List<OrderSFModel> orderSFModelList = thirdService.getListOrder( username);
            return new ResponseEntity<>(orderSFModelList, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>("FAIL", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/orderDetails/{orderCode}")
    public ResponseEntity<?> getOrderDetails(@PathVariable("orderCode") String orderCode){
        try {
          List<OrderDetailsSFModel> orderDetailsSFModelList = thirdService.getListOrderDetails( orderCode);
            return new ResponseEntity<>(orderDetailsSFModelList, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>("FAIL", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    Long them vao chuc nang promos
    @PostMapping("/getPromo")
    public ResponseEntity<?> getPromosOrder(@RequestBody PromoOrderDto promoOrderDto){
        try {
            List<Promos> promos = (List<Promos>) categoryCache.getCacheCategories().get(categoryCache.PROMOS);
            PromoOrderDto afterDiscount = iOrderService.orderSerive_promoOrderDto(promoOrderDto, promos).orElseThrow(() -> new RuntimeException("Illegal order"));
            return new ResponseEntity<>(afterDiscount, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Promo is invalid",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
