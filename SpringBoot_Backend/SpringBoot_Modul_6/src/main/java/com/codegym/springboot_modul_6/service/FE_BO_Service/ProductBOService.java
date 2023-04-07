//package com.codegym.springboot_modul_6.service.FE_BO_Service;
//
//import com.codegym.springboot_modul_6.model.FE_BO_Model.dto.RequestProductBODto;
//import com.codegym.springboot_modul_6.model.FE_BO_Model.dto.ResponseProductBODto;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//
//import java.util.Optional;
//
//public interface ProductBOService {
//    Optional<ResponseProductBODto> findById(Long id);
//    Page<ResponseProductBODto> findAll(Pageable pageable);
//    RequestProductBODto save(RequestProductBODto productDtoBO);
//    boolean block(Long id);
//}
