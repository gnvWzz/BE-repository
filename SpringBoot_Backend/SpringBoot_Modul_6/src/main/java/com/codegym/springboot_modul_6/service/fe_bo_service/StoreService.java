package com.codegym.springboot_modul_6.service.fe_bo_service;

import com.codegym.springboot_modul_6.model.fe_bo_model.dto.request.RequestStoreDto;
import com.codegym.springboot_modul_6.model.fe_bo_model.dto.response.ResponseStoreDto;


import java.util.Optional;

public interface StoreService {
    Optional<ResponseStoreDto> findStoreByAccountUsername(String accountUsername);
    ResponseStoreDto updateImage(RequestStoreDto requestStoreDto);

    ResponseStoreDto updateName(RequestStoreDto requestStoreDto);
}
