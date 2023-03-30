package com.codegym.springboot_modul_6.Service.FE_BO_Service;

import com.codegym.springboot_modul_6.Model.FE_BO_Model.Entity.Manufacturer;
import com.codegym.springboot_modul_6.Model.FE_BO_Model.dto.ManufacturerDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IManufacturerService {
    Optional<ManufacturerDto> findById(Long id);
    Page<ManufacturerDto> findAll(Pageable pageable);
    ManufacturerDto save(ManufacturerDto manufacturerDto);
    boolean delete(Long id);

}
