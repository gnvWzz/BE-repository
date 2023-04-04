package com.codegym.springboot_modul_6.Controller.FE_SF_Controller;

import com.codegym.springboot_modul_6.Model.FE_SF_Model.Entity.Image;
import com.codegym.springboot_modul_6.Model.FE_SF_Model.Entity.ProductSF;
import com.codegym.springboot_modul_6.Model.FE_SF_Model.dto.ImageDTO;
import com.codegym.springboot_modul_6.Model.FE_SF_Model.dto.ProductDto;
import com.codegym.springboot_modul_6.Service.FE_SF_Service.IImageService;
import com.codegym.springboot_modul_6.Service.FE_SF_Service.ProductService;
import com.codegym.springboot_modul_6.util.FE_SF_Util.Mapper.RequestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    @GetMapping(value = "/find-all/{name}")
    public ResponseEntity<?> findAllByCategory(@PathVariable(value = "name")String name){
        List<ProductSF> list = productService.findByCategory(name);
        List<ProductDto> productDtos = requestMapper.productDtos(list);
        for (ProductDto p:
                productDtos) {
            ProductSF productSF = productService.findBySerialNumber(p.getSerial_number());
            List<Image> lists = productSF.getImageList();
            List<ImageDTO> imageDTOList = requestMapper.imageDTOList(lists);
            List<String> urlList = new ArrayList<>();
            for (ImageDTO i:
                 imageDTOList) {
                urlList.add(i.getUrl());
            }
            p.setList(urlList);
        }
        return new ResponseEntity<>(productDtos, HttpStatus.OK);
    }

    @GetMapping(value = "/find-by-serial-number/{serial_number}")
    public ResponseEntity<?> findBySerialNumber(@PathVariable("serial_number") String serialNumber) {
        ProductSF productSF = productService.findBySerialNumber(serialNumber);
        ProductDto productDto = requestMapper.productDto(productSF);
        List<Image> imageList = productSF.getImageList();
        List<ImageDTO> imageDTOList = requestMapper.imageDTOList(imageList);
        List<String> urlList = new ArrayList<>();
        for (ImageDTO i:
             imageDTOList) {
            urlList.add(i.getUrl());
        }
        productDto.setList(urlList);
        return new ResponseEntity<>(productDto, HttpStatus.OK);
    }

    @GetMapping(value = "/find/{name}")
    public ResponseEntity<?> getProductWithPaging(@RequestParam(value = "offset" )  int offset,
                                                  @PathVariable(value = "name") String category,
                                                  @RequestParam(required = false, value = "sort_asc") String asc,
                                                  @RequestParam(required = false, value = "sort_desc") String desc,
                                                  @RequestParam(required = false, value = "sort_size") String sort_size){
        if (asc != null){
            Page<ProductSF> productSFS = productService.findOrderByPriceASC(category ,offset, 16);
            Page<ProductDto> productDtos = requestMapper.productDtoPage(productSFS);
            return new ResponseEntity<>(productDtos, HttpStatus.OK);
        }else if(desc != null){
            Page<ProductSF> productSFS = productService.findOrderByPriceDESC(category ,offset, 16);
            Page<ProductDto> productDtos = requestMapper.productDtoPage(productSFS);
            return new ResponseEntity<>(productDtos, HttpStatus.OK);
        }
        Page<ProductSF> productSFS = productService.findProductWithPagination(category, offset, 16);
        Page<ProductDto> productDto = requestMapper.productDtoPage(productSFS);
        return new ResponseEntity<>(productDto, HttpStatus.OK);
    }

    @GetMapping(value = "/test/{name}")
    public ResponseEntity<?> testfind(@PathVariable(value = "name")String name){
        productService.cache(name);
        List<String> demo = ProductService.cache.get("products");
        for (String t: demo
             ) {
            System.out.println(t);
        }
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @GetMapping(value = "/test2/{name}")
    public ResponseEntity<?> testfind2(@RequestParam(value = "offset") int offset,
                                       @RequestParam(value = "pageSize") int pageSize,
                                       @PathVariable(value = "name") String category){

            Page<ProductSF> productSFS = productService.findProductWithPagination(category ,offset, pageSize);
            Page<ProductDto> productDtos = requestMapper.productDtoPage(productSFS);


        return new ResponseEntity<>(productDtos, HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/findASC/{name}")
    public ResponseEntity<?> findASC(@RequestParam(value = "offset") int offset,
                                     @RequestParam(value = "pageSize") int pageSize,
                                     @PathVariable(value = "name") String category,
                                     @RequestParam(value = "sort_asc") String asc){
        Page<ProductSF> productSFS = productService.findOrderByPriceASC(category ,offset, pageSize);
        Page<ProductDto> productDtos = requestMapper.productDtoPage(productSFS);
        return new ResponseEntity<>(productDtos, HttpStatus.OK);
    }

    @GetMapping(value = "/findDESC/{name}")
    public ResponseEntity<?> findDESC(@RequestParam(value = "offset") int offset,
                                     @RequestParam(value = "pageSize") int pageSize,
                                     @PathVariable(value = "name") String category,
                                     @RequestParam(value = "sort_desc") String desc){
        Page<ProductSF> productSFS = productService.findOrderByPriceDESC(category ,offset, pageSize);
        Page<ProductDto> productDtos = requestMapper.productDtoPage(productSFS);
        return new ResponseEntity<>(productDtos, HttpStatus.OK);
    }

//    @GetMapping(value = "/findDESC/{name}")
//    public ResponseEntity<?> find_size_sortASC(@RequestParam(value = "offset") int offset,
//                                      @RequestParam(value = "pageSize") int pageSize,
//                                      @PathVariable(value = "name") String category,
//                                      @RequestParam(value = "sort_asc") String asc){
//        Page<ProductSF> productSFS = productService.findOrderByPriceDESC(category ,offset, pageSize);
//        Page<ProductDto> productDtos = requestMapper.productDtoPage(productSFS);
//        return new ResponseEntity<>(productDtos, HttpStatus.OK);
//    }

    @GetMapping(value = "/findBothASC/{name}")
    public ResponseEntity<?> find_price_size(@PathVariable(value = "name")String name,
                                             @RequestParam(value = "offset") int offset,
                                             @RequestParam(value = "pageSize") int pageSize,
                                             @RequestParam(value = "sort_asc") String asc,
                                             @RequestParam(value = "sort_size") String sort_size){

        Page<ProductSF> productSFS = productService.findCategoryAndSizeOrderByPriceAsc(name, sort_size, offset, pageSize);
        Page<ProductDto> productDtos = requestMapper.productDtoPage(productSFS);
        return new ResponseEntity<>(productDtos, HttpStatus.OK);
    }

    @GetMapping(value = "/findByName/{name}")
    public ResponseEntity<?> find_By_Name_Category(@PathVariable(value = "name") String name,
                                                   @RequestParam(value = "product_name") String product_name,
                                                   @RequestParam(value = "offset") int offset,
                                                   @RequestParam(value = "pageSize") int pageSize){
        Page<ProductSF> productSFS = productService.findCategoryByName(name, product_name, offset, pageSize);
        Page<ProductDto> productDtos = requestMapper.productDtoPage(productSFS);
        return new ResponseEntity<>(productDtos, HttpStatus.OK);
    }
 }
