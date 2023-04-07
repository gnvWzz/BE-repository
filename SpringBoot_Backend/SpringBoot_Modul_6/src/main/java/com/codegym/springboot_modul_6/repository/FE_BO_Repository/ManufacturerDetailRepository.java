package com.codegym.springboot_modul_6.repository.FE_BO_Repository;

import com.codegym.springboot_modul_6.model.FE_BO_Model.entity.ManufacturerDetail;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManufacturerDetailRepository extends PagingAndSortingRepository<ManufacturerDetail,Long> {
}

