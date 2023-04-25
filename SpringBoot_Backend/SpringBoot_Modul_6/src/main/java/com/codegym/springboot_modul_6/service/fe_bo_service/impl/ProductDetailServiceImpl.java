package com.codegym.springboot_modul_6.service.fe_bo_service.impl;

import com.codegym.springboot_modul_6.model.fe_bo_model.dto.request.RequestProductDetailInfoDto;
import com.codegym.springboot_modul_6.model.fe_bo_model.dto.response.ResponseProductDetailDto;
import com.codegym.springboot_modul_6.model.fe_bo_model.dto.response.ResponseProductInfoDto;
import com.codegym.springboot_modul_6.model.fe_bo_model.dto.response.ResponseProductGeneralDto;
import com.codegym.springboot_modul_6.model.fe_sf_model.entity.PriceList;
import com.codegym.springboot_modul_6.model.fe_sf_model.entity.ProductSF;
import com.codegym.springboot_modul_6.model.fe_sf_model.entity.ProductSFDetail;
import com.codegym.springboot_modul_6.model.fe_sf_model.dto.PriceListDto;
import com.codegym.springboot_modul_6.repository.fe_sf_repository.IProductDetailSFRepository;
import com.codegym.springboot_modul_6.service.fe_bo_service.ProductDetailService;
import com.codegym.springboot_modul_6.service.fe_sf_service.impl.ProductServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductDetailServiceImpl implements ProductDetailService {
    @Autowired
    private IProductDetailSFRepository productDetailRepository;
    @Autowired
    private ProductServiceImpl productServiceImpl;

    @Override
    public Optional<ProductSFDetail> findBySerialNumber(String serialNumber) {
        return productDetailRepository.findBySerialNumber(serialNumber);
    }

    @Override
    public ResponseProductInfoDto findProductInfoBySerialNumber(String serialNumber) {
        ProductSFDetail productSFDetail = productDetailRepository.findBySerialNumber(serialNumber).orElse(null);
        if(productSFDetail != null) {
            ResponseProductInfoDto responseProductInfoDto = new ResponseProductInfoDto();
            BeanUtils.copyProperties(productSFDetail, responseProductInfoDto);

            Long productId = productSFDetail.getProductSF().getId();
            ProductSF productSF = productServiceImpl.findById(productId).orElse(null);
            if (productSF != null) {
                String productName = productSF.getName();
                responseProductInfoDto.setProductName(productName);
                String category = productSF.getCategory();
                responseProductInfoDto.setCategory(category);
                String manufacturer = productSF.getManufacturer();
                responseProductInfoDto.setManufacturer(manufacturer);
//                Double standardPrice = priceListService.findStandardPriceByProductId(productId);
//                responseProductInfoDto.setStandardPrice(standardPrice);

                List<PriceList> priceList = productSF.getPrices();
                List<PriceListDto> priceListDto = new ArrayList<>();
                for(PriceList price: priceList){
                    PriceListDto priceDto = new PriceListDto();
                    BeanUtils.copyProperties(price, priceDto);
                    priceListDto.add(priceDto);
                }
                responseProductInfoDto.setPriceListDtos(priceListDto);

                return responseProductInfoDto;
            }
        }
        return null;
    }

    @Override
    public ResponseProductGeneralDto findProductGeneralInfoBySerialNumber(String serialNumber) {
        ProductSFDetail productSFDetail = productDetailRepository.findBySerialNumber(serialNumber).orElse(null);
        if(productSFDetail != null) {
            ResponseProductGeneralDto responseProductGeneralDto = new ResponseProductGeneralDto();
            Long productId = productSFDetail.getProductSF().getId();
            ProductSF productSF = productServiceImpl.findById(productId).orElse(null);
            if (productSF != null) {
                BeanUtils.copyProperties(productSF, responseProductGeneralDto);
                return responseProductGeneralDto;
            }
        }
        return null;
    }

    @Override
    public Boolean removeBySerialNumber(String serialNumber) {
        ProductSFDetail productSFDetail = productDetailRepository.findBySerialNumber(serialNumber).orElse(null);
        if(productSFDetail != null){
            productDetailRepository.removeBySerialNumber(serialNumber);
            return true;
        }
        return false;
    }

    @Override
    public ResponseProductDetailDto findProductDetailInfoBySerialNumber(String serialNumber) {
        ProductSFDetail productSFDetail = productDetailRepository.findBySerialNumber(serialNumber).orElse(null);
        if(productSFDetail != null) {
            ResponseProductDetailDto responseProductDetailDto = new ResponseProductDetailDto();
            BeanUtils.copyProperties(productSFDetail, responseProductDetailDto);

            ProductSF productSF = productSFDetail.getProductSF();
            responseProductDetailDto.setCategory(productSF.getCategory());

            return responseProductDetailDto;
        }
        return null;
    }


    @Override
    public Boolean updateProductDetailInfo(RequestProductDetailInfoDto requestProductDetailInfoDto) {
        String curSerialNumber = requestProductDetailInfoDto.getCurSerialNumber();
        ProductSFDetail productDetail= productDetailRepository.getProductSFDetail(curSerialNumber).orElse(null);
        String newSerialNumber = requestProductDetailInfoDto.getNewSerialNumber();
        if(productDetail != null){
            if(curSerialNumber.equals(newSerialNumber)){
            } else {
                Optional<ProductSFDetail> obj = productDetailRepository.getProductSFDetail(newSerialNumber);
                if(obj.isPresent()){
                    return false;
                }
            }
            productDetail.setSerialNumber(newSerialNumber);
            productDetail.setBriefDescription(requestProductDetailInfoDto.getBriefDescription());
            productDetail.setFullDescription(requestProductDetailInfoDto.getFullDescription());
            productDetail.setWeight(requestProductDetailInfoDto.getWeight());
            productDetail.setMaterial(requestProductDetailInfoDto.getMaterial());
            productDetail.setCpu(requestProductDetailInfoDto.getCpu());
            productDetail.setGpu(requestProductDetailInfoDto.getGpu());
            productDetail.setRam(requestProductDetailInfoDto.getRam());
            productDetail.setStorageDrive(requestProductDetailInfoDto.getStorageDrive());
            productDetail.setDisplay(requestProductDetailInfoDto.getDisplay());
            productDetail.setSize_color_img_quantity(requestProductDetailInfoDto.getSize_color_img_quantity());
            productDetailRepository.save(productDetail);
            return true;
        }
        return false;
    }
}
