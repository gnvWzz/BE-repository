package com.codegym.springboot_modul_6.model.FE_BO_Model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "productbo")
public class ProductBO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "`name`")
    private String name;
    @Column(name = "category")
    private String category;
    @Column(name = "serial_number")
    private String serialNumber;
    @Column(name = "size")
    private String size;
    @Column(name = "color")
    private String color;
    @Column(name = "price")
    private Double price;
    @Column(name = "brief_description")
    private String briefDescription;
    @Column(name = "full_description")
    private String fullDescription;
    @Column(name = "weight")
    private Double weight;
    @Column(name = "material")
    private String material;
    @Column(name = "`status`")
    private String status;
    @Column(name = "quantity")
    private Long quantity;
    @Column(name = "icon")
    private String icon;
    @OneToMany(mappedBy = "productBO", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ManufacturerProductBO> manufacturerProductBOS = new ArrayList<>();
}
