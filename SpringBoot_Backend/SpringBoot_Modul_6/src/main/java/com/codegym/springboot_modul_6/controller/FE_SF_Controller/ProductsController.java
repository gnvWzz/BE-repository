package com.codegym.springboot_modul_6.controller.FE_SF_Controller;


import com.codegym.springboot_modul_6.model.FE_SF_Model.Entity.ProductSF;
import com.codegym.springboot_modul_6.model.FE_SF_Model.dto.ProductSFDetailDto;
import com.codegym.springboot_modul_6.model.FE_SF_Model.dto.ProductSFDto;
import com.codegym.springboot_modul_6.service.FE_SF_Service.ICartService;
import com.codegym.springboot_modul_6.service.FE_SF_Service.IProductService;
import com.codegym.springboot_modul_6.service.thirdpartyservice.ThirdService;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/product")
public class ProductsController {

    @Autowired
    private IProductService productService;

    @Autowired
    private ThirdService thirdService;

    @GetMapping(value = "/{category}")
    public ResponseEntity<?> getAllByCategory(@PathVariable(value = "category") String category,
                                    @RequestParam(value = "offset") int offset,
                                    @RequestParam(required = false, value = "sort") String sort) {
        try{
            Page<ProductSF> temp = productService.getAllByCategory(category, sort, offset, 16);
            return new ResponseEntity<>(thirdService.productSFDtoPage(temp), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("FAIL", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping(value = "/getName/{category}")
    public ResponseEntity<?> getProductByName(@PathVariable(value = "category") String category,
                                              @RequestParam(value = "offset") int offset,
                                              @RequestParam(value = "name") String name){
        try{
            Page<ProductSF> temp = productService.getByName(category, name, offset, 16);
            return new ResponseEntity<>(thirdService.productSFDtoPage(temp), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("FAIL", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/get_home")
    public ResponseEntity<?> getAll(@RequestParam(required = true, value = "offset") int offset){
        try {
            Page<ProductSF> temp = productService.findAllPaging(offset, 16);
            return new ResponseEntity<>(thirdService.productSFDtoPage(temp), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("FAIL", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/package-id-product/{package-id}")
    public ResponseEntity<?> getProductByProductId(@PathVariable("package-id")String packageId) {
        ProductSFDto productSFDto = productService.getProductSFDto(packageId);
        return new ResponseEntity<>(productSFDto, HttpStatus.OK);
    }

    @GetMapping("/find-product-detail-by-color-and-size/{color}/{size}/{package_id}")
    public ResponseEntity<?> getProductDetailByColorAndSize(@PathVariable("color") String color,
                                                            @PathVariable("size") String size,
                                                            @PathVariable("package_id") String packageId) throws ParseException {
        ProductSFDetailDto productSFDetailDto = productService.getProductSFDetailDtoByColorAndSize(color, size, packageId);
        return new ResponseEntity<>(productSFDetailDto, HttpStatus.OK);
    }

    @PostMapping("/remove/{id}")
    public ResponseEntity<?> removeProduct(@PathVariable Long id) {
        productService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/new-product")
    public ResponseEntity<?> createNewProduct(@RequestBody ProductSFDto productSFDto) {
        ProductSF productSF = productService.mapProductSF(productSFDto);
        productService.save(productSF);
        return new ResponseEntity<>("Done!", HttpStatus.OK);
    }

    @GetMapping("/get-home")
    public ResponseEntity<?> getRandom(@RequestParam(value = "offset") int offset){
        try {
            Page<ProductSF> page = productService.productService_getRandomProduct(offset, 16);
            return new ResponseEntity<>(thirdService.productSFDtoPage(page), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
