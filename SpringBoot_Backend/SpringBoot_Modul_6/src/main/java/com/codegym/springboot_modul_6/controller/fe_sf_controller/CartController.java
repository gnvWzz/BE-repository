package com.codegym.springboot_modul_6.controller.fe_sf_controller;

import com.codegym.springboot_modul_6.model.fe_sf_model.dto.CartDto;
import com.codegym.springboot_modul_6.model.fe_sf_model.entity.CartSF;
import com.codegym.springboot_modul_6.model.fe_sf_model.model.CartModel;
import com.codegym.springboot_modul_6.service.fe_sf_service.CartService;
import com.codegym.springboot_modul_6.util.CartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "${app.cors.allowedOrigins}")
@RequestMapping(value = "/api/cart")
public class CartController {

    @Autowired
    private CartMapper mapper;

    @Autowired
    private CartService cartService;

    @PostMapping(value = "/")
    public ResponseEntity<?> addToCart(@RequestBody CartDto cartDto) {
        try {
            CartSF cartSF = mapper.mapperCart(cartDto);
            if (cartSF != null) {
                cartService.saveCart(cartSF);
                return new ResponseEntity<>("Add successfully", HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Fail", HttpStatus.OK);
    }

    @PutMapping(value = "/")
    public ResponseEntity<?> updateCart(@RequestBody CartDto cartDto) {
        try {
            CartSF cartSF = mapper.mapperCart(cartDto);
            if (cartSF != null) {
                cartService.updateCart(cartSF);
                return new ResponseEntity<>("OK", HttpStatus.OK);
            }
            return new ResponseEntity<>("OK", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("FAIL", HttpStatus.OK);
    }

    @GetMapping(value = "/")
    public ResponseEntity<?> getCartByAccountName(@RequestParam(value = "account-name") String accountName) {
        try {
            Optional<CartModel> cartModel = cartService.getCart(accountName);
            if (cartModel.isEmpty()) {
                return new ResponseEntity<>("Empty", HttpStatus.OK);
            }
            return new ResponseEntity<>(cartModel.orElseThrow(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Fail", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping(value = "/")
    public ResponseEntity<?> deleteAItemCart(@RequestBody Map<String, String> json) {
        try {
            cartService.removeCartItem(json.get("serialNumber"), json.get("accountName"));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Fail", HttpStatus.OK);
    }
}
