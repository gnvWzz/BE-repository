package com.codegym.springboot_modul_6.controller.FE_SF_Controller;


import com.codegym.springboot_modul_6.model.FE_SF_Model.Entity.ProductSF;
import com.codegym.springboot_modul_6.model.FE_SF_Model.dto.ProductSFDto;
import com.codegym.springboot_modul_6.service.FE_SF_Service.IProductService;
//import com.codegym.springboot_modul_6.service.thirdpartyservice.ThirdService;
import com.codegym.springboot_modul_6.service.thirdpartyservice.UserOnlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/api/product")
public class ProductsController {

    @Autowired
    private IProductService productService;

    public UserOnlineService userOnlineService = UserOnlineService.getUserOnlineService();


//    @Autowired
//    private ThirdService thirdService;


    @GetMapping(value = "")
    public ResponseEntity<?> getAll(){
        List<ProductSF> temp = productService.productSFS();
        return new ResponseEntity<>(temp, HttpStatus.OK);
    }



//    @GetMapping(value = "/{category}")
//    public ResponseEntity<?> getAllByCategory(@PathVariable(value = "category") String category,
//                                              @RequestParam(value = "offset") int offset,
//                                              @RequestParam(required = false, value = "sort_price") String sortByPrice,
//                                              @RequestParam(required = false, value = "sort_name") String sortByName) {
//        Page<ProductSF> productSFS = productService.getAllByCategory(category, sortByPrice, sortByName ,offset, 16);
//        return new ResponseEntity<>(thirdService.pageProductSFDto(productSFS), HttpStatus.OK);
//    }
//
//    @GetMapping(value = "")
//    public ResponseEntity<?> findAll(@RequestParam(value = "offset") int offset){
//        Page<ProductSF> productSFS = productService.findAllPaging(offset, 4);
//        return new ResponseEntity<>(thirdService.pageProductSFDto(productSFS), HttpStatus.OK);
//    }

//<<<<<<< HEAD
//    @GetMapping("/find-by-id/{id}")
//    public ResponseEntity<?> findProductById(@PathVariable("id") Long id) {
//        ProductSFDto productSFDto = thirdService.getProductSFDto(id);
//        return new ResponseEntity<>(productSFDto, HttpStatus.OK);
//=======
////    @GetMapping("/find-by-id/{id}")
////    public ResponseEntity<?> findProductById(@PathVariable("id") Long id) {
////        ProductSFDto productSFDto = thirdService.getProductSFDto(id);
////        return new ResponseEntity<>(productSFDto, HttpStatus.OK);
//
//>>>>>>> 83a22e32dc357ff66b065d5d8a0a965021564e4d
    }

