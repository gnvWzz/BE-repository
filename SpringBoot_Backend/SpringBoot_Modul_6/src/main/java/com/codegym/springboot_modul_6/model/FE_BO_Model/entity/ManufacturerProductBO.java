//package com.codegym.springboot_modul_6.model.FE_BO_Model.entity;
//
//import lombok.*;
//
//import javax.persistence.*;
//
//@Entity
//@NoArgsConstructor
//@AllArgsConstructor
//@Getter
//@Setter
//@Table(name = "manufacturer_product")
//public class ManufacturerProductBO {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "manufacturer_id")
//    private Manufacturer manufacturer;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "product_id")
//    private ProductBO productBO;
//}
