package com.codegym.springboot_modul_6.controller.fe_bo_controller;

import com.codegym.springboot_modul_6.model.fe_bo_model.dto.request.RequestStoreDto;
import com.codegym.springboot_modul_6.model.fe_bo_model.dto.response.ResponseStoreDto;
import com.codegym.springboot_modul_6.service.fe_bo_service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "${app.cors.allowedOrigins}")
@RequestMapping("/api/store")
public class StoreController {
    @Autowired
    private StoreService storeService;

    @GetMapping("/{accountUsername}")
    public ResponseEntity<?> getStoreByAccountUsername(@PathVariable String accountUsername){
        try{
            ResponseStoreDto responseStoreDto = storeService.findStoreByAccountUsername(accountUsername).orElse(null);
            if(responseStoreDto != null) {
                return new ResponseEntity<>(responseStoreDto, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/update-image")
    public ResponseEntity<?> updateImage(@RequestBody RequestStoreDto requestStoreDto) {
        try{
            ResponseStoreDto response = storeService.updateImage(requestStoreDto);
            if(response != null) {
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/update-name")
    public ResponseEntity<?> updateName(@RequestBody RequestStoreDto requestStoreDto) {
        try{
            ResponseStoreDto response = storeService.updateName(requestStoreDto);
            if(response != null) {
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

