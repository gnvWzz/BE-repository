package com.codegym.springboot_modul_6.service.FE_BO_Service.impl;

import com.codegym.springboot_modul_6.model.FE_BO_Model.dto.request.RequestManufacturerProductBODto;
import com.codegym.springboot_modul_6.model.FE_BO_Model.entity.ManufacturerProductBO;
import com.codegym.springboot_modul_6.repository.FE_BO_Repository.ManufacturerProductBORepository;
import com.codegym.springboot_modul_6.service.FE_BO_Service.ManufacturerProductBOService;
import com.codegym.springboot_modul_6.util.FE_BO_Util.Mapper.ManufacturerDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManufacturerProductBOServiceImpl implements ManufacturerProductBOService {
    @Autowired
    private ManufacturerProductBORepository manufacturerProductBORepository;

    @Autowired
    private ManufacturerDetailMapper manufacturerDetailMapper;

    @Override
    public RequestManufacturerProductBODto save(RequestManufacturerProductBODto requestManufacturerProductBODto) {
        try {
            ManufacturerProductBO manufacturerProductBO = manufacturerDetailMapper.toEntity(requestManufacturerProductBODto);
            manufacturerProductBORepository.save(manufacturerProductBO);
        } catch (Exception ex) {
            System.out.println("Loi:" + ex.getCause());
            throw new RuntimeException("Error while saving Manufacturer Detail", ex);
        }
        return requestManufacturerProductBODto;
    }
}
