package com.codegym.springboot_modul_6.service.FE_BO_Service.impl;

import com.codegym.springboot_modul_6.model.FE_BO_Model.dto.request.RequestManufacturerProductBODto;
import com.codegym.springboot_modul_6.model.FE_BO_Model.dto.response.ResponseManufacturerProductBODto;
import com.codegym.springboot_modul_6.model.FE_BO_Model.entity.ManufacturerProductBO;
import com.codegym.springboot_modul_6.model.FE_BO_Model.entity.ProductBO;
import com.codegym.springboot_modul_6.model.FE_BO_Model.dto.request.RequestProductBODto;
import com.codegym.springboot_modul_6.model.FE_BO_Model.dto.response.ResponseProductBODto;
import com.codegym.springboot_modul_6.repository.FE_BO_Repository.ManufacturerProductBORepository;
import com.codegym.springboot_modul_6.repository.FE_BO_Repository.ProductBORepository;
import com.codegym.springboot_modul_6.util.FE_BO_Util.Mapper.ManufacturerDetailMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductBOServiceImpl implements com.codegym.springboot_modul_6.service.FE_BO_Service.ProductBOService {
    @Autowired
    private ProductBORepository productBORepository;

    @Autowired
    private ManufacturerDetailMapper manufacturerDetailMapper;

    @Autowired
    private ManufacturerProductBORepository manufacturerProductBORepository;

    @Override
    @Transactional
    public Optional<ResponseProductBODto> findById(Long id) {
        ProductBO productBO = productBORepository.findById(id).orElse(null);
        if (productBO != null) {
            ResponseProductBODto productDtoBO = new ResponseProductBODto();

            //map cac property giong nhau tu object sang dto
            BeanUtils.copyProperties(productBO, productDtoBO);

            List<ManufacturerProductBO> manufacturerProductBOList = productBO.getManufacturerProductBOS();
            List<ResponseManufacturerProductBODto> responseManufacturerProductBODtoList = new ArrayList<>();
            for (ManufacturerProductBO ele : manufacturerProductBOList) {
                ResponseManufacturerProductBODto responseManufacturerProductBODto = new ResponseManufacturerProductBODto();
                BeanUtils.copyProperties(ele, responseManufacturerProductBODto);
                responseManufacturerProductBODto.setManufacturerId(ele.getManufacturer().getId());
                responseManufacturerProductBODto.setManufacturerName(ele.getManufacturer().getName());
                responseManufacturerProductBODto.setProductBOId(ele.getProductBO().getId());
                responseManufacturerProductBODto.setProductBOName(ele.getProductBO().getName());

                responseManufacturerProductBODtoList.add(responseManufacturerProductBODto);
            }
            productDtoBO.setResponseManufacturerProductBODtos(responseManufacturerProductBODtoList);

            return Optional.of(productDtoBO);
        }
        return null;
    }

    @Override
    @Transactional
    public Page<ResponseProductBODto> findAll(Pageable pageable) {
        Page<ProductBO> productBOs = productBORepository.findAll(pageable);
        List<ResponseProductBODto> productBODtoList = new ArrayList<>();
        for(ProductBO ele: productBOs){
            ResponseProductBODto productDtoBO = new ResponseProductBODto();
            BeanUtils.copyProperties(ele,productDtoBO);

            List<ManufacturerProductBO> manufacturerProductBOS = ele.getManufacturerProductBOS();
            List<ResponseManufacturerProductBODto> responseManufacturerProductBODtoList = new ArrayList<>();
            for(ManufacturerProductBO md: manufacturerProductBOS){
                ResponseManufacturerProductBODto responseManufacturerProductBODto = new ResponseManufacturerProductBODto();
                BeanUtils.copyProperties(md, responseManufacturerProductBODto);

                responseManufacturerProductBODto.setManufacturerId(md.getManufacturer().getId());
                responseManufacturerProductBODto.setManufacturerName(md.getManufacturer().getName());
                responseManufacturerProductBODto.setProductBOId(md.getProductBO().getId());
                responseManufacturerProductBODto.setProductBOName(md.getProductBO().getName());

                responseManufacturerProductBODtoList.add(responseManufacturerProductBODto);
            }
            productDtoBO.setResponseManufacturerProductBODtos(responseManufacturerProductBODtoList);

            productBODtoList.add(productDtoBO);
        }
        return new PageImpl<>(productBODtoList, pageable, productBOs.getTotalElements());
    }

    @Override
    @Transactional
    public RequestProductBODto save(RequestProductBODto productDtoBO) {
        try {
            ProductBO productBO = new ProductBO();
            BeanUtils.copyProperties(productDtoBO, productBO);
            productBO.setStatus("UNLOCKED");
            productBORepository.save(productBO);

            Long manufacturerId = productDtoBO.getManufacturerId();
            Long productId = productBO.getId();
            RequestManufacturerProductBODto requestManufacturerProductBODto = new RequestManufacturerProductBODto();
            requestManufacturerProductBODto.setManufacturerId(manufacturerId);
            requestManufacturerProductBODto.setProductBOId(productId);
            ManufacturerProductBO manufacturerProductBO = manufacturerDetailMapper.toEntity(requestManufacturerProductBODto);
            manufacturerProductBORepository.save(manufacturerProductBO);
        } catch (Exception ex) {
            System.out.println("Loi:" + ex.getCause());
            throw new RuntimeException("Error while saving Manufacturer", ex);
        }
        return productDtoBO;
    }

    @Override
    public boolean block(Long id) {
        if (id != null) {
            ProductBO productBO = productBORepository.findById(id).orElse(null);
            if (productBO != null) {
                if (productBO.getStatus().equals("UNLOCKED")) {
                    productBO.setStatus("BLOCKED");
                } else {
                    productBO.setStatus("UNLOCKED");
                }
                productBORepository.save(productBO);
                return true;
            }
        }
        return false;
    }
//    @Override
//    public Optional<ProductDtoBO> findById(Long id) {
//        ProductBO productBO = productRepositoryBO.findById(id).orElse(null);
//        if(productBO != null){
//            return Optional.of(mapper.map(productBO, ProductDtoBO.class));
//        }
//        return null;
//    }

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

//    @Override
//    public ProductDtoBO save(ProductDtoBO productDtoBO) {
//        try {
//            ProductBO productBO = mapper.map(productDtoBO, ProductBO.class);
//            productBO.setStatus("UNLOCKED");
//            productRepositoryBO.save(productBO);
//        } catch (Exception ex) {
//            System.out.println("Loi:" + ex.getCause());
//            throw new RuntimeException("Error while saving Manufacturer", ex);
//        }
//        return productDtoBO;
//    }
}
