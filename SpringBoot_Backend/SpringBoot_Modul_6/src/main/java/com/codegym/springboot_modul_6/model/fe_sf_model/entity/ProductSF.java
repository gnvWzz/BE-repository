package com.codegym.springboot_modul_6.model.fe_sf_model.entity;

import com.codegym.springboot_modul_6.model.fe_bo_model.entity.Store;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductSF implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String category;

    private String status;

    private String manufacturer;

    @OneToMany(mappedBy = "productSF", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
    private List<ProductSFDetail> productSFDetail;

    @OneToMany(mappedBy = "productSF", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
    private List<Price> prices;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

}
