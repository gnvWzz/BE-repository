package com.codegym.springboot_modul_6.Service.FE_BO_Service.imp;

import com.codegym.springboot_modul_6.Model.FE_BO_Model.Entity.Manufacturer;
import com.codegym.springboot_modul_6.Model.FE_BO_Model.Entity.ProductBO;
import com.codegym.springboot_modul_6.Model.FE_BO_Model.dto.ManufacturerDto;
import com.codegym.springboot_modul_6.Repository.FE_BO_Repository.IProductRepositoryBO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//@Service
//public class ProductServiceBO implements IProductServiceBO {
//    @Autowired
//    private IProductRepositoryBO productRepositoryBO;
//    @Autowired
//    private ModelMapper mapper;
//    @Override
//    public Optional<ProdD> findById(Long id) {
//        ProductBO productBO = productRepositoryBO.findById(id).orElse(null);
//        if(productBO != null){
//            return Optional.of(mapper.map(productBO, ProductDtoBO.class));
//        }
//        return null;
//    }
//
//    @Override
//    public Page<ProductDtoBO> findAll(Pageable pageable) {
//        Page<ProductBO> entities = productRepositoryBO.findAll(pageable);
//        List<ProductDtoBO> dtos = new ArrayList<>(
//                entities.getContent().stream()
//                        .parallel()
//                        .map(entity -> mapper.map(entity, ProductDtoBO.class))
//                        .collect(Collectors.toList()));
//        return new PageImpl<>(dtos, pageable, entities.getTotalElements());
//    }
//
//    @Override
//    public ProductDtoBO save(ProductDtoBO productDtoBO) {
//        try {
//            ProductBO productBO = mapper.map(productDtoBO, ProductBO.class);
//            productBO.setStatus("CÒN HÀNG");
//            productRepositoryBO.save(productBO);
//        } catch (Exception ex) {
//            System.out.println("Loi:" + ex.getCause());
//            throw new RuntimeException("Error while saving Manufacturer", ex);
//        }
//        return productDtoBO;
//    }
//
//    @Override
//    public boolean delete(Long id) {
//        if(id != null){
//            productRepositoryBO.deleteById(id);
//            return true;
//        }
//        return false;
//    }
//}
