package com.codegym.springboot_modul_6.controller.FE_BO_Controller;

import com.codegym.springboot_modul_6.model.FE_BO_Model.dto.request.RequestStoreDto;

import com.codegym.springboot_modul_6.model.FE_BO_Model.dto.response.ResponseStoreDto;
import com.codegym.springboot_modul_6.service.FE_BO_Service.StoreService;
import com.codegym.springboot_modul_6.service.FE_BO_Service.impl.StoreServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/store")
public class StoreController {
    @Autowired
    private StoreService storeService;

    @GetMapping("/{accountUsername}")
    public ResponseEntity<?> getStoreByAccountUsername(@PathVariable String accountUsername){
        ResponseStoreDto responseStoreDto = storeService.findStoreByAccountUsername(accountUsername).orElse(null);
        return new ResponseEntity<>(responseStoreDto, HttpStatus.OK);
    }
    @PostMapping("/update-image")
    public ResponseEntity<?> updateImage(@RequestBody RequestStoreDto requestStoreDto) {
        ResponseStoreDto response = storeService.updateImage(requestStoreDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PostMapping("/update-name")
    public ResponseEntity<?> updateName(@RequestBody RequestStoreDto requestStoreDto) {
        ResponseStoreDto response = storeService.updateName(requestStoreDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

