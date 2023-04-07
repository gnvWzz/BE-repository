package com.codegym.springboot_modul_6.service.FE_BO_Service;

import com.codegym.springboot_modul_6.model.FE_BO_Model.dto.ManufacturerDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ManufacturerService {
    Optional<ManufacturerDto> findById(Long id);
    Page<ManufacturerDto> findAll(Pageable pageable);
    ManufacturerDto save(ManufacturerDto manufacturerDto);
    boolean block(Long id);

}
