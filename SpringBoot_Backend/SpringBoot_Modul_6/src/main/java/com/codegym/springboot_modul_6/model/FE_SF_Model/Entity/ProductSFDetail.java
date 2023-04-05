package com.codegym.springboot_modul_6.model.FE_SF_Model.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "product_detail")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductSFDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "product_id")
    @ManyToOne
    private ProductSF productSF;

    private Long size;

    private Long price;

    @Column(name = "serial_number")
    private String serialNumber;

    @Column(name = "brief_description")
    private String briefDescription;

    @Column(name = "full_description")
    private String fullDescription;

    private Double weight;

    private String material;

    private String color;

    private String cpu;

    private String gpu;

    @Column(name = "storage_drive")
    private String storageDrive;

    private String display;

    private Long quantity;

    @OneToMany(mappedBy = "productSFDetail", fetch = FetchType.LAZY)
    private List<Image> imageList;

    public ProductSFDetail(ProductSF productSF, Long size, Long price, String serialNumber, String briefDescription, String fullDescription, Double weight, String material, String color, String cpu, String gpu, String storageDrive, String display, Long quantity) {
        this.productSF = productSF;
        this.size = size;
        this.price = price;
        this.serialNumber = serialNumber;
        this.briefDescription = briefDescription;
        this.fullDescription = fullDescription;
        this.weight = weight;
        this.material = material;
        this.color = color;
        this.cpu = cpu;
        this.gpu = gpu;
        this.storageDrive = storageDrive;
        this.display = display;
        this.quantity = quantity;
    }
}
