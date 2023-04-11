package com.codegym.springboot_modul_6.service.FE_BO_Service.impl;

import com.codegym.springboot_modul_6.model.FE_BO_Model.dto.request.RequestManufacturerDto;
import com.codegym.springboot_modul_6.model.FE_BO_Model.dto.request.RequestMemberDto;
import com.codegym.springboot_modul_6.model.FE_BO_Model.dto.request.RequestMemberRoleDto;
import com.codegym.springboot_modul_6.model.FE_BO_Model.dto.response.ResponseManufacturerProductBODto;
import com.codegym.springboot_modul_6.model.FE_BO_Model.dto.response.ResponseManufacturerDto;
import com.codegym.springboot_modul_6.model.FE_BO_Model.dto.response.ResponseMemberDto;
import com.codegym.springboot_modul_6.model.FE_BO_Model.dto.response.ResponseMemberRoleDto;
import com.codegym.springboot_modul_6.model.FE_BO_Model.entity.*;
import com.codegym.springboot_modul_6.repository.FE_BO_Repository.ManufacturerRepository;
import com.codegym.springboot_modul_6.service.FE_BO_Service.ManufacturerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {
    @Autowired
    private ManufacturerRepository manufacturerRepository;

    @Override
    @Transactional
    public Optional<ResponseManufacturerDto> findById(Long id) {
        Manufacturer manufacturer = manufacturerRepository.findById(id).orElse(null);
        if(manufacturer != null) {
            ResponseManufacturerDto responseManufacturerDto = new ResponseManufacturerDto();
            BeanUtils.copyProperties(manufacturer, responseManufacturerDto);

            List<ManufacturerProductBO> manufacturerProductBOList = manufacturer.getManufacturerProductBOS();
            List<ResponseManufacturerProductBODto> responseManufacturerProductBODtoList = new ArrayList<>();
            for(ManufacturerProductBO ele: manufacturerProductBOList){
                ResponseManufacturerProductBODto responseManufacturerProductBODto = new ResponseManufacturerProductBODto();
                BeanUtils.copyProperties(ele, responseManufacturerProductBODto);

                Long manufacturerId = ele.getManufacturer().getId();
                String manufacturerName = ele.getManufacturer().getName();
                Long productBOId = ele.getProductBO().getId();
                String productBOName = ele.getProductBO().getName();
                responseManufacturerProductBODto.setManufacturerId(manufacturerId);
                responseManufacturerProductBODto.setManufacturerName(manufacturerName);
                responseManufacturerProductBODto.setProductBOId(productBOId);
                responseManufacturerProductBODto.setProductBOName(productBOName);

                responseManufacturerProductBODtoList.add(responseManufacturerProductBODto);
            }
            responseManufacturerDto.setResponseManufacturerProductBODtos(responseManufacturerProductBODtoList);
            return Optional.of(responseManufacturerDto);
        }
        return null;
    }

    @Override
    @Transactional
    public Page<ResponseManufacturerDto> findAll(Pageable pageable) {
        Page<Manufacturer> manufacturers = manufacturerRepository.findAll(pageable);
        List<ResponseManufacturerDto> dtoList = new ArrayList<>();
        for(Manufacturer element: manufacturers){
            ResponseManufacturerDto dto = new ResponseManufacturerDto();
            BeanUtils.copyProperties(element, dto);

            List<ManufacturerProductBO> manufacturerProductBOS = element.getManufacturerProductBOS();
            List<ResponseManufacturerProductBODto> responseManufacturerProductBODtoList = new ArrayList<>();
            for(ManufacturerProductBO ele: manufacturerProductBOS){
                ResponseManufacturerProductBODto responseManufacturerProductBODto = new ResponseManufacturerProductBODto();
                BeanUtils.copyProperties(ele, responseManufacturerProductBODto);

                responseManufacturerProductBODto.setManufacturerId(ele.getManufacturer().getId());
                responseManufacturerProductBODto.setManufacturerName(ele.getManufacturer().getName());
                responseManufacturerProductBODto.setProductBOId(ele.getProductBO().getId());
                responseManufacturerProductBODto.setProductBOName(ele.getProductBO().getName());

                responseManufacturerProductBODtoList.add(responseManufacturerProductBODto);
            }
            dto.setResponseManufacturerProductBODtos(responseManufacturerProductBODtoList);

            dtoList.add(dto);
        }
        return new PageImpl<>(dtoList, pageable, manufacturers.getTotalElements());
    }

//    @Override
//    public RequestManufacturerDto save(RequestManufacturerDto requestManufacturerDto) {
//        try {
//            Manufacturer manufacturer = new Manufacturer();
//            BeanUtils.copyProperties(requestManufacturerDto, manufacturer);
//            manufacturer.setStatus("UNLOCKED"); //dto khong co truong status, khong the lay duoc gia tri mac dinh set trong database :(
//            manufacturerRepository.save(manufacturer);
//        } catch (Exception ex) {
//            System.out.println("Loi:" + ex.getCause());
//            throw new RuntimeException("Error while saving Manufacturer", ex);
//        }
//        return requestManufacturerDto;
//    }
    @Override
    @Transactional
    public ResponseManufacturerDto save(RequestManufacturerDto requestManufacturerDto) {
        ResponseManufacturerDto responseManufacturerDto = new ResponseManufacturerDto();
        try {
            Manufacturer manufacturer = new Manufacturer();
            BeanUtils.copyProperties(requestManufacturerDto, manufacturer);
            manufacturer.setStatus("UNLOCKED"); //dto khong co truong status
            manufacturerRepository.save(manufacturer);

            BeanUtils.copyProperties(manufacturer, responseManufacturerDto);
            List<ManufacturerProductBO> manufacturerProductBOList = manufacturer.getManufacturerProductBOS();
            List<ResponseManufacturerProductBODto> responseManufacturerProductBODtoList = new ArrayList<>();
            for(ManufacturerProductBO ele: manufacturerProductBOList){
                ResponseManufacturerProductBODto responseManufacturerProductBODto = new ResponseManufacturerProductBODto();
                responseManufacturerProductBODto.setId(ele.getId());
                responseManufacturerProductBODto.setManufacturerId(ele.getManufacturer().getId());
                responseManufacturerProductBODto.setManufacturerName(ele.getManufacturer().getName());
                responseManufacturerProductBODto.setProductBOId(ele.getProductBO().getId());
                responseManufacturerProductBODto.setProductBOName(ele.getProductBO().getName());

                responseManufacturerProductBODtoList.add(responseManufacturerProductBODto);
            }
            responseManufacturerDto.setResponseManufacturerProductBODtos(responseManufacturerProductBODtoList);
        } catch (Exception ex) {
            System.out.println("Loi:" + ex.getCause());
            throw new RuntimeException("Error while saving Member", ex);
        }
        return responseManufacturerDto;
    }


    @Override
    public boolean block(Long id) {
        if (id != null) {
            Manufacturer manufacturer = manufacturerRepository.findById(id).orElse(null);
            if (manufacturer != null) {
                if (manufacturer.getStatus().equals("UNLOCKED")) {
                    manufacturer.setStatus("BLOCKED");
                } else {
                    manufacturer.setStatus("UNLOCKED");
                }
                manufacturerRepository.save(manufacturer);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean addImage(Long id, String imageUrl) {
        Manufacturer manufacturer = manufacturerRepository.findById(id).orElse(null);
        if (manufacturer != null) {
            manufacturer.setImage(imageUrl);
            manufacturerRepository.save(manufacturer);
            return true;
        }
        return false;
    }
}
