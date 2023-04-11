package com.codegym.springboot_modul_6.repository.FE_SF_Repository;

import com.codegym.springboot_modul_6.model.FE_SF_Model.Entity.CartSF;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICartRepository extends JpaRepository<CartSF, Long> {
}
