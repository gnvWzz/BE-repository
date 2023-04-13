package com.codegym.springboot_modul_6.model.FE_SF_Model.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.json.simple.JSONObject;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "product_detail")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductSFDetail implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "product_id")
    @ManyToOne
    @JsonIgnore
    private ProductSF productSF;

    private Double price1;

    private Double price2;

    private Double price3;

    private Double price4;

    @Column(name = "serial_number")
    private String serialNumber;

    @Column(name = "brief_description")
    private String briefDescription;

    @Column(name = "full_description")
    private String fullDescription;

    private Double weight;

    private String material;

    private String cpu;

    private String gpu;

    private String ram;

    @Column(name = "storage_drive")
    private String storageDrive;

    private String display;

    private String size_color_img_quantity;

    public ProductSFDetail(ProductSF productSF, Double price1, Double price2, Double price3, Double price4, String serialNumber, String briefDescription, String fullDescription, Double weight, String material, String cpu, String gpu, String ram, String storageDrive, String display, String size_color_img_quantity) {
        this.productSF = productSF;
        this.price1 = price1;
        this.price2 = price2;
        this.price3 = price3;
        this.price4 = price4;
        this.serialNumber = serialNumber;
        this.briefDescription = briefDescription;
        this.fullDescription = fullDescription;
        this.weight = weight;
        this.material = material;
        this.cpu = cpu;
        this.gpu = gpu;
        this.ram = ram;
        this.storageDrive = storageDrive;
        this.display = display;
        this.size_color_img_quantity = size_color_img_quantity;
    }
}
