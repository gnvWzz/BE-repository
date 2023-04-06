package com.codegym.springboot_modul_6.controller.FE_SF_Controller;

import com.codegym.springboot_modul_6.model.FE_SF_Model.Entity.ProductSF;
import com.codegym.springboot_modul_6.model.FE_SF_Model.Entity.ProductSFDetail;
import com.codegym.springboot_modul_6.model.FE_SF_Model.dto.ImageDto;
import com.codegym.springboot_modul_6.model.FE_SF_Model.dto.ProductSFDetailDto;
import com.codegym.springboot_modul_6.model.FE_SF_Model.dto.ProductSFDto;
import com.codegym.springboot_modul_6.service.FE_SF_Service.ProductService;
import com.codegym.springboot_modul_6.service.thirdpartyservice.ThirdService;
import com.codegym.springboot_modul_6.util.FE_SF_Util.Mapper.LongMapper;
import com.codegym.springboot_modul_6.util.FE_SF_Util.Mapper.RequestMapper;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/api/product")
public class ProductsController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ThirdService thirdService;

    @GetMapping(value = "/{category}")
    public ResponseEntity<?> getAllByCategory(@PathVariable(value = "category") String category,
                                              @RequestParam(value = "offset") int offset,
                                              @RequestParam(required = false, value = "sort_price") String sortByPrice){
        Page<ProductSF> productSFS = productService.getAll(category,sortByPrice , offset, 16);
        return new ResponseEntity<>(thirdService.pageProductSFDto(productSFS), HttpStatus.OK);
    }






//    @Autowired
//    private ThirdService thirdService;
//
//    @Autowired
//    private IImageService iImageService;

//    @GetMapping(value = "/find-all/{name}")
//    public ResponseEntity<?> findAllByCategory(@PathVariable(value = "name") String name) {
//        List<ProductSF> list = productService.findByCategory(name);
//        List<ProductSFDto> productSFDtos = requestMapper.productDtos(list);
//        for (ProductSFDto p :
//                productSFDtos) {
//            ProductSF productSF = productService.findBySerialNumber(p.getSerial_number());
//            List<Image> lists = productSF.getImageList();
//            List<ImageDto> imageDtoList = requestMapper.imageDTOList(lists);
//            List<String> urlList = new ArrayList<>();
//            for (ImageDto i :
//                    imageDtoList) {
//                urlList.add(i.getUrl());
//            }
//            p.setList(urlList);
//        }
//        return new ResponseEntity<>(productSFDtos, HttpStatus.OK);
//    }
//
//    @GetMapping(value = "/find-by-serial-number/{serial_number}")
//    public ResponseEntity<?> findBySerialNumber(@PathVariable("serial_number") String serialNumber) {
//        ProductSF productSF = productService.findBySerialNumber(serialNumber);
//        ProductSFDto productSFDto = requestMapper.productDto(productSF);
//        List<Image> imageList = productSF.getImageList();
//        List<ImageDto> imageDtoList = requestMapper.imageDTOList(imageList);
//        List<String> urlList = new ArrayList<>();
//        for (ImageDto i :
//                imageDtoList) {
//            urlList.add(i.getUrl());
//        }
//        productSFDto.setList(urlList);
//        return new ResponseEntity<>(productSFDto, HttpStatus.OK);
//    }
//
//    @GetMapping(value = "/find/{name}")
//    public ResponseEntity<?> getProductWithPaging(@RequestParam(value = "offset") int offset,
//                                                  @PathVariable(value = "name") String category,
//                                                  @RequestParam(required = false, value = "sort") String sort,
//                                                  @RequestParam(required = false, value = "sort_size") String sort_size) {
//
//        Page<ProductSFDto> productDtos = thirdService.getProducts(sort, sort_size, category, offset);
//        return new ResponseEntity<>(productDtos, HttpStatus.OK);
//    }


//    @GetMapping(value = "/find-all/{name}")
//    public ResponseEntity<?> findAllByCategory(@PathVariable(value = "name") String name) {
//        List<ProductSF> list = productService.findByCategory(name);
//        List<ProductSFDto> productDtos = requestMapper.productDtos(list);
//        for (ProductSFDto p :
//                productDtos) {
//            ProductSF productSF = productService.findBySerialNumber(p.getSerial_number());
//            List<Image> lists = productSF.getImageList();
//            List<ImageDto> imageDTOList = requestMapper.imageDTOList(lists);
//            List<String> urlList = new ArrayList<>();
//            for (ImageDto i :
//                    imageDTOList) {
//                urlList.add(i.getUrl());
//            }
//            p.setList(urlList);
//        }
//        return new ResponseEntity<>(productDtos, HttpStatus.OK);
//    }

//    @GetMapping(value = "/find-by-serial-number/{serial_number}")
//    public ResponseEntity<?> findBySerialNumber(@PathVariable("serial_number") String serialNumber) {
//        ProductSF productSF = productService.findBySerialNumber(serialNumber);
//        ProductSFDto productDto = requestMapper.productDto(productSF);
//        List<Image> imageList = productSF.getImageList();
//        List<ImageDTO> imageDTOList = requestMapper.imageDTOList(imageList);
//        List<String> urlList = new ArrayList<>();
//        for (ImageDTO i :
//                imageDTOList) {
//            urlList.add(i.getUrl());
//        }
//        productDto.setList(urlList);
//        return new ResponseEntity<>(productDto, HttpStatus.OK);
//    }

//    @GetMapping(value = "/find/{name}")
//    public ResponseEntity<?> getProductWithPaging(@RequestParam(value = "offset") int offset,
//                                                  @PathVariable(value = "name") String category,
//                                                  @RequestParam(required = false, value = "sort") String sort,
//                                                  @RequestParam(required = false, value = "sort_size") String sort_size) {
//
//        Page<ProductSFDto> productDtos = thirdService.getProducts(sort, sort_size, category, offset);
//        return new ResponseEntity<>(productDtos, HttpStatus.OK);
//    }


//    @GetMapping(value = "/findByName/{name}")
//    public ResponseEntity<?> find_By_Name_Category(@PathVariable(value = "name") String name,
//                                                   @RequestParam(value = "product_name") String product_name,
//                                                   @RequestParam(value = "offset") int offset
//    ) {
//        Page<ProductSF> productSFS = productService.findCategoryByName(name, product_name, offset, 16);
//        Page<ProductSFDto> productDtos = requestMapper.productDtoPage(productSFS);
//        return new ResponseEntity<>(productDtos, HttpStatus.OK);
//    }

//    @GetMapping(value = "/findByName/{name}")
//    public ResponseEntity<?> find_By_Name_Category(@PathVariable(value = "name") String name,
//                                                   @RequestParam(value = "product_name") String product_name,
//                                                   @RequestParam(value = "offset") int offset,
//                                                   @RequestParam(value = "pageSize") int pageSize) {
//        Page<ProductSF> productSFS = productService.findCategoryByName(name, product_name, offset, pageSize);
//        Page<ProductSFDto> productDtos = requestMapper.productDtoPage(productSFS);
//        return new ResponseEntity<>(productDtos, HttpStatus.OK);
//    }

}
