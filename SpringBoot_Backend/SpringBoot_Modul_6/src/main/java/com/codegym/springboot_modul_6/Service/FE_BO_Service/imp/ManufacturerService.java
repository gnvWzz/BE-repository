package com.codegym.springboot_modul_6.Service.FE_BO_Service.imp;

import com.codegym.springboot_modul_6.Model.FE_BO_Model.Entity.Manufacturer;
import com.codegym.springboot_modul_6.Model.FE_BO_Model.dto.ManufacturerDto;
import com.codegym.springboot_modul_6.Repository.FE_BO_Repository.IManufacturerRepository;
import com.codegym.springboot_modul_6.Service.FE_BO_Service.IManufacturerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ManufacturerService implements IManufacturerService {
    @Autowired
    private IManufacturerRepository manufacturerRepository;
    @Autowired
    private ModelMapper mapper;
    @Override
    public Optional<ManufacturerDto> findById(Long id) {
        Manufacturer manufacturer = manufacturerRepository.findById(id).orElse(null);
        if(manufacturer != null) {
            return Optional.of(mapper.map(manufacturer, ManufacturerDto.class));
        }
        return null;
    }

    @Override
    public Page<ManufacturerDto> findAll(Pageable pageable) {
        Page<Manufacturer> entities = manufacturerRepository.findAll(pageable);
        List<ManufacturerDto> dtos = new ArrayList<>(
                entities.getContent().stream()
                        .parallel()
                        .map(entity -> mapper.map(entity, ManufacturerDto.class))
                        .collect(Collectors.toList()));
        return new PageImpl<>(dtos, pageable, entities.getTotalElements());
    }

    @Override
    public ManufacturerDto save(ManufacturerDto manufacturerDto) {
        try {
            Manufacturer manufacturer = mapper.map(manufacturerDto, Manufacturer.class);
            manufacturer.setStatus("ACTIVE"); //dto khong co truong status, khong the lay duoc gia tri mac dinh set trong database :(
            manufacturerRepository.save(manufacturer);
        } catch (Exception ex) {
            System.out.println("Loi:" + ex.getCause());
            throw new RuntimeException("Error while saving Manufacturer", ex);
        }
        return manufacturerDto;
    }

    @Override
    public boolean delete(Long id) {
        if (id != null){
            manufacturerRepository.deleteById(id);
            return  true;
        }
        return false;
    }
}
