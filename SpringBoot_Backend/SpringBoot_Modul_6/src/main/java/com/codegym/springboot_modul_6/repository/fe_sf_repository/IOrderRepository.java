package com.codegym.springboot_modul_6.repository.fe_sf_repository;

import com.codegym.springboot_modul_6.model.fe_sf_model.entity.OrderSF;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IOrderRepository extends JpaRepository<OrderSF, Long> {

    List<OrderSF> getAllByAccount_Id(Long accountId);

   Optional<OrderSF>  findByOrderCode(String orderCode);



//    @Query(value = "select u from OrderSF u where u.username = ?1")
//    Optional<OrderSF> getOrderByName(String username);
}
