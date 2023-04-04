package com.codegym.springboot_modul_6.Repository.FE_BO_Repository;

import com.codegym.springboot_modul_6.model.FE_BO_Model.Entity.Manufacturer;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IManufacturerRepository extends PagingAndSortingRepository<Manufacturer,Long> {
}
