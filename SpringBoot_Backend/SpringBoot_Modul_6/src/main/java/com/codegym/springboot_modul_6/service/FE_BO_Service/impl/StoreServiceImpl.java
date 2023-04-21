package com.codegym.springboot_modul_6.service.FE_BO_Service.impl;

import com.codegym.springboot_modul_6.model.FE_BO_Model.dto.request.RequestStoreDto;
import com.codegym.springboot_modul_6.model.FE_BO_Model.dto.response.ResponseProductDetailDto;
import com.codegym.springboot_modul_6.model.FE_BO_Model.dto.response.ResponseStoreDto;
import com.codegym.springboot_modul_6.model.FE_BO_Model.entity.Store;
import com.codegym.springboot_modul_6.model.FE_SF_Model.Entity.Account;
import com.codegym.springboot_modul_6.model.FE_SF_Model.Entity.ProductSF;
import com.codegym.springboot_modul_6.model.FE_SF_Model.Entity.ProductSFDetail;
import com.codegym.springboot_modul_6.repository.FE_BO_Repository.StoreRepository;
import com.codegym.springboot_modul_6.service.FE_BO_Service.StoreService;
import com.codegym.springboot_modul_6.service.FE_SF_Service.AccountService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class StoreServiceImpl implements StoreService {
    @Autowired
    private StoreRepository storeRepository;
@Autowired
private PriceListServiceImpl priceListService;
@Autowired
private AccountService accountService;
    @Override
    public Optional<ResponseStoreDto> findStoreByAccountUsername(String accountUsername) {
        ResponseStoreDto responseStoreDto = new ResponseStoreDto();
        List<ResponseProductDetailDto> responseProductDetailDtoList = new ArrayList<>();
        Account account = accountService.findAccountByUsername(accountUsername).orElse(null);
        if (account != null){
            try {
                Long accountId = account.getId();
                Store store = storeRepository.findByAccount_Id(accountId).orElse(null);
                BeanUtils.copyProperties(store,responseStoreDto);

                List<ProductSF> productSFList = store.getProductSF();

                for(ProductSF productSF: productSFList){
                    Long productId = productSF.getId();
                    Double standardPrice = priceListService.findStandardPriceByProductId(productId);
                    List<ProductSFDetail> productSFDetails = productSF.getProductSFDetail();
                    for(ProductSFDetail ele: productSFDetails){
                        if(ele.getStatus().equals("true")) {
                            ResponseProductDetailDto responseProductDetailDto = new ResponseProductDetailDto();
                            BeanUtils.copyProperties(ele, responseProductDetailDto);
                            responseProductDetailDto.setStandardPrice(standardPrice);

                            responseProductDetailDtoList.add(responseProductDetailDto);
                        }
                    }
                }

            } catch (Exception ex) {
                System.out.println("Loi:" + ex.getCause());
                throw new RuntimeException("Error while getting Store", ex);
            }
            responseStoreDto.setResponseProductDetailDtoList(responseProductDetailDtoList);
            return Optional.of(responseStoreDto);
        }
        return null;
    }


    @Override
    @Transactional
    public ResponseStoreDto updateImage(RequestStoreDto requestStoreDto) {
        String storeName = requestStoreDto.getCurName();
        String storeImageUrl = requestStoreDto.getImage();
        Store store = storeRepository.findStoreByName(storeName).orElse(null);
        if(store != null) {
            store.setImage(storeImageUrl);
            storeRepository.save(store);
            ResponseStoreDto responseStoreDto = new ResponseStoreDto();
            BeanUtils.copyProperties(store, responseStoreDto);
            return responseStoreDto;
        }
        return null;
    }

    @Override
    @Transactional
    public ResponseStoreDto updateName(RequestStoreDto requestStoreDto) {
        String storeName = requestStoreDto.getCurName();
        String newName = requestStoreDto.getNewName();
        Store store = storeRepository.findStoreByName(storeName).orElse(null);
        Store isExist = storeRepository.findStoreByName(newName).orElse(null);
        if(store != null && isExist == null) {
            store.setName(newName);
            storeRepository.save(store);
            ResponseStoreDto responseStoreDto = new ResponseStoreDto();
            BeanUtils.copyProperties(store, responseStoreDto);
            return responseStoreDto;
        }
        return null;
    }

}
