//package com.codegym.springboot_modul_6.util.FE_BO_Util.Mapper;
//
//import com.codegym.springboot_modul_6.model.FE_BO_Model.entity.Manufacturer;
//import com.codegym.springboot_modul_6.model.FE_BO_Model.entity.ManufacturerDetail;
//import com.codegym.springboot_modul_6.model.FE_BO_Model.entity.ProductBO;
//import com.codegym.springboot_modul_6.model.FE_BO_Model.dto.ManufacturerDetailDto;
//import com.codegym.springboot_modul_6.repository.FE_BO_Repository.ManufacturerRepository;
//import com.codegym.springboot_modul_6.repository.FE_BO_Repository.ProductBORepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.util.Optional;
//
//@Component
//public class ManufacturerDetailMapper {
//
//    @Autowired
//    private ProductBORepository productBORepository;
//    @Autowired
//    private ManufacturerRepository manufacturerRepository;
//
////    Nếu xuất hiện lỗi lặp vòng do tiêm Beans dùng constructor thay cho field
////    public ManufacturerDetailMapper() {
////    }
//
////    public ManufacturerDetailMapper(ProductBORepository productRepositoryBO, ManufacturerRepository manufacturerRepository) {
////        this.productRepositoryBO = productRepositoryBO;
////        this.manufacturerRepository = manufacturerRepository;
////    }
//
//    public ManufacturerDetail toEntity(ManufacturerDetailDto dto){
//        ManufacturerDetail result = new ManufacturerDetail();
//        Optional<Manufacturer> manufacturer = manufacturerRepository.findById(dto.getManufacturerId());
//        Optional<ProductBO> productBO = productBORepository.findById(dto.getProductBOId());
//        result.setManufacturer(manufacturer.get());
//        result.setProductBO(productBO.get());
//        return result;
//    }
//}
