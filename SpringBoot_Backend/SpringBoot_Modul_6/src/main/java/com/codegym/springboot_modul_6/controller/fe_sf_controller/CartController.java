package com.codegym.springboot_modul_6.controller.fe_sf_controller;

import com.codegym.springboot_modul_6.model.fe_sf_model.entity.CartSF;
import com.codegym.springboot_modul_6.model.fe_sf_model.dto.CartDto;
import com.codegym.springboot_modul_6.model.fe_sf_model.model.CartModel;
import com.codegym.springboot_modul_6.service.FE_SF_Service.ICartService;
import com.codegym.springboot_modul_6.util.FE_SF_Util.Mapper.LongMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/api/cart")
public class CartController {

    @Autowired
    private LongMapper mapper;

    @Autowired
    private ICartService iCartService;

    @PostMapping(value = "/add-to-cart")
    public ResponseEntity<?> addToCart(@RequestBody CartDto cartDto){
        try {
            CartSF cartSF =  mapper.mapperCart(cartDto);
            if (cartSF != null) {
                iCartService.saveCart(cartSF);
                return new ResponseEntity<>("Add successfully", HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Fail", HttpStatus.OK);
    }

    @PutMapping(value = "/")
    public ResponseEntity<?> updateCart(@RequestBody CartDto cartDto){
        try {
            CartSF cartSF = mapper.mapperCart(cartDto);
            if (cartSF != null){
                iCartService.updateCart(cartSF);
                return new ResponseEntity<>("OK", HttpStatus.OK);
            }
            return new ResponseEntity<>("OK", HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("FAIL", HttpStatus.OK);
    }

    @GetMapping(value = "/")
    public ResponseEntity<?> getCartByAccountName(@RequestParam(value = "account-name") String accountName){
        try {
            Optional<CartModel> cartModel = iCartService.getCart(accountName);
            if (cartModel.isEmpty()){
                return new ResponseEntity<>("Empty", HttpStatus.OK);
            }
            return new ResponseEntity<>(cartModel.orElseThrow(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Fail", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping(value = "/cartItem")
    public ResponseEntity<?> deleteAItemCart(@RequestBody Map<String, String > json){
        try{
            iCartService.removeCartItem(json.get("serialNumber"), json.get("accountName"));
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Fail", HttpStatus.OK);
    }
}
