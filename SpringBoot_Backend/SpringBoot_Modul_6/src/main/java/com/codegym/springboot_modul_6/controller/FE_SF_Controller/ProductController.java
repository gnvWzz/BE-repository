package com.codegym.springboot_modul_6.controller.FE_SF_Controller;


import com.codegym.springboot_modul_6.model.FE_BO_Model.dto.request.RequestProductDetailInfoDto;
import com.codegym.springboot_modul_6.model.FE_BO_Model.dto.request.RequestProductGeneralInfoDto;
import com.codegym.springboot_modul_6.model.FE_SF_Model.Entity.ProductSF;
import com.codegym.springboot_modul_6.model.FE_SF_Model.dto.ProductSFDetailDto;
import com.codegym.springboot_modul_6.model.FE_SF_Model.dto.ProductSFDto;
import com.codegym.springboot_modul_6.service.FE_SF_Service.IProductService;
import com.codegym.springboot_modul_6.service.thirdpartyservice.ThirdService;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/product")
public class ProductController {

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

    @GetMapping(value = "/max_min/{category}")
    public ResponseEntity<?> product_controllerGetMaxMin(@PathVariable(value = "category") String category,
                                                         @RequestParam(value = "offset") int offset,
                                                         @RequestParam(value = "min_price") Double minPrice,
                                                         @RequestParam(value = "max_price") Double maxPrice){
        try {
            Page<ProductSF> temp = productService.getMaxMinPriceProduct(minPrice, maxPrice, category, offset, 16);
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

    @GetMapping("/find-product-detail-by-color-and-size/{color}/{size}/{name}")
    public ResponseEntity<?> getProductDetailByColorAndSize(@PathVariable("color") String color,
                                                            @PathVariable("size") String size,
                                                            @PathVariable("name") String name) throws ParseException {
        ProductSFDetailDto productSFDetailDto = productService.getProductSFDetailDtoByColorAndSize(color, size, name);
        return new ResponseEntity<>(productSFDetailDto, HttpStatus.OK);
    }

    @GetMapping("/name-product/{name}")
    public ResponseEntity<?> getProductByProductId(@PathVariable("name")String name) {
        ProductSFDto productSFDto = productService.getProductSFDto(name);
        return new ResponseEntity<>(productSFDto, HttpStatus.OK);
    }

    @PostMapping("/remove/{id}")
    public ResponseEntity<?> removeProduct(@PathVariable Long id) {
        productService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping("/update/general")
    public ResponseEntity<?> updateProductGeneralInfo(@RequestBody RequestProductGeneralInfoDto requestProductGeneralInfoDto) {
        Boolean isSuccess = productService.updateProductGeneralInfo(requestProductGeneralInfoDto);
        if(isSuccess) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    @PostMapping("/new-product")
    public ResponseEntity<?> createNewProduct(@RequestBody ProductSFDto productSFDto) {
        ProductSF productSF = productService.mapProductSF(productSFDto);
        productService.save(productSF);
        return new ResponseEntity<>("Done!", HttpStatus.OK);
    }

    @GetMapping("/get-home")
    public ResponseEntity<?> getRandom(@RequestParam(value = "offset") int offset,
                                       @RequestParam(required = false, value = "sort") String sort){
        try {
            Page<ProductSF> page = productService.productService_getRandomProduct(offset, 16);
            return new ResponseEntity<>(thirdService.productSFDtoPage(page), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/random-single-product/{product_name}")
    public ResponseEntity<?> getRandomProductYouLikeThis (@PathVariable("product_name")  String productName ){
        try{
            List<ProductSFDto> productSFDtos = thirdService.getListProductDtoRandomByProductName(productName);
            return new ResponseEntity<>(productSFDtos, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
