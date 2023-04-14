package com.codegym.springboot_modul_6.controller.FE_BO_Controller;

import com.codegym.springboot_modul_6.model.FE_BO_Model.dto.response.ResponseProductBODto;
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

    @GetMapping("/{id}")
    public ResponseEntity<?> getStoreById(@PathVariable Long id){
        ResponseStoreDto responseStoreDto = storeService.findById(id).orElse(null);
        return new ResponseEntity<>(responseStoreDto, HttpStatus.OK);
    }


}
