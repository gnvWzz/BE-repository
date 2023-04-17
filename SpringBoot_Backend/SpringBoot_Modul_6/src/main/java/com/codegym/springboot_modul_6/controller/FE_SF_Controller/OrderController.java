package com.codegym.springboot_modul_6.controller.FE_SF_Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
@CrossOrigin(origins = "http://localhost:3000")
public class OrderController {

    public ResponseEntity<?> getOrderByName(@RequestBody String username){
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
