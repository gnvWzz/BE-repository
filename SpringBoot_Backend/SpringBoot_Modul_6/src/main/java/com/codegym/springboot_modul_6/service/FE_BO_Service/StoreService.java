package com.codegym.springboot_modul_6.service.FE_BO_Service;

import com.codegym.springboot_modul_6.model.FE_BO_Model.dto.response.ResponseProductBODto;
import com.codegym.springboot_modul_6.model.FE_BO_Model.dto.response.ResponseStoreDto;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface StoreService {
    Optional<ResponseStoreDto> findById(Long id);
}
