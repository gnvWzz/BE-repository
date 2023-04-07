//package com.codegym.springboot_modul_6.service.FE_BO_Service.impl;
//
//import com.codegym.springboot_modul_6.model.FE_BO_Model.dto.RequestManufacturerDetailDto;
//import com.codegym.springboot_modul_6.model.FE_BO_Model.dto.ResponseManufacturerDetailDto;
//import com.codegym.springboot_modul_6.model.FE_BO_Model.entity.ManufacturerDetail;
//import com.codegym.springboot_modul_6.model.FE_BO_Model.entity.ProductBO;
//import com.codegym.springboot_modul_6.model.FE_BO_Model.dto.RequestProductBODto;
//import com.codegym.springboot_modul_6.model.FE_BO_Model.dto.ResponseProductBODto;
//import com.codegym.springboot_modul_6.repository.FE_BO_Repository.ManufacturerDetailRepository;
//import com.codegym.springboot_modul_6.repository.FE_BO_Repository.ProductBORepository;
//import com.codegym.springboot_modul_6.util.FE_BO_Util.Mapper.ManufacturerDetailMapper;
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.BeanUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class ProductBOServiceImpl implements com.codegym.springboot_modul_6.service.FE_BO_Service.ProductBOService {
//    @Autowired
//    private ProductBORepository productBORepository;
////    @Autowired
////    private ManufacturerDetailServiceImpl manufacturerDetailService;
//
//    @Autowired
//    private ManufacturerDetailMapper manufacturerDetailMapper;
//
//    @Autowired
//    private ManufacturerDetailRepository manufacturerDetailRepository;
//    @Autowired
//    private ModelMapper mapper;
//
//    @Override
//    @Transactional
//    public Optional<ResponseProductBODto> findById(Long id) {
//        ProductBO productBO = productBORepository.findById(id).orElse(null);
//        if (productBO != null) {
//            ResponseProductBODto productDtoBO = new ResponseProductBODto();
//
//            //map cac property giong nhau tu object sang dto
//            BeanUtils.copyProperties(productBO, productDtoBO);
//
//            List<ManufacturerDetail> manufacturerDetailList = productBO.getManufacturerDetails();
//            List<ResponseManufacturerDetailDto> responseManufacturerDetailDtoList = new ArrayList<>();
//            for (ManufacturerDetail ele : manufacturerDetailList) {
//                ResponseManufacturerDetailDto responseManufacturerDetailDto = new ResponseManufacturerDetailDto();
//                BeanUtils.copyProperties(ele, responseManufacturerDetailDto);
//                responseManufacturerDetailDto.setManufacturerId(ele.getManufacturer().getId());
//                responseManufacturerDetailDto.setManufacturerName(ele.getManufacturer().getName());
//                responseManufacturerDetailDto.setProductBOId(ele.getProductBO().getId());
//
//                responseManufacturerDetailDtoList.add(responseManufacturerDetailDto);
//            }
//            productDtoBO.setResponseManufacturerDetailDtos(responseManufacturerDetailDtoList);
//
//            return Optional.of(productDtoBO);
//        }
//        return null;
//    }
//
////    @Override
////    @Transactional
////    public Optional<ResponseProductBODto> findById(Long id) {
////        ProductBO productBO = productBORepository.findById(id).orElse(null);
////        if (productBO != null) {
////            ResponseProductBODto productDtoBO = new ResponseProductBODto();
////
////            //map cac property giong nhau tu object sang dto
////            BeanUtils.copyProperties(productBO, productDtoBO);
////
////            List<ManufacturerDetail> manufacturerDetailList = productBO.getManufacturerDetails();
////            List<RequestManufacturerDetailDto> requestManufacturerDetailDtoList = new ArrayList<>();
////            for (ManufacturerDetail ele : manufacturerDetailList) {
////                RequestManufacturerDetailDto requestManufacturerDetailDto = new RequestManufacturerDetailDto();
////                BeanUtils.copyProperties(ele, requestManufacturerDetailDto);
////                requestManufacturerDetailDto.setManufacturerId(ele.getManufacturer().getId());
////                requestManufacturerDetailDto.setProductBOId(ele.getProductBO().getId());
////
////                requestManufacturerDetailDtoList.add(requestManufacturerDetailDto);
////            }
////            productDtoBO.setRequestManufacturerDetailDtos(requestManufacturerDetailDtoList);
////
////            return Optional.of(productDtoBO);
////        }
////        return null;
////    }
//
//    @Override
//    @Transactional
//    public Page<ResponseProductBODto> findAll(Pageable pageable) {
//        Page<ProductBO> productBOs = productBORepository.findAll(pageable);
//        List<ResponseProductBODto> productDtoBOList = new ArrayList<>();
//        for(ProductBO ele: productBOs){
//            ResponseProductBODto productDtoBO = new ResponseProductBODto();
//            BeanUtils.copyProperties(ele,productDtoBO);
//
//            List<ManufacturerDetail> manufacturerDetails = ele.getManufacturerDetails();
//            List<ResponseManufacturerDetailDto> responseManufacturerDetailDtoList = new ArrayList<>();
//            for(ManufacturerDetail md: manufacturerDetails){
//                ResponseManufacturerDetailDto responseManufacturerDetailDto = new ResponseManufacturerDetailDto();
//                BeanUtils.copyProperties(md, responseManufacturerDetailDto);
//
//                responseManufacturerDetailDto.setManufacturerId(md.getManufacturer().getId());
//                responseManufacturerDetailDto.setManufacturerName(md.getManufacturer().getName());
//                responseManufacturerDetailDto.setProductBOId(md.getProductBO().getId());
//
//                responseManufacturerDetailDtoList.add(responseManufacturerDetailDto);
//            }
//            productDtoBO.setResponseManufacturerDetailDtos(responseManufacturerDetailDtoList);
//
//            productDtoBOList.add(productDtoBO);
//        }
//        return new PageImpl<>(productDtoBOList, pageable, productBOs.getTotalElements());
//    }
//
//    @Override
//    @Transactional
//    public RequestProductBODto save(RequestProductBODto productDtoBO) {
//        try {
//            ProductBO productBO = mapper.map(productDtoBO, ProductBO.class);
//            productBO.setStatus("UNLOCKED");
//            productBORepository.save(productBO);
//
//            Long manufacturerId = productDtoBO.getManufacturerId();
//            Long productId = productBO.getId();
//            RequestManufacturerDetailDto requestManufacturerDetailDto = new RequestManufacturerDetailDto();
//            requestManufacturerDetailDto.setManufacturerId(manufacturerId);
//            requestManufacturerDetailDto.setProductBOId(productId);
//            ManufacturerDetail manufacturerDetail = manufacturerDetailMapper.toEntity(requestManufacturerDetailDto);
//            manufacturerDetailRepository.save(manufacturerDetail);
//        } catch (Exception ex) {
//            System.out.println("Loi:" + ex.getCause());
//            throw new RuntimeException("Error while saving Manufacturer", ex);
//        }
//        return productDtoBO;
//    }
//
////    @Override
////    public ProductDtoBO save(ProductDtoBO productDtoBO) {
////        try {
////            ProductBO productBO = mapper.map(productDtoBO, ProductBO.class);
////            productBO.setStatus("CÒN HÀNG");
////            productRepositoryBO.save(productBO);
////        } catch (Exception ex) {
////            System.out.println("Loi:" + ex.getCause());
////            throw new RuntimeException("Error while saving Manufacturer", ex);
////        }
////        return productDtoBO;
////    }
//
//    @Override
//    public boolean block(Long id) {
//        if (id != null) {
//            ProductBO productBO = productBORepository.findById(id).orElse(null);
//            if (productBO != null) {
//                if (productBO.getStatus().equals("UNLOCKED")) {
//                    productBO.setStatus("BLOCKED");
//                    productBORepository.save(productBO);
//                } else {
//                    productBO.setStatus("UNLOCKED");
//                    productBORepository.save(productBO);
//                }
//                return true;
//            }
//        }
//        return false;
//    }
////    @Override
////    public Optional<ProductDtoBO> findById(Long id) {
////        ProductBO productBO = productRepositoryBO.findById(id).orElse(null);
////        if(productBO != null){
////            return Optional.of(mapper.map(productBO, ProductDtoBO.class));
////        }
////        return null;
////    }
//
////    @Override
////    public Page<ProductDtoBO> findAll(Pageable pageable) {
////        Page<ProductBO> entities = productRepositoryBO.findAll(pageable);
////        List<ProductDtoBO> dtos = new ArrayList<>(
////                entities.getContent().stream()
////                        .parallel()
////                        .map(entity -> mapper.map(entity, ProductDtoBO.class))
////                        .collect(Collectors.toList()));
////        return new PageImpl<>(dtos, pageable, entities.getTotalElements());
////    }
//
//}
