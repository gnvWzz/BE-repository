package com.codegym.springboot_modul_6.model.FE_SF_Model.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    private Double price;

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

    private String  size_color_img_quantity;
    private String status;

}
