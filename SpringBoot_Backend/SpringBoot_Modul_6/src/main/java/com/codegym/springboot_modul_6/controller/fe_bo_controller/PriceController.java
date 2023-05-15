package com.codegym.springboot_modul_6.controller.fe_bo_controller;


import com.codegym.springboot_modul_6.model.fe_sf_model.dto.PriceDto;
import com.codegym.springboot_modul_6.service.fe_bo_service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "${app.cors.allowedOrigins}")
@RequestMapping("/api/price")
public class PriceController {
    @Autowired
    private PriceService priceService;

    @PostMapping("/update/{serialNumber}")
    public ResponseEntity<?> updatePriceList(@PathVariable String serialNumber, @RequestBody List<PriceDto> priceDto) {
        try{
            Boolean isSuccess = priceService.updatePriceList(serialNumber, priceDto);
            if (isSuccess) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
