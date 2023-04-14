package com.codegym.springboot_modul_6.service.FE_BO_Service.impl;

import com.codegym.springboot_modul_6.model.FE_BO_Model.dto.response.ResponseProductBODto;
import com.codegym.springboot_modul_6.model.FE_BO_Model.dto.response.ResponseProductSFDetailDto;
import com.codegym.springboot_modul_6.model.FE_BO_Model.dto.response.ResponseProductSFDto;
import com.codegym.springboot_modul_6.model.FE_BO_Model.dto.response.ResponseStoreDto;
import com.codegym.springboot_modul_6.model.FE_BO_Model.entity.Store;
import com.codegym.springboot_modul_6.model.FE_SF_Model.Entity.ProductSF;
import com.codegym.springboot_modul_6.model.FE_SF_Model.Entity.ProductSFDetail;
import com.codegym.springboot_modul_6.repository.FE_BO_Repository.StoreRepository;
import com.codegym.springboot_modul_6.service.FE_BO_Service.StoreService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class StoreServiceImpl implements StoreService {
    @Autowired
    private StoreRepository storeRepository;
    @Override
    public Optional<ResponseStoreDto> findById(Long id) {
        ResponseStoreDto responseStoreDto = new ResponseStoreDto();
        List<ResponseProductSFDetailDto> responseProductSFDetailDtoList = new ArrayList<>();
        try {
            Store store = storeRepository.findById(id).orElse(null);
            BeanUtils.copyProperties(store,responseStoreDto);

            List<ProductSF> productSFList = store.getProductSF();
            for(ProductSF productSF: productSFList){
                List<ProductSFDetail> productSFDetails = productSF.getProductSFDetail();
                for(ProductSFDetail ele: productSFDetails){
                    ResponseProductSFDetailDto responseProductSFDetailDto = new ResponseProductSFDetailDto();
                    BeanUtils.copyProperties(ele, responseProductSFDetailDto);

                    responseProductSFDetailDtoList.add(responseProductSFDetailDto);
                }
            }

        } catch (Exception ex) {
            System.out.println("Loi:" + ex.getCause());
            throw new RuntimeException("Error while getting Store", ex);
        }
        responseStoreDto.setResponseProductSFDetailDtoList(responseProductSFDetailDtoList);
        return Optional.of(responseStoreDto);
    }
}
