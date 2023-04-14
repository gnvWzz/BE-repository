package com.codegym.springboot_modul_6.model.FE_BO_Model.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "product")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductBO implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String category;

    @Column(name = "package_id")
    private String packageId;
    private String status;
    private String manufacturer;

    @OneToMany(mappedBy = "productBO", fetch = FetchType.LAZY)
    private List<ProductBODetail> productBODetail;
}





//@Table(name = "productbo")
//public class ProductBO {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    @Column(name = "`name`")
//    private String name;
//    @Column(name = "category")
//    private String category;
//    @Column(name = "serial_number")
//    private String serialNumber;
//    @Column(name = "size")
//    private String size;
//    @Column(name = "color")
//    private String color;
//    @Column(name = "price")
//    private Double price;
//    @Column(name = "brief_description")
//    private String briefDescription;
//    @Column(name = "full_description")
//    private String fullDescription;
//    @Column(name = "weight")
//    private Double weight;
//    @Column(name = "material")
//    private String material;
//    @Column(name = "`status`")
//    private String status;
//    @Column(name = "quantity")
//    private Long quantity;
//    @Column(name = "image")
//    private String image;
//    @Column(name = "size_color")
//    private String sizeColor;
//    @OneToMany(mappedBy = "productBO", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
//    private List<ManufacturerProductBO> manufacturerProductBOS = new ArrayList<>();
//}
