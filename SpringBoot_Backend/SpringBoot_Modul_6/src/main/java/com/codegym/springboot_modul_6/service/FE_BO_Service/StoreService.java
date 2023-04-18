package com.codegym.springboot_modul_6.service.FE_BO_Service;

import com.codegym.springboot_modul_6.model.FE_BO_Model.dto.request.RequestStoreDto;
import com.codegym.springboot_modul_6.model.FE_BO_Model.dto.response.ResponseStoreDto;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;

public interface StoreService {
    Optional<ResponseStoreDto> findById(Long id);
    ResponseStoreDto updateImage(RequestStoreDto requestStoreDto);

    ResponseStoreDto updateName(RequestStoreDto requestStoreDto);
}
