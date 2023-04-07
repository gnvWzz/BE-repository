package com.codegym.springboot_modul_6.service.FE_BO_Service;

import com.codegym.springboot_modul_6.model.FE_BO_Model.dto.RequestManufacturerDetailDto;

public interface ManufacturerDetailService {
//    Optional<RequestManufacturerDetailDto> findById(Long id);
//    Page<RequestManufacturerDetailDto> findAll(Pageable pageable);
    RequestManufacturerDetailDto save(RequestManufacturerDetailDto requestManufacturerDetailDto);
//    boolean delete(Long id);

}
