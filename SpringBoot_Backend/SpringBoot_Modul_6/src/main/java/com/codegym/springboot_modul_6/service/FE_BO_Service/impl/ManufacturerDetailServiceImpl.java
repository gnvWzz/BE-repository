package com.codegym.springboot_modul_6.service.FE_BO_Service.impl;

import com.codegym.springboot_modul_6.model.FE_BO_Model.entity.ManufacturerDetail;
import com.codegym.springboot_modul_6.model.FE_BO_Model.dto.RequestManufacturerDetailDto;
import com.codegym.springboot_modul_6.repository.FE_BO_Repository.ManufacturerDetailRepository;
import com.codegym.springboot_modul_6.service.FE_BO_Service.ManufacturerDetailService;
import com.codegym.springboot_modul_6.util.FE_BO_Util.Mapper.ManufacturerDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManufacturerDetailServiceImpl implements ManufacturerDetailService {
    @Autowired
    private ManufacturerDetailRepository manufacturerDetailRepository;

    @Autowired
    private ManufacturerDetailMapper manufacturerDetailMapper;

    @Override
    public RequestManufacturerDetailDto save(RequestManufacturerDetailDto requestManufacturerDetailDto) {
        try {
            ManufacturerDetail manufacturerDetail = manufacturerDetailMapper.toEntity(requestManufacturerDetailDto);
            manufacturerDetailRepository.save(manufacturerDetail);
        } catch (Exception ex) {
            System.out.println("Loi:" + ex.getCause());
            throw new RuntimeException("Error while saving Manufacturer Detail", ex);
        }
        return requestManufacturerDetailDto;
    }
}

