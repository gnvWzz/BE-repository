package com.codegym.springboot_modul_6.controller.FE_BO_Controller;

import com.codegym.springboot_modul_6.model.FE_BO_Model.dto.request.RequestProductBODto;
import com.codegym.springboot_modul_6.model.FE_BO_Model.dto.response.ResponseProductBODto;
import com.codegym.springboot_modul_6.service.FE_BO_Service.impl.ProductBOServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/bo/product")
public class ProductBOController {
    @Autowired
    private ProductBOServiceImpl productServiceBO;



    @GetMapping("/list")
    public ResponseEntity<?> getProductBOList(@PageableDefault(value = 10) Pageable pageable){
        Page<ResponseProductBODto> productDtoBOs = productServiceBO.findAll(pageable);
        return new ResponseEntity<>(productDtoBOs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductBObyId(@PathVariable Long id){
        ResponseProductBODto productDtoBO = productServiceBO.findById(id).orElse(null);
        return new ResponseEntity<>(productDtoBO, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveProduct(@RequestBody RequestProductBODto productDtoBO){
        productServiceBO.save(productDtoBO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/block/{id}")
    public ResponseEntity<?> blockProduct(@PathVariable Long id){
        productServiceBO.block(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

