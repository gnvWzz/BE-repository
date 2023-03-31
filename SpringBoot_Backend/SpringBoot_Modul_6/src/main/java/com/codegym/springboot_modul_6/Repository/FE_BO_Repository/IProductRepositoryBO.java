package com.codegym.springboot_modul_6.Repository.FE_BO_Repository;

import com.codegym.springboot_modul_6.Model.FE_BO_Model.Entity.ProductBO;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepositoryBO extends PagingAndSortingRepository<ProductBO,Long> {

}
