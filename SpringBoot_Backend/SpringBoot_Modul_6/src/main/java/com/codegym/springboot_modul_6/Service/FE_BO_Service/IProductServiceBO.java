package com.codegym.springboot_modul_6.Service.FE_BO_Service;

import com.codegym.springboot_modul_6.model.FE_BO_Model.dto.ProductDtoBO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IProductServiceBO {
    Optional<ProductDtoBO> findById(Long id);
    Page<ProductDtoBO> findAll(Pageable pageable);
    ProductDtoBO save(ProductDtoBO productDtoBO);
    boolean delete(Long id);
}
