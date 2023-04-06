package com.codegym.springboot_modul_6.Controller.FE_SF_Controller;

import com.codegym.springboot_modul_6.Model.FE_SF_Model.Entity.Image;
import com.codegym.springboot_modul_6.Model.FE_SF_Model.Entity.ProductSF;
import com.codegym.springboot_modul_6.Model.FE_SF_Model.dto.ImageDTO;
import com.codegym.springboot_modul_6.Model.FE_SF_Model.dto.ProductDto;
import com.codegym.springboot_modul_6.Service.FE_SF_Service.IImageService;
import com.codegym.springboot_modul_6.Service.FE_SF_Service.ProductService;
import com.codegym.springboot_modul_6.util.FE_SF_Util.Mapper.RequestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/api/product")
public class ProductsController {

    @Autowired
    private ProductService productService;

    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private IImageService iImageService;

    @GetMapping(value = "/find-all")
    public ResponseEntity<?> findAllByCategory(){

        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    @GetMapping(value = "/find-by-serial-number/{serial_number}")
    public ResponseEntity<?> findBySerialNumber(@PathVariable("serial_number") String serialNumber) {
        ProductSF productSF = productService.findBySerialNumber(serialNumber);
        ProductDto productDto = requestMapper.productDto(productSF);
        return new ResponseEntity<>(productDto, HttpStatus.OK);
    }
}
