package com.codegym.springboot_modul_6.service.FE_BO_Service;

import com.codegym.springboot_modul_6.model.FE_BO_Model.dto.ManufacturerDetailDto;

public interface ManufacturerDetailService {
//    Optional<ManufacturerDetailDto> findById(Long id);
//    Page<ManufacturerDetailDto> findAll(Pageable pageable);
    ManufacturerDetailDto save(ManufacturerDetailDto manufacturerDetailDto);
//    boolean delete(Long id);
}
