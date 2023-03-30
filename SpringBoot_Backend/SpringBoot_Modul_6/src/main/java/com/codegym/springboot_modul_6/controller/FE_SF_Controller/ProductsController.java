package com.codegym.springboot_modul_6.controller.FE_SF_Controller;

<<<<<<< HEAD
=======
import com.codegym.springboot_modul_6.Model.FE_SF_Model.Entity.ProductSF;
import com.codegym.springboot_modul_6.Model.FE_SF_Model.dto.ProductDto;
import com.codegym.springboot_modul_6.Service.FE_SF_Service.ProductService;
import com.codegym.springboot_modul_6.util.FE_SF_Util.Mapper.RequestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
>>>>>>> c75c60cdf818006d30f7bf5dabeff4ac3bada111
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/api/product")
public class ProductsController {

<<<<<<< HEAD
=======
    @Autowired
    private ProductService productService;

    @Autowired
    private RequestMapper requestMapper;

    @GetMapping(value = "/find-all/{name}")
    public ResponseEntity<?> findAllByCategory(@PathVariable(value = "name")String name){
        List<ProductSF> list = productService.findByCategory(name);
//        List<ProductDto> productDtos = requestMapper.productDtos(list);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

>>>>>>> c75c60cdf818006d30f7bf5dabeff4ac3bada111
}
