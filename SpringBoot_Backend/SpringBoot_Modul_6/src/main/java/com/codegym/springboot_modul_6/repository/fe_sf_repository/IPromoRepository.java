package com.codegym.springboot_modul_6.repository.fe_sf_repository;

import com.codegym.springboot_modul_6.model.fe_sf_model.entity.Promos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPromoRepository extends JpaRepository<Promos, Long> {

}
